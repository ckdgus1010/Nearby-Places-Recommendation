package com.project.nearby.api;

import com.project.nearby.api.enumeration.CategoryGroupCode;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

public final class KakaoUriBuilderService {

    private static final String REQUEST_URL = "https://dapi.kakao.com/v2/local/search/address.json";
    private static final String CATEGORY_PLACE_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/category.json";

    private KakaoUriBuilderService() {
    }

    /**
     * 주소를 좌표로 변환하기 위한 URI 제작
     *
     * @param address 도로명 주소(또는 지번 주소)
     */
    public static URI getRequestUriForAddressToCoordinate(String address) {
        return UriComponentsBuilder
                .fromUriString(REQUEST_URL)
                .queryParam("query", address)   // 쿼리 파라미터 추가
                .build()
                .encode()
                .toUri();
    }

    /**
     * 카테고리로 장소 검색을 위한 URI 제작
     * @param code
     * @param lng 경도
     * @param lat 위도
     * @param radius 검색 반경(단위: m, 0 ~ 20_000 사이 값)
     */
    public static URI getUriForCategorySearch(CategoryGroupCode code, String lng, String lat, Integer radius) {
        if (Objects.isNull(code) || Objects.isNull(lng) || Objects.isNull(lat) || Objects.isNull(radius)) {
            return null;
        }

        if (radius < 0 || radius > 20_000) {
            return null;
        }

        return UriComponentsBuilder
                .fromUriString(CATEGORY_PLACE_SEARCH_URL)
                .queryParam("category_group_code", code.getCode())
                .queryParam("x", lng)
                .queryParam("y", lat)
                .queryParam("radius", radius)
                .build()
                .encode()
                .toUri();
    }
}
