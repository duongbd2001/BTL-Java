package EnglishCenter.Website.Controllers;

import java.util.*;
import EnglishCenter.Website.Entities.HocVien;
import EnglishCenter.Website.Service.HocVienService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//màn hình của admin tại mục quản lý học viên
@Controller
public class HocVienController {
    private HocVienService hvService;

    public HocVienController(HocVienService hvService) {
        this.hvService = hvService;
    }

    //xem danh sách học viên đang có
    @GetMapping("/all-students")
    public String xemDanhSachTatCaHocVien(Model mod){
        mod.addAttribute("students", hvService.danhSachTatCaHocVien());
        return "student_list";
    }

    //thêm mới 1 học viên
    @PostMapping("/all-students/new-student")
    public String themHocVienMoi(@ModelAttribute("student") HocVien hv){
        if(!hvService.daTonTaiHocVien(hv.getId())){
            hvService.TaoHocVienMoi(hv);
            return "redirect:/all-students";
        }
        else return "message";
    }
    
    @GetMapping("/all-students/new-student")
    public String taoHocVien(Model mod){
        HocVien hv = new HocVien();
        mod.addAttribute("choosenstudent", hv);
        mod.addAttribute("situation", "create_new");
        return "create_new_or_edit_student";
    }
    //xóa học viên
    @GetMapping("/all-students/delete/{id}")
    public String xoaHocVienTheoID(@PathVariable String id){
        hvService.xoaHocVien(id);
        return "redirect:/all-students";
    }
    //tìm kiếm học viên theo id hoặc tên
    @GetMapping("/all-students/search-result")
    public String timKiemHocVien(@RequestParam(name = "keyword", required = true) String key, Model mod){
        List<HocVien> ds = hvService.timKiemHocVienVoiTuKhoa(key);
        mod.addAttribute("results", ds);
        mod.addAttribute("number_of_results", ds.size());
        mod.addAttribute("key", key);
        mod.addAttribute("type", "HV");
        return "search_result";
    }

    //cập nhật lại thông tin của học viên
    @GetMapping("/all-students/update/{id}")
    public String suaThongTinHocVien(@PathVariable String id, Model mod){
        HocVien hv = hvService.chonHocVien(id);
        mod.addAttribute("situation", "update");
        mod.addAttribute("choosenstudent", hv);
        return "create_new_or_edit_student";
    }

    @PostMapping("/all-students/update/{id}")
    public String capNhatHocVien(@ModelAttribute("student") HocVien hv, @PathVariable String id){
        hvService.TaoHocVienMoi(hv);
        return "redirect:/all-students";
    }

}
