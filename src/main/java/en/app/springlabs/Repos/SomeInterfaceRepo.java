package en.app.springlabs.Repos;

import en.app.springlabs.Domain.Singers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SomeInterfaceRepo extends CrudRepository<Singers, Long> {
    List<Singers> findByName(String name);
}
