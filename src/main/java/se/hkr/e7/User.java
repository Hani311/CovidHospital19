package se.hkr.e7;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    private String ssn;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Role role;
    private EmployeeInformation employeeInformation;

    public User() {
    }

    public User(String ssn, String password, String name, String email, String phone, String address,
                Role role, EmployeeInformation employeeInformation) {
        this.ssn = ssn;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.employeeInformation = employeeInformation;
    }

    static <T extends User> T load(String ssn, final Class<T> tClass) {
        Session session = SQL.getSession();
        session.beginTransaction();
        T person = session.get(tClass, ssn);
        session.getTransaction().commit();
        return person;
    }

    void save() {
        Session session = SQL.getSession();
        session.beginTransaction();
        session.saveOrUpdate(this);
        session.getTransaction().commit();
    }

    @Id
    @Column(unique = true)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public EmployeeInformation getEmployeeInformation() {
        return employeeInformation;
    }

    public void setEmployeeInformation(EmployeeInformation employeeInformation) {
        this.employeeInformation = employeeInformation;
    }

    @Override
    public String toString() {
        return "User{" +
                "ssn='" + ssn + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", employeeInformation=" + employeeInformation +
                '}';
    }
}

enum Role {
    ADMIN, ANALYSER, DOCTOR, PATIENT
}