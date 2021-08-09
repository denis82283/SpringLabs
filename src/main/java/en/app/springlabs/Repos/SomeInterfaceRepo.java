package en.app.springlabs.Repos;

import en.app.springlabs.Domain.Singers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SomeInterfaceRepo extends JpaRepository<Singers, Long> {
    List<Singers> findByName(@Param("name") String name);
}
