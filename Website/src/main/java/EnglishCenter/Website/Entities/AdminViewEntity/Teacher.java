package EnglishCenter.Website.Entities.AdminViewEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id",nullable = false,unique = true)
    private String id;

    @Column(name = "name_te", length = 50, nullable = false)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone")
    private long phoneNumber;

    @Column(name = "salary", nullable = false)
    private int salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DoB", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToMany(mappedBy = "gv")
    List<Course> teaches;
    
    public Teacher() {
        
    }

    public Teacher(String id, String name, LocalDate dateOfBirth, String sex, String email, long phoneNumber, int salary) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.teaches = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public LocalDate getdateOfBirth() {
        return dateOfBirth;
    }

    public void setdateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getsex() {
        return sex;
    }

    public void setsex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getsalary() {
        return salary;
    }

    public void setsalary(int salary) {
        this.salary = salary;
    }

    public List<Course> getTeaches() {
        return teaches;
    }

    public void setTeaches(List<Course> teaches) {
        this.teaches = teaches;
    }
    
}
