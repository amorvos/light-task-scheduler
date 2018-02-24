package com.github.ltsopensource.core.listener;

import java.util.List;

import com.github.ltsopensource.core.cluster.Node;

/**
 * @author Robert HG (254963746@qq.com) on 5/18/15.
 */
public interface NodeChangeListener {

    /**
     * 添加节点
     *
     * @param nodes 节点列表
     */
	void addNodes(List<Node> nodes);

    /**
     * 移除节点
     * @param nodes 节点列表
     */
	void removeNodes(List<Node> nodes);

}
