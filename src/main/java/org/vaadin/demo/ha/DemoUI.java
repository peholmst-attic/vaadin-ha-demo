package org.vaadin.demo.ha;

import com.vaadin.cdi.CDIUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.vaadin.demo.ha.backend.MyBackendBean;
import org.vaadin.demo.ha.backend.MyEntity;

import javax.inject.Inject;
import java.time.Instant;

//@Push(transport = Transport.LONG_POLLING) // Apache load balancer currently does not support push
@CDIUI
public class DemoUI extends UI {

    @Inject
    MyBackendBean myBackendBean;

    @Inject
    UIScopedComponent uiScopedComponent;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalSplitPanel root = new HorizontalSplitPanel();
        root.setSizeFull();
        setContent(root);

        BeanItemContainer<MyEntity> container = new BeanItemContainer<>(MyEntity.class);

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(new Label("Hello, the session ID is " + vaadinRequest.getWrappedSession().getId() + " and the UI was initialized at " + Instant
            .now()));
        layout.addComponent(new Table("Entities", container));
        layout.addComponent(new Button("Click me!", evt -> layout.addComponent(new Label(myBackendBean.echo("This label was added at " + Instant.now())))));
        layout.addComponent(new Button("Load from db", evt -> {
            container.removeAllItems();
            container.addAll(myBackendBean.findEntities());
        }));

        root.setFirstComponent(layout);
        root.setSecondComponent(uiScopedComponent);
    }
}
