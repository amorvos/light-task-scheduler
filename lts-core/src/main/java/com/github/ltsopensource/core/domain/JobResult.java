package com.github.ltsopensource.core.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 6/13/15. 发送给客户端的 任务执行结果
 */
@Setter
@Getter
@ToString
public class JobResult implements Serializable {

	private static final long serialVersionUID = -6542469058048149122L;

	private Job job;

	private String msg;

	private boolean success;

	/**
	 * 任务完成时间
	 */
	private Long time;

	/**
	 * 执行的时序 (每个执行周期都不一样，但是修复死任务，重试等不会改变)
	 */
	private String exeSeqId;
}
