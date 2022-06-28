package EnglishCenter.Website.Controllers;

import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import EnglishCenter.Website.Entities.AdminViewEntity.Student;
import EnglishCenter.Website.Entities.Login;
import EnglishCenter.Website.Repositories.AdminViewRepo.StudentRepo;
import EnglishCenter.Website.Repositories.LoginRepo;
import EnglishCenter.Website.Service.AdminViewService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LoginRepo loginRepository;
    @Autowired
    private StudentRepo studentRepo;

    //Trang welcome
    @PostMapping("/welcome")
    public String welcome(){
        return "index";
    }

    //trang login
    @RequestMapping("/index")
    public String login(){
        return "login";
    }

    //điều hướng login
    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model, HttpServletRequest request){
        //model kiểm tra tk và mk có trong DB hay ko
        Optional<Login> loginModel = loginRepository.findLoginModelByUsernameAndPassword(username,password);
        //model login kiểm tra quyền hạn của nick đăng nhập
        Login login = loginRepository.findLoginByUsernameAndPassword(username,password);

        //Kiểm tra đối tượng trong DB
        if (loginModel.isPresent()){
            //nếu quyền hạn là admin, điều hướng tới khung nhìn của admin
            if (login.getRole().equals("isAdmin")) {
                model.addAttribute("loginModel", loginModel.get());
                return new ModelAndView("adminView/admin");
            }else if (login.getRole().equals("isStudent")){     //điều hướng tới khung nhìn của sinh viên
                //tìm học sinh trong DB
                Student hocVien = studentRepo.findHocVienById(username);
                //Lưu tên đăng nhập vào session
                HttpSession session = request.getSession();
                session.setAttribute("studentUsername", username);
                session.setAttribute("studentName", hocVien.getName());
                //hiển thị tên và mã sv ra màn hình ở sidebar
                model.addAttribute("name", hocVien.getName());
                model.addAttribute("username", username);
                return new ModelAndView("studentView/studentView");
            }else {
                //điều hướng tới khung nhìn của giảng viên
                Teacher gv = teacherService.selectTeacher(username);
                model.addAttribute("teacher", gv);

                return new ModelAndView("teacherView/teacher_view");
            }
        }else {
            //hiện ra thông báo nếu sai tài khoản hoặc mật khẩu
            model.addAttribute("message", "Invalid username or password");
            return new ModelAndView("login");
        }
    }
}
