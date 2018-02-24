package com.github.ltsopensource.nio.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 2/15/16.
 */
@Setter
@Getter
@ToString
public class NioConfig {

	private Integer receiveBufferSize;

	private Boolean reuseAddress;

	private Integer backlog;

	private Boolean tcpNoDelay;

	private Boolean keepAlive;

	private Integer ipTos;

	private Boolean oobInline;

	private Integer soLinger;

	private int idleTimeBoth;

	private int idleTimeWrite;

	private int idleTimeRead;

	private int writeTimeoutInMillis;
}
