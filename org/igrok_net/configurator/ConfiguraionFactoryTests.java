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

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.igrok_net.configurator.interfaces.Configurable;
import org.junit.Test;

public class ConfiguraionFactoryTests {

    @Test
    public void configurationShouldBeBuiltCorrectly() {
        ConfigurationFactory factory = ConfigurationFactory.init();
        assertNotNull(factory.build());
    }

    @Test
    public void configurationShouldBeSavedAndLoadedCorrectly() throws IOException, ClassNotFoundException {
        ConfigurationFactory factory = ConfigurationFactory.init();
        ConfigurationFactory.save(factory.build(), "test.igncfg");
        ConfigurationFactory factoryLoaded = ConfigurationFactory.initFromFile("test.igncfg");
        assertNotNull(factoryLoaded);
    }

    @Test
    public void configurationShouldAssignValuesCorrectly() {
        ConfigurationFactory factory = ConfigurationFactory.init();
        factory.configuration.assignConfigValue("test", "test");
        factory.configuration.assignConfigValue("test2", 0);
        Configurable config = factory.build();
        assertNotNull(config);
        assertNotNull(config.retrieveValue("test"));
        assertNotNull(config.retrieveValue("test2"));
    }
}
