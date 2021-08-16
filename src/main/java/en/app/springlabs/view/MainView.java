package en.app.springlabs.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repos.SingersRepo;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout{
    private final SingersRepo singersRepo;

    private Grid<Singers> grid = new Grid<>(Singers.class);

    @Autowired
    public MainView(SingersRepo singersRepo) {
        this.singersRepo = singersRepo;

        add(grid);

        showSingers("");
    }

    private void showSingers(String name) {
        if(name.isEmpty()) {
            grid.setItems(singersRepo.findAll());
        } else {
            grid.setItems(singersRepo.findByName(name));
        }
    }
}
