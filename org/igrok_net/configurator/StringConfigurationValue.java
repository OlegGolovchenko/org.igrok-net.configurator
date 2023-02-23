package org.igrok_net.configurator;

import java.util.Objects;

import org.igrok_net.configurator.interfaces.ConfigurableValue;

class StringConfigurationValue implements ConfigurableValue {
    private String name;
    private String value;

    public StringConfigurationValue(String name, String value) {
        super();
        this.name = name;
        this.setValue(value);
    }
    
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
}