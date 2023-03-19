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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.igrok_net.configurator.interfaces.Configurable;
import org.igrok_net.configurator.interfaces.ConfigurableValue;

/**
 * Constructs configuration from given values or from file.
 * 
 * @author Oleg Golovchenko
 * @version 0.0.3
 */
public class ConfigurationFactory {

    protected Configurable configuration;

    protected ConfigurationFactory() {
        super();
        this.configuration = new Configuration();
    }

    /**
     * Creates new instance of factory.
     * 
     * @return Configuration factory instance.
     */
    public static final ConfigurationFactory init() {
        return new ConfigurationFactory();
    }

    /**
     * Initializes configuration factory from file.
     * 
     * @param path path to config file.
     * @return Configuration factory instance.
     * @throws IOException            on file read errors.
     * @throws ClassNotFoundException if version of serialized configuration is not
     *                                found.
     */
    public static final ConfigurationFactory initFromFile(String path) throws IOException, ClassNotFoundException {
        ConfigurationFactory factory = new ConfigurationFactory();
        try (FileInputStream fs = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fs)) {
            factory.configuration = (Configuration) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.err.println(ex);
            throw ex;
        }
        return factory;
    }

    /**
     * Assigns string value.
     * 
     * @param name  key.
     * @param value value.
     */
    public final void assignConfigValue(String name, String value) {
        this.configuration.assignConfigValue(name, value);
    }

    /**
     * Assigns integer value.
     * 
     * @param name  key.
     * @param value value.
     */
    public final void assignConfigValue(String name, int value) {
        this.configuration.assignConfigValue(name, value);
    }

    /**
     * Assigns boolean value.
     * 
     * @param name  key.
     * @param value value.
     */
    public final void assignConfigValue(String name, Boolean value) {
        this.configuration.assignConfigValue(name, value);
    }

    /**
     * Resets value.
     * 
     * @param valueKey key.
     */
    public final void resetValue(String valueKey) {
        ConfigurableValue value = this.configuration.retrieveValue(valueKey);
        if (value != null) {
            value.resetValue();
        }
    }

    /**
     * Builds configuration from this factory.
     * 
     * @return Cconfiguration.
     */
    public final Configurable build() {
        return this.configuration;
    }

    /**
     * Writes configuration in file.
     * 
     * @param configuration configuration to write.
     * @param path          destination path.
     * @throws IOException if file could not be written.
     */
    public static final void save(Configurable configuration, String path) throws IOException {
        if (configuration != null) {
            try (FileOutputStream fs = new FileOutputStream(path);
                    ObjectOutputStream oos = new ObjectOutputStream(fs)) {
                oos.writeObject(configuration);
            } catch (IOException ioex) {
                System.out.println(ioex.getLocalizedMessage());
                System.err.println(ioex);
                throw ioex;
            }
        }
    }
}
