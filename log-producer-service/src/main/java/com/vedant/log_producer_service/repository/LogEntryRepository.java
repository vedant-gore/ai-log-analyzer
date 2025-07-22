package com.vedant.log_producer_service.repository;

import com.vedant.log_producer_service.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findByLevel(String level);

    List<LogEntry> findByServiceName(String serviceName);

    List<LogEntry> findTop100ByOrderByTimestampDesc();

    List<LogEntry> findByLevelOrderByTimestampDesc(String level);

    List<LogEntry> findTop10ByLevelOrderByTimestampDesc(String level);
}
