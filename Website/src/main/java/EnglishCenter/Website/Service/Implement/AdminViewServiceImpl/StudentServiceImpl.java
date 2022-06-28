package EnglishCenter.Website.Service.Implement.AdminViewServiceImpl;

import EnglishCenter.Website.Entities.AdminViewEntity.Student;
import EnglishCenter.Website.Repositories.AdminViewRepo.StudentRepo;
import EnglishCenter.Website.Service.AdminViewService.StudentService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    
    @Override
    public List<Student> listOfStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student addNewStudent(Student hv) {
        return studentRepo.save(hv);
    }

    @Override
    public void deleteStudentByID(String id) {
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> searchStudentBy(String keyword) {
        return studentRepo.timKiemHocVienVoiTuKhoa(keyword);
    }

    @Override
    public Student selectStudentByID(String id) {
        return studentRepo.getById(id);
    }

    @Override
    public boolean studentExist(String id) {
        return studentRepo.existsById(id);
    }
    
}
