package at.bestsolution.e4fx.ui;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.core.event.EventBus;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

public class V1_solution {
	@Inject
	@ContextValue(value = "val")
	Property<String> publish;

	@Inject
	@org.eclipse.fx.core.preferences.Preference(key = Constants.PREFERENCE_KEY)
	Consumer<List<String>> preference;

	@Inject
	EventBus broker;

	private ListView<String> data;

	@PostConstruct
	void init(BorderPane parent) {
		data = new ListView<>();
		data.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		data.setItems(FXCollections.observableArrayList("Ab","Bb","Cb"));
		publish.bind(data.getSelectionModel()
			.selectedItemProperty());
		data.getSelectionModel()
			.getSelectedItems()
			.addListener( this::handleSelectionListChange );
		parent.setCenter(data);

		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				broker.publish(Constants_solution.CURRENT_TIME, new Date().getTime(), true);
			}
		}, 0, 1_000);
	}

	private void handleSelectionListChange(Change<? extends String> o) {
		preference.accept(data.getSelectionModel().getSelectedItems());
	}
}
