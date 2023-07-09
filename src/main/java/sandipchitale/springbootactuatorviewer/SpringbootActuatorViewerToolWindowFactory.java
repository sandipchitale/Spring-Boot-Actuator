package sandipchitale.springbootactuatorviewer;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class SpringbootActuatorViewerToolWindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow)
    {
        SpringbootActuatorViewerToolWindow gistToolWindow = new SpringbootActuatorViewerToolWindow(project);
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(gistToolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }


}