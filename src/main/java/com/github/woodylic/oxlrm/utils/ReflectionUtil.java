package com.github.woodylic.oxlrm.utils;

import com.github.woodylic.oxlrm.annotations.Label;
import com.github.woodylic.oxlrm.annotations.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * 返回entityType对应的Range名。
     * 如果Entity类型有RangeAnnotation，用annotation.name作为Range；
     * 否则用class.simpleName作为Range。
     *
     * @param entityType
     * @return
     */
    public static String getRangeName(Class entityType) {
        Annotation annotation = entityType.getAnnotation(Range.class);
        if (annotation != null) {
            return ((Range) annotation).name();
        } else {
            return entityType.getSimpleName();
        }
    }

    /**
     * 返回一个字典，包含entityType的每个字段名，以及在excel里对应的label。
     * 如果field有LabelAnnotation，用annotation.name作为label；
     * 否则以field.name作为label。
     *
     * @param entityType
     * @return
     */
    public static Map<String, String> getLabelsNames(Class entityType) {
        Field[] fields = entityType.getDeclaredFields();
        HashMap<String, String> labelsNames = new HashMap<>();

        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Label.class);

            if (annotation != null) {
                labelsNames.put(field.getName(), ((Label) annotation).name());
            } else {
                labelsNames.put(field.getName(), field.getName());
            }
        }

        return labelsNames;
    }
}
