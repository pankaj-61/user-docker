package com.user.api.userdetail.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveUserDetailResponse extends CommonAPIDataResponse {
    @JsonProperty("user_id")
    private Long userId;
}
