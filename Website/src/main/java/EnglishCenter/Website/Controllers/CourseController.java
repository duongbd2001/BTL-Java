package EnglishCenter.Website.Controllers;

import EnglishCenter.Website.Entities.Course;
import EnglishCenter.Website.Service.CourseService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    private CourseService cService;

    public CourseController(CourseService cService) {
        this.cService = cService;
    }
    //hiển thị tất cả các khóa học
    @GetMapping("/all-courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", cService.getAllCourse());
        return "course_list";
    }
    //tìm kiếm khóa học theo id hoặc tên khóa học
    @GetMapping("/all-courses/search-result")
    public String timKiemKhoaHoc(@RequestParam(name = "keyword", required = true) String key, Model mod){
        List<Course> ds = cService.timKiemKhoaHocVoiTuKhoa(key);
        mod.addAttribute("results", ds);
        mod.addAttribute("key", key);
        mod.addAttribute("type", "C");
        mod.addAttribute("number_of_results", ds.size());
        return "search_result";
    }
    //xóa khóa học
    @GetMapping("/all-courses/delete/{id}")
    public String xoaKhoaHocTheoID(@PathVariable String id){
        cService.xoaKhoaHoc(id);
        return "redirect:/all-courses";
    }
    //tạo mới 1 khóa học
    @GetMapping("/all-courses/new-course")
    public String taoKhoaHoc(Model mod){
        Course c = new Course();
        mod.addAttribute("situation", "create_new");
        mod.addAttribute("choosencourse", c);
        mod.addAttribute("teachers", cService.danhSachGiangVien());
        return "create_new_or_edit_course";
    }
    @PostMapping("/all-courses/new-course")
    public String themKhoaHocMoi(@ModelAttribute("course") Course c){
        if(!cService.daTonTaiKhoaHoc(c.getId())){
            cService.taoKhoaHocMoi(c);
            return "redirect:/all-courses";
        }
        else return "message";
    }
    //cập nhật lại thông tin khóa học
    @GetMapping("/all-courses/update/{id}")
    public String suaThongTinKhoaHoc(@PathVariable String id, Model mod){
        mod.addAttribute("choosencourse",cService.getCourseByID(id));
        mod.addAttribute("situation", "update");
        mod.addAttribute("teachers", cService.danhSachGiangVien());
//        cService.xoaKhoaHoc(id);
        return "create_new_or_edit_course";
    }
    @PostMapping("/all-courses/update/{id}")
    public String capNhatKhoaHoc(@ModelAttribute("course") Course c, @PathVariable String id){
        cService.taoKhoaHocMoi(c);
        return "redirect:/all-courses";
    }
    //lấy ra danh sách sinh viên trong khóa học
    @GetMapping("/course/{id}/student-list")
    public String xemDanhSachHocVienCuaKhoaHoc(@PathVariable String id, Model mod){
        Course c = cService.getCourseByID(id);
        mod.addAttribute("course", c);
        mod.addAttribute("teacher", c.getGv().getTen());
        return "students_in_a_course";
    }
    
}