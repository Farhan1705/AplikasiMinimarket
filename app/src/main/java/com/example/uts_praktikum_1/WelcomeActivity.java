package com.example.uts_praktikum_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.uts_praktikum_1.databinding.ActivityWelcomeBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;
    private ViewPagerAdapter adapter;
    private int currentTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarMain);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("MINIMARKET BANGKRUT");
        }

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new semuanyaFragment(), "Semuanya");
        adapter.addFragment(new makananfragment(), "Makanan");
        adapter.addFragment(new kebutuhanFragment(), "Keperluan");
        adapter.addFragment(new permainanfragment(), "Permainan");

        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                updateTotalFromCurrentFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        binding.btnBayar.setOnClickListener(v -> {
            if (currentTotal > 0) {
                Toast.makeText(this, "Pesanan Berhasil Dibayar! Total: " + formatToRupiah(currentTotal), Toast.LENGTH_LONG).show();
                resetAllFragments();
            } else {
                Toast.makeText(this, "Keranjang masih kosong!", Toast.LENGTH_SHORT).show();
            }
        });
        
        updateTotalPrice(0);
    }

    private void resetAllFragments() {
        for (int i = 0; i < adapter.getCount(); i++) {
            Fragment fragment = adapter.getItem(i);
            if (fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).resetItems();
            }
        }
        updateTotalPrice(0);
    }

    private void updateTotalFromCurrentFragment() {
        Fragment currentFragment = adapter.getItem(binding.viewPager.getCurrentItem());
        if (currentFragment instanceof BaseFragment) {
            updateTotalPrice(((BaseFragment) currentFragment).getTotalPrice());
        }
    }

    public void updateTotalPrice(int total) {
        this.currentTotal = total;
        binding.tvTotalPrice.setText(formatToRupiah(total));
    }

    private String formatToRupiah(int total) {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return formatRupiah.format(total).replace("Rp", "Rp. ").replace(",00", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment currentFragment = adapter.getItem(binding.viewPager.getCurrentItem());

        // Mengganti penggunaan interface dengan akses langsung ke RecyclerView
        if (currentFragment instanceof BaseFragment) {
            RecyclerView recyclerView = ((BaseFragment) currentFragment).getRecyclerView();
            if (recyclerView != null) {
                if (id == R.id.action_list) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    return true;
                } else if (id == R.id.action_grid) {
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}