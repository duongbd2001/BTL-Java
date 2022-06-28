package EnglishCenter.Website.Service.Implement.AdminViewServiceImpl;

import EnglishCenter.Website.Entities.AdminViewEntity.Course;
import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import EnglishCenter.Website.Repositories.AdminViewRepo.CourseRepo;
import EnglishCenter.Website.Service.AdminViewService.CourseService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
    
    @Override
    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> searchCourseBy(String key) {
        return courseRepo.searchCourseBy(key);
    }

    @Override
    public void deleteCourseById(String id) {
        courseRepo.deleteById(id);
    }

    @Override
    public Course addNewCourse(Course c) {   
        return courseRepo.save(c);
    }

    @Override
    public Course getCourseByID(String id) {
        return courseRepo.getById(id);
    }

    @Override
    public List<Teacher> listOfTeacher() {
        return courseRepo.listOfTeacher();
    }

    @Override
    public boolean courseExist(int id) {
        return courseRepo.existsById(String.valueOf(id));
    }

}
