package com.tesda8.region8.audit.listener;

import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.audit.service.AuditUtil;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;

public abstract class AbstractEntityListener<E>{

    public void doCreate(E entity, AuditAction auditAction) {
        postProcess(entity, auditAction);
    }
    public void doUpdate(E entity, AuditAction auditAction) {
        postProcess(entity, auditAction);
    }

    private void postProcess(E entity, AuditAction action) {
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCompletion(int status) {
                        if (status == STATUS_COMMITTED) {
                            persist(entity, action);
                        }
                    }
                });
    }
    
    private void persist(E entity, AuditAction action) {
        EntityManagerFactory entityManagerFactory =
                AuditUtil.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AuditBase auditEntity = mapAuditEntity(entity);
        auditEntity.setAuditAction(action);
        auditEntity.setAuditDate(LocalDateTime.now());
        entityManager.getTransaction().begin();
        entityManager.persist(auditEntity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public abstract AuditBase mapAuditEntity(E entity);
}
