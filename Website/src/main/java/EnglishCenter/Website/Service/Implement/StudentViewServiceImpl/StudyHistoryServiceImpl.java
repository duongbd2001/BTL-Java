package EnglishCenter.Website.Service.Implement.StudentViewServiceImpl;

import EnglishCenter.Website.Entities.StudentViewEntity.StudyHistory;
import EnglishCenter.Website.Repositories.StudentViewRepo.StudyHistoryRepo;
import EnglishCenter.Website.Service.StudentViewService.StudyHistoryService;
import EnglishCenter.Website.common.IDaoCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyHistoryServiceImpl implements StudyHistoryService {
    @Autowired
    private StudyHistoryRepo studyHistoryRepo;
    @Autowired
    private IDaoCommon iDaoCommon;

    @Autowired
    private IDaoCommon daoCommon;

    @Override
    public List<StudyHistory> findListCourseByID(String studentUsername) {
        return studyHistoryRepo.findListCourseByID(studentUsername, daoCommon);
    }

    @Override
    public void addNewEnrollCourse(String courseID) {
        iDaoCommon.save(courseID);
    }
}
