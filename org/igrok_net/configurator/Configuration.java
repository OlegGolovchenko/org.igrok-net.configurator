package org.igrok_net.configurator;

import java.util.HashMap;
import java.util.Map;

import org.igrok_net.configurator.interfaces.Configurable;
import org.igrok_net.configurator.interfaces.ConfigurableValue;

class Configuration implements Configurable {

    private Map<String, ConfigurableValue> configurationValues;

    public Configuration() {
        super();
        this.configurationValues = new HashMap<String, ConfigurableValue>();
    }

    @Override
    public void assignConfigValue(String name, String value) {
        ConfigurableValue cfgValue = new StringConfigurationValue(name, value);
        if (this.configurationValues.containsKey(name)) {
            this.configurationValues.replace(name, cfgValue);
        } else {
            this.configurationValues.put(name, cfgValue);
        }
    }

    @Override
    public void assignConfigValue(String name, int value) {
        ConfigurableValue cfgValue = new IntConfigurationValue(name, value);
        if (this.configurationValues.containsKey(name)) {
            this.configurationValues.replace(name, cfgValue);
        } else {
            this.configurationValues.put(name, cfgValue);
        }
    }

    @Override
    public ConfigurableValue retrieveValue(String key) {
        if(this.configurationValues.containsKey(key)) {
            return this.configurationValues.get(key);
        }
        return null;
    }
}