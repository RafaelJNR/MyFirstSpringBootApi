package com.bbsw.myFirstApi.common.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiError {

    @NonNull
    private  String errorCode;

    @NonNull
    private String cause;

    private LocalDateTime timestamp;

    private List<String> details;


    public ApiError(@NonNull String errorCode, @NonNull String cause, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.cause = cause;
        this.timestamp = timestamp;
    }
}
