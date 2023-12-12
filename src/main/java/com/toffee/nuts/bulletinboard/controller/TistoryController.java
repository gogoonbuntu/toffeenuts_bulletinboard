package com.toffee.nuts.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TistoryController {
    static String clientId = "ec5d32394fe7a294826f17f3fb162499";
    static String redirectUri = "http://localhost:8080";
    static String clientSecret = "ec5d32394fe7a294826f17f3fb162499424940c4282d0527826b6645d36c0c25f6b3f9f6";
    static String code = "fc9c57efe5db75e191e5bdf2f12c5c120f8b826383e9ab32ce45c722e81eb84809cd6b94";
    //https://www.tistory.com/oauth/access_token?client_id=ec5d32394fe7a294826f17f3fb162499&client_secret=ec5d32394fe7a294826f17f3fb162499424940c4282d0527826b6645d36c0c25f6b3f9f6&redirect_uri=http%3A%2F%2Flocalhost%3A8080&code=fc9c57efe5db75e191e5bdf2f12c5c120f8b826383e9ab32ce45c722e81eb84809cd6b94&grant_type=authorization_code

    public static String curl(String url, String method){
        try {
            log.info(url);
            URL urlObj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod(method);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));


            return br.readLine();
            //return null;
        } catch (IOException e) {
            e.printStackTrace();

            return "Error";
        }
    }
    public static String getAccessToken() {
        String url = "https://www.tistory.com/oauth/access_token?"
                + "client_id=" + clientId + "&"
                + "client_secret=" + clientSecret + "&"
                + "redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) + "&"
                + "code=" + code + "&"
                + "grant_type=authorization_code";
        return curl(url, "GET");
    }
    //String accessToken = getAccessToken();
    static String accessToken = "73477bf54beca91045987ceebb199f14_4c9715b9726d7d9ac8d0c109ea6239a1";
    public static void postNew() throws Exception {
        String title = "tEST";
        String content = "sdfasfawefawdf";
        String category = "개발/java";
        String url = "https://www.tistory.com/apis/post/write";
        String params =
                "  access_token="+accessToken+
                //"  &output={output-type}" +
                "  &blogName=gogoonbuntu" +
                "  &title="+ title +
                "  &content="+ content +
                "  &visibility=2" +
                "  &category="+category+
                "  &published={published}" +
                "  &slogan={slogan}" +
                "  &tag={tag}" +
                "  &acceptComment={acceptComment}" +
                "  &password={password}";
        sendPOST(url,params);
    }


    private static void sendPOST(String POST_URL, String POST_PARAMS) throws Exception {
        URL url = new URL(POST_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        OutputStream out = conn.getOutputStream();
        out.write(POST_PARAMS.getBytes());
        out.flush();
        out.close();

        int responseCode = conn.getResponseCode();
        log.info("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            log.info("POST request succeeded");
        } else {
            log.info("POST request not worked");
        }
    }
}
