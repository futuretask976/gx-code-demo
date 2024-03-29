package com.gx.code.utils.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String args[]) {
        ServiceLoader<SpiInterface> serviceLoader = ServiceLoader.load(SpiInterface.class);

        // 1. forEach 模式
        serviceLoader.forEach(SpiInterface::sayHello);
        // 2. 迭代器模式
        Iterator<SpiInterface> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            SpiInterface spiInterface = iterator.next();
            spiInterface.sayHello();
        }
    }
}