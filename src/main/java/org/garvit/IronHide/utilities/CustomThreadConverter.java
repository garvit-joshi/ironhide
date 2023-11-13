package org.garvit.IronHide.utilities;

import ch.qos.logback.classic.pattern.ThreadConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * A custom thread converter for Logback that distinguishes between virtual and regular threads.
 *
 * <p>This converter checks if the current thread is a virtual thread and, if so, returns the string
 * "VirtualThread". Otherwise, it returns the name of the current thread. This can be useful in
 * environments where both virtual and regular threads are in use and there's a need to
 * differentiate between them in log messages.
 *
 * <p>To use this converter in a Logback configuration, define it as a conversion rule and then use
 * the corresponding conversion word in your logging pattern.
 *
 * @author garvit-joshi on 12/11/23
 */
public class CustomThreadConverter extends ThreadConverter {

    /**
     * Converts the thread information of the logging event into a string representation.
     *
     * @param event The logging event.
     * @return "VirtualThread" if the current thread is a virtual thread, otherwise the name of the
     *     current thread.
     */
    @Override
    public String convert(ILoggingEvent event) {
        final Thread currentThread = Thread.currentThread();
        if (isVirtualThread(currentThread)) {
            return "VirtualThread";
        }
        return currentThread.getName();
    }

    /**
     * Checks if the provided thread is a virtual thread.
     *
     * <p>Currently, this method checks the thread's string representation to determine if it's a
     * virtual thread. This approach might need updates based on Java's final implementation of
     * virtual threads.
     *
     * @param thread The thread to check.
     * @return true if the thread is virtual, false otherwise.
     */
    private boolean isVirtualThread(Thread thread) {
        return thread.toString().contains("VirtualThread");
    }
}

