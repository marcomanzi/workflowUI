package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.vaadin.workflow.creator.setters.ObjectPropertySetter;
import com.easy.your.life.workflow.reader.Bpmn20Reader;
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

    //<activiti:formProperty id="type" name="Table" type="string"
    //  expression="&quot;addContainerProperty([Id:Integer,Username:String])&quot;" variable="properties">
    // </activiti:formProperty>

    private Component contentTabsheet() {
        HorizontalLayout content = new HorizontalLayout();
        FlowElement firstStep = bpmn20Reader.getNextStepAfter(processStart);
        if (firstStep instanceof UserTask) {
            for (FormProperty formProperty : ((UserTask) firstStep).getFormProperties()) {
                switch (formProperty.getId()) {
                    case "type":
                        content.addComponent(convertToVaadinElement(firstStep.getName(), formProperty));
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
