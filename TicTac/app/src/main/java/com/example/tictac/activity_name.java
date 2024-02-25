package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tictac.databinding.ActivityMainBinding;
import com.example.tictac.databinding.ActivityNameBinding;

import java.util.jar.Attributes;

public class activity_name extends AppCompatActivity {

    private ActivityNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //между какими активити связь
                Intent intent = new Intent(activity_name.this, TicTacGameBoard.class);
                //ключ и получаемое значение
                intent.putExtra("name1", binding.PersonName1.getText().toString());
                intent.putExtra("name2", binding.PersonName2.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}