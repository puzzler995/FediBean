package dev.puzzler995.fedibean.ui.app.frontend.component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import dev.puzzler995.fedibean.ui.app.frontend.view.MainView;
import java.io.Serial;

@ParentLayout(MainView.class)
public class BodyContainer extends VerticalLayout implements RouterLayout {
  @Serial private static final long serialVersionUID = -4060049958450486719L;
}
