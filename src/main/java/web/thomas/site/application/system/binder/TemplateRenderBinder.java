package web.thomas.site.application.system.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.thomas.site.render.HandleBarsTemplateRender;
import web.thomas.site.render.TemplateRender;

public class TemplateRenderBinder extends AbstractBinder {

  private static final Logger logger = LoggerFactory.getLogger(TemplateRenderBinder.class);

  @Override
  protected void configure() {

    bind(HandleBarsTemplateRender.class).to(TemplateRender.class).in(Singleton.class);
  }
}
