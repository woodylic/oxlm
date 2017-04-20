package com.github.woodylic.oxlrm.utils;

import com.github.woodylic.oxlrm.annotations.Label;
import com.github.woodylic.oxlrm.annotations.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    private ReflectionUtil(){}

    public static String getRangeName(Class entityType) {
        String rangeName = null;
        Annotation annotation = entityType.getAnnotation(Range.class);
        if(annotation != null) {
            //如果Entity类型有RangeAnnotation，用annotation.name作为Range。
            rangeName = ((Range)annotation).name();
        } else {
            //否则用class.simpleName作为Range。
            rangeName = entityType.getSimpleName();
        }
        return rangeName;
    }

    public static List<String> getLabelsNames(Class entityType) {
        Field[] fields = entityType.getFields();
        List<String> labels = new ArrayList<String>(fields.length);

        for(Field field : fields) {
            String fieldName = null;
            Annotation annotation = field.getAnnotation(Label.class);
            if(annotation != null) {
                //如果有Annotation，用annotation.name作为header。
                fieldName = ((Label)annotation).name();
            } else {
                //否则用class.simpleName作为Range。
                fieldName = field.getName();
            }
            labels.add(fieldName);
        }

        return labels;
    }
}
