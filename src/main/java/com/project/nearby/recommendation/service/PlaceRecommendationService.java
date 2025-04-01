package com.project.nearby.recommendation.service;

import com.project.nearby.api.KakaoAddressService;
import com.project.nearby.api.KakaoCategorySearchService;
import com.project.nearby.api.dto.CategorySearchDto;
import com.project.nearby.api.dto.KakaoCategorySearchResponseDto;
import com.project.nearby.api.dto.KakaoLocationResponseDto;
import com.project.nearby.api.dto.LocationDto;
import com.project.nearby.api.enumeration.CategoryGroupCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceRecommendationService {

    private final KakaoAddressService kakaoAddressService;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    /**
     * 요청 받은 주소를 기준으로 카테고리별 장소 목록 검색
     *
     * @param address  도로명 주소(지번 주소)
     * @param category 장소 카테고리
     * @param radius   검색 반경
     * @return 조회된 장소 목록
     */
    public List<CategorySearchDto> searchPlaces(String address, String category, Integer radius) {

        // 주소를 위도, 경도 변환
        KakaoLocationResponseDto locationResponse = kakaoAddressService.convertAddressToCoordinate(address);

        if (Objects.isNull(locationResponse) || CollectionUtils.isEmpty(locationResponse.getLoc ationDtos())) {
            return Collections.emptyList();
        }

        CategoryGroupCode code = CategoryGroupCode.getCategoryGroupCode(category);
        LocationDto location = locationResponse.getLocationDtos().get(0);
        String lng = location.getLongitude();
        String lat = location.getLatitude();

        // 장소 검색
        KakaoCategorySearchResponseDto searchResult = kakaoCategorySearchService.search(code, lng, lat, radius);

        if (Objects.isNull(searchResult)) {
            return Collections.emptyList();
        }

        return searchResult.getCategorySearchDtos();
    }
}
