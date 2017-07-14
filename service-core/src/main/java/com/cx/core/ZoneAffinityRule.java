package com.cx.core;

import com.google.common.collect.Lists;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caixiang on 2017/7/10.
 */
public class ZoneAffinityRule extends ZoneAvoidanceRule {

    private static final Logger logger = LoggerFactory.getLogger(ZoneAffinityRule.class);

    public static final String META_DATA_KEY_ZONE = "zone";

    private final AtomicInteger nextIndex = new AtomicInteger();

    @Override
    public Server choose(Object key) {
        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }
        //可变List存储同zone server
        List<Server> availableList = Lists.newArrayList();

        for (Server server : serverList) {
            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();

            // 匹配zone
            String zone = metadata.get(META_DATA_KEY_ZONE);
            if (!StringUtils.isEmpty(zone)) {
                if (CoreHeaderWebInterceptor.label.get().equals(zone)) {
                    availableList.add(server);
                }
            }
        }
        if(!CollectionUtils.isEmpty(availableList)){
            //refer to RoundRobin's implements
            return availableList.get(nextIndex.getAndIncrement() % availableList.size());
        }
        return null;
    }
}
