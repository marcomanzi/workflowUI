package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.ui.TabSheet;

/**
 * Created by marcolin on 07/05/15.
 */
public class UIBuilder {

    private UITabWithTwoStepCreator creator;
    private Bpmn20Reader bpmn20Reader;

    public void buildInterface() {
        creator.buildInterface(bpmn20Reader);
    }

    public enum UI_TYPE {TAB_WITH_TWO_STEP}

    public UIBuilder(UI_TYPE uiType, Bpmn20Reader bpmn20Reader) {
        switch (uiType) {
            case TAB_WITH_TWO_STEP:
                creator = new UITabWithTwoStepCreator();
                break;
        }
        this.bpmn20Reader = bpmn20Reader;
    }

    public void setTabSheet(TabSheet tabSheet) {
        creator.setTabSheet(tabSheet);
    }


}
