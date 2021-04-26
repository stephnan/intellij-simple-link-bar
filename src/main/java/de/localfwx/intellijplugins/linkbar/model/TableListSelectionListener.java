package de.localfwx.intellijplugins.linkbar.model;

import de.localfwx.intellijplugins.linkbar.config.AppSettingsComponent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableListSelectionListener implements ListSelectionListener {

    private AppSettingsComponent components;

    public TableListSelectionListener(AppSettingsComponent components) {
        this.components = components;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        components.setNameText(components.getTable().getValueAt(components.getTable().getSelectedRow(), 0).toString());
        components.setUrlText(components.getTable().getValueAt(components.getTable().getSelectedRow(), 1).toString());
    }
}
