package com.user.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ErrorResponse {

    @JsonProperty("success")
    private int success;

    @JsonProperty("error")
    private List<String> error;

    @JsonIgnore
    @JsonProperty("data")
    private Object data;

}
