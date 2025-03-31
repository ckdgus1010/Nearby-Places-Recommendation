package com.project.nearby.api;

import com.project.nearby.api.enumeration.CategoryGroupCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class KakaoUriBuilderServiceTest {

    private final String categorySearchUrl = "https://dapi.kakao.com/v2/local/search/category.json";
    private CategoryGroupCode code = CategoryGroupCode.MART;
    private String lng = "126.981408909449";
    private String lat = "37.4765228347619";
    Integer radius = 1000;

    @Test
    @DisplayName("한글 파라미터 정상적으로 인코딩")
    void buildUriSuccess() {
        // given
        String address = "동작구 남부순환로 2089";
        String urlTemplate = "https://dapi.kakao.com/v2/local/search/address.json?query=";

        // when
        URI uri = KakaoUriBuilderService.getRequestUriForAddressToCoordinate(address);
        String decoded = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

        // then
        assertThat(decoded).isEqualTo(urlTemplate + address);
    }

    @Test
    @DisplayName("카테고리 검색 URI - 한글 파라미터 정상적으로 인코딩")
    void buildCategorySearchUri() {
        // given

        // when
        URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, lng, lat, radius);
        String decode = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

        // then
        String expected = String.format(
                "%s?category_group_code=%s&x=%s&y=%s&radius=%s", categorySearchUrl, code.getCode(), lng, lat, radius
        );
        assertThat(decode).isEqualTo(expected);
    }

    @Test
    @DisplayName("카테고리 검색 URI 생성 성공 - 유효한 범위의 반경 입력 시 성공적으로 URI 생성")
    void getAvailableUriWhenUsingAvailableRadius() {
        // given
        Integer[] arr = new Integer[]{0, 100, 1000, 10_000, 20_000};

        for (Integer validRadius : arr) {
            // when
            URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, lng, lat, validRadius);
            String decoded = URLDecoder.decode(uri.toString(), StandardCharsets.UTF_8);

            // then
            String expected = String.format(
                    "%s?category_group_code=%s&x=%s&y=%s&radius=%s",
                    categorySearchUrl, code.getCode(), lng, lat, validRadius
            );

            assertThat(decoded).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("카테고리 검색 URI 생성 실패 - 유효하지 않은 범위의 반경 입력 시 null 반환")
    void getNullWhenUsingUnavailableRadius() {
        // given
        Integer[] arr = new Integer[]{-1, 20_001};

        for (Integer invalidRadius : arr) {
            // when
            URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, lng, lat, invalidRadius);

            // then
            assertThat(uri).isNull();
        }
    }

    @Test
    @DisplayName("카테고리 검색 URI 생성 실패 - 카테고리 그룹 코드에 null 입력 시 null 반환")
    void getNullWhenUsingNullCategoryGroupCode() {
        // given
        CategoryGroupCode categoryGroupCode = null;

        // when
        URI uri = KakaoUriBuilderService.getUriForCategorySearch(categoryGroupCode, lng, lat, radius);

        // then
        assertThat(uri).isNull();
    }

    @Test
    @DisplayName("카테고리 검색 URI 생성 실패 - 경도에 null 입력 시 null 반환")
    void getNullWhenUsingNullLongitude() {
        // given
        String longitude = null;

        // when
        URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, longitude, lat, radius);

        // then
        assertThat(uri).isNull();
    }

    @Test
    @DisplayName("카테고리 검색 URI 생성 실패 - 위도에 null 입력 시 null 반환")
    void getNullWhenUsingNullLatitude() {
        // given
        String latitude = null;

        // when
        URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, lng, latitude, radius);

        // then
        assertThat(uri).isNull();
    }
}