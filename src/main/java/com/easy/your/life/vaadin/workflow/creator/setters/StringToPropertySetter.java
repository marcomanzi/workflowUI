package com.easy.your.life.vaadin.workflow.creator.setters;

import java.lang.reflect.Method;

/**
 * Created by marcolin on 08/05/15.
 */
class StringToPropertySetter {
    private enum TYPE {
        String(java.lang.String.class),
        Integer(java.lang.Integer.class);

        private Class<?> typeClass;

        TYPE(Class<?> typeClass) {
            this.typeClass = typeClass;
        }

        public Object conversion(String value) {
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
        TYPE typeForExpression = TYPE.valueOf(methodValueType[1].split("-")[1]);
        try {
            Method method = bean.getClass().getMethod(methodValueType[0], typeForExpression.typeClass);
            method.invoke(bean, typeForExpression.conversion(methodValueType[1].split("-")[0]));
        } catch (Exception e) {
            System.out.println("Problems while coping properties on object");
        }
    }

}
