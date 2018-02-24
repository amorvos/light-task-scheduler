package com.github.ltsopensource.admin.access.domain;

import java.util.Date;

import com.github.ltsopensource.core.cluster.NodeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 节点的上下线情况日志
 *
 * @author Robert HG (254963746@qq.com) on 9/26/15.
 */
@Setter
@Getter
@ToString
public class NodeOnOfflineLog {

	/**
	 * 日志时间
	 */
	private Date logTime;

	/**
	 * 取值 ONLINE(上线) OFFLINE(离线)
	 */
	private String event;

	/**
	 * 下面属性来自 {@link com.github.ltsopensource.core.cluster.Node}
	 */
	private String clusterName;

	private String ip;

	private Integer port;

	private String hostName;

	private String group;

	private Long createTime;

	private Integer threads;

	private String identity;

	private NodeType nodeType;

	private Integer httpCmdPort;
}
