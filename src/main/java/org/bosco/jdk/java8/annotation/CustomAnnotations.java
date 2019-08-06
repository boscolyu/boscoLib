package org.bosco.jdk.java8.annotation;

import java.lang.reflect.Field;

public class CustomAnnotations {

    public static String toString(Object obj) {
        if (obj == null) return "null";
        Class<?> cl = obj.getClass();
        CustomAnnotation cs = cl.getAnnotation(CustomAnnotation.class);
        if (cs == null) return obj.toString();
        StringBuilder result = new StringBuilder();
        if (cs.includeName()) result.append(cl.getName());
        result.append("[");
        boolean first = true;
        for (Field f : cl.getDeclaredFields()) {
            cs = f.getAnnotation(CustomAnnotation.class);
            if (cs != null) {
                if (first) first = false; else result.append(",");
                f.setAccessible(true);

                if (cs.includeName()) {
                    result.append(f.getName());
                    result.append("=");
                }

                try {
                    result.append(CustomAnnotations.toString(f.get(obj)));
                } catch (ReflectiveOperationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        result.append("]");
        return result.toString();

    }
}
