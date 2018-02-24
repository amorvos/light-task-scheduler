package com.github.ltsopensource.remoting;

import com.github.ltsopensource.core.constant.Constants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客户端配置类
 */
@Setter
@Getter
@ToString
public class RemotingClientConfig {

	// 处理Server Response/Request
	private int clientWorkerThreads = 4;

	private int clientCallbackExecutorThreads = Constants.AVAILABLE_PROCESSOR * 2;

	private int clientSelectorThreads = 1;

	private int clientOnewaySemaphoreValue = 256;

	private int clientAsyncSemaphoreValue = 128;

	private long connectTimeoutMillis = 3000;

	// channel超过1分钟不被访问 就关闭
	private long channelNotActiveInterval = 1000 * 60;

	private int readerIdleTimeSeconds = 0;

	private int writerIdleTimeSeconds = 0;

	private int clientChannelMaxIdleTimeSeconds = 120;
}
