# org.igrok-net.configurator
## Configuration management tool
Used to create and manage configuration files used by Igrok-NET products.
Can be run as console app(in the future) or used as java library.
## Versions
### 0.0.1
Contains basic functionality as library.
#### Usage
Here is the sample of code to use configuration.
``` java
import org.igrok_net.configuration.*;
import org.igrok_net.configuration.interfaces*;

public class TestProgram {

    public static void main(String[]args) {
        ConfigurationFactory cfgFactory = ConfigurationFactory.init();
        cfgFactory.assignConfigValue("test", "test string value");
        cfgFactory.assignConfigValue("test2", 0);
        Configuration cfg = cfgFactory.build();

        //write in file
        cfgFactory.save(cfg, "testfile");

        //reload config
        cfgFactory = ConfigurationFactory.initFromFile("testfile");
        cfg = cfgFactory.build();

        //get value from built configuration
        ConfigurableValue cfgValue = cfg.retrieveValue("test");
        if(cfgValue != null) {
            String value= cfgValue.asString();
        }
    }
}
```
### 0.0.2
Contains command line interface.
#### Usage
Run jar from command line and type help to list all commands.
### 0.0.3
Added boolean configuration value type.

### 0.0.4
Added gui.
added a possibility to reset value by using magic reset value when setting on command line or by using gui.