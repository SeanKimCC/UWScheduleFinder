package com.sean.kim.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author gturner
 *
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
  @Override
  protected Class< ? >[] getRootConfigClasses()
  {
    return new Class[] {  SpringRootConfig.class, ServicesConfig.class };
  }

  @Override
  protected Class< ? >[] getServletConfigClasses()
  {
    return new Class[] { SpringWebConfig.class };
  }

  @Override
  protected String[] getServletMappings()
  {
    return new String[] { "/" };
  }
}
