package web.thomas.site.application.system;

import web.thomas.site.config.ConfigurationKey;

public enum ConfigurationVariable implements ConfigurationKey {

  TEST("TEST");

  final String name;

  ConfigurationVariable(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return name;
  }
}
