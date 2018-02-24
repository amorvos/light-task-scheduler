package com.github.ltsopensource.ec.injvm;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.ltsopensource.core.constant.Constants;
import com.github.ltsopensource.core.json.JSON;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.ec.EventCenter;
import com.github.ltsopensource.ec.EventInfo;
import com.github.ltsopensource.ec.EventSubscriber;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 在一个jvm中的pub sub 简易实现
 */
public class InJvmEventCenter implements EventCenter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventCenter.class.getName());

	private final ConcurrentHashMap<String, Set<EventSubscriber>> ecMap = new ConcurrentHashMap<>();

	private final ExecutorService executor = new ThreadPoolExecutor(Constants.AVAILABLE_PROCESSOR * 2,
			Constants.AVAILABLE_PROCESSOR * 2, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>(1024),
			new ThreadFactoryBuilder().setNameFormat("LTS-InJvm-EventCenter-Executor").setDaemon(true).build(),
			new ThreadPoolExecutor.AbortPolicy());

	@Override
	public void subscribe(EventSubscriber subscriber, String... topics) {
		for (String topic : topics) {
			Set<EventSubscriber> subscribers = ecMap.get(topic);
			if (subscribers == null) {
				subscribers = Sets.newConcurrentHashSet();
				Set<EventSubscriber> oldSubscribers = ecMap.putIfAbsent(topic, subscribers);
				if (oldSubscribers != null) {
					subscribers = oldSubscribers;
				}
			}
			subscribers.add(subscriber);
		}
	}

	@Override
	public void unSubscribe(String topic, EventSubscriber subscriber) {
		Set<EventSubscriber> subscribers = ecMap.get(topic);
		if (subscribers != null) {
			for (EventSubscriber eventSubscriber : subscribers) {
				if (eventSubscriber.getId().equals(subscriber.getId())) {
					subscribers.remove(eventSubscriber);
				}
			}
		}
	}

	@Override
	public void publishSync(EventInfo eventInfo) {
		Set<EventSubscriber> subscribers = ecMap.get(eventInfo.getTopic());
		if (subscribers != null) {
			for (EventSubscriber subscriber : subscribers) {
				eventInfo.setTopic(eventInfo.getTopic());
				try {
					subscriber.getObserver().onObserved(eventInfo);
				} catch (Throwable t) { // 防御性容错
					LOGGER.error(" eventInfo:{}, subscriber:{}", JSON.toJSONString(eventInfo),
							JSON.toJSONString(subscriber), t);
				}
			}
		}
	}

	@Override
	public void publishAsync(final EventInfo eventInfo) {
		executor.submit(() -> {
			String topic = eventInfo.getTopic();

			Set<EventSubscriber> subscribers = ecMap.get(topic);
			if (subscribers == null) {
				return;
			}
			for (EventSubscriber subscriber : subscribers) {
				try {
					eventInfo.setTopic(topic);
					subscriber.getObserver().onObserved(eventInfo);
				} catch (Throwable t) { // 防御性容错
					LOGGER.error(" eventInfo:{}, subscriber:{}", JSON.toJSONString(eventInfo),
							JSON.toJSONString(subscriber), t);
				}
			}
		});
	}
}
