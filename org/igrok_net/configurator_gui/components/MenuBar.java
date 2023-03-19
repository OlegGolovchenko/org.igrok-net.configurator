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
package org.igrok_net.configurator_gui.components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import org.igrok_net.configurator_gui.actions.ExitAction;
import org.igrok_net.configurator_gui.actions.LoadConfigAction;
import org.igrok_net.configurator_gui.actions.ResetValueAction;
import org.igrok_net.configurator_gui.actions.SetBooleanValueAction;
import org.igrok_net.configurator_gui.actions.SetIntegerValueAction;
import org.igrok_net.configurator_gui.actions.SetTextValueAction;
import org.igrok_net.configurator_gui.actions.StoreConfigAction;

public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenuItem outputFileMenu;
    private JMenuItem storeFileMenu;
    private JMenuItem exitProgramMenu;
    private JMenu configMenu;
    private JMenuItem loadConfigMenu;
    private JMenuItem setTextValueMenu;
    private JMenuItem setIntValueMenu;
    private JMenuItem setBooleanValueMenu;
    private JMenuItem resetValueMenu;

    public MenuBar() {
        super();
        this.fileMenu = new JMenu("File");
        this.outputFileMenu = new JMenuItem("Output");
        this.storeFileMenu = new JMenuItem("Store");
        this.fileMenu.add(this.outputFileMenu);
        this.fileMenu.add(this.storeFileMenu);
        this.fileMenu.add(new JSeparator());
        this.exitProgramMenu = new JMenuItem("Exit");
        this.fileMenu.add(this.exitProgramMenu);
        this.add(this.fileMenu);
        this.configMenu = new JMenu("Configuraion");
        this.loadConfigMenu = new JMenuItem("Load");
        this.configMenu.add(this.loadConfigMenu);
        this.configMenu.add(new JSeparator());
        this.setTextValueMenu = new JMenuItem("Set Text Value");
        this.configMenu.add(this.setTextValueMenu);
        this.setIntValueMenu = new JMenuItem("Set Integer Value");
        this.configMenu.add(this.setIntValueMenu);
        this.setBooleanValueMenu = new JMenuItem("Set Boolean Value");
        this.configMenu.add(this.setBooleanValueMenu);
        this.configMenu.add(new JSeparator());
        this.resetValueMenu = new JMenuItem("Reset Value");
        this.configMenu.add(this.resetValueMenu);
        this.add(this.configMenu);
    }

    public void setExitAction(ExitAction action) {
        this.exitProgramMenu.addActionListener(action);
    }

    public void setOutputAction(LoadConfigAction action) {
        this.outputFileMenu.addActionListener(action);
    }

    public void setStoreAction(StoreConfigAction action) {
        this.storeFileMenu.addActionListener(action);
    }

    public void setLoadAction(LoadConfigAction action) {
        this.loadConfigMenu.addActionListener(action);
    }

    public void setStringValueAction(SetTextValueAction action) {
        this.setTextValueMenu.addActionListener(action);
    }

    public void setBooleanValueAction(SetBooleanValueAction action) {
        this.setBooleanValueMenu.addActionListener(action);
    }

    public void setIntegerValueAction(SetIntegerValueAction action) {
        this.setIntValueMenu.addActionListener(action);
    }

    public void setResetValueAction(ResetValueAction action) {
        this.resetValueMenu.addActionListener(action);
    }
}
