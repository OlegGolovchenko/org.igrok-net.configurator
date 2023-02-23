package org.igrok_net.configurator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.igrok_net.configurator.interfaces.Configurable;

public class ConfigurationFactory {

    private Configurable configuration;

    private ConfigurationFactory() {
        super();
        this.configuration = new Configuration();
    }

    public static final ConfigurationFactory init() {
        return new ConfigurationFactory();
    }

    public static final ConfigurationFactory initFromFile(String path) {
        ConfigurationFactory factory = new ConfigurationFactory();
        try (FileInputStream fs = new FileInputStream(path);
                ObjectInputStream oos = new ObjectInputStream(fs)) {
            factory.configuration = (Configurable) oos.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.err.println(ex);
        }
        return factory;
    }

    public final void assignConfigValue(String name, String value) {
        this.configuration.assignConfigValue(name, value);
    }

    public final void assignConfigValue(String name, int value) {
        this.configuration.assignConfigValue(name, value);
    }

    public final Configurable build() {
        return this.configuration;
    }

    public final void save(Configurable configuration, String path) {
        if (configuration != null) {
            try (FileOutputStream fs = new FileOutputStream(path);
                    ObjectOutputStream oos = new ObjectOutputStream(fs)) {
                oos.writeObject(configuration);
            } catch (IOException ioex) {
                System.out.println(ioex.getLocalizedMessage());
                System.err.println(ioex);
            }
        }
    }
}
