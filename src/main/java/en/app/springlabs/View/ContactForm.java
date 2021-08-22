package en.app.springlabs.View;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import en.app.springlabs.Domain.Singers;

import java.util.List;

public class ContactForm extends FormLayout {

    private Singers singers;

    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField musical_group = new TextField("Musical group");
    TextField pseudonym = new TextField("pseudonym");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");


    public ContactForm(List<Singers> companies) {
        addClassName("contact-form");

        add(name,
                surname,
                musical_group,
                pseudonym,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, singers)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        fireEvent(new SaveEvent(this, singers));
    }

    public void setSingers(Singers singers) {
        this.singers = singers;
    }

    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Singers singers;

        protected ContactFormEvent(ContactForm source, Singers contact) {
            super(source, false);
            this.singers = contact;
        }

        public Singers getContact() {
            return singers;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Singers singers) {
            super(source, singers);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Singers singers) {
            super(source, singers);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}