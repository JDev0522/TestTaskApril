package com.sukhachev.testTaskApril.model;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.*;

//Entity класс содержащий пользователей, свзязан с Role, имплементиует UserDetails
@Entity(name = "users")
@Component
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "password")
    @NotEmpty(message = "Введите пароль!")
    private String password;

    @Column(name = "username")
    @NotEmpty(message = "Введите корректное имя!")

    private String username;


    @Column(name = "lastname")
    @NotEmpty(message = "Введите корректную фамилию! ")

    private String lastName;


    @Column(name = "dateOfBirth")
    @Min(value = 1, message = "Введите корректную дату рождения!")
    private int dateOfBirth;


    @Column(name = "adress")
    @NotEmpty(message = "Введите адрес!")
    private String adress;


    @Column(name = "user_info")
    @NotEmpty(message = "Это поле необходимо заполнить!")
    private String userInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.MERGE})

    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "role_id"))
    private Set<Role> roles;

    public User(long id, String password, String username, String lastName,
                int dateOfBirth, String adress, String userInfo, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
        this.userInfo = userInfo;
        this.roles = roles;
    }


    public User() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public @Min(value = 1, message = "Ваш возраст не может быть меньше чем 1!") int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", adress='" + adress + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", roles=" + roles +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}