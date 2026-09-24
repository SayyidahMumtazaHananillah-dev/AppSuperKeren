package com.example.taza;

import android.os.Bundle;
import android.widget.ImageView; // Tambahkan import ini
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class DashboardActivity extends AppCompatActivity {

    TextInputEditText searchEditText;
    ImageView btnBack; // Deklarasikan variabel tombol back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        searchEditText = findViewById(R.id.searchEditText);
        btnBack = findViewById(R.id.btnBack); // Hubungkan dengan ID di XML

        // Berikan aksi klik untuk kembali ke halaman sebelumnya/main
        btnBack.setOnClickListener(v -> {
            finish(); // Menutup activity ini dan kembali ke activity sebelumnya
        });

        // Listener pencarian opsional
        searchEditText.setOnKeyListener((v, keyCode, event) -> {
            return false;
        });
    }
}