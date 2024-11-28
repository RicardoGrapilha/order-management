package com.order.demo.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MessageStorage {

    private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void clear() {
        messages.clear();
    }
}
