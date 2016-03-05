package at.bestsolution.e4fx.ui;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.fx.core.event.EventBus;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class V2_solution {
	private Label currentSelection = new Label();
	private Label currentTime = new Label();
	private Label preferenceValue = new Label();

	@Inject
	public V2_solution(BorderPane parent, EventBus eventBus) {
		VBox box = new VBox();
		box.getChildren().add(currentSelection);
		box.getChildren().add(currentTime);
		box.getChildren().add(preferenceValue);
		parent.setCenter(box);
		eventBus.subscribe(Constants_solution.CURRENT_TIME, EventBus.data( this::updateTime ));
	}

	@Inject
	public void setVal(@Optional @Named("val") String val) {
		currentSelection.setText("Selected value: " + val);
	}

	public void updateTime(long time) {
		currentTime.setText(new Date(time).toString());
	}

	@Inject
	public void setPreferenceValues(@org.eclipse.fx.core.preferences.Preference(key=Constants.PREFERENCE_KEY) List<String> values) {
		preferenceValue.setText(values != null ?
				values.stream().collect(Collectors.joining(",")) : "");
	}

}
