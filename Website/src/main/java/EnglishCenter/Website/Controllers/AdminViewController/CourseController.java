package EnglishCenter.Website.Controllers.AdminViewController;

import EnglishCenter.Website.Entities.AdminViewEntity.Course;
import EnglishCenter.Website.Service.AdminViewService.CourseService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    //hiển thị tất cả các khóa học
    @GetMapping("/all-courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourse());
        return "course_list";
    }
    //tìm kiếm khóa học theo id hoặc tên khóa học
    @GetMapping("/all-courses/search-result")
    public String searchCourse(@RequestParam(name = "keyword", required = true) String key, Model mod){
        List<Course> courseList = courseService.searchCourseBy(key);
        mod.addAttribute("results", courseList);
        mod.addAttribute("key", key);
        mod.addAttribute("type", "C");
        mod.addAttribute("number_of_results", courseList.size());
        return "search_result";
    }
    //xóa khóa học
    @GetMapping("/all-courses/delete/{id}")
    public String deleteCourseByID(@PathVariable String id){
        courseService.deleteCourseById(id);
        return "redirect:/all-courses";
    }
    //tạo mới 1 khóa học
    @GetMapping("/all-courses/new-course")
    public String addNewCourse(Model mod){
        Course c = new Course();
        mod.addAttribute("situation", "create_new");
        mod.addAttribute("choosencourse", c);
        mod.addAttribute("teachers", courseService.listOfTeacher());
        return "adminView/create_new_or_edit_course";
    }
    @PostMapping("/all-courses/new-course")
    public String addNewCourse(@ModelAttribute("course") Course c){
        //Nếu khóa học chưa tồn tại => thêm vào csdl
        if(!courseService.courseExist(c.getId())){
            courseService.addNewCourse(c);
            return "redirect:/all-courses";
        }
        //Nếu khóa học đã tồn tại => đưa ra thông báo
        else return "adminView/message";
    }
    //cập nhật lại thông tin khóa học
    @GetMapping("/all-courses/update/{id}")
    public String editCourseInformation(@PathVariable String id, Model mod){
        mod.addAttribute("choosencourse",courseService.getCourseByID(id));
        mod.addAttribute("situation", "update");
        mod.addAttribute("teachers", courseService.listOfTeacher());
//        cService.xoaKhoaHoc(id);
        return "adminView/create_new_or_edit_course";
    }
    @PostMapping("/all-courses/update/{id}")
    public String editCourseInformation(@ModelAttribute("course") Course c, @PathVariable String id){
        courseService.addNewCourse(c);
        return "redirect:/all-courses";
    }

    //lấy ra danh sách sinh viên trong khóa học
    @GetMapping("/course/{id}/student-list")
    public String listOfStudentInCourse(@PathVariable String id, Model mod){
        Course c = courseService.getCourseByID(id);
        mod.addAttribute("course", c);
        mod.addAttribute("teacher", c.getTeacher().getname());
        return "students_in_a_course";
    }
    
}