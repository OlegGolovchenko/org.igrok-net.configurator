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
package org.igrok_net.configurator.interfaces;

import java.io.Serializable;

/**
 * Represents configuration value
 * @author Oleg Golovchenko
 * @version 0.0.3
 */
public interface ConfigurableValue extends Serializable {

    /**
     * Sets configuration value.
     * In default implementation accepts string and integer value.
     * @param value value to set
     */
    void setValue(Object value);

    /**
     * Retrives value as integer.
     * @return value as integer or null if underlying value is not int.
     */
    Integer asNumeric();

    /**
     * Retrives value as string.
     * @return value as string or null if underlying value is not string.
     */
    String asString();

    /**
     * Retrieves value as boolean.
     * @return value as boolean or null if underlying value is not a boolean.
     */
    Boolean asBoolean();

    /**
     * Checks if this value has specified key.
     * @param key key to check for.
     * @return true if key exactly matches, false otherwise.
     */
    boolean hasKey(String key);
}
