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
public class LocationDto {

    @JsonProperty("y")
    private String latitude;    // 위도

    @JsonProperty("x")
    private String longitude;   // 경도

}
