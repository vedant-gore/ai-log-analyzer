package com.vedant.log_analyzer_service.feign;

import com.vedant.log_analyzer_service.model.LogEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "log-producer-service", url = "http://localhost:8081")
public interface LogProducerClient {

    @GetMapping("/logs")
    List<LogEntry> getAllLogs();

    @GetMapping("/logs/recent")
    List<LogEntry> getRecentLogs();

    @GetMapping("/logs/{id}")
    LogEntry getLogById(@PathVariable("id") Long id);

    @GetMapping(value = "/logs", params = "level")
    List<LogEntry> getLogsByLevel(@RequestParam("level") String level);

    @GetMapping(value = "/logs", params = "service")
    List<LogEntry> getLogsByService(@RequestParam("service") String serviceName);
}
