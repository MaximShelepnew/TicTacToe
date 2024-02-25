package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.tictac.databinding.ActivityTableVictoryBinding;
import com.example.tictac.databinding.ActivityTicTacGameBoardBinding;

public class tableVictory extends AppCompatActivity {

    private ActivityTableVictoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableVictoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.buttonBackGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tableVictory.this, TicTacGameBoard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}