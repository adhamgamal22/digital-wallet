package com.digitalwallet.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TransactionIdGenerator {

    private static final String PREFIX = "TXN";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final AtomicLong sequence = new AtomicLong(1);

    public String generateTransactionId() {
        String date = LocalDateTime.now().format(DATE_FORMAT);
        String seq = String.format("%06d", sequence.getAndIncrement() % 1000000);
        String random = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return String.format("%s-%s-%s-%s", PREFIX, date, seq, random);
    }
}