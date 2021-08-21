package en.app.springlabs.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("log")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView() {
        LoginOverlay loginOverlay = getContent();
        loginOverlay.setOpened(true);
    }
}
