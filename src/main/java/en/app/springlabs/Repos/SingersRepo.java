package en.app.springlabs.Repos;

import en.app.springlabs.Domain.Singers;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingersRepo extends JpaRepository<Singers, Long> {
    List<Singers> findByName(@Param("name") String name);
    void deleteById(@NotNull @Param("id") Long id);

}
