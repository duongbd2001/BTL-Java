package EnglishCenter.Website.Repositories;

import EnglishCenter.Website.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Login, Integer> {
    Optional<Login> findLoginModelByUsernameAndPassword(String username, String password);
    Login findLoginByUsernameAndPassword(String username, String password);
}
