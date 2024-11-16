package com.user.api.userdetail.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.user.api.userdetail.model.request.DeleteUserDetailDataRequest;
import com.user.api.userdetail.model.request.GetUserDetailDataRequest;
import com.user.api.userdetail.model.request.SaveUserDetailDataRequest;
import com.user.api.userdetail.model.request.UpdateUserDetailDataRequest;
import com.user.api.userdetail.model.response.CommonAPIDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailListDataResponse;
import com.user.api.userdetail.model.response.SaveUserDetailResponse;
import com.user.api.userdetail.service.UserDetailRepository;
import com.user.constant.ErrorResponse;
import com.user.constant.SuccessResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailResourceHandler {

    private final UserDetailRepository userDetailRepository;

    public UserDetailResourceHandler(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }


    public ResponseEntity<JsonNode> getUserDetail(GetUserDetailDataRequest getUserDetailDataRequest) {

        if (getUserDetailDataRequest.checkBadRequest()) {
            return new ResponseEntity<>(generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
        }

        GetUserDetailDataResponse getUserDetailDataResponse = userDetailRepository.getUserDetail(getUserDetailDataRequest);

        if (getUserDetailDataResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(generateErrorResponse(getUserDetailDataResponse.getValidationMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<>(generateSuccessResponse(getUserDetailDataResponse), HttpStatus.OK);
    }


    public ResponseEntity<JsonNode> getUserList() {


        GetUserDetailListDataResponse getUserDetailListDataResponse = userDetailRepository.getUserDetailList();
        if (getUserDetailListDataResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(generateErrorResponse(getUserDetailListDataResponse.getValidationMessage()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(generateSuccessResponse(getUserDetailListDataResponse), HttpStatus.OK);
    }




    public ResponseEntity<JsonNode> saveUserDetail(SaveUserDetailDataRequest saveUserDetailDataRequest) {
        if (saveUserDetailDataRequest.checkBadRequest()) {
            return new ResponseEntity<>(generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
        }
        SaveUserDetailResponse saveUserDetailResponse = userDetailRepository.saveUserDetail(saveUserDetailDataRequest);
        if (saveUserDetailResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(generateErrorResponse(saveUserDetailResponse.getValidationMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<>(generateSuccessResponse(saveUserDetailResponse), HttpStatus.OK);
    }

    public ResponseEntity<JsonNode> updateUserDetail(UpdateUserDetailDataRequest updateUserDetailDataRequest) {


        if (updateUserDetailDataRequest.checkBadRequest()) {
            return new ResponseEntity<>(generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
        }

        CommonAPIDataResponse commonAPIDataResponse = userDetailRepository.updateUserDetail(updateUserDetailDataRequest);

        if (commonAPIDataResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
    }

    public ResponseEntity<JsonNode> deleteUserDetail(DeleteUserDetailDataRequest deleteUserDetailDataRequest) {

        if (deleteUserDetailDataRequest.checkBadRequest()) {
            return new ResponseEntity<>(generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
        }
        CommonAPIDataResponse commonAPIDataResponse = userDetailRepository.deleteUserDetail(deleteUserDetailDataRequest);
        if (commonAPIDataResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
    }


    public static JsonNode generateSuccessResponse(Object object) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setSuccess(1);
        successResponse.setData(object);
        successResponse.setError(new ArrayList<>());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.convertValue(successResponse, JsonNode.class);
    }

    public static JsonNode generateErrorResponse(String message) {
        ErrorResponse successResponse = new ErrorResponse();
        successResponse.setSuccess(0);
        successResponse.setData(new Object());
        ArrayList<String> errors = new ArrayList<>();
        errors.add(message);
        successResponse.setError(errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.convertValue(successResponse, JsonNode.class);
    }

}
