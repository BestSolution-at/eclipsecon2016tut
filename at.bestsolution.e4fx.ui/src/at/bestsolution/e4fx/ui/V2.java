package at.bestsolution.e4fx.ui;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class V2 {
	private Label l = new Label();

	@Inject
	public V2(BorderPane parent) {
		parent.setCenter(l);
	}

	@Inject
	public void setVal(@Optional @Named("val") String val) {
		l.setText("Selected value: " + val);
	}
}
