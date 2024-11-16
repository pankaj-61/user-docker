package com.user.api.userdetail.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserDetailDataRequest {

    private Long user_id;

    public boolean checkBadRequest() {
        return Objects.isNull(this.user_id);
    }
}
