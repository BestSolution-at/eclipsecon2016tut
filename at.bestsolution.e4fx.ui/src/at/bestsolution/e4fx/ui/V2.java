package at.bestsolution.e4fx.ui;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.fx.core.event.EventBus;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

@SuppressWarnings("restriction")
public class V2 {
	private Label currentSelection = new Label();
	private Label currentTime = new Label();
	private Label preferenceValue = new Label();

	@Inject
	public V2(BorderPane parent, EventBus bus) {
		VBox box = new VBox();
		box.getChildren().add(currentSelection);
		box.getChildren().add(currentTime);
		box.getChildren().add(preferenceValue);
		parent.setCenter(box);
		bus.subscribe(Constants.CURRENT_TIME, EventBus.data(this::updateTime));
	}

	@Inject
	public void setVal(@Optional @Named("val") String val) {
		currentSelection.setText("Selected value: " + val);
	}

	public void updateTime(long time) {
		currentTime.setText(new Date(time).toString());
	}

	@Inject
	public void setPreferenceValues(@Preference(value=Constants.PREFERENCE_KEY) String values) {
		preferenceValue.setText(values);
	}

}
