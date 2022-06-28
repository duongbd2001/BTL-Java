package EnglishCenter.Website.Service.AdminViewService;
import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import java.util.List;

public interface TeacherService {
    List<Teacher> listOfTeacher();
    Teacher addNewTeacher(Teacher gv);
    void deleteTeacherById(String id);
    List<Teacher> searchTeacherBy(String keyword);
    void retired(String id);    //giảng viên đã nghỉ dạy
    Teacher selectTeacher(String id);
    boolean teacherExist(String id);
}
