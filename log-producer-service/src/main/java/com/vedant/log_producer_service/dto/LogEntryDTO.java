package com.vedant.log_producer_service.dto;

import java.time.LocalDateTime;

public class LogEntryDTO {
    private Long id;
    private String serviceName;
    private String level;
    private String message;
    private LocalDateTime timestamp;

    // constructor, getters, setters
    public LogEntryDTO(){}

    public LogEntryDTO(Long id, String serviceName, String level, String message, LocalDateTime timestamp) {
        this.id = id;
        this.serviceName = serviceName;
        this.level = level;
        this.message = message;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

