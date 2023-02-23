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

import org.igrok_net.configurator.interfaces.Configurable;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConfigurableTests {
    
    @Test
    public void configurationShouldNotAssignIntValueToExistingStringValue() {
        Configurable configuration = new Configuration();
        configuration.assignConfigValue("test", "test");
        configuration.assignConfigValue("test", 0);
        assertEquals("test", configuration.retrieveValue("test").asString());
    }

    @Test
    public void configurationShouldAssignStringValueToExistingStringValue() {
        Configurable configuration = new Configuration();
        configuration.assignConfigValue("test", "test");
        configuration.assignConfigValue("test", "0");
        assertEquals("0", configuration.retrieveValue("test").asString());
    }
}
