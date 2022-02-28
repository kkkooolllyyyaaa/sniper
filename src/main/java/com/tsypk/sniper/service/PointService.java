package com.tsypk.sniper.service;

import com.tsypk.sniper.model.entity.point.Point;

import java.util.List;
import java.util.Optional;

/**
 * @author tsypk on 23.02.2022 17:47
 * @project sniper
 */
public interface PointService {
    List<Point> getAll();

    List<Point> getAllByUserId(Long id);

    Optional<Point> savePoint(Point point);

    List<Point> removePointsByUserId(Long userId);

}
