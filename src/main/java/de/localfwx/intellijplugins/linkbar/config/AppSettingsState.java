package de.localfwx.intellijplugins.linkbar.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
        name = "de.localfwx.intellijplugins.linkbar.config.AppSettingsState",
        storages = {@Storage("linkBarPlugin.xml")}
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

    public String name;
    public String url;

    public static AppSettingsState getInstance() {
        return ServiceManager.getService(AppSettingsState.class);
    }

    @Nullable
    @Override
    public AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}