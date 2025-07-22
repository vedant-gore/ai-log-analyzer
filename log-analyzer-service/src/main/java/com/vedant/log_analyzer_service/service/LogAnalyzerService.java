package com.vedant.log_analyzer_service.service;

import com.vedant.log_analyzer_service.feign.LogProducerClient;
import com.vedant.log_analyzer_service.model.LogEntry;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAnalyzerService {

    @Autowired
    private LogProducerClient logProducerClient;

    private final ChatClient chatClient;

    public LogAnalyzerService(ChatClient chatClient){
        this.chatClient = chatClient;
    }


    public List<LogEntry> fetchRecentLogs() {
        return logProducerClient.getRecentLogs();
    }

    public LogEntry fetchLogById(Long id) {
        return logProducerClient.getLogById(id);
    }

    public List<LogEntry> fetchLogsByLevel(String level) {
        return logProducerClient.getLogsByLevel(level);
    }

    public List<LogEntry> fetchLogsByService(String service) {
        return logProducerClient.getLogsByService(service);
    }

    public List<LogEntry> fetchAllLogs() {
        return logProducerClient.getAllLogs();
    }

    public String summarizeRecentLogs() {
        List<LogEntry> logs = logProducerClient.getRecentLogs();

        StringBuilder prompt = new StringBuilder("Summarize the following logs and detect any possible issues:\n\n");
        for (LogEntry log : logs) {
            prompt.append("[").append(log.getTimestamp()).append("] ")
                    .append(log.getServiceName()).append(" - ")
                    .append(log.getLevel()).append(": ")
                    .append(log.getMessage()).append("\n");
        }

        return chatClient.prompt(prompt.toString()).call().content();
    }
}
