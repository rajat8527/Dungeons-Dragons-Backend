package com.rakuten.service.impl;

import com.rakuten.service.CallExternalAPIService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CallExternalAPIServiceImpl implements CallExternalAPIService {
    //Generic method for calling DnD APIs
    @Override
    public StringBuilder callExternalAPI(String inputUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(inputUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.getResponseCode();
        if (httpURLConnection.getResponseCode() == 200) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
            }
        }
        return sb;
    }
}
