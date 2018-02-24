package com.github.ltsopensource.core;

import com.github.ltsopensource.cmd.HttpCmdServer;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.cluster.MasterElector;
import com.github.ltsopensource.core.cluster.SubscribedNodeManager;
import com.github.ltsopensource.core.monitor.MStatReporter;
import com.github.ltsopensource.core.protocol.command.CommandBodyWrapper;
import com.github.ltsopensource.core.registry.RegistryStatMonitor;
import com.github.ltsopensource.ec.EventCenter;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Robert HG (254963746@qq.com) on 8/17/14. 用来存储 程序的数据
 */
@Setter
@Getter
public abstract class AppContext {

	/**
	 * 节点配置信息
	 */
	private Config config;

	/**
	 * 节点管理
	 */
	private SubscribedNodeManager subscribedNodeManager;

	/**
	 * master节点选举者
	 */
	private MasterElector masterElector;

	/**
	 * 节点通信CommandBody包装器
	 */
	private CommandBodyWrapper commandBodyWrapper;

	/**
	 * 事件中心
	 */
	private EventCenter eventCenter;

	/**
	 * 监控中心
	 */
	private MStatReporter mStatReporter;

	/**
	 * 注册中心状态监控
	 */
	private RegistryStatMonitor registryStatMonitor;

	/**
	 * 命令中心
	 */
	private HttpCmdServer httpCmdServer;
}
