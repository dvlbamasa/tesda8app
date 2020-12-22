package com.tesda8.region8.audit.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AuditUtil implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
    }
    public static<T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
    private static void setContext(ApplicationContext context) {
        AuditUtil.context = context;
    }
}
