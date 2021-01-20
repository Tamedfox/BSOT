package com.cf.bsot.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/11 23:27
 */
@Component
public class SpringContextUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    private static SpringContextUtil INSTANCE = new SpringContextUtil();

    private SpringContextUtil() {
    }

    public static SpringContextUtil getInstance() {
        return INSTANCE;
    }

    /**
     * 根据类型获取已经注册的bean
     * @param claz
     * @param <T>
     * @return
     */
    public  <T> T getBean(Class<T> claz){
        return applicationContext.getBean(claz);
    }

    /**
     * 根据名称和类型获取已注册的bean
     * @param name
     * @param claz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> claz){
        return applicationContext.getBean(name,claz);
    }

    /**
     * 根据类型获取bean map
     * @param claz
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getBeans(Class<T> claz){
        return applicationContext.getBeansOfType(claz);
    }

    /**
     * 根据名称获取已注册的bean
     * @param name
     * @param <T>
     * @return
     */
    public <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取Application信息
     * @return
     */
    public String getApplicationAllInfo() {
        StringBuilder sb = new StringBuilder();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = applicationContext.getBean(beanName);
            sb.append("bean id=").append(beanName);
            sb.append(" bean=").append(bean);
        }
        return sb.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
