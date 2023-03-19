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
package org.igrok_net.configurator_gui.window;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.igrok_net.configurator.ConfigurationFactory;
import org.igrok_net.configurator_gui.actions.ExitAction;
import org.igrok_net.configurator_gui.actions.LoadConfigAction;
import org.igrok_net.configurator_gui.actions.ResetValueAction;
import org.igrok_net.configurator_gui.actions.SetBooleanValueAction;
import org.igrok_net.configurator_gui.actions.SetIntegerValueAction;
import org.igrok_net.configurator_gui.actions.SetTextValueAction;
import org.igrok_net.configurator_gui.actions.StoreConfigAction;
import org.igrok_net.configurator_gui.components.MainPage;
import org.igrok_net.configurator_gui.components.MenuBar;

public class MainWindow extends JFrame {

    private MenuBar menu;
    private BorderLayout layout;
    private JTextArea mainPage;
    private ExitAction exitAction;
    private ConfigurationFactory factory;
    private String filePath;
    private LoadConfigAction loadAction;
    private StoreConfigAction storeAction;
    private SetTextValueAction setTextAction;
    private SetBooleanValueAction setBooleanValueAction;
    private SetIntegerValueAction setIntegerValueAction;
    private ResetValueAction resetValueAction;

    public MainWindow() {
        super();
        this.filePath = "config.igncfg";
        this.factory = ConfigurationFactory.init();
        this.layout = new BorderLayout();
        this.setTitle("IgRok-NET Configurator");
        this.setSize(320, 240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.exitAction = new ExitAction(this);
        this.loadAction = new LoadConfigAction(this);
        this.storeAction = new StoreConfigAction(this);
        this.setTextAction = new SetTextValueAction(this);
        this.setBooleanValueAction = new SetBooleanValueAction(this);
        this.setIntegerValueAction = new SetIntegerValueAction(this);
        this.resetValueAction = new ResetValueAction(this);
        this.menu = new MenuBar();
        this.menu.setExitAction(this.exitAction);
        this.menu.setOutputAction(this.loadAction);
        this.menu.setStoreAction(this.storeAction);
        this.menu.setStringValueAction(this.setTextAction);
        this.menu.setBooleanValueAction(this.setBooleanValueAction);
        this.menu.setIntegerValueAction(this.setIntegerValueAction);
        this.menu.setResetValueAction(this.resetValueAction);
        this.setJMenuBar(this.menu);
        this.getContentPane().setLayout(this.layout);
        this.mainPage = new MainPage();
        this.getContentPane().add(this.mainPage, BorderLayout.CENTER);
        this.setVisible(true);
        try {
            loadConfig();
        } catch (ClassNotFoundException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void storeConfig() throws IOException {
        if (factory != null) {
            ConfigurationFactory.save(this.factory.build(), this.filePath);
        }
    }

    public void selectDestination() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public void loadConfig() throws ClassNotFoundException, IOException {
        this.factory = ConfigurationFactory.initFromFile(this.filePath);
        this.mainPage.setText(this.factory.build().toString());
    }

    public void setStringValue() {
        String name = (String) JOptionPane.showInputDialog(this, "Enter value name", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        String value = (String) JOptionPane.showInputDialog(this, "Enter value", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        this.factory.assignConfigValue(name, value);
        this.mainPage.setText(this.factory.build().toString());
    }

    public void setIntValue() {
        String name = (String) JOptionPane.showInputDialog(this, "Enter value name", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        String value = (String) JOptionPane.showInputDialog(this, "Enter value", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        if (value != null) {
            try {
                int numValue = Integer.parseInt(value);
                this.factory.assignConfigValue(name, numValue);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please input numeric value", "Error occured",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        this.mainPage.setText(this.factory.build().toString());
    }

    public void setBooleanValue() {
        Object[] values = new Object[] { true, false };
        Object currentValue = false;
        String name = (String) JOptionPane.showInputDialog(this, "Enter value name", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        Boolean value = (Boolean) JOptionPane.showInputDialog(this, "Enter value", "Input required",
                JOptionPane.INFORMATION_MESSAGE, null, values, currentValue);
        if (value != null) {
            this.factory.assignConfigValue(name, value);
        }
        this.mainPage.setText(this.factory.build().toString());
    }

    public void resetValue() {
        String name = (String) JOptionPane.showInputDialog(this, "Enter value name", "Input required",
                JOptionPane.OK_CANCEL_OPTION);
        this.factory.resetValue(name);
        this.mainPage.setText(this.factory.build().toString());
    }
}
