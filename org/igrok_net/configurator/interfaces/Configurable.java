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
import java.util.List;

/**
 * Represents configuration
 * 
 * @author Oleg Golovchenko
 * @version 0.0.3
 */
public interface Configurable extends Serializable {

    /**
     * Assigns string configuration value.
     * 
     * @param name  value key.
     * @param value value.
     */
    void assignConfigValue(String name, String value);

    /**
     * Assigns integer configuration value.
     * 
     * @param name  value key.
     * @param value value.
     */
    void assignConfigValue(String name, int value);

    /**
     * Assigns boolean configuration value.
     * 
     * @param name  value key.
     * @param value value.
     */
    void assignConfigValue(String name, boolean value);

    /**
     * Retrieves value for given key.
     * 
     * @param key key to search for.
     * @return value if found or null.
     */
    ConfigurableValue retrieveValue(String key);

    /**
     * Lists all values
     * 
     * @return list of values or empty list if failed
     */
    List<ConfigurableValue> listValues();
}