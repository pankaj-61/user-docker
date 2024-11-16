package com.user.kafka.listener;


import com.user.database.model.AuditLog;
import com.user.database.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuditListener {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @KafkaListener(topics = "user-topic", groupId = "crud-group")
    public void handleUserTopic(String message) {
        AuditLog log = new AuditLog();
        log.setAction(message.split(":")[0]);
        log.setDetails(message);
        log.setTime(new Date().getTime());
        auditLogRepository.save(log);
    }

}