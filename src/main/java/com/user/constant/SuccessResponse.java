package com.user.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class SuccessResponse {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private List<String> error;

    @JsonProperty("data")
    private Object data;

    public SuccessResponse(int success, ArrayList<String> error, Object data) {
        this.setSuccess(success);
        this.setError(error);
        this.setData(data);
    }
}
