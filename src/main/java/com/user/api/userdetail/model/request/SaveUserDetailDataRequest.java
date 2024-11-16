package com.user.api.userdetail.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class SaveUserDetailDataRequest {
    private String first_name;

    private String last_name = null;


    private String email = null;

    private String dial_code = null;

    private String phone_number = null;


    public boolean checkBadRequest() {
        return StringUtils.isEmpty(this.getFirst_name()) || StringUtils.isEmpty(this.getPhone_number())
                || StringUtils.isEmpty(this.getEmail()) || StringUtils.isEmpty(this.getDial_code());
    }
}
