package com.acuity.web.site;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Zach on 7/31/2017.
 */


@WebServlet(urlPatterns = "/*", name = "RESTDemoUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = AcuityUI.class, productionMode = false)
public class AcuityServlet extends VaadinServlet {

}
