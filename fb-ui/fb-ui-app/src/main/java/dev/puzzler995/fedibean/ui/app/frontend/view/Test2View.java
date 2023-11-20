package dev.puzzler995.fedibean.ui.app.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.puzzler995.fedibean.ui.app.frontend.component.BodyContainer;
import java.io.Serial;

@AnonymousAllowed
@Route(value = "test2", layout = BodyContainer.class)
public class Test2View extends VerticalLayout {
  @Serial private static final long serialVersionUID = -5378751754693136163L;

  public Test2View() {
    add("Test2");
  }
}
