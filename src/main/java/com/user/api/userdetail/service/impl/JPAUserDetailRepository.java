package com.user.api.userdetail.service.impl;

import com.user.api.userdetail.model.request.DeleteUserDetailDataRequest;
import com.user.api.userdetail.model.request.GetUserDetailDataRequest;
import com.user.api.userdetail.model.request.SaveUserDetailDataRequest;
import com.user.api.userdetail.model.request.UpdateUserDetailDataRequest;
import com.user.api.userdetail.model.response.CommonAPIDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailListDataResponse;
import com.user.api.userdetail.model.response.SaveUserDetailResponse;
import com.user.api.userdetail.service.UserDetailRepository;
import com.user.database.model.UserMasterData;
import com.user.database.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JPAUserDetailRepository implements UserDetailRepository {

    @Autowired
    private UserRepository userRepository;
    private static final String TOPIC = "user-topic";


    @Autowired
    private KafkaTemplate<String, String> kafkatemplate;



    @Override
    public SaveUserDetailResponse saveUserDetail(SaveUserDetailDataRequest saveUserDetailDataRequest) {
        SaveUserDetailResponse saveUserDetailResponse = new SaveUserDetailResponse();

        UserMasterData sameEmailUserExist = userRepository.findByAndEmail(saveUserDetailDataRequest.getEmail().toLowerCase());

        if (!Objects.isNull(sameEmailUserExist)) {
            saveUserDetailResponse.setCheckValidationFailed(true);
            saveUserDetailResponse.setValidationMessage("USER_EMAIL_ALREADY_REGISTERED");
            return saveUserDetailResponse;
        }


        UserMasterData userMasterData = UserMasterData.builder()
                .firstName(saveUserDetailDataRequest.getFirst_name())
                .lastName(saveUserDetailDataRequest.getLast_name())
                .dialCode(saveUserDetailDataRequest.getDial_code())
                .phoneNumber(saveUserDetailDataRequest.getPhone_number())
                .email(saveUserDetailDataRequest.getEmail())
                .build();
        userRepository.save(userMasterData);
        saveUserDetailResponse.setMessage("User Details Save SuccessFully...");
        saveUserDetailResponse.setUserId(userMasterData.getId());

        kafkatemplate.send(TOPIC, "CREATE:" + userMasterData);

        return saveUserDetailResponse;
    }


    @Override
    public CommonAPIDataResponse updateUserDetail(UpdateUserDetailDataRequest updateUserDetailDataRequest) {

        CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();

        UserMasterData userMasterData = userRepository.findByMainId(updateUserDetailDataRequest.getId());

        if (Objects.isNull(userMasterData)) {
            commonAPIDataResponse.setValidationMessage("User Details Not Found");
            commonAPIDataResponse.setCheckValidationFailed(true);
            return commonAPIDataResponse;
        }


        if (StringUtils.isNotEmpty(updateUserDetailDataRequest.getEmail())) {
            UserMasterData byEmail = userRepository.findByAndEmail(updateUserDetailDataRequest.getEmail());

            if (Objects.nonNull(byEmail)
                    && !updateUserDetailDataRequest.getId().equals(byEmail.getId())) {
                commonAPIDataResponse.setValidationMessage("USER_EMAIL_ALREADY_REGISTERED");
                commonAPIDataResponse.setCheckValidationFailed(true);
                return commonAPIDataResponse;
            }
        }


        userMasterData.setFirstName(
                Objects.isNull(updateUserDetailDataRequest.getFirstName())
                        ? userMasterData.getFirstName()
                        : updateUserDetailDataRequest.getFirstName()
        );

        userMasterData.setLastName(
                Objects.isNull(updateUserDetailDataRequest.getLastName())
                        ? userMasterData.getLastName()
                        : updateUserDetailDataRequest.getLastName()
        );


        userMasterData.setEmail(
                Objects.isNull(updateUserDetailDataRequest.getEmail())
                        ? userMasterData.getEmail()
                        : updateUserDetailDataRequest.getEmail()
        );

        userMasterData.setDialCode(
                Objects.isNull(updateUserDetailDataRequest.getDialCode())
                        ? userMasterData.getDialCode()
                        : updateUserDetailDataRequest.getDialCode()
        );

        userMasterData.setPhoneNumber(
                Objects.isNull(updateUserDetailDataRequest.getPhoneNumber())
                        ? userMasterData.getPhoneNumber()
                        : updateUserDetailDataRequest.getPhoneNumber()
        );

        userRepository.save(userMasterData);
        commonAPIDataResponse.setMessage("User Details update SuccessFully");


        return commonAPIDataResponse;
    }



    @Override
    public GetUserDetailDataResponse getUserDetail(GetUserDetailDataRequest getUserDetailDataRequest) {
        GetUserDetailDataResponse getUserDetailDataResponse = new GetUserDetailDataResponse();

        UserMasterData userMasterData = userRepository.findByMainId(getUserDetailDataRequest.getUser_id());
        if (Objects.isNull(userMasterData)) {
            getUserDetailDataResponse.setCheckValidationFailed(true);
            getUserDetailDataResponse.setValidationMessage("USER_USER_DETAIL_DATA_NOT_FOUND");
            return getUserDetailDataResponse;
        }
        getUserDetailDataResponse.setUserDetail(new GetUserDetailDataResponse.UserDetail(userMasterData));
        return getUserDetailDataResponse;
    }

    @Override
    public GetUserDetailListDataResponse getUserDetailList() {
        GetUserDetailListDataResponse getUserDetailListDataResponse = new GetUserDetailListDataResponse();
        List<UserMasterData> userMasterDataList = userRepository.findAll();
        List<GetUserDetailListDataResponse.UserDetail> userDetailArrayList = new ArrayList<>();
        userMasterDataList.forEach(userMasterData -> {
            GetUserDetailListDataResponse.UserDetail userDetail = new GetUserDetailListDataResponse.UserDetail();

            userDetail.setId(userMasterData.getId());
            userDetail.setFirstName(userMasterData.getFirstName());
            userDetail.setLastName(userMasterData.getLastName());
            userDetail.setEmail(userMasterData.getEmail());
            userDetail.setDialCode(userMasterData.getDialCode());
            userDetail.setPhoneNumber(userMasterData.getPhoneNumber());
        });

        getUserDetailListDataResponse.setUsers(userDetailArrayList);

        return getUserDetailListDataResponse;
    }


    @Override
    public CommonAPIDataResponse deleteUserDetail(DeleteUserDetailDataRequest deleteUserDetailDataRequest) {
        CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
        UserMasterData userMaster = userRepository.findByMainId(deleteUserDetailDataRequest.getUser_id());

        if (userMaster == null) {
            commonAPIDataResponse.setCheckValidationFailed(true);
            commonAPIDataResponse.setValidationMessage("User Details Not Found");
            return commonAPIDataResponse;
        }

        int isRemoved = userRepository.removeUserDetail(deleteUserDetailDataRequest.getUser_id());
        if (isRemoved == 1) {
            commonAPIDataResponse.setMessage("User Details Deleted Successfully.....");

        } else {
            commonAPIDataResponse.setCheckValidationFailed(true);
            commonAPIDataResponse.setValidationMessage("User Details Not Delete SucessFully");
        }
        return commonAPIDataResponse;
    }
}
