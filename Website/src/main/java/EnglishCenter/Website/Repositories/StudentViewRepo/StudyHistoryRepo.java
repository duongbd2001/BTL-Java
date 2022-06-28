package EnglishCenter.Website.Repositories.StudentViewRepo;

import EnglishCenter.Website.Entities.AdminViewEntity.Student;
import EnglishCenter.Website.Entities.StudentViewEntity.StudyHistory;
import EnglishCenter.Website.common.IDaoCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StudyHistoryRepo  extends JpaRepository<Student, String> {
    public default List<StudyHistory> findListCourseByID(String studentID, IDaoCommon daoCommon) {

        StringBuilder sql = new StringBuilder("SELECT ROW_NUMBER() OVER(ORDER BY c.id ASC) AS stt, c.id as courseID, c.ten as nameCourse,c.hoc_phi as cost,g.ten as nameTeacher,c.so_hoc_vien as numberOfStudent " +
                "FROM dbo.courses as c, dbo.enroll as e,dbo.hocvien as h,dbo.giangvien as g " +
                " WHERE h.id = :studentID AND e.stu_id = :studentID AND e.Course_id = c.id AND c.teacher_id = g.id");
        Map<String, Object> params = new HashMap<>();
        params.put("studentID", studentID);
        List<StudyHistory> list = daoCommon.list(sql.toString(), params, StudyHistory.class);

        return list;
    }

}
