package com.springboot.crm.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    //    Realm
    @Bean
    public StatelessRealm statelessRealm() {
        return new StatelessRealm();
    }

    @Bean
    public StatelessDefaultSubjectFactory statelessDefaultSubjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public DefaultSessionManager defaultSessionManager() {
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        DefaultSubjectDAO dao = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator) dao.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

        securityManager.setSubjectFactory(statelessDefaultSubjectFactory());
        securityManager.setSessionManager(defaultSessionManager());
        SecurityUtils.setSecurityManager(securityManager);

        securityManager.setRealm(statelessRealm());

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        必须注入securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        wei shou quan
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("statelessAuthcFilter", new StatelessAuthcFilter());
        shiroFilterFactoryBean.setFilters(filters);
//拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        应为是无状态的，所以此处的白名单不起作用
//        filterChainDefinitionMap.put("/data/login/*", "anon");
        filterChainDefinitionMap.put("/**", "statelessAuthcFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
