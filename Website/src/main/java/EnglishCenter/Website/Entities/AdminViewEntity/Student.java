package EnglishCenter.Website.Entities.AdminViewEntity;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "stu_id",length = 5,nullable = false,unique = true)
    private String id;
    @Column(name = "name_stu", length = 30, nullable = false)
    private String name;
    @Column(name = "DoB", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(name = "sex", nullable = false, length = 10 )
    private String sex;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_num", nullable = true, length = 10)
    private String phoneNumber;
    @OneToMany(mappedBy = "hv", cascade = CascadeType.REMOVE)
    private List<Enroll> enrolls;

    public Student(String id, String name, LocalDate dateOfBirth, String sex, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrolls = new ArrayList<>();
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }
    
}
