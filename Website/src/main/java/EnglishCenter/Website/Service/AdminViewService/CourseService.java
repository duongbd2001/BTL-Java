package EnglishCenter.Website.Service.AdminViewService;
import EnglishCenter.Website.Entities.AdminViewEntity.Course;
import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    List<Course> searchCourseBy(String key);
    void deleteCourseById(String id);
    Course addNewCourse(Course c);
    Course getCourseByID(String id);
    List<Teacher> listOfTeacher();
    boolean courseExist(int id);
}
