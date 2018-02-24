package com.github.ltsopensource.core.domain;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JobMeta implements Serializable {

	private static final long serialVersionUID = 1476984243004969158L;

	private Job job;

	private String jobId;

	private int retryTimes;

	private String realTaskId;

	private JobType jobType;

	private Integer repeatedCount;

	private Map<String, String> internalExtParams;

	public String getInternalExtParam(String key) {
		if (internalExtParams == null) {
			return null;
		}
		return internalExtParams.get(key);
	}

	public void setInternalExtParam(String key, String value) {
		if (internalExtParams == null) {
			internalExtParams = Maps.newHashMap();
		}
		internalExtParams.put(key, value);
	}

}
