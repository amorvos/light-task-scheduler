package com.github.ltsopensource.core.domain.monitor;

import com.github.ltsopensource.core.cluster.NodeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MNode {

	/**
	 * 节点类型
	 */
	private NodeType nodeType;

	/**
	 * NodeGroup
	 */
	private String nodeGroup;

	/**
	 * TaskTracker 节点标识
	 */
	private String identity;
}
