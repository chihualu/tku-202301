package org.tku.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_info")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name="role_id")
    private String roleId;
    @Column(name="enabled")
    private String enabled;

}
