package com.github.ltsopensource.ec;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * 事件订阅者
 * 
 * @author Robert HG (254963746@qq.com) on 5/11/15.
 */
@Setter
@Getter
public class EventSubscriber {

	public EventSubscriber(String id, Observer observer) {
		this.id = id;
		this.observer = observer;
	}

	private String id;

	private Observer observer;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EventSubscriber that = (EventSubscriber) o;
		return Objects.equals(id, that.id) && Objects.equals(observer, that.observer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, observer);
	}
}
