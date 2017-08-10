package com.acuity.web.site.views.impl.dashboard.proxy;

import com.acuity.db.domain.common.EncryptedString;
import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.Proxy;
import com.acuity.db.services.impl.AcuityAccountService;
import com.acuity.db.services.impl.ProxyService;
import com.acuity.security.AcuityEncryption;
import com.google.common.base.Strings;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * Created by Zachary Herridge on 8/10/2017.
 */
public class AddProxyForm extends FormLayout {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private Window window;

    private TextField host = new TextField("Host");
    private TextField port = new TextField("Port");
    private TextField username = new TextField("Username");
    private PasswordField password = new PasswordField("Password");
    private PasswordField acuityPassword = new PasswordField("Acuity Password");

    public AddProxyForm(Window window) {
        this.window = window;
        buildComponent();
    }

    private void buildComponent(){
        setSizeUndefined();
        setResponsive(true);
        addFields();
        addCreateButton();
    }

    private void addCreateButton() {
        Button create = new Button("Create");
        create.addClickListener(clickEvent -> {

            if (AcuityAccountService.getInstance().checkLoginByID(acuityAccount.getID(), acuityPassword.getValue()).isPresent()){
                try {
                    EncryptedString encryptedString = null;
                    if (!password.getValue().isEmpty()){
                        encryptedString = AcuityEncryption.encryptString(password.getValue(), acuityPassword.getValue(), acuityAccount.getPasswordEncryptionKey());
                    }
                    Proxy proxy = new Proxy(acuityAccount.getID(), host.getValue(), Integer.parseInt(port.getValue()), username.getValue(), encryptedString);
                    ProxyService.getInstance().insert(proxy);
                    if (window != null) window.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        create.setIcon(VaadinIcons.PLUS_CIRCLE);
        addComponent(create);
    }

    private void addFields(){
        host.setIcon(VaadinIcons.SERVER);
        host.setRequiredIndicatorVisible(true);
        addComponent(host);

        port.setIcon(VaadinIcons.CONNECT);
        port.setRequiredIndicatorVisible(true);
        addComponent(port);

        username.setIcon(VaadinIcons.USER);
        username.setRequiredIndicatorVisible(true);
        addComponent(username);

        password.setIcon(VaadinIcons.PASSWORD);
        password.setRequiredIndicatorVisible(true);
        addComponent(password);

        acuityPassword.setIcon(VaadinIcons.CAMERA);
        acuityPassword.setRequiredIndicatorVisible(true);
        acuityPassword.setValue(Strings.nullToEmpty((String) VaadinSession.getCurrent().getAttribute("passwordStore")));
        addComponent(acuityPassword);
    }
}
