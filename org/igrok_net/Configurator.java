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
package org.igrok_net;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.igrok_net.configurator.ConfigurationFactory;

import java.util.regex.Matcher;

public class Configurator {

    private static String consoleHeader = "";
    private static boolean running = true;
    private static final String DEFAULT_FILE_PATH = "config.igncfg";
    private static String filePath;
    private static ConfigurationFactory cfgFactory;

    static {
        consoleHeader += "IgRok-NET Configurator  Copyright (C) 2023  Oleg Golovchenko\n";
        consoleHeader += "This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.\n";
        consoleHeader += "This is free software, and you are welcome to redistribute it\n";
        consoleHeader += "under certain conditions; type `show c' for details.\n";
        consoleHeader += "type 'help' for a list of commands.";
    }

    public static void main(String[] args) {
        System.out.println(consoleHeader);
        filePath = DEFAULT_FILE_PATH;
        try (Scanner keyboard = new Scanner(System.in)) {
            while (running) {
                System.out.print("IgRok-NET-Configurator>");
                if (keyboard.hasNextLine()) {
                    String command = keyboard.nextLine();
                    processCommand(command);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.err.println(e);
            running = false;
        }
        System.out.println("Goodbye.");
    }

    // process commands

    private static void processCommand(String command) {
        switch (command) {
            case "exit":
                running = false;
                break;
            case "show c":
            case "show w":
                printVisitGnu();
                break;
            case "output":
                setOutput(null);
                break;
            case "init":
                initConfig(false);
                break;
            case "load":
                initConfig(true);
                break;
            case "store":
                saveConfig();
                break;
            case "help":
                printHelp();
                break;
            case "type":
                System.out.println(cfgFactory.build());
                break;
            default:
                processRegexMatches(command);
                break;
        }
    }

    private static void processRegexMatches(String command) {
        Pattern pattern = Pattern.compile("^output+=+[a-zA-Z0-9]+.+[a-zA-Z]+$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            setOutput(command.split("=")[1]);
        } else if(command.startsWith("value")) {
            addValue(command);
        }else {
            System.out.println("unknown command: '" + command + "' please try again.");
        }
    }

    // commands code

    private static void addValue(String command) {
        String data = command.split("=")[1];
        String type = data.split("><")[0];
        String name = data.split("><")[1];
        String value = data.split("><")[2];
        switch(type) {
            case "text":
                cfgFactory.assignConfigValue(name, value);
                break;
            case "int":
                int nbValue = Integer.parseInt(value);
                cfgFactory.assignConfigValue(name, nbValue);
                break;
            case "bool":
                boolean boolValue = Boolean.parseBoolean(value);
                cfgFactory.assignConfigValue(name, boolValue);
                break;
            default:
                System.out.println("unknown value type");
                break;
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("- init : creates new empty configuration");
        System.out.println("- load : loads configuration from file specified in 'output' command");
        System.out.println("- store : saves configuration");
        System.out.println("- show c, show w : prints link to online gpl3 license version");
        System.out.println("- exit : exits application, this command does not save configuration");
        System.out.println("- help : prints this message");
        System.out.println("- output : sets filepath specified after '=' sign. Syntax 'output=[filepath]'");
        System.out.println("- type : prints configuration contents");
        System.out.println("- value : adds value of type 'text', 'bool' or 'int'. Syntax 'value=[type]><[name]><[value]'");
    }

    private static void saveConfig() {
        ConfigurationFactory.save(cfgFactory.build(), filePath);
        System.out.println("Configuration written.");
    }

    private static void initConfig(boolean fromFile) {
        if (fromFile) {
            cfgFactory = ConfigurationFactory.initFromFile(filePath);
        } else {
            cfgFactory = ConfigurationFactory.init();
        }
        System.out.println("(Re)Iniitialized configuration" + (fromFile ? " from output file" : "") + ".");
    }

    private static void printVisitGnu() {
        System.out.println("Please visit https://www.gnu.org/licenses/gpl-3.0.en.html");
        System.out.println("for full copy of license if file not included");
    }

    private static void setOutput(String file) {
        if (file == null) {
            filePath = DEFAULT_FILE_PATH;
            System.out.println("set output file to : " + filePath);
        } else {
            filePath = file;
            System.out.println("set output file to : " + filePath);
        }
    }
}