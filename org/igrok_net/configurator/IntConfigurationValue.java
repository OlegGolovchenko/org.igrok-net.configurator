package org.igrok_net.configurator;

import java.util.Objects;

import org.igrok_net.configurator.interfaces.ConfigurableValue;

class IntConfigurationValue implements ConfigurableValue {

    private String name;
    private int value;

    public IntConfigurationValue(String name, int value) {
        super();
        this.name = name;
        this.setValue(value);
    }

    private static boolean canBeAssignedFromValue(Object value) {
        return value != null && value instanceof Integer;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof IntConfigurationValue))
            return false;
        IntConfigurationValue otherValue = (IntConfigurationValue) other;
        if (!otherValue.name.matches(this.name) || otherValue.value != this.value)
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
            this.value = (Integer) value;
    }

    @Override
    public Integer asNumeric() {
        return this.value;
    }

    @Override
    public String asString() {
        return null;
    }
}
