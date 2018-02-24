package com.github.ltsopensource.core.monitor;

/**
 * 节点状态监控
 */
public interface MStatReporter {

	/**
	 * 启动
	 */
	void start();

	/**
	 * 停止
	 */
	void stop();

}
