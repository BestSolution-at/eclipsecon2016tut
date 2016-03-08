package at.bestsolution.e4fx.app.handler;

import org.eclipse.fx.ui.workbench.renderers.fx.services.FlyInTransitionService;
import org.eclipse.fx.ui.workbench.renderers.fx.services.LightweightDialogTransitionService;
import org.osgi.service.component.annotations.Component;

@Component(service=LightweightDialogTransitionService.class)
public class MyDialogTransition extends FlyInTransitionService {

}
