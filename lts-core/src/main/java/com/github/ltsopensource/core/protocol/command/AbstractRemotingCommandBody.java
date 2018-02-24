package com.github.ltsopensource.core.protocol.command;

import java.util.Map;

import com.github.ltsopensource.core.support.SystemClock;
import com.github.ltsopensource.remoting.RemotingCommandBody;
import com.github.ltsopensource.remoting.annotation.NotNull;
import com.github.ltsopensource.remoting.annotation.Nullable;
import com.github.ltsopensource.remoting.exception.RemotingCommandFieldCheckException;
import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 7/24/14. 抽象的header 传输信息
 */
@Setter
@Getter
@ToString
public class AbstractRemotingCommandBody implements RemotingCommandBody {

	private static final long serialVersionUID = -8184979792935677160L;

	/**
	 * 节点组 当前节点的 group(统一类型, 具有相同功能的节点group相同)
	 */
	@NotNull
	private String nodeGroup;

	/**
	 * NodeType 的字符串表示, 节点类型
	 */
	@NotNull
	private String nodeType;

	/**
	 * 当前节点的唯一标识
	 */
	@NotNull
	private String identity;

	private Long timestamp = SystemClock.now();

	@Nullable
	private Map<String, Object> extParams;

	public void putExtParam(String key, Object obj) {
		if (this.extParams == null) {
			this.extParams = Maps.newHashMap();
		}
		this.extParams.put(key, obj);
	}

	@Override
	public void checkFields() throws RemotingCommandFieldCheckException {

	}
}
