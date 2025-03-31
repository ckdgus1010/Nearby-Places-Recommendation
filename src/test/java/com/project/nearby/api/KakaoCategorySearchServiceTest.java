package com.project.nearby.api;

import com.project.nearby.api.dto.KakaoCategorySearchResponseDto;
import com.project.nearby.api.enumeration.CategoryGroupCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class KakaoCategorySearchServiceTest {

    @Autowired
    private KakaoCategorySearchService kakaoCategorySearchService;

    private final CategoryGroupCode code = CategoryGroupCode.MART;
    private final String lng = "126.981408909449";
    private final String lat = "37.4765228347619";
    private final Integer radius = 10_000;

    @Test
    @DisplayName("카테고리 검색 성공 - 유효한 값을 입력하면 정상적으로 값을 받아옴")
    void searchPlacesSuccess() {
        // given

        // when
//        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(code, lng, lat, radius);
        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(code, "", "", radius);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getMetaDto()).isNotNull();
        assertTrue(response.getCategorySearchDtos().size() > 0);

    }

    @Test
    @DisplayName("카테고리 검색 실패 - 카테고리 그룹 코드에 null 입력 시")
    void searchFailWhenUsingNullCategoryGroupCode() {
        // given
        CategoryGroupCode categoryGroupCode = null;

        // when
        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(categoryGroupCode, lng, lat, radius);

        // then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName("카테고리 검색 실패 - 위도에 null 입력 시")
    void searchFailWhenUsingNullLongitude() {
        // given
        String longitude = null;

        // when
        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(code, longitude, lat, radius);

        // then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName("카테고리 검색 실패 - 경도에 null 입력 시")
    void searchFailWhenUsingNullLatitude() {
        // given
        String latitude = null;

        // when
        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(code, lng, latitude, radius);

        // then
        assertThat(response).isNull();
    }

    @Test
    @DisplayName("카테고리 검색 실패 - 검색 반경에 null 입력 시")
    void searchFailWhenUsingNullRadius() {
        // given
        Integer value = null;

        // when
        KakaoCategorySearchResponseDto response = kakaoCategorySearchService.search(code, lng, lat, value);

        // then
        assertThat(response).isNull();
    }
}