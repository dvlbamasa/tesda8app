package com.tesda8.region8.audit.repository;

import com.tesda8.region8.audit.model.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>, QuerydslPredicateExecutor<AuditLog> {
}
