package en.app.springlabs.View;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import en.app.springlabs.Service.SingersService;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin")
public class DashboardView extends VerticalLayout {

    private SingersService singersService;

    public DashboardView(SingersService singersService) {
        this.singersService = singersService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getContactStats());
    }

    private Component getContactStats() {
        Span stats = new Span(singersService.count() + " singers");
        stats.addClassName("contact-stats");
        return stats;
    }



}
