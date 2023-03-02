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
 * Represents boolean configuration value
 * @author Oleg Golovchenko
 * @version 0.0.3
 */
class BooleanConfigurationValue implements ConfigurableValue {

    private String name;
    private boolean value;

    private static final long serialVersionUID = 1L;

    /**
     * Constructs new instace with given name and value.
     * @param name name of configuration value
     * @param value
     */
    public BooleanConfigurationValue(String name, boolean value) {
        super();
        this.name = name;
        this.value = value;
    }    
    
    /**
     * Checks if value can be assigned to this instance.
     * @param value configuration value.
     * @return true if can be assigned, false otherwise.
     */
    private static boolean canBeAssignedFromValue(Object value) {
        return value != null && value instanceof Boolean;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof BooleanConfigurationValue))
            return false;
        BooleanConfigurationValue otherValue = (BooleanConfigurationValue) other;
        if (!otherValue.name.matches(this.name) || !otherValue.value == this.value)
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
            this.value = (Boolean) value;
    }

    @Override
    public Integer asNumeric() {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public boolean hasKey(String key) {
       return this.name.matches(key);
    }

    @Override
    public Boolean asBoolean() {
        return this.value;
    }

}
