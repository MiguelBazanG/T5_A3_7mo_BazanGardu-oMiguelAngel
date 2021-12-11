package com.example.proyectofinal_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usuario, contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.caja_usu);
        contra = findViewById(R.id.caja_p);
    }
    public void abrirMenu(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btn_is:
                i = new Intent(this, ActivityMenu.class);
                startActivity(i);
                break;
            case R.id.btn_reg:
                break;
        }
    }
}