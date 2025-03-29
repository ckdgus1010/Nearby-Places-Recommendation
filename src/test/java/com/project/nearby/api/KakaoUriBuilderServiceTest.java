package com.project.nearby.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

class KakaoUriBuilderServiceTest {

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
        Assertions.assertThat(decoded).isEqualTo(urlTemplate + address);
    }
}