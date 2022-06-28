package EnglishCenter.Website.Entities.AdminViewEntity;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "enroll")
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private Student hv;

    @ManyToOne
    @JoinColumn(name = "Course_id", nullable = false)
    private Course c;

    @Column(name = "date_enroll")
    private LocalDate ngayDangKy;

    public Enroll() {
    }

    public Enroll(long id, Student hv, Course c, LocalDate ngayDangKy) {
        this.id = id;
        this.hv = hv;
        this.c = c;
        this.ngayDangKy = ngayDangKy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getHv() {
        return hv;
    }

    public void setHv(Student hv) {
        this.hv = hv;
    }

    public Course getC() {
        return c;
    }

    public void setC(Course c) {
        this.c = c;
    }

    public LocalDate getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(LocalDate ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
    
}
