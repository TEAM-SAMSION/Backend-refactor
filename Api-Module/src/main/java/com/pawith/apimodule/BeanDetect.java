package com.pawith.apimodule;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

//@Component
@RequiredArgsConstructor
public class BeanDetect {
    private final ApplicationContext applicationContext;

    @EventListener(ApplicationReadyEvent.class)
    public void getBeanList() {
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println(bean);
        }
    }
}
