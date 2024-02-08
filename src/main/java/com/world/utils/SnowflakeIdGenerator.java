package com.world.utils;

public class SnowflakeIdGenerator {
    
    // Custom epoch for Snowflake (January 1, 2020 00:00:00 UTC)
    private static final long EPOCH = 1577836800000L;
    
    // Number of bits for each part of the ID
    private static final long TIMESTAMP_BITS = 41;
    private static final long WORKER_ID_BITS = 10;
    private static final long SEQUENCE_BITS = 12;
    
    // Max values for worker ID and sequence
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);
    
    // Shifts
    private static final long TIMESTAMP_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    
    // Worker ID and sequence
    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    
    public SnowflakeIdGenerator(long workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException(String.format("Worker ID must be between 0 and %d", MAX_WORKER_ID));
        }
        this.workerId = workerId;
    }
    
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID for " + (lastTimestamp - timestamp) + " milliseconds.");
        }
        
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Sequence overflow, wait until next millisecond
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        
        lastTimestamp = timestamp;
        
        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT) |
               (workerId << WORKER_ID_SHIFT) |
               sequence;
    }
    
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

}
