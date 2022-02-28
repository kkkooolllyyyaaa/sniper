package com.tsypk.sniper.repository;

import com.tsypk.sniper.model.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tsypk on 04.02.2022 10:52
 * @project sniper
 */
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByUserId(Long userId);

    List<Point> deleteAllByUserId(Long userId);
}
