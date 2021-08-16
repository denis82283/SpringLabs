package en.app.springlabs.Components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repos.SingersRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;



@SpringComponent
@UIScope
public class SingersEditor extends VerticalLayout implements KeyNotifier {
    private final SingersRepo singersRepo;

    private Singers singers;

    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField musical_group = new TextField("Musical group");
    TextField pseudonym = new TextField("Pseudonym");

    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Singers> binder = new Binder<>(Singers.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public SingersEditor(SingersRepo singersRepo) {
        this.singersRepo = singersRepo;

        add(name, surname, musical_group, pseudonym, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editSinger(singers));
        setVisible(false);
    }

    private void delete() {
        singersRepo.delete(singers);
        changeHandler.onChange();
    }

    private void save() {
        singersRepo.save(singers);
        changeHandler.onChange();
    }

    private void editSinger(Singers newSinger) {
        if(newSinger == null) {
            setVisible(false);
            return;
        }

        if(newSinger.getId() != null) {
            this.singers = singersRepo.findById(newSinger.getId()).orElse(newSinger);
        } else {
            this.singers = newSinger;
        }
        binder.setBean(singers);

        setVisible(true);

        surname.focus();
    }
}
