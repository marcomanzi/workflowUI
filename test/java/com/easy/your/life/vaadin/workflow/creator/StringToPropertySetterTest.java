package com.easy.your.life.vaadin.workflow.creator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by marcolin on 08/05/15.
 */
public class StringToPropertySetterTest {

    @Test
    public void testSetPropertyOnSimpleBean() {
        SimpleBean bean = new SimpleBean();
        String simpleValue = "setTestStringParameter:value:String";
        StringToPropertySetter.setParametersFromTo(simpleValue, bean);
        assertEquals("value", bean.getTestStringParameter());
    }

    @Test
    public void testSetTwoPropertiesOnSimpleBean() {
        SimpleBean bean = new SimpleBean();
        String twoValues = "setTestStringParameter:value:String;setTestIntegerParameter:1:Integer";
        StringToPropertySetter.setParametersFromTo(twoValues, bean);
        assertEquals("value", bean.getTestStringParameter());
        assertEquals(new Integer(1), bean.getTestIntegerParameter());
    }

    class SimpleBean {
        private String testStringParameter;
        private Float testFloadParameter;
        private Integer testIntegerParameter;
        private Boolean testBooleanParameter;

        public String getTestStringParameter() {
            return testStringParameter;
        }

        public void setTestStringParameter(String testStringParameter) {
            this.testStringParameter = testStringParameter;
        }

        public Float getTestFloadParameter() {
            return testFloadParameter;
        }

        public void setTestFloadParameter(Float testFloadParameter) {
            this.testFloadParameter = testFloadParameter;
        }

        public Integer getTestIntegerParameter() {
            return testIntegerParameter;
        }

        public void setTestIntegerParameter(Integer testIntegerParameter) {
            this.testIntegerParameter = testIntegerParameter;
        }

        public Boolean getTestBooleanParameter() {
            return testBooleanParameter;
        }

        public void setTestBooleanParameter(Boolean testBooleanParameter) {
            this.testBooleanParameter = testBooleanParameter;
        }
    }

}
