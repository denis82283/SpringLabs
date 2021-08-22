package en.app.springlabs.View;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Service.SingersService;

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout{
    private SingersService singersService;

    private Grid<Singers> grid = new Grid<>(Singers.class);
    private TextField filterText = new TextField();
    private ContactForm form;

    public MainView(SingersService singersService) {
        this.singersService = singersService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        form = new ContactForm(singersService.findAll());
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();

        closeEditor();

    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns(
                "name",
                "surname",
                "musical_group",
                "pseudonym"
        );
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editSingers(event.getValue()));
    }

    private void updateList() {
        grid.setItems(singersService.findAll(filterText.getValue()));
    }

    public void editSingers(Singers singers) {
        if (singers == null) {
            closeEditor();
        } else {
            form.setSingers(singers);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setSingers(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveContact(ContactForm.SaveEvent event) {
        singersService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        singersService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    void addSinger() {
        grid.asSingleSelect().clear();
        editSingers(new Singers());
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addSinger());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

}
