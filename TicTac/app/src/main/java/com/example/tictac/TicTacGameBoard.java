package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tictac.databinding.ActivityTicTacGameBoardBinding;

public class TicTacGameBoard extends AppCompatActivity {

    String[] nameUser = {"1", "2"};


    private ActivityTicTacGameBoardBinding binding;

 //   private Board boardTicTac;
//    public TicTacGameBoard(GameLogic gameLogic) {
//        this.gameLogic = gameLogic;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

 //       boardTicTac = binding.board.findViewById(binding.board.getId());

        super.onCreate(savedInstanceState);
        binding = ActivityTicTacGameBoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        nameUser[0]=getIntent().getStringExtra("name1");
        nameUser[1]=getIntent().getStringExtra("name2");
        // binding.textView8.setText(binding.board.setNumberPlayer());
        if (getIntent().getStringExtra("name1").trim().equals("")){
            nameUser[0]="Первый игрок";
        }
        binding.textView8.setText(nameUser[0]+"(нолик)");
        //binding.textView8.setTextColor(getResources().getColor(R.color.Ocolor));
        binding.board.setUpGame(findViewById(R.id.textView8), nameUser);

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.board.reset_board();
                binding.board.invalidate();
            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //между какими активити связь
                Intent intent = new Intent(TicTacGameBoard.this, activity_name.class);
                startActivity(intent);
                finish();
            }
        });

        binding.buttonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacGameBoard.this, tableVictory.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
