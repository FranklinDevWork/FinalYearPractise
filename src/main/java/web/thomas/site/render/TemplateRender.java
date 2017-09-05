package web.thomas.site.render;

/** Generic interface for template engines */
public interface TemplateRender {

  /** For rendering of a template based on a name */
  String render(String templateName, Object context);
}
