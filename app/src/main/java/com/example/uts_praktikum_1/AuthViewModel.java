package com.example.uts_praktikum_1;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.concurrent.Executors;

public class AuthViewModel extends AndroidViewModel {

    private DatabaseHelper databaseHelper;
    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = new DatabaseHelper(application);
    }

    public LiveData<Boolean> getLoginStatus() { return loginStatus; }

    public LiveData<Boolean> getRegistrationStatus() { return registrationStatus; }

    public void register(String username, String password) {
        Executors.newSingleThreadExecutor().execute(() -> {
            boolean isRegistered = databaseHelper.addUser(username, password);
            registrationStatus.postValue(isRegistered);
        });
    }

    public void login(String username, String password) {
        Executors.newSingleThreadExecutor().execute(() -> {
            boolean isLoggedIn = databaseHelper.checkUser(username, password);
            loginStatus.postValue(isLoggedIn);
        });
    }
}