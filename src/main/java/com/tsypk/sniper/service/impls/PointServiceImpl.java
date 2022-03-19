package com.tsypk.sniper.service.impls;

import com.tsypk.sniper.model.entity.point.Point;
import com.tsypk.sniper.model.entity.user.User;
import com.tsypk.sniper.repository.PointRepository;
import com.tsypk.sniper.repository.UserRepository;
import com.tsypk.sniper.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tsypk on 23.02.2022 17:56
 * @project sniper
 */
@Service
@Slf4j
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository, UserRepository userRepository) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Point> getAll() {
        List<Point> result = pointRepository.findAll();
        log.info("[getAll] total {} points found", result.size());
        return result;
    }

    @Override
    public List<Point> getAllByUserId(Long id) {
        List<Point> result = pointRepository.findAllByUserId(id);
        log.info("[getAllByUserId] total {} points found", result.size());
        return result;
    }

    @Override
    @Transactional
    public Optional<Point> savePoint(Point point) {
        Optional<User> userOptional = userRepository.findById(point.getUserId());
        if (!userOptional.isPresent()) {
            log.warn("[savePoint] not any user found by point userId: {}", point.getUserId());
            return Optional.empty();
        }
        return Optional.of(pointRepository.save(point));
    }

    @Override
    @Transactional
    public List<Point> removePointsByUserId(Long userId) {
        List<Point> result = pointRepository.deleteAllByUserId(userId);
        log.info("[removePointsByUserId] total {} points removed", result.size());
        return result;
    }
}
