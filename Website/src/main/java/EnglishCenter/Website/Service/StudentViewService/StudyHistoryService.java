package EnglishCenter.Website.Service.StudentViewService;

import EnglishCenter.Website.Entities.StudentViewEntity.StudyHistory;

import java.util.List;

public interface StudyHistoryService {
    List<StudyHistory> findListCourseByID(String studentUsername);
    void addNewEnrollCourse(String courseID);
}
