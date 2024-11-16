package com.user.api.userdetail.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GetUserDetailDataRequest {

    private Long user_id;

    public boolean checkBadRequest() {
        return Objects.isNull(this.getUser_id());
    }

}
