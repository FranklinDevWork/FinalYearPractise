package web.thomas.site.application.system.binder;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import web.thomas.site.application.system.helper.ConfigurationVariableParam;
import web.thomas.site.config.FileValuesConfiguration;

import static org.glassfish.jersey.server.model.Parameter.Source.UNKNOWN;

public class ConfigurationValueBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(ConfigurationValueResolverFactory.class).to(ValueFactoryProvider.class).in(Singleton.class);
    bind(ConfigurationValueResolver.class).to(new TypeLiteral<InjectionResolver<ConfigurationVariableParam>>() {
    }).in(Singleton.class);
  }

  @Singleton
  private static class ConfigurationValueResolver extends ParamInjectionResolver<ConfigurationVariableParam> {

    @Inject
    public ConfigurationValueResolver() {

      super(ConfigurationValueResolverFactory.class);
    }
  }

  @Singleton
  private static class ConfigurationValueResolverFactory extends AbstractValueFactoryProvider {

    @Inject
    public ConfigurationValueResolverFactory(final MultivaluedParameterExtractorProvider extractorProvider, final ServiceLocator
        injector) {

      super(extractorProvider, injector, UNKNOWN);
    }

    @Override
    protected Factory<?> createValueFactory(final Parameter parameter) {

      final Class<?> classType = parameter.getRawType();

      if (classType == null) {
        return null;
      }

      if (parameter.getAnnotation(ConfigurationVariableParam.class) == null) {
        return null;
      }

      return new AbstractContainerRequestValueFactory<Object>() {

        @Override
        public Object provide() {

          String value = FileValuesConfiguration.properties.getProperty(parameter.getAnnotation(ConfigurationVariableParam.class).value().getName());

          if (classType.equals(Integer.class)) {
            return Integer.valueOf(value);
          }

          if (classType.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
          }

          return value;
        }
      };
    }
  }
}
