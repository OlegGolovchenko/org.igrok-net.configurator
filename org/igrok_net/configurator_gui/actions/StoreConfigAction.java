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
package org.igrok_net.configurator_gui.actions;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.igrok_net.configurator_gui.window.MainWindow;

public class StoreConfigAction implements ActionListener {

    private MainWindow parentWindow;

    public StoreConfigAction(MainWindow parentWindow) {
        super();
        this.parentWindow = parentWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.parentWindow.storeConfig();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this.parentWindow, ex.getLocalizedMessage(), "Error occured",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
