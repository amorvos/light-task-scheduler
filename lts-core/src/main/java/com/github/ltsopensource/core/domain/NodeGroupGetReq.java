package com.github.ltsopensource.core.domain;

import com.github.ltsopensource.admin.request.PaginationReq;
import com.github.ltsopensource.core.cluster.NodeType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NodeGroupGetReq extends PaginationReq {

	private NodeType nodeType;

	private String nodeGroup;
}
