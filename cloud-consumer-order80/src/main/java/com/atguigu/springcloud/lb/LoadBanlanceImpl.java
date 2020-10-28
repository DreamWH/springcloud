package com.atguigu.springcloud.lb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBanlanceImpl implements LoadBanlance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncerment(){
        int current;
        int next;
        do {
            //获取当前值
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            System.out.println("访问次数:" + next);
        }while (!this.atomicInteger.compareAndSet(current,next));
            return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstance) {
        for (ServiceInstance instance : serviceInstance) {
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        int index = getAndIncerment() % serviceInstance.size();
        System.out.println(serviceInstance.size());
        System.out.println(index);

        return serviceInstance.get(index);
    }
}
