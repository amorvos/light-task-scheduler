package com.github.ltsopensource.ec.injvm;

import com.github.ltsopensource.core.factory.NamedThreadFactory;
import com.github.ltsopensource.core.json.JSON;
import com.github.ltsopensource.core.constant.Constants;
import com.github.ltsopensource.core.logger.Logger;
import com.github.ltsopensource.core.logger.LoggerFactory;
import com.github.ltsopensource.ec.EventCenter;
import com.github.ltsopensource.ec.EventInfo;
import com.github.ltsopensource.ec.EventSubscriber;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在一个jvm中的pub sub 简易实现
 *
 * @author Robert HG (254963746@qq.com) on 5/12/15.
 */
public class InjvmEventCenter implements EventCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventCenter.class.getName());

    private final ConcurrentHashMap<String, Set<EventSubscriber>> ecMap =
            new ConcurrentHashMap<String, Set<EventSubscriber>>();

    private final ExecutorService executor = Executors.newFixedThreadPool(Constants.AVAILABLE_PROCESSOR * 2, new NamedThreadFactory("LTS-InjvmEventCenter-Executor", true));

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
                } catch (Throwable t) {      // 防御性容错
                    LOGGER.error(" eventInfo:{}, subscriber:{}",
                            JSON.toJSONString(eventInfo), JSON.toJSONString(subscriber), t);
                }
            }
        }
    }

    @Override
    public void publishAsync(final EventInfo eventInfo) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                String topic = eventInfo.getTopic();

                Set<EventSubscriber> subscribers = ecMap.get(topic);
                if (subscribers != null) {
                    for (EventSubscriber subscriber : subscribers) {
                        try {
                            eventInfo.setTopic(topic);
                            subscriber.getObserver().onObserved(eventInfo);
                        } catch (Throwable t) {     // 防御性容错
                            LOGGER.error(" eventInfo:{}, subscriber:{}",
                                    JSON.toJSONString(eventInfo), JSON.toJSONString(subscriber), t);
                        }
                    }
                }
            }
        });
    }
}
