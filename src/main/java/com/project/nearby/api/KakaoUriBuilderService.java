package com.project.nearby.api;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public final class KakaoUriBuilderService {

    private static final String REQUEST_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    private KakaoUriBuilderService() {
    }

    /**
     * 주소를 좌표로 변환하기 위한 URI 제작
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
}
