package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.workflow.reader.Bpmn20Reader;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by marcolin on 07/05/15.
 */
public class UITabWithTwoStepCreatorTest {

    @Test
    public void testTabIsAdded() {
        assertNotNull(createTestUsersUI().getTab(0));
    }

    @Test
    public void testTabNameIsCorrect() {
        assertEquals("Users", createTestUsersUI().getTab(0).getCaption());
    }

    @Test
    public void testContentInTabIsHorizzontal() {
        assertTrue(createTestUsersUI().getTab(0).getComponent() instanceof HorizontalLayout);
    }

    @Test
    public void testLeftComponentInContentIsTable() {
        assertTrue(((HorizontalLayout)createTestUsersUI().getTab(0).getComponent()).getComponent(0) instanceof Table);
    }

    @Test
    public void testContainerPropertiesAreSetOnTable() {
        Table table = (Table) ((HorizontalLayout) createTestUsersUI().getTab(0).getComponent()).getComponent(0);
        assertEquals(2, table.getContainerPropertyIds().size());
    }



    private TabSheet createTestUsersUI() {
        UIBuilder uiBuilder = new UIBuilder(UIBuilder.UI_TYPE.TAB_WITH_TWO_STEP, new Bpmn20Reader(inputStreamFor("user-tabsheet.bpmn20.xml")));
        TabSheet tabSheet = new TabSheet();
        uiBuilder.setTabSheet(tabSheet);
        uiBuilder.buildInterface();
        return tabSheet;
    }

    private static InputStream inputStreamFor(String fileName) {
        return UITabWithTwoStepCreatorTest.class.getClassLoader().getResourceAsStream(fileName);
    }
}
