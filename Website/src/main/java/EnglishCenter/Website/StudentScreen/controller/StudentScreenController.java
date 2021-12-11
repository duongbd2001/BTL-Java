package EnglishCenter.Website.StudentScreen.controller;

import EnglishCenter.Website.Entities.Course;
import EnglishCenter.Website.Entities.Enroll;
import EnglishCenter.Website.Entities.HocVien;
import EnglishCenter.Website.Repositories.CourseRepo;
import EnglishCenter.Website.Repositories.EnrollRepo;
import EnglishCenter.Website.Repositories.HocVienRepo;
import EnglishCenter.Website.StudentScreen.entity.StudyHistory;
import EnglishCenter.Website.StudentScreen.service.StudyHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Controller
public class StudentScreenController {
    //Xem lịch sử học tập
    @Autowired
    private StudyHistoryService studyHistoryService;
    @Autowired
    private HocVienRepo hocVienRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private EnrollRepo enrollRepo;

    //màn hình chính của học viên
    @RequestMapping("/student")
    public String studentScreen(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String studentUsername = (String) session.getAttribute("studentUsername");
        String studentName = (String) session.getAttribute("studentName");
        model.addAttribute("name", studentName);
        model.addAttribute("username", studentUsername);
        return "student_screen/StudentScreen";
    }

    @GetMapping("/enroll-course")
    public ModelAndView enroll(Model model, HttpServletRequest request){
        //laay thong tin username cua hoc vien
        HttpSession session = request.getSession();
        String stu_id = (String) session.getAttribute("studentUsername");

        HocVien hocVien = hocVienRepo.findHocVienById(stu_id);
        Enroll enroll = new Enroll();
        enroll.setHv(hocVien);
        enroll.setNgayDangKy(LocalDate.now());
        model.addAttribute("enroll", enroll);
        return new ModelAndView("student_screen/enroll");
    }

    //đăng kí khóa học
    @PostMapping("/enroll-course")
    public ModelAndView enroll(Model model, @RequestParam(name = "courseID") String courseID, HttpServletRequest request){
        Optional<Course> optionalCourse = courseRepo.findById(courseID);

        if (optionalCourse.isPresent()){
            //laay thong tin username cua hoc vien
            HttpSession session = request.getSession();
            String stu_id = (String) session.getAttribute("studentUsername");

            Course course = courseRepo.getById(courseID);
            Enroll enroll = new Enroll();
            HocVien hocVien = hocVienRepo.findHocVienById(stu_id);
            enroll.setC(course);
            enroll.setHv(hocVien);
            enroll.setNgayDangKy(LocalDate.now());
            enrollRepo.save(enroll);
//            studyHistoryService.addNewEnrollCourse();
            return new ModelAndView("redirect:/study");
        }else {
            model.addAttribute("message", "Course does not exist");
            return new ModelAndView("student_screen/enroll");
        }
    }

    //màn hình lịch sử học tập
    @GetMapping("/study")
    public ModelAndView study(Model model, HttpServletRequest request){
        //Hiển thị tên của sinh viên trong side bar
        HttpSession session = request.getSession();
        String studentUsername = (String) session.getAttribute("studentUsername");
        String studentName = (String) session.getAttribute("studentName");
        model.addAttribute("name", studentName);
        model.addAttribute("username", studentUsername);

        //lấy ra các khóa học đã đăng kí, hiển thị lên mục lịch sử học tập
        List<StudyHistory> listCourse = studyHistoryService.findListCourseByID(studentUsername);
        model.addAttribute("listCourse", listCourse);
        //thêm listCourse vào session
        session.setAttribute("list_course", listCourse);
        return new ModelAndView("student_screen/study");
    }

    //tìm kiếm khóa học đã đăng kí
    @GetMapping("/study/search")
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model, HttpServletRequest request){
        //Hiển thị tên của sinh viên trong side bar
        HttpSession session = request.getSession();
        String studentUsername = (String) session.getAttribute("studentUsername");
        String studentName = (String) session.getAttribute("studentName");
        model.addAttribute("name", studentName);
        model.addAttribute("username", studentUsername);

        //lấy ra danh sách các khóa học đã đăng kí
        List<StudyHistory> list = (List<StudyHistory>) session.getAttribute("list_course");
        List<StudyHistory> searchList = new ArrayList<>();
        //tìm kiếm khóa học
        for (StudyHistory i : list){
            if (i.getCourseID().contains(keyword) || i.getNameCourse().contains(keyword)){
                searchList.add(i);
            }
        }
        model.addAttribute("listCourse", searchList);

        return  "student_screen/search";
    }

    //vào màn hình profile
    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String studentUsername = (String) session.getAttribute("studentUsername");
        String studentName = (String) session.getAttribute("studentName");
        model.addAttribute("name", studentName);
        model.addAttribute("username", studentUsername);

        //tim hoc vien trong DB
        HocVien hocVien = hocVienRepo.getById(studentUsername);
        model.addAttribute("hocVien", hocVien);
        boolean is_male = true;
        if (hocVien.getGioiTinh().equalsIgnoreCase("Female")){
            is_male = false;
        }
        model.addAttribute("check", is_male);
        return "student_screen/profile";
    }

    //cập nhật sửa đổi
    @PostMapping("/profile/{id}")
    public String giangVienCapNhatProfile(@ModelAttribute("hocVien") HocVien hocVien, @PathVariable String id){
        hocVienRepo.save(hocVien);
        return "redirect:/student";
    }
}
