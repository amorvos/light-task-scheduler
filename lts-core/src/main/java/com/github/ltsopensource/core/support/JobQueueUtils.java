package com.github.ltsopensource.core.support;

/**
 * @author Robert HG (254963746@qq.com) on 5/28/15.
 */
public class JobQueueUtils {

	private JobQueueUtils() {
	}

	/**
	 * 在数据库中就是表名, taskTrackerNodeGroup 是 TaskTracker的 nodeGroup
	 */
	public static String getExecutableQueueName(String taskTrackerNodeGroup) {
		return LTS_EXECUTING_JOB_QUEUE;
	}

	/**
	 * 在数据库中就是表名, jobClientNodeGroup 是 JobClient 的 nodeGroup
	 */
	public static String getFeedbackQueueName(String jobClientNodeGroup) {
		return LTS_JOB_FEEDBACK_QUEUE;
	}

	public static final String CRON_JOB_QUEUE = "lts_cron_job_queue";

	public static final String REPEAT_JOB_QUEUE = "lts_repeat_job_queue";

	public static final String EXECUTING_JOB_QUEUE = "lts_executing_job_queue";

	public static final String NODE_GROUP_STORE = "lts_node_group_store";

	public static final String SUSPEND_JOB_QUEUE = "lts_suspend_job_queue";

	public static final String LTS_EXECUTING_JOB_QUEUE = "lts_executing_job_queue";

	public static final String LTS_JOB_FEEDBACK_QUEUE = "lts_job_feedback_queue";
}
