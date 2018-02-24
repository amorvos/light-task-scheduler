package com.github.ltsopensource.core.loadbalance;

import com.github.ltsopensource.core.cluster.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 主从模式: 譬如将JobClient和TaskTracker设置为这种负载均衡模式,
 * 那么总会去连接最老的一台JobTracker,从而达到主从模式的效果
 *
 * @author Robert HG (254963746@qq.com) on 11/21/15.
 */
public class MasterSalveLoadBalance extends AbstractLoadBalance {

    @Override
    protected <S> S doSelect(List<S> shards, String seed) {

        if (shards.get(0) instanceof Node) {
            shards.sort(Comparator.comparing(left -> ((Node) left).getCreateTime()));
        }

        return shards.get(0);
    }
}
