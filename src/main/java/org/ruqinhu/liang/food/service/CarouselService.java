package org.ruqinhu.liang.food.service;

import org.apache.commons.lang3.StringUtils;
import org.ruqinhu.liang.food.model.Carousel;
import org.ruqinhu.liang.food.model.dto.CarouselDto;

import java.util.*;

public class CarouselService {

    /**
     * 默认数据库中数据都是非空的且类型正确的
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Carousel> listCarousel(Date startDate, Date endDate) {
        List<Carousel> carousels = new ArrayList<>();
        return carousels;
    }

    /**
     * 返回指定 dtoList
     * @param carousels 轮播
     * @return
     */
    public List<CarouselDto> convertCarousel(List<Carousel> carousels) {
        //默认数据库中数据都是非空的且类型正确的

        Map<Integer, Set<Carousel>> map = new HashMap<>();

        for (Carousel carousel:carousels) {
            //第一层 分割出带时段的 carousel, 即：8-9,9-10 -> [8-9,9-10]
            String[] intervalStrs = StringUtils.split(carousel.getInterval(), ",");

            for (String intervalStr:intervalStrs) {
                //第二层，分割出时间点，即 8-9 -> [8,9]
                String[] interval = StringUtils.split(intervalStr, "-");
                int startInterval = Integer.parseInt(interval[0]);
                int endInterval = Integer.parseInt(interval[1]);

                //重写了 Carousel 的 hashCode 方法，所以这里 set 可以根据内容去重
                for (int i = startInterval; i < endInterval; i++) {
                    Set<Carousel> carouselSet = map.computeIfAbsent(i, k -> new HashSet<>());
                    carouselSet.add(carousel);
                }
            }
        }

        return convertMap2List(map);
    }

    private List<CarouselDto> convertMap2List(Map<Integer, Set<Carousel>> carouselDtos) {
        List<CarouselDto> retCarouselDtos = new ArrayList<>();
        for (Map.Entry<Integer, Set<Carousel>> entry:carouselDtos.entrySet()) {
            CarouselDto carouselDto = new CarouselDto();
            carouselDto.setInterval(entry.getKey());
            carouselDto.setCarouselList(new ArrayList<>(entry.getValue()));
            carouselDto.setCount(entry.getValue().size());
            retCarouselDtos.add(carouselDto);
        }
        return retCarouselDtos;
    }

}
