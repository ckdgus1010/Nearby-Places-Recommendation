package com.project.nearby.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategorySearchDto {

    @JsonProperty("place_name")
    private String placeName;   // 장소명, 업체명

    @JsonProperty("phone")
    private String phone;   // 전화번호

    @JsonProperty("address_name")
    private String addressName; // 지번 주소

    @JsonProperty("road_address_name")
    private String roadAddressName; // 도로명 주소

    @JsonProperty("x")
    private String longitude;   // 경도

    @JsonProperty("y")
    private String latitude;    // 위도

    @JsonProperty("placeUrl")
    private String placeUrl;    // 장소 상세 페이지 URL

    @JsonProperty("distance")
    private String distance;    // 중심좌표까지의 거리(단위: m)
}
