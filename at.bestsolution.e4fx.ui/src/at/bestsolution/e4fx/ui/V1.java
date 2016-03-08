package at.bestsolution.e4fx.ui;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.core.event.EventBus;
import org.osgi.service.prefs.BackingStoreException;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

@SuppressWarnings("restriction")
public class V1 {
	@Inject
	@ContextValue(value="val")
	Property<String> context;

	@Inject
	EventBus broker;


	@Inject
	@Preference
	IEclipsePreferences preference;

	private ListView<String> data;

	@PostConstruct
	void init(BorderPane parent) {
		data = new ListView<>();
		data.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		data.setItems(FXCollections.observableArrayList("Ab","Bb","Cb"));
		data.getSelectionModel()
			.getSelectedItems().addListener(this::handleSelectionListChange);
		context.bind(data.getSelectionModel()
			.selectedItemProperty());

		parent.setCenter(data);

		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				broker.publish(Constants.CURRENT_TIME, new Date().getTime(), true);
			}
		}, 0, 1_000);
	}

	private void handleSelectionListChange(Change<? extends String> o) {
		String value = data.getSelectionModel()
				.getSelectedItems()
				.stream()
				.collect(Collectors.joining(","));
		preference.put(Constants.PREFERENCE_KEY, value);
		try {
			preference.flush();
		} catch (BackingStoreException e) {}
	}

//	private void handleSelection(Observable o, String oldV, String newV) {
//		context.getParent() // get the window context
//			.set("val", newV);
//	}
}
