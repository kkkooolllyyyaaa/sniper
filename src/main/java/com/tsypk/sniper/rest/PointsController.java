package com.tsypk.sniper.rest;

import com.tsypk.sniper.exception.SaveException;
import com.tsypk.sniper.model.entity.point.Point;
import com.tsypk.sniper.model.entity.user.User;
import com.tsypk.sniper.model.graph.Graph;
import com.tsypk.sniper.network.PointDTO;
import com.tsypk.sniper.network.PointRequestValidator;
import com.tsypk.sniper.service.PointService;
import com.tsypk.sniper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tsypk on 03.02.2022 03:25
 * @project sniper
 */
@RestController
@RequestMapping("/points")
@Slf4j
public class PointsController {
    private final PointService pointService;
    private final UserService userService;
    private final PointRequestValidator validator;
    private final Graph graph;

    @Autowired
    public PointsController(PointService pointService, UserService userService, PointRequestValidator validator, Graph graph) {
        this.pointService = pointService;
        this.userService = userService;
        this.validator = validator;
        this.graph = graph;
    }


    @GetMapping("/get")
    @PreAuthorize("hasAuthority('points:read')")
    public List<PointDTO> getAll(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Point> points = pointService.getAllByUserId(user.getId());

        log.info("[getAll] total {} points of user: {} got", points.size(), principal.getName());

        return points.stream().map(this::checkAndConvert).collect(Collectors.toList());
    }

    @PostMapping("/check")
    @PreAuthorize("hasAuthority('points:write')")
    public ResponseEntity<?> check(Principal principal, @RequestBody PointDTO pointDTO) {
        validator.validate(pointDTO);
        Point point = convertFromDTO(pointDTO, userService.findByUsername(principal.getName()).getId());
        Optional<Point> optionalPoint = pointService.savePoint(point);
        if (!optionalPoint.isPresent()) {
            throw new SaveException("Point couldn't be saved");
        }

        log.info("[check] point of user: {} successfully checked", principal.getName());

        return ResponseEntity.ok().body(optionalPoint);
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasAuthority('points:delete')")
    public ResponseEntity<?> clear(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Point> removedPoints = pointService.removePointsByUserId(user.getId());
        ResponseEntity<?> response = ResponseEntity.ok().
                body((Optional.of(removedPoints)));

        log.info("[clear] total {} points removed of user: {}", removedPoints.size(), principal.getName());

        return response;
    }

    private PointDTO checkAndConvert(Point point) {
        PointDTO dto = new PointDTO();
        dto.setX(point.getX());
        dto.setY(point.getY());
        dto.setRadius(point.getRadius());
        dto.setResult(point.getResult());
        return dto;
    }

    private Point convertFromDTO(PointDTO dto, Long userId) {
        Point point = new Point();
        point.setX(dto.getX());
        point.setY(dto.getY());
        point.setRadius(dto.getRadius());
        point.setResult(graph.isHit(dto));
        point.setUserId(userId);
        return point;
    }
}
