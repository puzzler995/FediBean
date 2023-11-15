package dev.puzzler995.fedibean.ui.app.frontend.component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import dev.puzzler995.fedibean.ui.app.frontend.view.MainView;

@ParentLayout(MainView.class)
public class BodyContainer extends VerticalLayout implements RouterLayout {}
