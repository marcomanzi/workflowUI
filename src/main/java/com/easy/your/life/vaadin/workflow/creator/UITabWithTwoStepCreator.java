package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.vaadin.workflow.creator.setters.ObjectPropertySetter;
import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.UserTask;

/**
 * Created by marcolin on 07/05/15.
 */
class UITabWithTwoStepCreator {
    private TabSheet tabSheet;
    private FlowElement processStart;
    private Bpmn20Reader bpmn20Reader;

    public void setTabSheet(TabSheet tabSheet) {
        this.tabSheet = tabSheet;
    }

    public void buildInterface(Bpmn20Reader bpmn20Reader) {
        this.bpmn20Reader = bpmn20Reader;
        processStart = this.bpmn20Reader.getProcessStart();
        if (processStart != null) {
            tabSheet.addTab(contentTabsheet(), processStart.getName());
        }
    }

    private Component contentTabsheet() {
        HorizontalLayout content = new HorizontalLayout();
        content.setWidth(100, Sizeable.Unit.PERCENTAGE);
        FlowElement firstStep = bpmn20Reader.getNextStepAfter(processStart);
        if (firstStep instanceof UserTask) {
            for (FormProperty formProperty : ((UserTask) firstStep).getFormProperties()) {
                switch (formProperty.getId()) {
                    case "type":
                        Component vaadinElement = convertToVaadinElement(firstStep.getName(), formProperty);
                        vaadinElement.setWidth(50, Sizeable.Unit.PERCENTAGE);
                        content.addComponent(vaadinElement);
                        break;
                }
            }
        }

        return content;
    }

    public enum VAADIN_ELEMENT {
        Table
    }

    private Component convertToVaadinElement(String name, FormProperty formProperty) {
        switch (VAADIN_ELEMENT.valueOf(formProperty.getName())) {
            default:
                Table table = new Table(name);
                applyPropertiesFromExpression(table, formProperty.getExpression());
                return table;
        }
    }

    private void applyPropertiesFromExpression(Object object, String expression) {
        ObjectPropertySetter.setParametersFromTo(expression, object);
    }
}
