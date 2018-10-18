package com.hengbang.hbcrm.utils.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {
    private boolean success;
    private String message;
    private T data;
    private Page<T> page;

    public ResponseResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public ResponseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(boolean success, String message, T data, Page<T> page) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
