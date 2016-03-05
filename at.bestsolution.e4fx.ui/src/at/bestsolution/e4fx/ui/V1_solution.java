package at.bestsolution.e4fx.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.di.ContextValue;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class V1_solution {
	@Inject
	@ContextValue(value = "val")
	Property<String> publish;

	@PostConstruct
	void init(BorderPane parent) {
		ListView<String> data = new ListView<>();
		data.setItems(FXCollections.observableArrayList("A","B","C"));
		publish.bind(data.getSelectionModel()
			.selectedItemProperty());
		parent.setCenter(data);
	}
}
