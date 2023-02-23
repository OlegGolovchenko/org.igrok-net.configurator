package org.igrok_net.configurator.interfaces;

import java.io.Serializable;

public interface Configurable extends Serializable {
    void assignConfigValue(String name, String value);

    void assignConfigValue(String name, int value);

    ConfigurableValue retrieveValue(String key);
}