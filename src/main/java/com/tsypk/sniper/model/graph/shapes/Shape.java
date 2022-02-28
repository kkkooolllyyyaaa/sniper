package com.tsypk.sniper.model.graph.shapes;

/**
 * @author tsypk on 07.02.2022 09:54
 * @project sniper
 */
public abstract class Shape {
    public abstract boolean isHit(double radius, double x, double y);
}
