package com.project.nearby.api;

import com.project.nearby.api.dto.KakaoCategorySearchResponseDto;
import com.project.nearby.api.enumeration.CategoryGroupCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
@Slf4j
public class KakaoCategorySearchService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.rest.api.key}")
    private String KakaoRestApiKey;

    // 카테고리 검색

    /**
     * 장소 검색하기
     * @param code 카테고리 그룹 코드
     * @param lng 경도
     * @param lat 위도
     * @param radius 검색 반경
     * @return
     */
    public KakaoCategorySearchResponseDto search(CategoryGroupCode code, String lng, String lat, Integer radius) {

        // URI 생성
        URI uri = KakaoUriBuilderService.getUriForCategorySearch(code, lng, lat, radius);

        if (Objects.isNull(uri)) {
            return null;
        }

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + KakaoRestApiKey);

        HttpEntity httpEntity = new HttpEntity(headers);

        return restTemplate
                .exchange(uri, HttpMethod.GET, httpEntity, KakaoCategorySearchResponseDto.class)
                .getBody();
    }
}
