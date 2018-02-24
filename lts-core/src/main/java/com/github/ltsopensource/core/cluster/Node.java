package com.github.ltsopensource.core.cluster;

import java.util.ArrayList;
import java.util.List;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.core.registry.NodeRegistryUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 6/22/14. 节点
 */
@Setter
@Getter
@ToString
public class Node {

	/**
	 * 是否可用
	 */
	private boolean available = true;

	private String clusterName;

	private NodeType nodeType;

	private String ip;

	private Integer port = 0;

	private String hostName;

	private String group;

	private Long createTime;

	/**
	 * 线程个数
	 */
	private Integer threads;

	/**
	 * 唯一标识
	 */
	private String identity;

	/**
	 * 命令端口
	 */
	private Integer httpCmdPort;

	/**
	 * 监听的节点类型
	 */
	private List<NodeType> listenNodeTypes;

	private String fullString;

	private Job job;

	public String getAddress() {
		return ip + ":" + port;
	}

	public void addListenNodeType(NodeType nodeType) {
		if (this.listenNodeTypes == null) {
			this.listenNodeTypes = new ArrayList<NodeType>();
		}
		this.listenNodeTypes.add(nodeType);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Node node = (Node) o;
		return !(identity != null ? !identity.equals(node.identity) : node.identity != null);

	}

	@Override
	public int hashCode() {
		return identity != null ? identity.hashCode() : 0;
	}

	public String toFullString() {
		if (fullString == null) {
			fullString = NodeRegistryUtils.getFullPath(this);
		}
		return fullString;
	}
}
