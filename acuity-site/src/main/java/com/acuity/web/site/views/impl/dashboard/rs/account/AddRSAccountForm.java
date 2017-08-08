package com.acuity.web.site.views.impl.dashboard.rs.account;

import com.acuity.db.domain.vertex.impl.RSAccount;
import com.acuity.db.services.impl.RSAccountService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.util.UUID;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class AddRSAccountForm extends FormLayout {

    private TextField email = new TextField("Email");
    private TextField ign = new TextField("IGN");
    private PasswordField password = new PasswordField("RS-Password");

    public AddRSAccountForm(String acuityID, Window window) {
        setId(UUID.randomUUID().toString());

        email.setIcon(VaadinIcons.MAILBOX);
        email.setRequiredIndicatorVisible(true);
        addComponent(email);


        ign.setIcon(VaadinIcons.USER);
        addComponent(ign);

        PasswordField password = new PasswordField("Password");
        password.setIcon(VaadinIcons.PASSWORD);
        password.setRequiredIndicatorVisible(true);
        addComponent(password);

        addComponent(new Button("Add", clickEvent -> {
            RSAccount rsAccount = new RSAccount(acuityID, getEmail(), getIGN(), getPassword());
            RSAccountService.getInstance().insert(rsAccount);
            if (window != null) window.close();
        }));
    }

    public String getEmail(){
        return email.getValue();
    }

    public String getIGN(){
        return ign.getValue();
    }

    public String getPassword(){
        return password.getValue();
    }
}
