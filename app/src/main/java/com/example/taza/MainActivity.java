package com.example.taza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView; // Jangan lupa import TextView
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView menuProfil, menuCari, menuPengaturan, menuKeluar;
    TextView tvNamaUser; // Tambahkan variabel untuk teks nama di header

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Mengacu pada file main.xml (dashboard menu grid)

        // Inisialisasi ID dari XML
        menuProfil = findViewById(R.id.menu_profil);
        menuCari = findViewById(R.id.menu_cari);
        menuPengaturan = findViewById(R.id.menu_pengaturan);
        menuKeluar = findViewById(R.id.menu_keluar);
        tvNamaUser = findViewById(R.id.tvNamaUser); // Inisialisasi TextView nama di header

        // Set nama user di header (kamu bisa ganti sesuai nama/akun yang login)
        tvNamaUser.setText("Nanas");

        // Tombol Cari Data dipencet -> Pindah ke DashboardActivity (Daftar Siswa)
        menuCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        // Tombol Keluar dipencet -> Kembali ke LoginActivity dan hapus riwayat activity
        menuKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}