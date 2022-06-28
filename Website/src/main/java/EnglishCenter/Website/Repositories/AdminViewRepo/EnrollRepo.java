package EnglishCenter.Website.Repositories.AdminViewRepo;

import EnglishCenter.Website.Entities.AdminViewEntity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepo extends JpaRepository<Enroll, Long>{
    
}
