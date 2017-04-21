package com.github.woodylic.oxlrm.unittests.utils;

import com.github.woodylic.oxlrm.utils.ReflectionUtil;
import com.github.woodylic.oxlrm.unittests.entities.EntityWithFields;
import com.github.woodylic.oxlrm.unittests.entities.EntityWithRange;
import com.github.woodylic.oxlrm.unittests.entities.EntityWithoutRange;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ReflectionUtilTest {

    @Test
    public void testGetRangeName() {

        String nameByRange = ReflectionUtil.getRangeName(EntityWithRange.class);
        assertEquals("Some_Range_Name", nameByRange);

        String nameByClass = ReflectionUtil.getRangeName(EntityWithoutRange.class);
        assertEquals("EntityWithoutRange", nameByClass);
    }

    @Test
    public void testGetLabelsNames() {
        Map<String, String> labelsNames = ReflectionUtil.getLabelsNames(EntityWithFields.class);

        assertEquals(2, labelsNames.size());

        assertTrue(labelsNames.containsKey("name"));
        assertEquals("name", labelsNames.get("name"));

        assertTrue(labelsNames.containsKey("password"));
        assertEquals("passwd", labelsNames.get("password"));
    }

}