package en.app.springlabs.Controller;

import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repos.SomeInterfaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private SomeInterfaceRepo someInterfaceRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name );
        return "greeting";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Singers> singers = someInterfaceRepo.findAll();
        model.put("singers", singers);
        return "main";
    }

    @GetMapping("/getdata")
    @ResponseBody
    public Iterable<Singers> getSingers() {
        return someInterfaceRepo.findAll();
    }

    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam(defaultValue = "alone singer") String musical_group,
            @RequestParam(defaultValue = "No") String pseudonym,
            Map<String, Object> model
    ) {
        Singers database = new Singers(name, surname, musical_group, pseudonym);
        someInterfaceRepo.save(database);
        Iterable<Singers> singers = someInterfaceRepo.findAll();
        model.put("singers", singers);
        return "main";
    }
}
