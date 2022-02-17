package utez.edu.mx.manejoErrores.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "lastName")
    private String lastName;

    @Column(name = "surName", nullable = true)
    private String surName;

    @Column(nullable = false, name = "email", unique = true)
    private String username;

    @Column(nullable = false, name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(nullable = false, name = "enabled")
    public Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> roles;

    public void normalizeInfo() {
        this.setUsername(this.getUsername().toLowerCase().trim());
    }

}
