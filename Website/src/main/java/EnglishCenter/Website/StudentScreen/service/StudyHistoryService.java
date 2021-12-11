package EnglishCenter.Website.StudentScreen.service;

import EnglishCenter.Website.StudentScreen.entity.StudyHistory;

import java.util.List;

public interface StudyHistoryService {
    List<StudyHistory> findListCourseByID(String studentUsername);
    void addNewEnrollCourse(String courseID);
}
