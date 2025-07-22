package com.vedant.log_producer_service.service;

import com.vedant.log_producer_service.entity.LogEntry;
import com.vedant.log_producer_service.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class LogGeneratorService {

    private final LogEntryRepository repository;
    private final Random random = new Random();

    public LogGeneratorService(LogEntryRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 300000)
    public void generateLog() {
        double chance = random.nextDouble() * 100;
        String level;
        String message;
        String exceptionType = null;
        String stackTrace = null;

        if (chance < 5) {
            level = "FATAL";
            String[] fatalMessages = {
                    "Critical system failure occurred.",
                    "Kernel panic detected.",
                    "Fatal disk I/O error.",
                    "Memory corruption detected.",
                    "Container OOM killed unexpectedly."
            };
            String[] fatalExceptions = {
                    "OutOfMemoryError",
                    "StackOverflowError",
                    "FatalSignalError",
                    "SegmentationFaultError",
                    "DiskFailureException"
            };
            level = "FATAL";
            message = pickRandom(fatalMessages);
            exceptionType = pickRandom(fatalExceptions);
            stackTrace = exceptionType + ": simulated fatal stack trace...";
        } else if (chance < 25) { // 5 + 20
            level = "ERROR";
            String[] errorMessages = {
                    "Unhandled exception during request.",
                    "Failed to connect to database.",
                    "External API call failed.",
                    "JSON parsing failed.",
                    "User authentication failed.",
                    "Payment gateway timeout.",
                    "Unable to load config file.",
                    "Cache deserialization error.",
                    "FileNotFoundException during upload.",
                    "Illegal state detected in workflow."
            };
            String[] errorExceptions = {
                    "NullPointerException",
                    "SQLException",
                    "HttpClientErrorException",
                    "JsonProcessingException",
                    "AuthenticationException",
                    "TimeoutException",
                    "IllegalStateException",
                    "IOException",
                    "FileNotFoundException",
                    "CacheReadException"
            };
            message = pickRandom(errorMessages);
            exceptionType = pickRandom(errorExceptions);
            stackTrace = exceptionType + ": simulated error stack trace...";
        } else if (chance < 35) { // 25 + 10
            level = "WARN";
            message = "Deprecated API usage detected.";
        } else {
            level = "INFO";
            message = "Request processed successfully.";
        }

        LogEntry log = new LogEntry();
        log.setLevel(level);
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        log.setServiceName("demo-service");
        log.setTraceId(UUID.randomUUID().toString());
        log.setThreadName(Thread.currentThread().getName());
        log.setExceptionType(exceptionType);
        log.setStackTrace(stackTrace);

        repository.save(log);
    }

    private String pickRandom(String[] options) {
        return options[random.nextInt(options.length)];
    }


    public List<LogEntry> getAllLogs() {
        return repository.findAll();
    }

    public Optional<LogEntry> getLogById(Long id) {
        return repository.findById(id);
    }

    public List<LogEntry> getRecentLogs() {
        return repository.findTop100ByOrderByTimestampDesc();
    }

    public List<LogEntry> getLogsByLevel(String level) {
        return repository.findByLevelOrderByTimestampDesc(level);
    }

    public List<LogEntry> getLogsByService(String service) {
        return repository.findByServiceName(service);
    }

    public List<LogEntry> getRecentErrors() {
        return repository.findTop10ByLevelOrderByTimestampDesc("ERROR");
    }

}
