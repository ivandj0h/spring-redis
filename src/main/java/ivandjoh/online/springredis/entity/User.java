package ivandjoh.online.springredis.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "ages")
    private int ages;
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "salary")
    private Double salary;

    public User() {}

    public User(Long userId, int ages, String emailAddress, String fullName, Double salary) {
        this.userId = userId;
        this.ages = ages;
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.salary = salary;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
