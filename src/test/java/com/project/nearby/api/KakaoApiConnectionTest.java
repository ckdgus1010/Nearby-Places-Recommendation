package com.project.nearby.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class KakaoApiConnectionTest {

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    @Test
    @DisplayName("카카오 API 연결 테스트 - 주소를 위도, 경도로 변환")
    void kakaoRestApiConnectionTest() {
        String address = "서울특별시 동작구 남부순환로 2089";
        String requestUri = "https://dapi.kakao.com/v2/local/search/address.json";

        String uri = UriComponentsBuilder
                .fromUriString(requestUri)
                .queryParam("query", address)
                .build()
                .encode()
                .toUriString();
        try {
            // GET 요청
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + kakaoRestApiKey);

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            assertTrue(responseCode == 200);

            // JSON 응답 읽기
            readJsonResponse(conn);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readJsonResponse(HttpURLConnection conn) throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        // JSON 응답 파싱
        JSONObject jsonResponse = new JSONObject(sb.toString());
        JSONArray documents = jsonResponse.getJSONArray("documents");

        // 좌표 값 추출 및 출력
        JSONObject firstDocument = documents.getJSONObject(0);
        String x = firstDocument.getString("x");
        String y = firstDocument.getString("y");

        System.out.println("경도: " + x + ", 위도: " + y);
    }
}