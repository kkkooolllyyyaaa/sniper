package com.tsypk.sniper.model.entity.point;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author tsypk on 02.02.2022 16:20
 * @project sniper
 */
@Entity
@Table(schema = "sniper_schema", name = "points")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x")
    @Min(-4)
    @Max(4)
    private Double x;

    @Column(name = "y")
    @Min(-5)
    @Max(5)
    private Double y;

    @Column(name = "radius")
    @Min(1)
    @Max(4)
    private Double radius;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "user_id")
    @Min(1)
    private Long userId;
}
