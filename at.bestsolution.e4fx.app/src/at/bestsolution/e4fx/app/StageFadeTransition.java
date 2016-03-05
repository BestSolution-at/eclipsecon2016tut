package at.bestsolution.e4fx.app;

import javafx.animation.Transition;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StageFadeTransition extends Transition {
	private final Stage stage;
	private final double start;
	private final double delta;

	public StageFadeTransition(Stage stage, double start, double end, Duration duration) {
		this.stage = stage;
		this.start = start;
		this.delta = end - start;
		setCycleDuration(duration);
	}

	@Override
	protected void interpolate(double frac) {
		double v = start + frac * delta;
		final double newOpacity = Math.max(0.0,
                Math.min(v, 1.0));
		stage.setOpacity(newOpacity);
	}

}
