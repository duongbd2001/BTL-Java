package EnglishCenter.Website.Service.Implement.AdminViewServiceImpl;

import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import EnglishCenter.Website.Repositories.AdminViewRepo.TeacherRepo;
import EnglishCenter.Website.Service.AdminViewService.TeacherService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepo teacherRepo;

    public TeacherServiceImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }
    
    @Override
    public List<Teacher> listOfTeacher() {
        return teacherRepo.findAll();
    }

    @Override
    public Teacher addNewTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    @Override
    public void deleteTeacherById(String id) {
        teacherRepo.deleteById(id);
    }

    @Override
    public List<Teacher> searchTeacherBy(String keyword) {
        return teacherRepo.searchTeacherBy(keyword);
    }

    @Override
    public void retired(String id) {
        teacherRepo.retiredAllCourse(id);
    }

    @Override
    public Teacher selectTeacher(String id) {
        return teacherRepo.getById(id);
    }

    @Override
    public boolean teacherExist(String id) {
        return teacherRepo.existsById(id);
    }

    
}
