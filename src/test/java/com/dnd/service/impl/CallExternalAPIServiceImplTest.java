package com.dnd.service.impl;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CallExternalAPIServiceImplTest {

    @InjectMocks
    private CallExternalAPIServiceImpl callExternalAPIService;

    private String dndBaseURL;
    private String dndBaseURLResponse;

    @Before
    public void setUp(){
        dndBaseURL = "http://www.dnd5eapi.co/api";
        dndBaseURLResponse = this.readResource("dndBaseUrlResponse.json", Charsets.UTF_8);
    }

    @Test
    public void shouldGetDndContent_ifInputUrlIsCorrect() throws IOException, ParseException {
        final HttpURLConnection mockCon = mock(HttpURLConnection.class);
        //mocking httpconnection by URLStreamHandler since we can not mock URL class.
        URLStreamHandler stubURLStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u ) throws IOException {
                return mockCon ;
            }
        };
        callExternalAPIService = new CallExternalAPIServiceImpl();
        URL url = new URL(null, dndBaseURL, stubURLStreamHandler);
        StringBuilder actual = callExternalAPIService.callExternalAPI(String.valueOf(url));
        JSONObject resultObject = (JSONObject) new JSONParser().parse(actual.toString());
        assertEquals(dndBaseURLResponse, resultObject.toJSONString());
    }

    private String readResource(final String fileName, Charset charset) {
        try {
            return Resources.toString(Resources.getResource(fileName), charset);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}