package org.ruqinhu.liang.food.model.dto;

import lombok.Data;
import org.ruqinhu.liang.food.model.Carousel;

import java.util.List;

@Data
public class CarouselDto {

    private Integer count;

    private Integer interval;

    private List<Carousel> carouselList;
}
