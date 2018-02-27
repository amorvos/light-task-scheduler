package com.github.ltsopensource.tasktracker.processor;

import com.github.ltsopensource.remoting.RemotingProcessor;
import com.github.ltsopensource.tasktracker.domain.TaskTrackerAppContext;

public abstract class AbstractProcessor implements RemotingProcessor {

	protected TaskTrackerAppContext appContext;

	protected AbstractProcessor(TaskTrackerAppContext appContext) {
		this.appContext = appContext;
	}
}
