package com.project.nearby.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoCategorySearchResponseDto {

    @JsonProperty("meta")
    private KakaoApiMetaDto metaDto;

    @JsonProperty("documents")
    private List<CategorySearchDto> categorySearchDtos;

}
