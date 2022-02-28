package com.tsypk.sniper.config;

import com.tsypk.sniper.model.graph.Graph;
import com.tsypk.sniper.model.graph.shapes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tsypk on 26.02.2022 23:53
 * @project sniper
 */
@Configuration
public class BeansConfig {
    @Bean
    Graph taskGraph() {
        Shape topLeft = new Triangle(Radius.HALF_R, Radius.HALF_R);
        Shape botRight = new Rectangle(Radius.HALF_R, Radius.R);
        Shape botLeft = new Circle(Radius.R);
        return new Graph(topLeft, null, botRight, botLeft);
    }
}
