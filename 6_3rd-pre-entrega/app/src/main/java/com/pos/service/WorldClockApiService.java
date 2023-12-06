package com.pos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Service
public class WorldClockApiService {

    public Date getUtcTimeNow() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            final String url = "http://worldclockapi.com/api/json/utc/now";
            Map response = restTemplate.getForObject(url, Map.class);
            assert response != null;
            String currentDateTimeString = response.get("currentDateTime").toString();
            String timeZoneString = response.get("timeZoneName").toString();

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            isoFormat.setTimeZone(TimeZone.getTimeZone(timeZoneString));
            return isoFormat.parse(currentDateTimeString);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        } finally {
            return new Date();
        }
    }
}
