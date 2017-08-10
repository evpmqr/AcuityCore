package com.acuity.web.site.views.impl.dashboard.modals;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.services.impl.AcuityAccountService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class ConfirmPasswordModal extends FormLayout {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);

    public ConfirmPasswordModal() {
        PasswordField passwordField = new PasswordField("Password");
        addComponent(passwordField);
        Button confirm = new Button("Confirm");
        addComponent(confirm);

        confirm.addClickListener(clickEvent -> {
            AcuityAccount acuityAccount = AcuityAccountService.getInstance().checkLoginByID(this.acuityAccount.getID(), passwordField.getValue()).orElse(null);
            onConfirm(passwordField.getValue(), acuityAccount);
        });
    }

    public void onConfirm(String password, AcuityAccount acuityAccount) {

    }
}
