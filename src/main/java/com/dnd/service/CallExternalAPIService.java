package com.dnd.service;

import java.io.IOException;

public interface CallExternalAPIService {
    StringBuilder callExternalAPI(String inputUrl) throws IOException;
}
