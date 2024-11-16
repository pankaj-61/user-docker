package com.user.api.userdetail.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.user.api.userdetail.handler.UserDetailResourceHandler;
import com.user.api.userdetail.model.request.DeleteUserDetailDataRequest;
import com.user.api.userdetail.model.request.GetUserDetailDataRequest;
import com.user.api.userdetail.model.request.SaveUserDetailDataRequest;
import com.user.api.userdetail.model.request.UpdateUserDetailDataRequest;
import com.user.constant.APIRequestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.baseurl}")
public class UserDetailController {

    @Autowired
    private UserDetailResourceHandler userDetailResourceHandler;


    @PostMapping(APIRequestURL.USER_DETAIL_POST_API_URL)
    public ResponseEntity<JsonNode> saveUserDetail(@RequestBody SaveUserDetailDataRequest saveUserDetailDataRequest) {
        return userDetailResourceHandler.saveUserDetail(saveUserDetailDataRequest);
    }

    @PutMapping(APIRequestURL.USER_DETAIL_PUT_API_URL)
    public ResponseEntity<JsonNode> updateUserDetail(@RequestBody UpdateUserDetailDataRequest updateUserDetailDataRequest) {
        return userDetailResourceHandler.updateUserDetail(updateUserDetailDataRequest);
    }

    @GetMapping(APIRequestURL.USER_DETAIL_GET_ALL_API_URL)
    public ResponseEntity<JsonNode> getUserDetailList(@ModelAttribute GetUserDetailDataRequest getUserDetailDataRequest) {
        return userDetailResourceHandler.getUserDetail(getUserDetailDataRequest);
    }

    @GetMapping(APIRequestURL.USER_DETAIL_GET_API_URL)
    public ResponseEntity<JsonNode> getUserDetail(@PathVariable Long id, @ModelAttribute GetUserDetailDataRequest getUserDetailDataRequest) {
        getUserDetailDataRequest.setUser_id(id);
        return userDetailResourceHandler.getUserDetail(getUserDetailDataRequest);
    }


    @DeleteMapping(APIRequestURL.USER_DETAIL_DELETE_API_URL)
    public ResponseEntity<JsonNode> deleteUserDetail(@PathVariable Long id, @ModelAttribute DeleteUserDetailDataRequest deleteUserDetailDataRequest) {
        deleteUserDetailDataRequest.setUser_id(id);
        return userDetailResourceHandler.deleteUserDetail(deleteUserDetailDataRequest);
    }

}
