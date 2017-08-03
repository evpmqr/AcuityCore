package com.acuity.web.site;

import com.acuity.db.AcuityDB;
import com.acuity.web.site.events.Events;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Zach on 7/31/2017.
 */


@WebServlet(urlPatterns = "/*", name = "RESTDemoUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = DashboardUI.class, productionMode = false)
public class DashboardServlet extends VaadinServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        AcuityDB.init();
        Events.start();
    }

    @Override
    public void destroy() {
        super.destroy();
        AcuityDB.stop();
        Events.stop();
    }
}
