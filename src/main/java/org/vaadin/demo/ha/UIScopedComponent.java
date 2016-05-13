package org.vaadin.demo.ha;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.demo.ha.backend.MySessionScopedStatefulBean;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@UIScoped
public class UIScopedComponent extends VerticalLayout {

    @Inject
    MySessionScopedStatefulBean mySessionScopedStatefulBean;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Button("Click me too!", evt -> addComponent(new Label(mySessionScopedStatefulBean.getLastInstant()))));
    }
}
