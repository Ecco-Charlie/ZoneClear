package soft.exe.zone.clear.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import soft.exe.zone.clear.entity.Credenciales;

import java.util.Optional;

@Repository
public interface CredencialesDao extends CrudRepository<Credenciales, Long>
{

    Optional<Credenciales> findByEmail(String email);

    @Query("SELECT c.email FROM Credenciales c WHERE c.id = :id")
    Optional<String> getEmailById(Long id);

}
