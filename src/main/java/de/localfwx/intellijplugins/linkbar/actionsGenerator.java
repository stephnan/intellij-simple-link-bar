package de.localfwx.intellijplugins.linkbar;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import de.localfwx.intellijplugins.linkbar.config.AppSettingsState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class actionsGenerator extends ActionGroup {
    @NotNull
    @Override
    public AnAction[] getChildren(AnActionEvent anActionEvent) {
        List<AnAction> actions = new ArrayList<>();
        AppSettingsState.getInstance().list.entrySet().stream().forEach(e -> actions.add(new createLinks(e.getKey(), e.getValue())));

        AnAction[] actionArray = new AnAction[actions.size()];
        actions.toArray(actionArray);
        return actionArray;
    }
    class createLinks extends AnAction {
        private String url;

        public createLinks(String text, String url) {
            super(text);
            this.url = url;

        }
        @Override
        public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
            BrowserUtil.browse(url);
        }
    }
}
