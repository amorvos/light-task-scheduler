package com.github.ltsopensource.core.cluster;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.commons.utils.CollectionUtils;
import com.github.ltsopensource.core.constant.EcTopic;
import com.github.ltsopensource.core.listener.NodeChangeListener;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.ec.EventInfo;
import com.google.common.collect.Sets;

/**
 * @author Robert HG (254963746@qq.com) on 6/22/14. 节点管理 (主要用于管理自己关注的节点)
 */
public class SubscribedNodeManager implements NodeChangeListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscribedNodeManager.class);

	private final ConcurrentHashMap<NodeType, Set<Node>> NODES = new ConcurrentHashMap<NodeType, Set<Node>>();

	private AppContext appContext;

	public SubscribedNodeManager(AppContext appContext) {
		this.appContext = appContext;
	}

	/**
	 * 添加监听的节点
	 */
	private void addNode(Node node) {
		Set<Node> nodeSet = NODES.get(node.getNodeType());
		if (CollectionUtils.isEmpty(nodeSet)) {
			nodeSet = Sets.newConcurrentHashSet();
			Set<Node> oldNodeList = NODES.putIfAbsent(node.getNodeType(), nodeSet);
			if (oldNodeList != null) {
				nodeSet = oldNodeList;
			}
		}
		nodeSet.add(node);
		EventInfo eventInfo = new EventInfo(EcTopic.NODE_ADD);
		eventInfo.setParam("node", node);
		appContext.getEventCenter().publishSync(eventInfo);
		LOGGER.info("Add Node {}", node);
	}

	public List<Node> getNodeList(final NodeType nodeType, final String nodeGroup) {
		Set<Node> nodes = NODES.get(nodeType);
		if (CollectionUtils.isEmpty(nodes)) {
			return Collections.emptyList();
		}
		return nodes.stream().filter(node -> node.getGroup().equals(nodeGroup)).collect(Collectors.toList());
	}

	public List<Node> getNodeList(NodeType nodeType) {
		return CollectionUtils.setToList(NODES.get(nodeType));
	}

	private void removeNode(Node delNode) {
		Set<Node> nodes = NODES.get(delNode.getNodeType());
		if (CollectionUtils.isEmpty(nodes)) {
			return;
		}
		nodes.forEach(node -> {
			if (!Objects.equals(node.getIdentity(), delNode.getIdentity())) {
				return;
			}
			nodes.remove(node);
			EventInfo eventInfo = new EventInfo(EcTopic.NODE_REMOVE);
			eventInfo.setParam("node", node);
			appContext.getEventCenter().publishSync(eventInfo);
			LOGGER.info("Remove {}", node);
		});
	}

	@Override
	public void addNodes(List<Node> nodes) {
		if (CollectionUtils.isEmpty(nodes)) {
			return;
		}
		for (Node node : nodes) {
			addNode(node);
		}
	}

	@Override
	public void removeNodes(List<Node> nodes) {
		if (CollectionUtils.isEmpty(nodes)) {
			return;
		}
		for (Node node : nodes) {
			removeNode(node);
		}
	}
}
