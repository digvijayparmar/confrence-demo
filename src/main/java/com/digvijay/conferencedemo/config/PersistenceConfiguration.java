package com.digvijay.conferencedemo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    //Below method returns the Datasource object, Spring will look if one already exists
    // in the context and will replace the data source definition with this implementation
   // @Bean
   // public DataSource dataSource(){
        //Configuration happens as follows
 /*       DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/postgres");
        System.out.println("********Loaded from java config file*******");
        return builder.build();*/

   // }
}
