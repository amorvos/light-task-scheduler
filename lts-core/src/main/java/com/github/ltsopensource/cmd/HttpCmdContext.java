package com.github.ltsopensource.cmd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.github.ltsopensource.core.commons.utils.Assert;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * @author Robert HG (254963746@qq.com)  on 2/17/16.
 */
public class HttpCmdContext {

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 节点标识 -> (cmd -> HttpCmdProc)
     */
	private final Map<String, Map<String, HttpCmdProc>> NODE_PROCESSOR_MAP = Maps.newHashMap();

    public void addCmdProcessor(HttpCmdProc proc) {
		Preconditions.checkNotNull(proc, "proc can not be null");

        String identity = proc.nodeIdentity();
        Assert.hasText(identity, "nodeIdentity can't be empty");

        String command = proc.getCommand();
        Assert.hasText(command, "command can't be empty");

        Map<String, HttpCmdProc> cmdProcessorMap = NODE_PROCESSOR_MAP.get(identity);
        if (cmdProcessorMap == null) {
            lock.lock();
            cmdProcessorMap = NODE_PROCESSOR_MAP.get(identity);
            if (cmdProcessorMap == null) {
                cmdProcessorMap = Maps.newConcurrentMap();
                NODE_PROCESSOR_MAP.put(identity, cmdProcessorMap);
            }
            lock.unlock();
        }
        cmdProcessorMap.put(command, proc);
    }

    public HttpCmdProc getCmdProcessor(String nodeIdentity, String command) {
        Assert.hasText(nodeIdentity, "nodeIdentity can't be empty");

        Map<String, HttpCmdProc> cmdProcessorMap = NODE_PROCESSOR_MAP.get(nodeIdentity);
        if (cmdProcessorMap == null) {
            return null;
        }
        return cmdProcessorMap.get(command);
    }

}
