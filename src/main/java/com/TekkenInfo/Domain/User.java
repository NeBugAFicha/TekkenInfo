package com.TekkenInfo.Domain;


import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Пожалуйста, введите имя пользователя")
    @Length(max=30, message = "Слишком длинное имя пользователя, максимальное число символов 30")
    private String username;
    @NotBlank(message="Пожалуйста, введите пароль")
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Role role;
    public User(){}


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    private List<Guide> guieds;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Guide> getGuieds() {
        return guieds;
    }

    public void setGuieds(List<Guide> guieds) {
        if(guieds!=null) guieds.forEach(guide -> {
            guide.setAuthor(this);
        });
        this.guieds = guieds;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==Status.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==Status.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status==Status.ACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return status==Status.ACTIVE;
    }
}
