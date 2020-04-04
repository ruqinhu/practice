//package org.ruqinhu.springboot.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.PropertyValues;
//import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.PropertyResourceConfigurer;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Configuration
//public class TestExtensionPoint implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor, InitializingBean, BeanNameAware {
//
//    private String beanName;
//
//    private ConcurrentHashMap concurrentHashMap;
//
//    private HashMap hashMap;
//
//    private ApplicationContext applicationContext;
//
//    private String basePackage = "basePackage";
//
//    private Environment environment;
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition beandefinition = ((ConfigurableApplicationContext) applicationContext).getBeanFactory()
//                .getBeanDefinition(beanName);
//        //这里获取不到，因为在 MapperScannerConfigurer 中，propertyValues 是通过 set 方法注入。
//        PropertyValues propertyValues = beandefinition.getPropertyValues();
//
//        Map<String, PropertyResourceConfigurer> prcs = applicationContext.getBeansOfType(PropertyResourceConfigurer.class);
//
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        factory.registerBeanDefinition(beanName, beandefinition);
//
//        for (PropertyResourceConfigurer prc:prcs.values()) {
//            prc.postProcessBeanFactory(factory);
//        }
//
//        this.environment = applicationContext.getEnvironment();
//
//        System.out.println("占位符");
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        System.out.println(configurableListableBeanFactory);
//
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//        System.out.println("aaa");
//    }
//
//    @Override
//    public void setBeanName(String s) {
//        this.beanName = s;
//        System.out.println(s);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
