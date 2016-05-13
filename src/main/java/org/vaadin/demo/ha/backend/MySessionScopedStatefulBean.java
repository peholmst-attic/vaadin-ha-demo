package org.vaadin.demo.ha.backend;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Instant;

@SessionScoped
@Stateful
public class MySessionScopedStatefulBean implements Serializable {

    private String lastInstant = "This is the first instant: " + Instant.now();

    public String getLastInstant() {
        String s = "Updated instant: " + Instant.now();
        String m = s + "(last one was " + lastInstant + ")";
        lastInstant = s;
        return m;
    }
}
