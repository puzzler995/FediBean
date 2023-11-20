package dev.puzzler995.fedibean.ui.app.frontend.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import dev.puzzler995.fedibean.ui.app.frontend.component.BodyContainer;
import dev.puzzler995.fedibean.ui.app.frontend.component.Sidebar;
import java.io.Serial;

public class MainView extends HorizontalLayout implements RouterLayout {
  @Serial private static final long serialVersionUID = 8406847076955743651L;

  public MainView() {
    Sidebar layoutCol = new Sidebar();
    BodyContainer layoutCol2 = new BodyContainer();
    addClassName(Gap.MEDIUM);
    setWidth("100%");
    setHeight("100%");
    getStyle().set("flex-grow", "1");
    layoutCol.getStyle().set("flex-grow", "1");
    layoutCol.setWidth("fit-content");
    layoutCol.setHeight("100%");
    layoutCol.getElement().setAttribute("theme", Lumo.DARK);
    layoutCol2.setWidth("100%");
    layoutCol2.getStyle().set("flex-grow", "1");
    add(layoutCol);
  }
}
