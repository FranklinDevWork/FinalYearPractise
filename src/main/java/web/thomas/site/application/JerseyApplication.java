package web.thomas.site.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.thomas.site.application.system.binder.ConfigurationValueBinder;
import web.thomas.site.application.system.binder.TemplateRenderBinder;
import web.thomas.site.config.FileValuesConfiguration;

public class JerseyApplication extends ResourceConfig {

  private static final Logger logger = LoggerFactory.getLogger(JerseyApplication.class);

  public JerseyApplication() {

    packages("web.thomas.site.application");

    loadConfigurationValues();

    register(new ConfigurationValueBinder());
    register(new TemplateRenderBinder());
  }

  private void loadConfigurationValues() {

    new FileValuesConfiguration().setValuesFromConfigurationFile();
  }
}
