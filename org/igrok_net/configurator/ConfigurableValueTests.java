/*
 *  IgRok-NET configuration management utility
 *  Copyright (C) 2023  Oleg Golovchenko
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.igrok_net.configurator;

import org.igrok_net.configurator.interfaces.ConfigurableValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    @Test
    public void valueShouldBeRetrievedAsCorrectTypeOrNull() {
        ConfigurableValue first = new StringConfigurationValue("test", "test");
        ConfigurableValue second = new IntConfigurationValue("test", 25);
        assertNull(first.asNumeric());
        assertNull(second.asString());
        assertNotNull(first.asString());
        assertNotNull(second.asNumeric());
    }

    @Test
    public void hasKeyShouldReturnTrueForAssignedValueName() {
        ConfigurableValue first = new StringConfigurationValue("test", "test");
        assertTrue(first.hasKey("test"));
    }
}
