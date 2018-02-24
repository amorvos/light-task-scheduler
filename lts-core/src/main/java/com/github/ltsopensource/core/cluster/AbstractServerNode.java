package com.github.ltsopensource.core.cluster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.constant.Constants;
import com.github.ltsopensource.core.constant.ExtConfig;
import com.github.ltsopensource.core.factory.NamedThreadFactory;
import com.github.ltsopensource.core.remoting.RemotingServerDelegate;
import com.github.ltsopensource.core.spi.ServiceLoader;
import com.github.ltsopensource.remoting.RemotingProcessor;
import com.github.ltsopensource.remoting.RemotingServer;
import com.github.ltsopensource.remoting.RemotingServerConfig;
import com.github.ltsopensource.remoting.RemotingTransporter;
import com.google.common.collect.Queues;

/**
 * 抽象服务端
 */
public abstract class AbstractServerNode<T extends Node, App extends AppContext> extends AbstractJobNode<T, App> {

	protected RemotingServerDelegate remotingServer;

	@Override
	protected void beforeRemotingStart() {
		RemotingServerConfig remotingServerConfig = new RemotingServerConfig();
		// config 配置
		if (config.getListenPort() == 0) {
			config.setListenPort(Constants.JOB_TRACKER_DEFAULT_LISTEN_PORT);
			node.setPort(config.getListenPort());
		}
		remotingServerConfig.setListenPort(config.getListenPort());
		remotingServer = new RemotingServerDelegate(getRemotingServer(remotingServerConfig), appContext);
		beforeStart();
	}

	private RemotingServer getRemotingServer(RemotingServerConfig remotingServerConfig) {
		return ServiceLoader.load(RemotingTransporter.class, config).getRemotingServer(appContext,
				remotingServerConfig);
	}

	@Override
	protected void remotingStart() {
	    // 开启Netty服务,监听相关端口
		remotingServer.start();

		RemotingProcessor defaultProcessor = getDefaultProcessor();
		if (defaultProcessor != null) {
			int processorSize = config.getParameter(ExtConfig.PROCESSOR_THREAD, Constants.DEFAULT_PROCESSOR_THREAD);
			ThreadFactory namedThreadFactory = new NamedThreadFactory(AbstractServerNode.class.getSimpleName(), true);
			ExecutorService pool = new ThreadPoolExecutor(processorSize, processorSize, 0L, TimeUnit.MILLISECONDS,
					Queues.newLinkedBlockingQueue(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
			remotingServer.registerDefaultProcessor(defaultProcessor, pool);
		}
	}

	@Override
	protected void remotingStop() {
		remotingServer.shutdown();
	}

	@Override
	protected void afterRemotingStart() {
		afterStart();
	}

	@Override
	protected void beforeRemotingStop() {
		beforeStop();
	}

	@Override
	protected void afterRemotingStop() {
		afterStop();
	}

	protected abstract RemotingProcessor getDefaultProcessor();

	protected abstract void beforeStart();

	protected abstract void afterStart();

	protected abstract void afterStop();

	protected abstract void beforeStop();

    public void setListenPort(int listenPort) {
        config.setListenPort(listenPort);
    }

}
