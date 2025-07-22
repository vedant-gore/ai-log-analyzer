package com.vedant.log_analyzer_service.controller;

import com.vedant.log_analyzer_service.model.LogEntry;
import com.vedant.log_analyzer_service.service.LogAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyze")
public class LogAnalyzerController {

    @Autowired
    private LogAnalyzerService logAnalyzerService;

    @GetMapping("/logs")
    public List<LogEntry> getAllLogs() {
        return logAnalyzerService.fetchAllLogs();
    }

    @GetMapping("/logs/recent")
    public List<LogEntry> getRecentLogs() {
        return logAnalyzerService.fetchRecentLogs();
    }

    @GetMapping("/logs/{id}")
    public LogEntry getLogById(@PathVariable Long id) {
        return logAnalyzerService.fetchLogById(id);
    }

    @GetMapping(value = "/logs", params = "level")
    public List<LogEntry> getLogsByLevel(@RequestParam String level) {
        return logAnalyzerService.fetchLogsByLevel(level);
    }

    @GetMapping(value = "/logs", params = "service")
    public List<LogEntry> getLogsByService(@RequestParam String service) {
        return logAnalyzerService.fetchLogsByService(service);
    }

    @GetMapping("/summarize")
    public String summarizeRecentLogs() {
        return logAnalyzerService.summarizeRecentLogs();
    }
}
