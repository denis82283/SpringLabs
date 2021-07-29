package en.app.springlabs.Repos;

import en.app.springlabs.Domain.Singers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeInterfaceRepo extends CrudRepository<Singers, Long> {
}
