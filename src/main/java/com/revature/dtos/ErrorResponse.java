package com.revature.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This gets sent as the response body when an error occurs
 */

@Data
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private List<String> message;
    private LocalDateTime timestamp;

    public ErrorResponse(int statusCode, List<String> message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
