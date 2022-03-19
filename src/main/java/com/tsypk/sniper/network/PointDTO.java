package com.tsypk.sniper.network;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author tsypk on 23.02.2022 23:11
 * @project sniper
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PointDTO {
    @NotNull
    @Min(value = -4, message = "x должен быть не менее -4")
    @Max(value = 4, message = "x должен быть не более 4")
    private Double x;
    @NotNull
    @Min(value = -5, message = "y должен быть не менее -5")
    @Max(value = 5, message = "y должен быть не более 5")
    private Double y;
    @NotNull
    @Min(value = 1, message = "radius должен быть не менее 1")
    @Max(value = 4, message = "radius должен быть не больше 4")
    private Double radius;

    private Long id;

    private boolean result;
}
