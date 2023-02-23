package org.igrok_net.configurator;

import org.igrok_net.configurator.interfaces.ConfigurableValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ConfigurableValueTests {

    @Test
    public void stringShouldBeEqualWhenObjectsHaveSameValues() {
        ConfigurableValue first = new StringConfigurationValue("test", "test");
        ConfigurableValue second = new StringConfigurationValue("test", "test");
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void stringAndIntShouldNotBeEqual() {
        ConfigurableValue first = new StringConfigurationValue("test", "test");
        ConfigurableValue second = new IntConfigurationValue("test", 25);
        assertFalse(first.equals(second));
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void stringValueShouldBeAssignedFromString() {
        ConfigurableValue first = new StringConfigurationValue("test", "test");
        first.setValue("test2");
        ConfigurableValue second = new StringConfigurationValue("test", "test2");
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void intValueShouldBeAssignedFromInt() {
        ConfigurableValue first = new IntConfigurationValue("test", 24);
        first.setValue(25);
        ConfigurableValue second = new IntConfigurationValue("test", 25);
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }
}
