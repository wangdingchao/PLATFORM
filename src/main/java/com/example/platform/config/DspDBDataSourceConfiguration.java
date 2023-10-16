package com.example.platform.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {"com.example.platform.mapper"},
        sqlSessionFactoryRef = "dspdbSqlSessionFactory")
public class DspDBDataSourceConfiguration {



    @Bean(name = "dspdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dspdb")
    @Primary
    public DataSource sogaldbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dspdbTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("dspdbDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dspdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dspdbDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(ArrayUtils.addAll(
                new PathMatchingResourcePatternResolver().getResources("classpath*:com.example.platform.mapper/*.xml"),
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*-mapper.xml")
        ));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "dspdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("dspdbSqlSessionFactory")
                                                                SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
