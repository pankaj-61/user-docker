package com.user.api.userdetail.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.database.model.UserMasterData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetUserDetailListDataResponse extends CommonAPIDataResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("users")
    private List<UserDetail> users;

    @Data
    @NoArgsConstructor
    public static class UserDetail {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("email")
        private String email;

        @JsonProperty("dial_code")
        private String dialCode;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("created_date")
        private Long createdDate;

        @JsonProperty("updated_date")
        private Long updated_date;

        public UserDetail(UserMasterData userMasterData) {
            this.id = userMasterData.getId();
            this.firstName = userMasterData.getFirstName();
            this.lastName = userMasterData.getLastName();
            this.email = userMasterData.getEmail();
            this.dialCode = userMasterData.getDialCode();
            this.phoneNumber = userMasterData.getPhoneNumber();
            this.createdDate = userMasterData.getCreatedDate();

        }
    }
}
