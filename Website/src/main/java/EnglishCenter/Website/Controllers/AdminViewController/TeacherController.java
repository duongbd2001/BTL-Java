
package EnglishCenter.Website.Controllers.AdminViewController;

import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import EnglishCenter.Website.Service.AdminViewService.TeacherService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //xem danh sách giảng viên
    @GetMapping("/all-teachers")
    public String listOfTeacher(Model mod){
        mod.addAttribute("teachers", teacherService.listOfTeacher());
        return "teacher_list";
    }
    //thêm mới giảng viên
    @GetMapping("/all-teachers/new-teacher")
    public String addNewTeacher(Model mod){
        Teacher teacher = new Teacher();
        mod.addAttribute("choosenteacher", teacher);
        mod.addAttribute("situation", "create_new");
        return "adminView/create_new_or_edit_teacher";
    }

    @PostMapping("/all-teachers/new-teacher")
    public String addNewTeacher(@ModelAttribute("teacher") Teacher teacher){
        if(!teacherService.teacherExist(teacher.getId())){
            teacherService.addNewTeacher(teacher);
            return "redirect:/all-teachers";
        }
        else return "adminView/message";
    }

    //xóa giảng viên
    @GetMapping("/all-teachers/delete/{id}")
    public String deleteTeacherByID(@PathVariable String id){
        teacherService.retired(id);
        teacherService.deleteTeacherById(id);
        return "redirect:/all-teachers";
    }

    //tìm kiếm giảng viên
    @GetMapping("/all-teachers/search-result")
    public String searchTeacher(@RequestParam(name = "keyword",required = true) String key, Model mod){
        List<Teacher> teacherList = teacherService.searchTeacherBy(key);
        mod.addAttribute("results", teacherList);
        mod.addAttribute("number_of_results", teacherList.size());
        mod.addAttribute("key", key);
        mod.addAttribute("type", "GV");
        return "search_result";
    }

    //cập nhật thông tin giảng viên
    @GetMapping("/all-teachers/update/{id}")
    public String editTeacherInfor(@PathVariable String id, Model mod){
        Teacher teacher = teacherService.selectTeacher(id);
        mod.addAttribute("situation", "update");
        mod.addAttribute("choosenteacher", teacher);
        return "adminView/create_new_or_edit_teacher";
    }
    @PostMapping("/all-teachers/update/{id}")
    public String editTeacherInfor(@ModelAttribute("teacher") Teacher teacher, @PathVariable String id){
        teacherService.addNewTeacher(teacher);
        return "redirect:/all-teachers";
    }
}
