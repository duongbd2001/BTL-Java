package EnglishCenter.Website.Controllers.TeacherViewController;

import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import EnglishCenter.Website.Service.AdminViewService.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherViewController {
    private TeacherService teacherService;
    //truy cập khung nhìn của giảng viên thông qua id giảng viên
    @GetMapping("/teacher/{id}")
    public String teacherView(@PathVariable String id, Model mod){
        Teacher teacher = teacherService.selectTeacher(id);
        mod.addAttribute("teacher", teacher);
        return "teacherView/teacher_view";
    }

    //giảng viên xem và cập nhật lại thông tin cá nhân của mình
    @GetMapping("/profile/teacher/{id}")
    public String editProfile(@PathVariable String id, Model mod){
        Teacher teacher = teacherService.selectTeacher(id);
        mod.addAttribute("teacher", teacher);
        boolean is_male = true;
        if(teacher.getsex().equalsIgnoreCase("female")) is_male = false;
        mod.addAttribute("check", is_male);
        return "teacherView/teacher_profile";
    }
    @PostMapping("/profile/teacher/{id}")
    public String editProfile(@ModelAttribute("teacher") Teacher teacher, @PathVariable String id){
        teacherService.addNewTeacher(teacher);
        return "redirect:/teacher/{id}";
    }
}
