package com.tsypk.sniper.model.entity.user;

import com.tsypk.sniper.model.entity.point.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author tsypk on 02.02.2022 16:55
 * @project sniper
 */
@Entity
@Table(schema = "sniper_schema", name = "users")

@NoArgsConstructor
@Setter
@Getter
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "users_id_seq")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Point> pointList;
}
