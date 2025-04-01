package com.project.nearby.recommendation.service;

import com.project.nearby.api.dto.CategorySearchDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PlaceRecommendationServiceTest {

    @Autowired
    private PlaceRecommendationService placeRecommendationService;

    private String address = "동작구 남부순환로 2089";
    String category = "대형마트";

    @Test
    @DisplayName("장소 추천 성공 - 유효한 값을 입력하면 정상적으로 장소 데이터를 불러옴")
    void successWhenInputValidParams() {
        // given
        Integer[] radii = new Integer[] {500, 1000, 20_000};

        for (Integer radius : radii) {

            // when
            List<CategorySearchDto> results = placeRecommendationService.searchPlaces(address, category, radius);

            // then
            assertThat(results).isNotNull();
            assertTrue(results.size() > 0);
        }
    }

    @Test
    @DisplayName("장소 추천 실패 - 지나치게 짧은 검색 반경을 입력하면 장소 데이터를 불러올 수 없음")
    void failWhenRadiusIsTooShort() {
        // given
        Integer[] radii = new Integer[]{0, 1, 10};

        for (Integer radius : radii) {

            // when
            List<CategorySearchDto> results = placeRecommendationService.searchPlaces(address, category, radius);

            // then
            assertThat(results).isNotNull();
            assertTrue(results.size() == 0);
        }
    }

    @Test
    @DisplayName("장소 추천 실패 - 유효하지 않은 주소를 입력하면 장소 데이터를 불러올 수 없음")
    void failWhenInputWrongAddress() {
        // given
        String address = "동작구 잘못된 주소";
        Integer radius = 10_000;

        // when
        List<CategorySearchDto> results = placeRecommendationService.searchPlaces(address, category, radius);

        // then
        assertThat(results).isNotNull();
        assertTrue(results.size() == 0);
    }

    @Test
    @DisplayName("장소 추천 실패 - 유효하지 않은 카테고리를 입력하면 장소 데이터를 불러올 수 없음")
    void failWhenInputWrongCategory() {
        // given
        String category = "대형마";
        Integer radius = 10_000;

        // when
        List<CategorySearchDto> results = placeRecommendationService.searchPlaces(address, category, radius);

        // then
        assertThat(results).isNotNull();
        assertTrue(results.size() == 0);
    }
}