package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.uts_praktikum_1.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mengambil data username yang dikirim melalui Intent
        String username = getIntent().getStringExtra("USERNAME");

        if (username != null) {
            binding.tvUsername.setText("Selamat Datang, " + username + "!");
        }


        // Tugas
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new ExploreFragment(), "Explore");
        adapter.addFragment(new AccountFragment(), "Account");

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.tvSuccessMessage.setVisibility(View.GONE);
                binding.tvUsername.setVisibility(View.GONE);

                binding.layoutTugasFragment.setVisibility(View.VISIBLE);
            }
        }, 2000); // 2000 milidetik = 2 detik
    }
}