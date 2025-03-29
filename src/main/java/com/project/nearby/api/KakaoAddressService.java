package com.project.nearby.api;

import com.project.nearby.api.dto.KakaoLocationResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class KakaoAddressService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.rest.api.key}")
    private String KakaoRestApiKey;

    /**
     * 주소를 위도, 경도로 변환
     * @param address 도로명 주소, 지번 주소
     * @return
     */
    public KakaoLocationResponseDto convertAddressToCoordinate(String address) {

        if (ObjectUtils.isEmpty(address)) {
            return null;
        }

        // URI 생성
        URI uri = KakaoUriBuilderService.getRequestUriForAddressToCoordinate(address);

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + KakaoRestApiKey);

        HttpEntity httpEntity = new HttpEntity(headers);

        return restTemplate
                .exchange(uri, HttpMethod.GET, httpEntity, KakaoLocationResponseDto.class)
                .getBody();

    }
}
