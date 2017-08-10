package com.acuity.web.site.views.impl;

import com.acuity.db.services.impl.AcuityAccountService;
import com.acuity.web.site.utils.Utils;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class RegistrationView  extends VerticalLayout implements View {

    public RegistrationView() {
        setSizeFull();
        setMargin(false);
        setSpacing(false);

        Component registrationForm = buildRegistrationForm();
        addComponent(registrationForm);
        setComponentAlignment(registrationForm, Alignment.MIDDLE_CENTER);
    }

    private Component buildRegistrationForm() {
        final VerticalLayout content = new VerticalLayout();
        content.setSizeUndefined();
        content.setMargin(false);
        Responsive.makeResponsive(content);
        content.addComponent(buildFields());
        return content;
    }

    private Component buildFields() {
        VerticalLayout fields = new VerticalLayout();
        fields.addStyleName("fields");

        final TextField email = new TextField("Email");
        email.setIcon(VaadinIcons.MAILBOX);
        email.setRequiredIndicatorVisible(true);
        email.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        Utils.addValidator(email, new EmailValidator("Must be a valid email address."));

        final TextField displayName = new TextField("Username");
        displayName.setMaxLength(15);
        Utils.addValidator(displayName, new StringLengthValidator("Username must be between 3 and 15 letters.", 3, 15));
        displayName.setRequiredIndicatorVisible(true);
        displayName.setIcon(VaadinIcons.USER);
        displayName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField("Password");
        Utils.addValidator(password, new StringLengthValidator("Password must be between 8 and 25 letters.", 8, 25));
        password.setIcon(VaadinIcons.LOCK);
        password.setRequiredIndicatorVisible(true);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField passwordConfirmation = new PasswordField("Confirm Password");
        passwordConfirmation.setIcon(VaadinIcons.LOCK);
        passwordConfirmation.setRequiredIndicatorVisible(true);
        passwordConfirmation.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button("Register");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        fields.addComponents(email, displayName, password, passwordConfirmation, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(clickEvent -> {
            if (isValid(email) && isValid(password) && isValid(displayName) && password.getValue().equals(passwordConfirmation.getValue())){
                try {
                    AcuityAccountService.getInstance().registerAccount(email.getValue(), displayName.getValue(), password.getValue()).ifPresent(acuityAccount -> {
                        getUI().getNavigator().navigateTo(com.acuity.web.site.views.View.LOGIN.getName());
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Notification.show("Check field errors.", Notification.Type.TRAY_NOTIFICATION);
            }
        });
        return fields;
    }

    private boolean isValid(TextField textField){
        return !textField.isEmpty() && textField.getComponentError() == null;
    }
}
