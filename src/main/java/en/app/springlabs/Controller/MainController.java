package en.app.springlabs.Controller;

import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repos.SingersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller
public class MainController {
    @Autowired
    private SingersRepo singersRepo;

//    @GetMapping("/")
//    public String greeting(Map<String, Object> model) {
//        return "greeting";
//    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Singers> singers = singersRepo.findAll();
        model.put("singers", singers);
        return "main";
    }

    @GetMapping("/getdata")
    @ResponseBody
    public Iterable<Singers> getSingers() {
        return singersRepo.findAll();
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
        singersRepo.save(database);
        Iterable<Singers> singers = singersRepo.findAll();
        model.put("singers", singers);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        Iterable<Singers> singers;
        if(!name.isEmpty()) {
            singers = singersRepo.findByName(name);
        } else {
            singers = singersRepo.findAll();
        }
        model.put("singers", singers);
        return "main";
    }

    @PutMapping
    public String update(@RequestBody Singers singers, @PathVariable String id) {

        return "main";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        System.out.println(id);
        singersRepo.deleteById(id);
        if(singersRepo.findAll().iterator().hasNext()) {
            model.put("singers", singersRepo.findAll());
        }
        return "main";
    }
}
