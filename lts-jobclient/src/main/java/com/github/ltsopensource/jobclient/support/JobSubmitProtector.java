package com.github.ltsopensource.jobclient.support;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.ltsopensource.core.constant.Constants;
import com.github.ltsopensource.core.constant.ExtConfig;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.core.exception.JobSubmitException;
import com.github.ltsopensource.jobclient.domain.JobClientAppContext;
import com.github.ltsopensource.jobclient.domain.Response;
import com.google.common.util.concurrent.RateLimiter;

/**
 * 用来处理客户端请求过载问题
 */
public class JobSubmitProtector {

	private RateLimiter rateLimiter;

	private int acquireTimeout;

	private String errorMsg;

	private int maxQPS;

	public JobSubmitProtector(JobClientAppContext appContext) {

		this.maxQPS = appContext.getConfig().getParameter(ExtConfig.JOB_SUBMIT_MAX_QPS,
				Constants.DEFAULT_JOB_SUBMIT_MAX_QPS);
		if (this.maxQPS < 10) {
			this.maxQPS = Constants.DEFAULT_JOB_SUBMIT_MAX_QPS;
		}

		this.errorMsg = "the maxQPS is " + maxQPS + " , submit too fast , use " + ExtConfig.JOB_SUBMIT_MAX_QPS
				+ " can change the concurrent size .";
		this.acquireTimeout = appContext.getConfig().getParameter(ExtConfig.JOB_SUBMIT_LOCK_ACQUIRE_TIMEOUT, 100);

		this.rateLimiter = RateLimiter.create(this.maxQPS);
	}

	public Response execute(final List<Job> jobs, final JobSubmitExecutor<Response> jobSubmitExecutor)
			throws JobSubmitException {
		if (!rateLimiter.tryAcquire(acquireTimeout, TimeUnit.MILLISECONDS)) {
			throw new JobSubmitProtectException(maxQPS, errorMsg);
		}
		return jobSubmitExecutor.execute(jobs);
	}

	public int getMaxQPS() {
		return maxQPS;
	}
}
