package com.vedant.log_producer_service.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_entries")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; // INFO, WARN, ERROR, FATAL
    private String message;
    private String serviceName;
    private LocalDateTime timestamp;

    private String traceId;
    private String threadName;
    private String exceptionType; // nullable
    private String stackTrace;    // nullable

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    // Getters, setters, constructors
    public LogEntry(){ }

    public LogEntry(Long id, String level, String message, String serviceName, LocalDateTime timestamp, String traceId, String threadName, String exceptionType, String stackTrace) {
        this.id = id;
        this.level = level;
        this.message = message;
        this.serviceName = serviceName;
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.threadName = threadName;
        this.exceptionType = exceptionType;
        this.stackTrace = stackTrace;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
