package com.github.ltsopensource.core.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 8/19/14. TaskTracker 任务执行结果
 */
@Setter
@Getter
@ToString
public class JobRunResult implements Serializable {

	private static final long serialVersionUID = 8622758290605000897L;

	private JobMeta jobMeta;

	private Action action;

	private String msg;

	// 任务完成时间
	private Long time;

}
