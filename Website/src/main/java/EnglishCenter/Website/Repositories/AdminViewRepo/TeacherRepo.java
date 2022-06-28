/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnglishCenter.Website.Repositories.AdminViewRepo;

import EnglishCenter.Website.Entities.AdminViewEntity.Teacher;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Lenovo T440s
 */
public interface TeacherRepo extends JpaRepository<Teacher, String>{
    @Query(value = "SELECT * FROM giangvien WHERE LOWER(ten) LIKE %?1% OR LOWER(id) LIKE %?1%", nativeQuery = true)
    List<Teacher> searchTeacherBy(String keyword);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE courses SET teacher_id = NULL WHERE teacher_id = ?1", nativeQuery = true)
    void retiredAllCourse(String id);
}
