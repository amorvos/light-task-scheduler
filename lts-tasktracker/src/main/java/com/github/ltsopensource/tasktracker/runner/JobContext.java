package com.github.ltsopensource.tasktracker.runner;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.logger.BizLogger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JobContext {

	/**
	 * 用户提交的job
	 */
	private Job job;

	/**
	 * 额外的一些信息
	 */
	private JobExtInfo jobExtInfo;

	/**
	 * 业务日志
	 */
	private BizLogger bizLogger;
}
