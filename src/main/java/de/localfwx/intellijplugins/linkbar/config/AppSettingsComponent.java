package de.localfwx.intellijplugins.linkbar.config;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.util.Map;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField myNameText = new JBTextField();
    private final JBTextField myLinkText = new JBTextField();
    private final JButton buttonAdd = new JButton();
    private final JButton buttonDelete = new JButton();
    private JBTable table;
    private JTableHeader header;

    public AppSettingsComponent() {

        AppSettingsState settings = AppSettingsState.getInstance();
        TableModel tableModel = new TableModel(settings.list);
        buttonAdd.setText("Add");
        buttonDelete.setText("Delete");

        table = new JBTable(tableModel);
        header = table.getTableHeader();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setShowColumns(true);

        buttonAdd.addActionListener(e -> {
            table.clearSelection();
            settings.list.put(getNameText(), getLinkText());
            tableModel.fireTableDataChanged();
            table.repaint();
            resetTextBoxes();
        });
        buttonDelete.addActionListener(e -> {
            settings.list.remove(getNameText(), getLinkText());
            tableModel.fireTableDataChanged();
            table.repaint();
            table.clearSelection();
            resetTextBoxes();
        });

        table.getSelectionModel().addListSelectionListener(event -> {
            myNameText.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            myLinkText.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
        });


        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter displayname: "), myNameText, 1, false)
                .addLabeledComponent(new JBLabel("Enter URL: "), myLinkText, 1, false)
                .addLabeledComponent(buttonAdd, buttonDelete)
                .addSeparator()
                .addComponent(header, 1)
                .addComponentFillVertically(table, 3)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private void resetTextBoxes() {
        myNameText.setText("");
        myLinkText.setText("");
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return myNameText;
    }

    public String getNameText() {
        return myNameText.getText();
    }

    public void setNameText(@NotNull String newText) {
        myNameText.setText(newText);
    }

    public String getLinkText() {
        return myLinkText.getText();
    }

    public void setLinkText(@NotNull String newText) {
        myLinkText.setText(newText);
    }

}

class TableModel extends AbstractTableModel {

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
        try {
            return data[rowIndex][columnIndex];
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        //FIXME
        return data[0][0];
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


