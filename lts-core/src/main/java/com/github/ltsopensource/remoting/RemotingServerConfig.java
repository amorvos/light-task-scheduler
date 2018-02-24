package com.github.ltsopensource.remoting;

import com.github.ltsopensource.core.constant.Constants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 服务端配置
 */
@Setter
@Getter
@ToString
public class RemotingServerConfig {

    private int listenPort = 8888;

    private int serverWorkerThreads = 32;

    private int serverCallbackExecutorThreads = Constants.AVAILABLE_PROCESSOR * 2;

    private int serverSelectorThreads = Constants.AVAILABLE_PROCESSOR * 2;

    private int serverOnewaySemaphoreValue = 32;

    private int serverAsyncSemaphoreValue = 64;

    private int readerIdleTimeSeconds = 0;

    private int writerIdleTimeSeconds = 0;

    private int serverChannelMaxIdleTimeSeconds = 120;
}
