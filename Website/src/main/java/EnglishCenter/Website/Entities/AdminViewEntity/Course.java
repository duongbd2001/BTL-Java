package EnglishCenter.Website.Entities.AdminViewEntity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "Course_id", updatable = false, nullable = false)
    private int id;

    @Column(name = "Name_", nullable = false, length = 50)
    private String name;

    @Column(name = "Num_Of_Stu", nullable = false)
    private int numberOfStudent;

    @Column(name = "Cost", nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @Column(name = "time_length", nullable = false)
    private int timeLength;

    @OneToMany(mappedBy = "c", cascade = CascadeType.REMOVE)
    private List<Enroll> enrolled;

    public Course(){
    }

    public Course(int id, String name, int cost, int numberOfStudent, int timeLength, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.numberOfStudent = numberOfStudent;
        this.timeLength = timeLength;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Enroll> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Enroll> enrolled) {
        this.enrolled = enrolled;
    }

    public int tongTien() {
        return this.cost * this.numberOfStudent;
    }
}
