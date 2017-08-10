package com.acuity.web.site.views.impl.dashboard.landing;

import com.acuity.web.site.components.InlineLabel;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * Created by Eclipseop.
 * Date: 8/9/2017.
 */
public class LandingView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		buildComponent();
	}

	private void buildComponent() {
		setSizeFull();
		setMargin(false);
		setSpacing(false);
		addStyleName("view");

		Panel mainPanel = createMainPanel();
		addComponent(mainPanel);
		setComponentAlignment(mainPanel, Alignment.MIDDLE_CENTER);
	}

	private Panel createMainPanel() {
		final Panel panel = new Panel("<strong>Why Acuity?</strong>");
		panel.setSizeUndefined();

		final GridLayout content = new GridLayout(3, 2);
		content.setResponsive(true);
		content.setSpacing(true);
		content.addStyleName("view-top");

		content.addComponent(new InlineLabel("<strong>Bot Control Done Right</strong>", VaadinIcons.AUTOMATION));
		content.addComponent(new InlineLabel("<strong>Security Up the Wazoo</strong>", VaadinIcons.KEY));
		content.addComponent(new InlineLabel("<strong>Made with ❤</strong>", VaadinIcons.CURLY_BRACKETS));

		final Label feature1 = new Label("Acuity takes out all of the headache that occurs when creating a large scale bot farm. With our sleek design and intuitive api, even users that are relativly new to the botting scene will be able to create farms with ease.");
		feature1.setWidth(300, Unit.PIXELS);
		content.addComponent(feature1);

		final Label feature2 = new Label("Every password stored in the database is encrypted with 128-bit AES encryption, also used by the U.S. government. Because of this, your passwords are never stored as plain-text, instead, the password hash in stored, making it so not even Acuity developers will be able to decipher your password.");
		feature2.setWidth(300, Unit.PIXELS);
		content.addComponent(feature2);

		final Label feature3 = new Label("Although mostly maintained by a small group of developers under the Acuity team, the Acuity project is open-source. This helps to ensure our users that there is no malicious code anywhere to be found in the project. Having an open-source project also allows fellow developers to contribute to the project, assisting in making Acuity the number one botting platform.");
		feature3.setWidth(300, Unit.PIXELS);
		content.addComponent(feature3);

		panel.setContent(content);

		return panel;
	}
}
