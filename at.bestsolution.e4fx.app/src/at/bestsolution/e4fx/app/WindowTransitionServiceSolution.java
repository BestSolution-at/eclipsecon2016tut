package at.bestsolution.e4fx.app;

import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.fx.ui.workbench.renderers.base.services.WindowTransitionService;

import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("restriction")
//@Component
public class WindowTransitionServiceSolution implements WindowTransitionService<Stage> {

	@Override
	public AnimationDelegate<Stage> getShowDelegate(
			MWindow window) {
		return new AnimationDelegate<Stage>() {

			@Override
			public void animate(Stage window, Runnable finished) {
				window.setOpacity(0);
				window.show();
				StageFadeTransition fade = new StageFadeTransition(window, 0, 1, Duration.millis(2000));
				fade.setOnFinished( e -> finished.run());
				fade.play();
			}
		};
	}

	@Override
	public AnimationDelegate<Stage> getHideDelegate(
			MWindow window) {
		return null;
	}

}
