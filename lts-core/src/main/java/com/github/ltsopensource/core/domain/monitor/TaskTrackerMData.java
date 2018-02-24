package com.github.ltsopensource.core.domain.monitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskTrackerMData extends MData {

	private Long exeSuccessNum;

	private Long exeFailedNum;

	private Long exeLaterNum;

	private Long exeExceptionNum;

	private Long totalRunningTime;
}
