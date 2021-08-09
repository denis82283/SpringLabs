package en.app.springlabs.Controller;

import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repos.SomeInterfaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private SomeInterfaceRepo someInterfaceRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
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

    @PostMapping("/main")
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

    @PostMapping("filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        Iterable<Singers> singers;
        if(!name.isEmpty()) {
            singers = someInterfaceRepo.findByName(name);
        } else {
            singers = someInterfaceRepo.findAll();
        }
        model.put("singers", singers);
        return "main";
    }
}