package com.github.ltsopensource.cmd;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 2/17/16.
 */
@Setter
@Getter
@ToString
public class HttpCmdResponse implements Serializable {

	private boolean success = false;

	private String msg;

	private String code;

	private String obj;

	public static HttpCmdResponse newResponse(boolean success, String msg) {
		HttpCmdResponse response = new HttpCmdResponse();
		response.setSuccess(success);
		response.setMsg(msg);
		return response;
	}
}
