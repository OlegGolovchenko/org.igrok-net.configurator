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

import java.util.ArrayList;
import java.util.List;

import org.igrok_net.configurator.interfaces.Configurable;
import org.igrok_net.configurator.interfaces.ConfigurableValue;

/**
 * Default implementation of configuration
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
class Configuration implements Configurable {

    private List<ConfigurableValue> configurationValues;

    /**
     * Creates new configuration instance
     */
    public Configuration() {
        super();
        this.configurationValues = new ArrayList<ConfigurableValue>();
    }

    @Override
    public void assignConfigValue(String name, String value) {
        ConfigurableValue cfgValue = new StringConfigurationValue(name, value);
        if (this.configurationValues.contains(cfgValue)) {
            int valueIndex = this.configurationValues.indexOf(cfgValue);
            this.configurationValues.get(valueIndex).setValue(value);
        } else {
            this.configurationValues.add(cfgValue);
        }
    }

    @Override
    public void assignConfigValue(String name, int value) {
        ConfigurableValue cfgValue = new IntConfigurationValue(name, value);
        if (this.configurationValues.contains(cfgValue)) {
            int valueIndex = this.configurationValues.indexOf(cfgValue);
            this.configurationValues.get(valueIndex).setValue(value);
        } else {
            this.configurationValues.add(cfgValue);
        }
    }

    @Override
    public ConfigurableValue retrieveValue(String key) {
        for (ConfigurableValue configurableValue : configurationValues) {
            if(configurableValue.hasKey(key))
                return configurableValue;
        }
        return null;
    }
}