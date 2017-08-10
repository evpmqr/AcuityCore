package com.acuity.web.site.views.impl.dashboard.script;

import com.acuity.db.domain.vertex.impl.AcuityAccount;
import com.acuity.db.domain.vertex.impl.scripts.Script;
import com.acuity.db.services.impl.ScriptService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.Arrays;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class AddScriptView extends FormLayout implements View {

    private AcuityAccount acuityAccount = VaadinSession.getCurrent().getAttribute(AcuityAccount.class);
    private Binder<Script> scriptBinder = new Binder<>();

    private TextField title = new TextField("Title");
    private ComboBox<String> category = new ComboBox<>("Category", Arrays.asList("Other"));
    private TextArea description = new TextArea("Description");
    private Button addScript = new Button("Add Script");

    public AddScriptView() {
        buildComponent();
    }

    private void buildComponent(){
        addStyleName("view");
        setSizeFull();
        setResponsive(true);
        addFields();
        addAddScriptButton();
        addValidators();
    }

    private void addAddScriptButton(){
        addScript.addClickListener(clickEvent -> {
            try {
                Script script = new Script();
                scriptBinder.writeBean(script);
                script.setOwnerID(acuityAccount.getID());
                String key = ScriptService.getInstance().getCollection().insertDocument(script).getKey();
                getUI().getNavigator().navigateTo(com.acuity.web.site.views.View.SCRIPT.getName() + "/" + key);
            } catch (ValidationException e) {
                Notification.show("Script could not be saved, please check error messages for each field.");
            }
        });

        addScript.setIcon(VaadinIcons.CLOUD_UPLOAD);
        addComponent(addScript);
    }

    private void addFields(){
        title.setRequiredIndicatorVisible(true);
        title.setIcon(VaadinIcons.TEXT_LABEL);
        addComponent(title);

        category.setRequiredIndicatorVisible(true);
        category.setIcon(VaadinIcons.FILE_TREE_SMALL);
        addComponent(category);

        description.setIcon(VaadinIcons.FILE_TEXT);
        description.setRequiredIndicatorVisible(true);
        addComponent(description);
    }

    private void addValidators(){
        scriptBinder.forField(description)
                .asRequired("Must have a description.")
                .withValidator(new StringLengthValidator("Description must be between 25 and 250 letters.", 25, 250))
                .bind(Script::getDesc, Script::setDesc);
        scriptBinder.forField(category)
                .asRequired("Must select a category")
                .bind(Script::getCategory, Script::setCategory);
        scriptBinder.forField(title)
                .asRequired("Must have a title")
                .withValidator(new StringLengthValidator("Title must be between 5 and 30 letters.", 5, 30))
                .bind(Script::getTitle, Script::setTitle);
    }

}
