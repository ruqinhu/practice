package org.ruqinhu.liang.food.controller;

import org.ruqinhu.liang.food.model.Carousel;
import org.ruqinhu.liang.food.model.dto.CarouselDto;
import org.ruqinhu.liang.food.service.CarouselService;

import java.util.Date;
import java.util.List;

public class CarouselController {

    //可以使用 spring 容器来注入
    private CarouselService carouselService = new CarouselService();

    /**
     * 获取指定时段轮播列表
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CarouselDto> listCarousel(Date startDate, Date endDate) {
        List<Carousel> carousels = carouselService.listCarousel(startDate, endDate);
        return carouselService.convertCarousel(carousels);
    }

}
