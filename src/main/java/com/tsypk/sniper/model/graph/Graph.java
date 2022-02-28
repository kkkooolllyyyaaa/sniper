package com.tsypk.sniper.model.graph;

import com.tsypk.sniper.model.graph.shapes.Shape;
import com.tsypk.sniper.network.PointDTO;
import lombok.AllArgsConstructor;

/**
 * @author tsypk on 07.02.2022 09:53
 * @project sniper
 */
@AllArgsConstructor
public class Graph {
    private final Shape topLeft;
    private final Shape topRight;
    private final Shape botRight;
    private final Shape botLeft;

    public boolean isHit(double radius, double x, double y) {
        if (topLeft != null && x <= 0 && y >= 0)
            return topLeft.isHit(radius, x, y);
        else if (topRight != null && x >= 0 && y >= 0)
            return topRight.isHit(radius, x, y);
        else if (botRight != null && x >= 0 && y <= 0)
            return botRight.isHit(radius, x, y);
        else if (botLeft != null && x <= 0 && y <= 0)
            return botLeft.isHit(radius, x, y);
        return false;
    }

    public boolean isHit(PointDTO p) {
        double x = p.getX(), y = p.getY(), radius = p.getRadius();
        return isHit(radius, x, y);
    }
}
