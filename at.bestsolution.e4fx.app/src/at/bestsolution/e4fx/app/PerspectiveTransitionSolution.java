package at.bestsolution.e4fx.app;

import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.fx.ui.animation.pagetransition.animation.FadeAnimation;
import org.eclipse.fx.ui.workbench.renderers.base.services.PerspectiveTransitionService;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("restriction")
//@Component
public class PerspectiveTransitionSolution implements PerspectiveTransitionService<BorderPane, Node> {

	@Override
	public AnimationDelegate<BorderPane, Node> getDelegate(MPerspective fromPerspective, MPerspective toPerspective) {
		return new AnimationDelegate<BorderPane, Node>() {

			@Override
			public void animate(BorderPane container, Node control, Runnable finished) {
				FadeAnimation animation = new FadeAnimation();
				animation.animate(container, control, finished);
			}
		};
	}

}
