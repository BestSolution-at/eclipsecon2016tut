package at.bestsolution.e4fx.app.handler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationElement;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.fx.ui.controls.dialog.TitleAreaDialog;
import org.eclipse.fx.ui.controls.tree.LazyTreeItem;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService.ModalityScope;

import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@SuppressWarnings("restriction")
public class DialogSample_Solution {
	@Execute
	public void run(LightWeightDialogService dialogService) {
		dialogService.openDialog(MyLightweightDialog.class, ModalityScope.WINDOW);
	}

	static class ModelTreeCell extends TreeCell<MApplicationElement> {
		public ModelTreeCell(TreeView<MApplicationElement> v) {
		}

		@Override
		protected void updateItem(MApplicationElement item, boolean empty) {
			super.updateItem(item, empty);
			if( item != null && ! empty ) {
				setText(((EObject)item).eClass().getName() + " - " + item.getElementId());
			} else {
				setText("");
			}
		}
	}


	static class MyLightweightDialog extends TitleAreaDialog {
		@Inject
		public MyLightweightDialog(MApplication application) {
			super("Hello World", "Hello World", "This is a hello world message");
			setClientArea(createClientArea(application));
			addDefaultButtons();
			setPrefWidth(500);
		}

		private Node createClientArea(MApplication application) {
			TreeView<MApplicationElement> v = new TreeView<>();
			v.setCellFactory( ModelTreeCell::new );
			v.setRoot(new LazyTreeItem<MApplicationElement>(application, (t) -> this.createTreeItem(t.getValue())));
			return v;
		}

		private List<TreeItem<MApplicationElement>> createTreeItem(MApplicationElement p) {
			if( p instanceof MElementContainer ) {
				return ((MElementContainer<?>)p).getChildren()
						.stream()
						.map( e -> new LazyTreeItem<MApplicationElement>(e, t -> createTreeItem(t.getValue())))
						.collect(Collectors.toList());
			} else {
				return Collections.emptyList();
			}
		}
	}
}
