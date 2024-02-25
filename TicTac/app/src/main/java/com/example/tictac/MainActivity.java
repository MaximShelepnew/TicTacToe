package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tictac.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //между какими активити связь
                Intent intent = new Intent(MainActivity.this, activity_name.class);
                //ключ и получаемое значение
                // intent.putExtra("city1", binding.userFild.getText().toString());
                //String url="https://api.openweathermap.org/data/2.5/weather?q="+binding.userFild.getText().toString()+"&appid=cde6ce1422ba67d4a8db763ee58d254a&units=metric&lang=ru";
                // intent.putExtra("weather1", binding.answer.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }
}