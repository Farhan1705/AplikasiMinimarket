package com.example.uts_praktikum_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.uts_praktikum_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;
    private boolean isLoginAttempted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getLoginStatus().observe(this, isSuccess -> {
            if (isSuccess) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else if (isLoginAttempted) {
                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                isLoginAttempted = false;
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            String user = binding.edtUsername.getText().toString().trim();
            String pass = binding.edtPassword.getText().toString().trim();
            if (!user.isEmpty() && !pass.isEmpty()) {
                isLoginAttempted = true;
                authViewModel.login(user, pass);
            } else {
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}