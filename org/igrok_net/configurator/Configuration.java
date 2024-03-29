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
 * 
 * @author Oleg Golovchenko
 * @version 0.0.3
 */
class Configuration implements Configurable {

    private List<ConfigurableValue> configurationValues;

    private static final long serialVersionUID = 1L;

    /**
     * Creates new configuration instance
     */
    public Configuration() {
        super();
        this.configurationValues = new ArrayList<ConfigurableValue>();
    }

    @Override
    public void assignConfigValue(String name, String value) {
        ConfigurableValue cfgValue = null;
        for (ConfigurableValue configurableValue : configurationValues) {
            if (configurableValue.hasKey(name)) {
                cfgValue = configurableValue;
                break;
            }
        }
        if (cfgValue != null) {
            int valueIndex = this.configurationValues.indexOf(cfgValue);
            this.configurationValues.get(valueIndex).setValue(value);
        } else {
            cfgValue = new StringConfigurationValue(name, value);
            this.configurationValues.add(cfgValue);
        }
    }

    @Override
    public void assignConfigValue(String name, int value) {
        ConfigurableValue cfgValue = null;
        for (ConfigurableValue configurableValue : configurationValues) {
            if (configurableValue.hasKey(name)) {
                cfgValue = configurableValue;
                break;
            }
        }
        if (cfgValue != null) {
            int valueIndex = this.configurationValues.indexOf(cfgValue);
            this.configurationValues.get(valueIndex).setValue(value);
        } else {
            cfgValue = new IntConfigurationValue(name, value);
            this.configurationValues.add(cfgValue);
        }
    }

    @Override
    public ConfigurableValue retrieveValue(String key) {
        for (ConfigurableValue configurableValue : configurationValues) {
            if (configurableValue.hasKey(key))
                return configurableValue;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration: [\n");
        for (ConfigurableValue cfgValue : this.configurationValues) {
            sb.append(" - " + cfgValue.toString() + "\n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void assignConfigValue(String name, boolean value) {
        ConfigurableValue cfgValue = null;
        for (ConfigurableValue configurableValue : configurationValues) {
            if (configurableValue.hasKey(name)) {
                cfgValue = configurableValue;
                break;
            }
        }
        if (cfgValue != null) {
            int valueIndex = this.configurationValues.indexOf(cfgValue);
            this.configurationValues.get(valueIndex).setValue(value);
        } else {
            cfgValue = new BooleanConfigurationValue(name, value);
            this.configurationValues.add(cfgValue);
        }
    }

    @Override
    public List<ConfigurableValue> listValues() {
        return this.configurationValues;
    }
}