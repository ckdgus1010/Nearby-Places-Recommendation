package com.project.nearby.api;

import com.project.nearby.api.dto.KakaoLocationResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class KakaoAddressServiceTest {

    @Autowired
    private KakaoAddressService kakaoAddressService;

    @Test
    @DisplayName("올바른 주소 입력 시 정상적으로 위도, 경도 정보를 받음")
    void requestCoordinateUsingAddressSuccess() {
        // given
        String address = "동작구 남부순환로 2089";

        // when
        KakaoLocationResponseDto response = kakaoAddressService.convertAddressToCoordinate(address);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getMetaDto()).isNotNull();
        assertTrue(response.getLocationDtos().size() > 0);
    }

    @Test
    @DisplayName("유효하지 않은 주소를 입력하면 ")
    void requestCoordinateUsingAddressFailure() {
        // given
        String address = "동작구 잘못된 주소";

        // when
        KakaoLocationResponseDto response = kakaoAddressService.convertAddressToCoordinate(address);

        // then
        assertThat(response).isNotNull();
        assertTrue(response.getMetaDto().getTotalCount() == 0);
        assertTrue(response.getLocationDtos().size() == 0);
    }

    @Test
    @DisplayName("주소에 null 입력 시 응답으로 null을 반환")
    void getNullWhenAddressIsNull() {
        // given
        String address = null;

        // when
        KakaoLocationResponseDto response = kakaoAddressService.convertAddressToCoordinate(address);

        // then
        assertThat(response).isNull();
    }
}