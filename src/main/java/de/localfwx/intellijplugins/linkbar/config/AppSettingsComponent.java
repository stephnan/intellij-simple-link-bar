package de.localfwx.intellijplugins.linkbar.config;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class AppSettingsComponent {

    private final JPanel myMainPanel;
    private final JBTextField myNameText = new JBTextField();
    private final JBTextField myLinkText = new JBTextField();

    public AppSettingsComponent() {

        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Enter displayname: "), myNameText, 1, false)
                .addLabeledComponent(new JBLabel("Enter URL: "), myLinkText, 1, false)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
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

    public void setNameText(String newText) {
        myNameText.setText(newText);
    }

    public String getLinkText() {
        return myLinkText.getText();
    }
    public void setLinkText(String newText) {
        myLinkText.setText(newText);
    }

}