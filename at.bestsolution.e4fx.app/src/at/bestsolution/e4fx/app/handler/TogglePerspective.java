package at.bestsolution.e4fx.app.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class TogglePerspective {
	@Execute
	public void toggle(MWindow window, EPartService partService) {
		MPerspectiveStack stack = (MPerspectiveStack) window.getChildren().get(0);
		if( stack.getSelectedElement() == stack.getChildren().get(1) ) {
			partService.switchPerspective(stack.getChildren().get(0));
		} else {
			partService.switchPerspective(stack.getChildren().get(1));
		}
	}
}
