package com.hp.grocerystore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hp.grocerystore.application.GRCApplication;
import com.hp.grocerystore.model.user.DeactivateOTP;
import com.hp.grocerystore.model.user.DeviceInfoResponse;
import com.hp.grocerystore.model.user.UpdatePasswordRequest;
import com.hp.grocerystore.network.RetrofitClient;
import com.hp.grocerystore.network.api.AuthApi;
import com.hp.grocerystore.network.api.UserApi;
import com.hp.grocerystore.repository.AuthRepository;
import com.hp.grocerystore.repository.UserRepository;
import com.hp.grocerystore.utils.Resource;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    public ProfileViewModel() {
        this.authRepository = AuthRepository.getInstance(RetrofitClient.getAuthApi());
        this.userRepository = UserRepository.getInstance(RetrofitClient.getUserApi());
    }

    public LiveData<Resource<Void>> logout() {
        return authRepository.logout();
    }

    public LiveData<Resource<Void>> updatePassword(UpdatePasswordRequest request) {
        return userRepository.updatePassword(request);
    }

    public LiveData<Resource<List<DeviceInfoResponse>>> getLoggedInDevices(){
        return userRepository.getLoggerInDevice();
    }

    public LiveData<Resource<Void>> requestDeactivateAccount(){
        return userRepository.requestDeactivateAccount();
    }

    public LiveData<Resource<Void>> confirmDeactivateAccount(String otp){
        return userRepository.confirmDeactivateAccount(new DeactivateOTP(otp));
    }
}
