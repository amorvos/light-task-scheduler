package com.github.ltsopensource.tasktracker.domain;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.constant.Level;
import com.github.ltsopensource.core.remoting.RemotingClientDelegate;
import com.github.ltsopensource.tasktracker.monitor.StopWorkingMonitor;
import com.github.ltsopensource.tasktracker.runner.RunnerFactory;
import com.github.ltsopensource.tasktracker.runner.RunnerPool;
import com.github.ltsopensource.tasktracker.support.JobPullMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 3/30/15.
 */
@Setter
@Getter
@ToString
public class TaskTrackerAppContext extends AppContext {

	private RemotingClientDelegate remotingClient;
	// runner 线程池
	private RunnerPool runnerPool;
	//
	private RunnerFactory runnerFactory;
	// Pull Job Machine
	private JobPullMachine jobPullMachine;

	private StopWorkingMonitor stopWorkingMonitor;
	/**
	 * 业务日志记录级别
	 */
	private Level bizLogLevel;
	/**
	 * 执行任务的class
	 */
	private Class<?> jobRunnerClass;
}
