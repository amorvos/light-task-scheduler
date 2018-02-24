package com.github.ltsopensource.core.cluster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.constant.Constants;
import com.github.ltsopensource.core.constant.ExtConfig;
import com.github.ltsopensource.core.factory.NamedThreadFactory;
import com.github.ltsopensource.core.remoting.HeartBeatMonitor;
import com.github.ltsopensource.core.remoting.RemotingClientDelegate;
import com.github.ltsopensource.core.spi.ServiceLoader;
import com.github.ltsopensource.remoting.RemotingClient;
import com.github.ltsopensource.remoting.RemotingClientConfig;
import com.github.ltsopensource.remoting.RemotingProcessor;
import com.github.ltsopensource.remoting.RemotingTransporter;
import com.google.common.collect.Queues;

/**
 * 抽象客户端
 */
public abstract class AbstractClientNode<T extends Node, Context extends AppContext>
		extends AbstractJobNode<T, Context> {

	protected RemotingClientDelegate remotingClient;

	private HeartBeatMonitor heartBeatMonitor;

	@Override
	protected void beforeRemotingStart() {
		this.remotingClient = new RemotingClientDelegate(getRemotingClient(new RemotingClientConfig()), appContext);
		this.heartBeatMonitor = new HeartBeatMonitor(remotingClient, appContext);
		beforeStart();
	}

	@Override
	protected void remotingStart() {
		remotingClient.start();
		heartBeatMonitor.start();

		RemotingProcessor defaultProcessor = getDefaultProcessor();
		if (defaultProcessor != null) {
			int processorSize = config.getParameter(ExtConfig.PROCESSOR_THREAD, Constants.DEFAULT_PROCESSOR_THREAD);
			ThreadFactory namedThreadFactory = new NamedThreadFactory(AbstractClientNode.class.getSimpleName(), true);
			ExecutorService pool = new ThreadPoolExecutor(processorSize, processorSize, 0L, TimeUnit.MILLISECONDS,
					Queues.newLinkedBlockingQueue(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
			remotingClient.registerDefaultProcessor(defaultProcessor, pool);
		}
	}

	/**
	 * 得到默认的处理器
	 */
	protected abstract RemotingProcessor getDefaultProcessor();

	@Override
	protected void afterRemotingStart() {
		afterStart();
	}

	@Override
	protected void beforeRemotingStop() {
		beforeStop();
	}

	@Override
	protected void remotingStop() {
		heartBeatMonitor.stop();
		remotingClient.shutdown();
	}

	@Override
	protected void afterRemotingStop() {
		afterStop();
	}

	/**
	 * 设置节点组名
	 */
	public void setNodeGroup(String nodeGroup) {
		config.setNodeGroup(nodeGroup);
	}

	public boolean isServerEnable() {
		return remotingClient.isServerEnable();
	}

	private RemotingClient getRemotingClient(RemotingClientConfig remotingClientConfig) {
		return ServiceLoader.load(RemotingTransporter.class, config).getRemotingClient(appContext,
				remotingClientConfig);
	}

	protected abstract void beforeStart();

	protected abstract void afterStart();

	protected abstract void afterStop();

	protected abstract void beforeStop();

}
