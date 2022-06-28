package EnglishCenter.Website.Controllers.AdminViewController;

import java.util.*;
import EnglishCenter.Website.Entities.AdminViewEntity.Student;
import EnglishCenter.Website.Service.AdminViewService.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//góc nhìn của admin tại mục quản lý học viên
@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //xem danh sách học viên đang có
    @GetMapping("/all-students")
    public String listOfStudent(Model mod){
        mod.addAttribute("students", studentService.listOfStudent());
        return "student_list";
    }

    //thêm mới 1 học viên
    @PostMapping("/all-students/new-student")
    public String addNewStudent(@ModelAttribute("student") Student student){
        if(!studentService.studentExist(student.getId())){
            studentService.addNewStudent(student);
            return "redirect:/all-students";
        }
        else return "adminView/message";
    }
    
    @GetMapping("/all-students/new-student")
    public String addNewStudent(Model mod){
        Student student = new Student();
        mod.addAttribute("choosenstudent", student);
        mod.addAttribute("situation", "create_new");
        return "adminView/create_new_or_edit_student";
    }
    //xóa học viên
    @GetMapping("/all-students/delete/{id}")
    public String deleteStudentByID(@PathVariable String id){
        studentService.deleteStudentByID(id);
        return "redirect:/all-students";
    }
    //tìm kiếm học viên theo id hoặc tên
    @GetMapping("/all-students/search-result")
    public String searchStudentByIdOrName(@RequestParam(name = "keyword", required = true) String key, Model mod){
        List<Student> studentList = studentService.searchStudentBy(key);
        mod.addAttribute("results", studentList);
        mod.addAttribute("number_of_results", studentList.size());
        mod.addAttribute("key", key);
        mod.addAttribute("type", "HV");
        return "search_result";
    }

    //cập nhật lại thông tin của học viên
    @GetMapping("/all-students/update/{id}")
    public String updateStudentInfor(@PathVariable String id, Model mod){
        Student student = studentService.selectStudentByID(id);
        mod.addAttribute("situation", "update");
        mod.addAttribute("choosenstudent", student);
        return "adminView/create_new_or_edit_student";
    }

    @PostMapping("/all-students/update/{id}")
    public String updateStudentInfor(@ModelAttribute("student") Student student, @PathVariable String id){
        studentService.addNewStudent(student);
        return "redirect:/all-students";
    }

}
