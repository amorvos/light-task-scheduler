package com.github.ltsopensource.jobtracker.domain;

import com.github.ltsopensource.biz.logger.JobLogger;
import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.remoting.RemotingServerDelegate;
import com.github.ltsopensource.jobtracker.channel.ChannelManager;
import com.github.ltsopensource.jobtracker.sender.JobSender;
import com.github.ltsopensource.jobtracker.support.JobReceiver;
import com.github.ltsopensource.jobtracker.support.NonRelyOnPrevCycleJobScheduler;
import com.github.ltsopensource.jobtracker.support.OldDataHandler;
import com.github.ltsopensource.jobtracker.support.checker.ExecutableDeadJobChecker;
import com.github.ltsopensource.jobtracker.support.checker.ExecutingDeadJobChecker;
import com.github.ltsopensource.jobtracker.support.checker.FeedbackJobSendChecker;
import com.github.ltsopensource.jobtracker.support.cluster.JobClientManager;
import com.github.ltsopensource.jobtracker.support.cluster.TaskTrackerManager;
import com.github.ltsopensource.queue.CronJobQueue;
import com.github.ltsopensource.queue.ExecutableJobQueue;
import com.github.ltsopensource.queue.ExecutingJobQueue;
import com.github.ltsopensource.queue.JobFeedbackQueue;
import com.github.ltsopensource.queue.NodeGroupStore;
import com.github.ltsopensource.queue.PreLoader;
import com.github.ltsopensource.queue.RepeatJobQueue;
import com.github.ltsopensource.queue.SuspendJobQueue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * JobTracker Application
 *
 * @author Robert HG (254963746@qq.com) on 3/30/15.
 */
@Setter
@Getter
@ToString
public class JobTrackerAppContext extends AppContext {

	private RemotingServerDelegate remotingServer;
	// channel manager
	private ChannelManager channelManager;
	// JobClient manager for job tracker
	private JobClientManager jobClientManager;
	// TaskTracker manager for job tracker
	private TaskTrackerManager taskTrackerManager;
	// dead job checker
	private ExecutingDeadJobChecker executingDeadJobChecker;
	private FeedbackJobSendChecker feedbackJobSendChecker;
	private ExecutableDeadJobChecker executableDeadJobChecker;

	// old data handler, dirty data
	private OldDataHandler oldDataHandler;
	// biz logger
	private JobLogger jobLogger;

	// executable job queue（waiting for exec）
	private ExecutableJobQueue executableJobQueue;
	// executing job queue
	private ExecutingJobQueue executingJobQueue;
	// store the connected node groups
	private NodeGroupStore nodeGroupStore;

	// Cron Job queue
	private CronJobQueue cronJobQueue;
	// feedback queue
	private JobFeedbackQueue jobFeedbackQueue;
	private SuspendJobQueue suspendJobQueue;
	private RepeatJobQueue repeatJobQueue;
	private PreLoader preLoader;
	private JobReceiver jobReceiver;
	private JobSender jobSender;

	private NonRelyOnPrevCycleJobScheduler nonRelyOnPrevCycleJobScheduler;
}
