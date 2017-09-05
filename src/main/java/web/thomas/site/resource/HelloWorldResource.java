package web.thomas.site.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.thomas.site.application.system.ConfigurationVariable;
import web.thomas.site.application.system.helper.ConfigurationVariableParam;
import web.thomas.site.render.TemplateRender;

import static java.util.Collections.emptyMap;

@Singleton
@Path("/")
public class HelloWorldResource {

  private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

  private TemplateRender templateRender;

  @ConfigurationVariableParam(ConfigurationVariable.TEST)
  String test;

  @Inject
  public HelloWorldResource(TemplateRender templateRender) {

    this.templateRender = templateRender;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayhello() {

    logger.info("HELLO");

    logger.info(test);

    return templateRender.render("hello", emptyMap());
  }
}
