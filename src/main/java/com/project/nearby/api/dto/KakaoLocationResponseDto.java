package com.project.nearby.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoLocationResponseDto {

    @JsonProperty("meta")
    private KakaoApiMetaDto metaDto;

    @JsonProperty("documents")
    private List<LocationDto> locationDtos;
}
