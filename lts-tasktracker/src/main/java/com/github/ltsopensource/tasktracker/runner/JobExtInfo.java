package com.github.ltsopensource.tasktracker.runner;

import com.github.ltsopensource.core.domain.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JobExtInfo {

	/**
	 * 已经重试的次数 (用户不要设置)
	 */
	private int retryTimes = 0;

	/**
	 * 已经重复的次数, (用户不要设置)
	 */
	private int repeatedCount = 0;
	/**
	 * 是否是重试
	 */
	private boolean retry;
	/**
	 * 任务类型
	 */
	private JobType jobType;
	/**
	 * 执行的时序 (每个执行周期都不一样，但是修复死任务，重试等不会改变)
	 */
	private String seqId;
}
