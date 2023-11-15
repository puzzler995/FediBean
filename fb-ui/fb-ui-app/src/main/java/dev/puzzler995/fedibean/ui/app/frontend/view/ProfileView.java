package dev.puzzler995.fedibean.ui.app.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = "user", layout = MainView.class)
public class ProfileView extends VerticalLayout implements HasUrlParameter<String> {
  private String userId;

  public ProfileView() {
    add("Profile");
  }

  @Override
  public void setParameter(BeforeEvent event, String parameter) {
    this.userId = parameter;
  }
}
