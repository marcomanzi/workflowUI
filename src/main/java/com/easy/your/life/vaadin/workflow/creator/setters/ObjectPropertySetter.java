package com.easy.your.life.vaadin.workflow.creator.setters;

import com.vaadin.ui.Table;

/**
 * Created by marcolin on 08/05/15.
 */
public class ObjectPropertySetter {
    public static void setParametersFromTo(String multiValueExpression, Object bean) {
        StringToPropertySetter.setParametersFromTo(multiValueExpression, bean);
    }

    public static void setParametersFromTo(String multiValueExpression, Table table) {
        TableToPropertySetter.setParametersFromTo(multiValueExpression, table);
    }
}
