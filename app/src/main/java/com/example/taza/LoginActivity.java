package com.example.taza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etNisn, etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        etNisn = findViewById(R.id.etNisn);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nisn = etNisn.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (nisn.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "NISN dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    // Cek ke Database
                    boolean checkLogin = dbHelper.checkUser(nisn, password);
                    if (checkLogin) {
                        Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Masuk ke Dashboard setelah login
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "NISN atau Password salah!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}