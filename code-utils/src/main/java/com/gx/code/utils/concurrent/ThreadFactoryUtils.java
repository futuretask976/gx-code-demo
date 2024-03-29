package com.gx.code.utils.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
//import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryUtils {
//    public static ThreadFactory newSpringCustomizableThreadFactory() {
//        ThreadFactory springThreadFactory = new CustomizableThreadFactory(
//                "springThreadFactory-");
//        return springThreadFactory;
//    }

    public static ThreadFactory newGuavaThreadFactory() {
        ThreadFactory guavaThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("guavaThreadFactory-")
                .build();
        return guavaThreadFactory;
    }

    public static ThreadFactory newApacheCommonBasicThreadFactory() {
        ThreadFactory basicThreadFactory = new BasicThreadFactory.Builder()
                .namingPattern("basicThreadFactory-").build();
        return basicThreadFactory;
    }

    public static ThreadFactory newSimpleThreadFactory() {
        return r -> new Thread(r, "simpleThreadFactory-" + r.hashCode());
    }
}
