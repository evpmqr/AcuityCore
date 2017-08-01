package com.acuity.web.site.view.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Zachary Herridge on 8/1/2017.
 */

public class AccountsView extends VerticalLayout implements View{

    public AccountsView() {
        Label countingLbl = new Label("Count = 0");
        addComponent(countingLbl);

        UI current = UI.getCurrent();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                current.access(() -> countingLbl.setValue("Count = " + finalI));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
