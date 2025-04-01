package com.project.nearby.api.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum CategoryGroupCode {
    MART("MT1", "대형마트"),
    CONVENIENCE_STORE("CS2", "편의점"),
    KINDERGARTEN("PS3", "어린이집, 유치원"),
    SCHOOL("SC4", "학교"),
    ACADEMY("AC5", "학원"),
    PARKING_LOT("PK6", "주차장"),
    GAS_STATION("OL7", "주유소, 충전소"),
    SUBWAY_STATION("SW8", "지하철역"),
    BANK("BK9", "은행"),
    CULTURE_FACILITY("CT1", "문화시설"),
    AGENCY("AG2", "중개업소"),
    PUBLIC_INSTITUTION("PO3", "공공기관"),
    TOURIST_SPOT("AT4", "관광명소"),
    ACCOMMODATION("AD5", "숙박"),
    RESTAURANT("FD6", "음식점"),
    CAFE("CE7", "카페"),
    HOSPITAL("HP8", "병원"),
    PHARMACY("PM9", "약국");

    private final String code;         // 카테고리 코드
    private final String description;   // 카테고리 설명

    private static final Map<String, CategoryGroupCode> descMap = new HashMap<>();

    static {
        for (CategoryGroupCode categoryGroupCode : values()) {
            descMap.put(categoryGroupCode.getDescription(), categoryGroupCode);
        }
    }

    public static CategoryGroupCode getCategoryGroupCode(String description) {
        return descMap.get(description);
    }

    CategoryGroupCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}


