package spring.boot.template.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {

    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil() {
    }

    public static long nextId() {
        return IDX.incrementAndGet();
    }


}
