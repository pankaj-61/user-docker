package com.user.api.userdetail.service;

import com.user.api.userdetail.model.request.DeleteUserDetailDataRequest;
import com.user.api.userdetail.model.request.GetUserDetailDataRequest;
import com.user.api.userdetail.model.request.SaveUserDetailDataRequest;
import com.user.api.userdetail.model.request.UpdateUserDetailDataRequest;
import com.user.api.userdetail.model.response.CommonAPIDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailDataResponse;
import com.user.api.userdetail.model.response.GetUserDetailListDataResponse;
import com.user.api.userdetail.model.response.SaveUserDetailResponse;

public interface UserDetailRepository {


    GetUserDetailDataResponse getUserDetail(GetUserDetailDataRequest getUserDetailDataRequest);

    GetUserDetailListDataResponse getUserDetailList();

    SaveUserDetailResponse saveUserDetail(SaveUserDetailDataRequest saveUserDetailDataRequest);

    CommonAPIDataResponse updateUserDetail(UpdateUserDetailDataRequest updateUserDetailDataRequest);

    CommonAPIDataResponse deleteUserDetail(DeleteUserDetailDataRequest deleteUserDetailDataRequest);

}
