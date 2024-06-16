package com.secureApp.springsecuritybasic.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T result;

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public static class ApiResponseBuilder<T> {

        private Integer code;
        private String message;
        private T result;

        public ApiResponseBuilder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> result(T result) {
            this.result = result;
            return this;
        }

        public ApiResponse<T> build() {
            ApiResponse<T> apiResponse = new ApiResponse<>();
            apiResponse.code = code;
            apiResponse.message = message;
            apiResponse.result = result;
            return apiResponse;
        }


    }

}
