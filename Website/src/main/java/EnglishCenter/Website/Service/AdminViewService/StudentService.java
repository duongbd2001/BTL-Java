package EnglishCenter.Website.Service.AdminViewService;

import EnglishCenter.Website.Entities.AdminViewEntity.Student;
import java.util.List;

public interface StudentService {
    List<Student> listOfStudent();
    Student addNewStudent(Student hv);
    void deleteStudentByID(String id);
    List<Student> searchStudentBy(String keyword);
    Student selectStudentByID(String id);
    boolean studentExist(String id);
}
