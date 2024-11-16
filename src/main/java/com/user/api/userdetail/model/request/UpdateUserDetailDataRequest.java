package com.user.api.userdetail.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDetailDataRequest {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName = null;


    @JsonProperty("email")
    private String email = null;

    @JsonProperty("dial_code")
    private String dialCode = null;

    @JsonProperty("phone_number")
    private String phoneNumber = null;


    public boolean checkBadRequest() {
        return Objects.isNull(this.getId());
    }
}
