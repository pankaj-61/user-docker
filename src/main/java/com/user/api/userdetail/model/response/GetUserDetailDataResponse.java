package com.user.api.userdetail.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.database.model.UserMasterData;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserDetailDataResponse extends CommonAPIDataResponse {
    @JsonProperty("user_detail")
    private UserDetail userDetail;

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
        @JsonProperty("created_by")
        private String createdBy;

        public UserDetail(UserMasterData userMasterData) {
            this.id = userMasterData.getId();
            this.firstName = userMasterData.getFirstName();
            this.lastName = userMasterData.getLastName();
            this.email = userMasterData.getEmail();
            this.dialCode = userMasterData.getDialCode();
            this.phoneNumber = userMasterData.getPhoneNumber();
            this.createdDate = userMasterData.getCreatedDate();
            this.createdBy = userMasterData.getCreatedBy();

        }
    }
}
