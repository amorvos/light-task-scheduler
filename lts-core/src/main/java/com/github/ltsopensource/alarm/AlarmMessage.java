package com.github.ltsopensource.alarm;

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
public class AlarmMessage implements Serializable {

	private long time;

	private AlarmType type;

	private String msg;
}
