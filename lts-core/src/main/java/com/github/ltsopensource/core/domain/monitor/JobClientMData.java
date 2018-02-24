package com.github.ltsopensource.core.domain.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JobClientMData extends MData {

	/**
	 * 提交成功的个数
	 */
	private Long submitSuccessNum;

	/**
	 * 提交失败的个数
	 */
	private Long submitFailedNum;

	/**
	 * 存储FailStore的个数
	 */
	private Long failStoreNum;

	/**
	 * 提交FailStore的个数
	 */
	private Long submitFailStoreNum;

	/**
	 * 处理的反馈的个数
	 */
	private Long handleFeedbackNum;
}
