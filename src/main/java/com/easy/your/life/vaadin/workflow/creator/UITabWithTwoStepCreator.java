package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.ui.TabSheet;
import org.activiti.bpmn.model.FlowElement;

/**
 * Created by marcolin on 07/05/15.
 */
class UITabWithTwoStepCreator {
    private TabSheet tabSheet;

    public void setTabSheet(TabSheet tabSheet) {
        this.tabSheet = tabSheet;
    }

    public void buildInterface(Bpmn20Reader bpmn20Reader) {
        FlowElement processStart = bpmn20Reader.getProcessStart();
        if (processStart != null) {
            tabSheet.addTab(new TabSheet(), processStart.getName());
        }
    }
}
