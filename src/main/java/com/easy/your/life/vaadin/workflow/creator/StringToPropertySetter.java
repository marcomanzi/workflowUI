package com.easy.your.life.vaadin.workflow.creator;

import java.lang.reflect.Method;

/**
 * Created by marcolin on 08/05/15.
 */
public class StringToPropertySetter {
    private enum TYPE {
        String(java.lang.String.class),
        Integer(java.lang.Integer.class);

        private Class<?> typeClass;

        TYPE(Class<?> typeClass) {
            this.typeClass = typeClass;
        }

        Object conversion(String value) {
            switch (this) {
                case Integer: return new Integer(value);
                default: return value;
            }
        }
    }

    public static void setParametersFromTo(String multiValueExpression, Object bean) {
        for (String oneValueExpression : multiValueExpression.split(";")) {
            setOneParameterFromTo(oneValueExpression, bean);
        }
    }

    private static void setOneParameterFromTo(String singleValueExpression, Object bean) {
        String[] methodValueType = singleValueExpression.split(":");
        TYPE typeForExpression = TYPE.valueOf(methodValueType[2]);
        try {
            Method method = bean.getClass().getMethod(methodValueType[0], typeForExpression.typeClass);
            method.invoke(bean, typeForExpression.conversion(methodValueType[1]));
        } catch (Exception e) {
            System.out.println("Problems while coping properties on object");
        }
    }

}
