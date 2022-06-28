package EnglishCenter.Website.Repositories.AdminViewRepo;

import EnglishCenter.Website.Entities.AdminViewEntity.Course;
import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, String> {
    @Query(value = "SELECT * FROM courses WHERE LOWER(ten) LIKE %?1% OR LOWER(id) LIKE %?1%", nativeQuery = true)
    List<Course> searchCourseBy(String key);
    
    @Query(value = "SELECT id, ten FROM giangvien", nativeQuery = true)
    List<Teacher> listOfTeacher();
}
