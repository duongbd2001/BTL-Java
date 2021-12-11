package EnglishCenter.Website.Repositories;

import EnglishCenter.Website.Entities.Course;
import EnglishCenter.Website.Entities.GiangVien;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, String> {
    @Query(value = "SELECT * FROM courses WHERE LOWER(ten) LIKE %?1% OR LOWER(id) LIKE %?1%", nativeQuery = true)
    List<Course> timKiemKhoaHocVoiTuKhoa(String key);
    
    @Query(value = "SELECT id, ten FROM giangvien", nativeQuery = true)
    List<String[]> tatCaGiangVien();
}
