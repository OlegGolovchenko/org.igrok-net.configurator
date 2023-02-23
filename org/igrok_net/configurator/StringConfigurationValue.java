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

import java.util.Objects;

import org.igrok_net.configurator.interfaces.ConfigurableValue;

/**
 * Represents string configuration value
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
class StringConfigurationValue implements ConfigurableValue {
    private String name;
    private String value;
    
    /**
     * Constructs new instace with given name and value.
     * @param name name of configuration value
     * @param value 
     */
    public StringConfigurationValue(String name, String value) {
        super();
        this.name = name;
        this.setValue(value);
    }

    /**
     * Checks if value can be assigned to this instance.
     * @param value configuration value.
     * @return true if can be assigned, false otherwise.
     */
    private static boolean canBeAssignedFromValue(Object value) {
        return value != null && value instanceof String;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof StringConfigurationValue))
            return false;
        StringConfigurationValue otherValue = (StringConfigurationValue) other;
        if (!otherValue.name.matches(this.name) || !otherValue.value.matches(this.value))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.value);
    }

    @Override
    public String toString() {
        return "[" + this.name + " (" + this.value + ")]";
    }

    @Override
    public void setValue(Object value) {
        if (canBeAssignedFromValue(value))
            this.value = (String) value;
    }

    @Override
    public Integer asNumeric() {
        return null;
    }

    @Override
    public String asString() {
        return this.value;
    }

    @Override
    public boolean hasKey(String key) {
       return this.name.matches(key);
    }
}