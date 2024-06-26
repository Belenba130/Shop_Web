package com.example.shop_web.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class ErrorMessages {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMessages.class);
    private static Map<String, String> errorMap;

    public ErrorMessages() {
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(getClass().getResourceAsStream("/messages.properties"), StandardCharsets.UTF_8));
            errorMap = properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue().toString()
                    ));
            errorMap.put("error.permission","Bạn không có quyền thêm đơn hàng");
        } catch (Exception ex) {
            LOGGER.error("ErrorMessageLoader load message failed with ex: {}", ex.getMessage());
        }
    }
    public String getMessage(String errorCode) {
        return errorMap.get(errorCode);
    }
}
