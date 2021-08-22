package en.app.springlabs.Service;

import en.app.springlabs.Domain.Singers;
import en.app.springlabs.Repo.SingersRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SingersService {
    private static final Logger LOGGER = Logger.getLogger(SingersService.class.getName());
    private SingersRepo singersRepo;

    public SingersService(SingersRepo singersRepo) {
        this.singersRepo = singersRepo;
    }

    public List<Singers> findAll() {
        return singersRepo.findAll();
    }

    public List<Singers> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return singersRepo.findAll();
        } else {
            return singersRepo.findByName(stringFilter);
        }
    }

    public long count() {
        return singersRepo.count();
    }

    public void delete(Singers singers) {
        singersRepo.delete(singers);
    }

    public void save(Singers singers) {
        if (singers == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        singersRepo.save(singers);
    }
}

