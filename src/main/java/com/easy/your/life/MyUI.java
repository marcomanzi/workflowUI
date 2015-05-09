package com.easy.your.life;

import javax.servlet.annotation.WebServlet;

import com.easy.your.life.vaadin.workflow.creator.UIBuilder;
import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import java.io.InputStream;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.easy.your.life.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Button button = new Button("Click Me 2");
        button.addClickListener(e -> layout.addComponent(new Label("Thank you for clicking")));
        layout.addComponent(button);

        UIBuilder uiBuilder = new UIBuilder(UIBuilder.UI_TYPE.TAB_WITH_TWO_STEP, new Bpmn20Reader(inputStreamFor("user-tabsheet.bpmn20.xml")));
        TabSheet tabSheet = new TabSheet();
        uiBuilder.setTabSheet(tabSheet);
        uiBuilder.buildInterface();
        layout.addComponent(tabSheet);
    }

    private static InputStream inputStreamFor(String fileName) {
        return MyUI.class.getClassLoader().getResourceAsStream(fileName);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
