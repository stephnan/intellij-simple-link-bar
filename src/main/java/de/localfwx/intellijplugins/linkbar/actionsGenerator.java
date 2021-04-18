package de.localfwx.intellijplugins.linkbar;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.localfwx.intellijplugins.linkbar.config.AppSettingsState;
import org.jetbrains.annotations.NotNull;

public class actionsGenerator extends ActionGroup {
    @NotNull
    @Override
    public AnAction[] getChildren(AnActionEvent anActionEvent) {



        return new AnAction[]{
                new createLinks(AppSettingsState.getInstance().url, AppSettingsState.getInstance().name),
        };
    }
    class createLinks extends AnAction {
        private String url;

        public createLinks(String url, String text) {
            super(text);
            this.url = url;

        }
        @Override
        public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
            BrowserUtil.browse(url);
        }
    }
}
