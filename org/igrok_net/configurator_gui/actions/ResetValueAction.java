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

import org.igrok_net.configurator_gui.window.MainWindow;

public class ResetValueAction implements ActionListener {

    private MainWindow parentWindow;

    public ResetValueAction(MainWindow parent) {
        super();
        this.parentWindow = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.parentWindow.resetValue();
    }

}