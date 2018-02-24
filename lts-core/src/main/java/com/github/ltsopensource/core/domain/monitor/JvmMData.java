package com.github.ltsopensource.core.domain.monitor;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JvmMData {

	private Map<String, Object> memoryMap;

	private Map<String, Object> gcMap;

	private Map<String, Object> threadMap;
}
