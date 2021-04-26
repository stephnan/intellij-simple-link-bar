package de.localfwx.intellijplugins.linkbar.config;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.FormBuilder;
import de.localfwx.intellijplugins.linkbar.model.TableListSelectionListener;
import de.localfwx.intellijplugins.linkbar.model.TableModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.JTableHeader;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

    private final JPanel mainPanel;
    private final JBTextField name = new JBTextField();
    private final JBTextField url = new JBTextField();
    private final JButton buttonAdd = new JButton();
    private final JButton buttonDelete = new JButton();
    private JBTable table;
    private JTableHeader header;
    private TableModel tableModel;
    private AppSettingsState settings;

    public AppSettingsComponent() {

        settings = AppSettingsState.getInstance();
        tableModel = new TableModel(settings.list);
        table = new JBTable(tableModel);
        header = table.getTableHeader();

        buttonAdd.setText("Add");
        buttonDelete.setText("Delete");

        buttonAdd.addActionListener(e -> {
            if (isValid(getNameText(), getUrlText())) {
                settings.list.put(getNameText(), getUrlText());
                handleChanges();
            }

        });
        buttonDelete.addActionListener(e -> {
            if (isValid(getNameText(), getUrlText())) {
                settings.list.remove(getNameText(), getUrlText());
                handleChanges();
            }
        });

        table.getSelectionModel().addListSelectionListener(new TableListSelectionListener(this));


        mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter name: "), name, 1, false)
                .addLabeledComponent(new JBLabel("Enter url: "), url, 1, false)
                .addLabeledComponent(buttonAdd, buttonDelete)
                .addSeparator()
                .addComponent(header, 1)
                .addComponentFillVertically(table, 3)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private boolean isValid(String nameText, String urlText) {
        return !nameText.isBlank() && !nameText.isEmpty() && !urlText.isEmpty() && !urlText.isBlank();
    }

    private void handleChanges() {
        settings.modified = true;
        tableModel.fireTableDataChanged();
        table.repaint();
        resetTextBoxes();
    }

    private void resetTextBoxes() {
        name.setText("");
        url.setText("");
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return name;
    }

    public String getNameText() {
        return name.getText();
    }

    public void setNameText(@NotNull String newText) {
        name.setText(newText);
    }

    public String getUrlText() {
        return url.getText();
    }

    public void setUrlText(@NotNull String newText) {
        url.setText(newText);
    }

    public JBTable getTable() {
        return table;
    }
}


