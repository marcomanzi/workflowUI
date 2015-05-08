package com.easy.your.life.vaadin.workflow.creator;

import com.easy.your.life.vaadin.workflow.creator.setters.ObjectPropertySetter;
import com.vaadin.ui.Table;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by marcolin on 08/05/15.
 */
public class StringToPropertySetterTest {

    @Test
    public void testSetPropertyOnSimpleBean() {
        SimpleBean bean = new SimpleBean();
        String simpleValue = "setTestStringParameter:value-String";
        ObjectPropertySetter.setParametersFromTo(simpleValue, bean);
        assertEquals("value", bean.getTestStringParameter());
    }

    @Test
    public void testSetTwoPropertiesOnSimpleBean() {
        SimpleBean bean = new SimpleBean();
        String twoValues = "setTestStringParameter:value-String;setTestIntegerParameter:1-Integer";
        ObjectPropertySetter.setParametersFromTo(twoValues, bean);
        assertEquals("value", bean.getTestStringParameter());
        assertEquals(new Integer(1), bean.getTestIntegerParameter());
    }

    @Test
    public void testSetContainersOnTable() {
        Table bean = new Table();
        String twoValues = "addContainerProperty:Id-String;addContainerProperty:Username-String";
        ObjectPropertySetter.setParametersFromTo(twoValues, bean);
        assertEquals(2, bean.getContainerPropertyIds().size());
    }


    public class SimpleBean {
        private String testStringParameter;
        private Integer testIntegerParameter;

        public String getTestStringParameter() {
            return testStringParameter;
        }

        public void setTestStringParameter(String testStringParameter) {
            this.testStringParameter = testStringParameter;
        }

        public Integer getTestIntegerParameter() {
            return testIntegerParameter;
        }

        public void setTestIntegerParameter(Integer testIntegerParameter) {
            this.testIntegerParameter = testIntegerParameter;
        }

    }

}
