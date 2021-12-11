package EnglishCenter.Website.Entities;

import javax.persistence.*;

@Entity
@Table(name = "log_in")
public class Login {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "pass_word")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "quyen_han")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
