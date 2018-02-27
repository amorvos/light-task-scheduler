package com.github.ltsopensource.remoting.protocol;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.ltsopensource.remoting.RemotingCommandBody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Remoting模块中，服务器与客户端通过传递RemotingCommand来交互
 */
@Setter
@Getter
@ToString
public class RemotingCommand implements Serializable {

	private static final long serialVersionUID = -6424506729433386206L;

	/**
	 * serializableTypeId
	 */
	private int sid = -1;

	private static final AtomicInteger requestId = new AtomicInteger(0);
	/**
	 * Header 部分
	 */
	private int code;
	private int subCode;
	private int version = 0;
	private int opaque;
	private int flag = 0;
	private String remark;

	/**
	 * body
	 */
	private transient RemotingCommandBody body;

	private RemotingCommand() {

	}

	public static RemotingCommand createRequestCommand(int code, RemotingCommandBody body) {
		RemotingCommand cmd = new RemotingCommand();
		cmd.setCode(code);
		cmd.setBody(body);
		cmd.setOpaque(requestId.getAndIncrement());
		return cmd;
	}

	public static RemotingCommand createResponseCommand(int code, String remark, RemotingCommandBody body) {
		RemotingCommand cmd = new RemotingCommand();
		RemotingCommandHelper.markResponseType(cmd);
		cmd.setCode(code);
		cmd.setRemark(remark);
		cmd.setBody(body);
		cmd.setOpaque(requestId.getAndIncrement());
		return cmd;
	}

	public static RemotingCommand createResponseCommand(int code, RemotingCommandBody body) {
		return createResponseCommand(code, null, body);
	}

	public static RemotingCommand createResponseCommand(int code) {
		return createResponseCommand(code, null, null);
	}

	public static RemotingCommand createResponseCommand(int code, String remark) {
		return createResponseCommand(code, remark, null);
	}

	@SuppressWarnings("unchecked")
	public <T extends RemotingCommandBody> T getBody() {
		return (T) body;
	}

}
