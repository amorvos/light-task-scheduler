package com.github.ltsopensource.admin.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Robert HG (254963746@qq.com) on 6/6/15.
 */
@Setter
@Getter
@ToString
public class PaginationReq {

	private Integer start = 0;

	private Integer limit = 10;

	private String field;

	private String direction;
}
