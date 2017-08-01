package com.acuity.web.site;

import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;

/**
 * Created by Zach on 7/31/2017.
 */
public class AcuityServlet extends VaadinServlet {

    @Override
    protected final void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(new SessionListener());
    }

}
