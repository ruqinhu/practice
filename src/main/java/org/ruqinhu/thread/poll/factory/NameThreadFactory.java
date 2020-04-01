package org.ruqinhu.thread.poll.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaorunze
 * @since 2020/1/6
 */
public class NameThreadFactory implements ThreadFactory {
    private static Map<String, AtomicInteger> PREFIX_COUNTER = new ConcurrentHashMap<>();
    private AtomicInteger namePrefix;
    private final int totalSize;
    private final String prefix;
    private final boolean makeDaemons;

    public NameThreadFactory(String prefix, int totalSize, boolean makeDaemons) {
        PREFIX_COUNTER.putIfAbsent(prefix,new AtomicInteger(0));
        int prefixCounter = PREFIX_COUNTER.get(prefix).getAndAdd(1);
        this.totalSize = totalSize;
        this.prefix = prefix + "_" + prefixCounter;
        this.makeDaemons = makeDaemons;
        namePrefix = new AtomicInteger(1);
    }

    public NameThreadFactory(String prefix, boolean makeDaemons) {
        this(prefix, 0, makeDaemons);
    }

    public NameThreadFactory(String prefix, int totalSize) {
        this(prefix, totalSize, true);
    }

    @Override
    public Thread newThread(Runnable r) {
        String name =prefix;
        if (totalSize > 1) {
            name += "_" + namePrefix.getAndAdd(1);
        }
        Thread thread = new Thread(r, name);

        thread.setDaemon(makeDaemons);
        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
