package com.vedant.log_analyzer_service.model;

import java.time.LocalDateTime;

public class LogEntry {
    private Long id;
    private String level;
    private String message;
    private String serviceName;
    private LocalDateTime timestamp;

    // Getters and Setters

    public LogEntry(Long id, String level, String message, String serviceName, LocalDateTime timestamp) {
        this.id = id;
        this.level = level;
        this.message = message;
        this.serviceName = serviceName;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
