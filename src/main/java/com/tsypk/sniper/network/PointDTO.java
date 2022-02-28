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
    @Min(-4)
    @Max(4)
    private Double x;
    @NotNull
    @Min(-5)
    @Max(5)
    private Double y;
    @NotNull
    @Min(1)
    @Max(4)
    private Double radius;

    private boolean result;
}
