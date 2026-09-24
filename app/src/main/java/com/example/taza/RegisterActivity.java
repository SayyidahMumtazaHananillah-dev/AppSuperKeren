package com.example.taza;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etFullName, etNisn, etEmail, etUsername, etPhone, etPassword, etConfirmPassword;
    Button btnRegister;
    TextView tvLogin;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        etFullName = findViewById(R.id.etFullName);
        etNisn = findViewById(R.id.etNisn);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = etFullName.getText().toString().trim();
                String nisn = etNisn.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                // 1. Cek kolom kosong
                if (fullname.isEmpty() || nisn.isEmpty() || email.isEmpty() || username.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                }
                // 2. Cek NISN harus pas 10 digit
                else if (nisn.length() != 10) {
                    etNisn.setError("NISN harus terdiri dari 10 digit angka!");
                    etNisn.requestFocus();
                }
                // 3. Cek format email wajib ada '@' (dan domain valid)
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Format email tidak valid (harus ada @)!");
                    etEmail.requestFocus();
                }
                // 4. Cek kesesuaian password
                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password dan Konfirmasi Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
                // 5. Simpan ke SQLite
                else {
                    boolean isInserted = dbHelper.registerUser(fullname, nisn, email, username, phone, password);
                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Registrasi Berhasil! Silakan Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registrasi Gagal (NISN atau Email sudah terdaftar)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}