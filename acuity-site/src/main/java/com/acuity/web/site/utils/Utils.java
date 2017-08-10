package com.acuity.web.site.utils;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractField;

/**
 * Created by Zachary Herridge on 8/9/2017.
 */
public class Utils {

    @SuppressWarnings("unchecked")
    public static void addValidator(AbstractField field, Validator validator) {
        field.addValueChangeListener(event -> {
            ValidationResult result = validator.apply(event.getValue(), new ValueContext(field));
            if (result.isError()) {
                UserError error = new UserError(result.getErrorMessage());
                field.setComponentError(error);
            } else {
                field.setComponentError(null);
            }
        });
    }
}
