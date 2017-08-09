package com.acuity.web.site.views.impl.dashboard.rs.account;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.services.impl.AcuityAccountService;
import com.acuity.db.services.impl.RSAccountService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.UUID;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class AddRSAccountForm extends FormLayout {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    private TextField email = new TextField("Email");
    private TextField ign = new TextField("IGN");
    private PasswordField acuityPassword = new PasswordField("Acuity Password");
    private PasswordField password = new PasswordField("RS-Password");

    public AddRSAccountForm(Window window) {
        setId(UUID.randomUUID().toString());

        email.setIcon(VaadinIcons.MAILBOX);
        email.setRequiredIndicatorVisible(true);
        addComponent(email);


        ign.setIcon(VaadinIcons.USER);
        addComponent(ign);

        acuityPassword.setIcon(VaadinIcons.CAMERA);
        acuityPassword.setRequiredIndicatorVisible(true);
        addComponent(acuityPassword);

        password.setIcon(VaadinIcons.PASSWORD);
        password.setRequiredIndicatorVisible(true);
        addComponent(password);

        addComponent(new Button("Add", clickEvent -> {
            try {
                if (AcuityAccountService.getInstance().checkLogin(acuityAccount.getEmail(), acuityPassword.getValue()).isPresent()){
                    RSAccountService.getInstance().addRSAccount(acuityAccount.getID(), email.getValue(), ign.getValue(), password.getValue(), acuityPassword.getValue(), acuityAccount.getRsAccountEIV(), acuityAccount.getRsAccountEKey());
                }
                else {
                    Notification.show("Incorrect Acuity Login.", Notification.Type.TRAY_NOTIFICATION);
                }

                if (window != null) window.close();
            } catch (Exception e) {
                e.printStackTrace();
                Notification.show("Failed to create account try again later.", Notification.Type.TRAY_NOTIFICATION);
            }
        }));

        setResponsive(true);
    }
}
