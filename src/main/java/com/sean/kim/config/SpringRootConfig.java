package com.sean.kim.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan({ "com.sean.kim.config" })
public class SpringRootConfig
{ 
  /**
   * @return
   */
  @Bean
  public static PropertyPlaceholderConfigurer propertyConfigIn()
  {
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
    propertyPlaceholderConfigurer
        .setLocations( new Resource[] { new ClassPathResource( "application.properties" ) } );
    return propertyPlaceholderConfigurer;
  }
}