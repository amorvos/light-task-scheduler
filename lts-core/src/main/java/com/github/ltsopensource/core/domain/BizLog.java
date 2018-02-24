package com.github.ltsopensource.core.domain;

import java.io.Serializable;

import com.github.ltsopensource.core.constant.Level;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BizLog implements Serializable {

	private static final long serialVersionUID = -7770486329649514754L;

	private String taskId;

	private JobType jobType;

	private String jobId;

	private String realTaskId;

	private String msg;

	private Level level;

	private Long logTime;

	private String taskTrackerIdentity;

	private String taskTrackerNodeGroup;

}
