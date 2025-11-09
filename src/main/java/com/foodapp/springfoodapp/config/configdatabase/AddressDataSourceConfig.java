//package com.foodapp.springfoodapp.config.configdatabase;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "addressEntityManagerFactoryRef",
//        transactionManagerRef = "addressTransactionManagerRef",
//        basePackages = {"com.foodapp.springfoodapp.repository"}
//)
//public class AddressDataSourceConfig {
//
//    @Value("${spring.restaurant.datasource.url}")
//    private String url;
//    @Value("${spring.restaurant.datasource.driver-class-name}")
//    private String driverClass;
//    @Value("${spring.restaurant.datasource.username}")
//    private String userName;
//    @Value("${spring.restaurant.datasource.password}")
//    private String password;
//    @Value("${spring.jpa.properties.hibernate.dialect}")
//    private String dialect;
//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddlAuto;
//
//    @Bean(name = "addressDataSource")
//    public DataSource AddressDataSource() {
//        return DataSourceBuilder.create()
//                .url("url")
//                .driverClassName(driverClass)
//                .username(userName)
//                .password(password)
//                .build();
//    }
//
//    // todo should be
//    @Bean(name = "addressEntityManagerFactoryRef")
//    public LocalContainerEntityManagerFactoryBean managerFactoryBean() {
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(AddressDataSource());
//
//        //todo must match with addressEntity package
//        factoryBean.setPackagesToScan("com.foodapp.springfoodapp.entiry");
//
//        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        factoryBean.setJpaVendorAdapter(adapter);
//
//        // todo database configuration properties
//        Map<String, String> props = new HashMap<>();
//        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.auto", "update");
//
//        factoryBean.setJpaPropertyMap(props);
//        return factoryBean;
//
//    }
//
//    @Bean(name = "addressTransactionManagerRef")
//    public PlatformTransactionManager transactionManager(){
//        JpaTransactionManager manager=new JpaTransactionManager();
//        manager.setEntityManagerFactory(managerFactoryBean().getObject());
//        return manager;
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
