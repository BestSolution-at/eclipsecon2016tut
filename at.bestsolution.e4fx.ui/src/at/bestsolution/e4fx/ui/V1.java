package at.bestsolution.e4fx.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class V1 {
	@Inject
	IEclipseContext context;

	@PostConstruct
	void init(BorderPane parent) {
		ListView<String> data = new ListView<>();
		data.setItems(FXCollections.observableArrayList("A","B","C"));
		data.getSelectionModel()
			.selectedItemProperty().addListener(this::handleSelection);
		parent.setCenter(data);
	}

	private void handleSelection(Observable o, String oldV, String newV) {
		context.getParent() // get the window context
			.set("val", newV);
	}
}
