package com.vedant.log_producer_service.controller;

import com.vedant.log_producer_service.entity.LogEntry;
import com.vedant.log_producer_service.service.LogGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogGeneratorService logService;

    @GetMapping
    public List<LogEntry> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/recent")
    public List<LogEntry> getRecentLogs() {
        return logService.getRecentLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLogById(@PathVariable Long id) {
        Optional<LogEntry> optionalLog = logService.getLogById(id);
        if (optionalLog.isPresent()) {
            return ResponseEntity.ok(optionalLog.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Log not found");
        }
    }

    @GetMapping(params = "level")
    public List<LogEntry> getLogsByLevel(@RequestParam String level) {
        return logService.getLogsByLevel(level);
    }

    @GetMapping(params = "service")
    public List<LogEntry> getLogsByService(@RequestParam String service) {
        return logService.getLogsByService(service);
    }

    @GetMapping("/errors/recent")
    public List<LogEntry> getRecentErrors() {
        return logService.getRecentErrors();
    }
}


