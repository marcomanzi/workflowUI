package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.ui.TabSheet;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by marcolin on 07/05/15.
 */
public class UITabWithTwoStepCreatorTest {

    @Test
    public void testTabIsAdded() {
        UIBuilder uiBuilder = new UIBuilder(UIBuilder.UI_TYPE.TAB_WITH_TWO_STEP, new Bpmn20Reader(inputStreamFor("start-step-end.bpmn20.xml")));
        TabSheet tabSheet = new TabSheet();
        uiBuilder.setTabSheet(tabSheet);
        uiBuilder.buildInterface();
        assertNotNull(tabSheet.getTab(0));
    }


    private static InputStream inputStreamFor(String fileName) {
        return UITabWithTwoStepCreatorTest.class.getClassLoader().getResourceAsStream(fileName);
    }
}
