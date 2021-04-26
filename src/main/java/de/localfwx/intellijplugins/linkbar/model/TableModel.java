package de.localfwx.intellijplugins.linkbar.model;

import de.localfwx.intellijplugins.linkbar.config.AppSettingsState;

import javax.swing.table.AbstractTableModel;
import java.util.Map;

public class TableModel extends AbstractTableModel {

    String[] columnNames = {"Name", "URL"};
    String[][] data;

    public TableModel(Map<String, String> list) {
        getData(list);
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String[][] getData(Map<String, String> list) {
        data = new String[list.size()][columnNames.length];
        int i = 0;
        for (Map.Entry<String, String> stringStringEntry : list.entrySet()) {
            data[i][0] = stringStringEntry.getKey();
            data[i][1] = stringStringEntry.getValue();
            i++;
        }
        return data;
    }

    @Override
    public void fireTableDataChanged() {
        AppSettingsState settings = AppSettingsState.getInstance();
        getData(settings.list);
    }
}
