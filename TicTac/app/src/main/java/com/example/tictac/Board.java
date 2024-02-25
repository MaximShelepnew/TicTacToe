package com.example.tictac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {

    private final int boardColor;
    private final int Ocolor;
    private final int Xcolor;
    private final int LineColor;
    private float cellSize;
    private final GameLogic gameLogic;
    private final Paint paint = new Paint();


    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gameLogic = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Board, 0, 0);

        try {
            boardColor = a.getInteger(R.styleable.Board_boardColor, 0);
            Ocolor = a.getInteger(R.styleable.Board_Ocolor, 0 );
            Xcolor = a.getInteger(R.styleable.Board_Xcolor, 0 );
            LineColor = a.getInteger(R.styleable.Board_LineColor, 0 );
        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure( int width, int height){
        super.onMeasure(width, height);

        float dimensions = Math.min(getMeasuredHeight(), getMeasuredWidth());
        cellSize = (float)((dimensions-dimensions*0.1)/3);
        setMeasuredDimension((int)(dimensions-dimensions*0.1), (int)(dimensions-dimensions*0.1));//размер таблицы для игры
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if (gameLogic.updateGameBoard(row, col)){
                invalidate();

                if (gameLogic.getPlayer() % 2 == 0){
                    gameLogic.setPlayer(gameLogic.getPlayer()-1);
                }
                else {
                    gameLogic.setPlayer(gameLogic.getPlayer()+1);
                }
            }

            invalidate();
            return true;
        }
        return false;
    }
    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int i=1;i<3;i++){
            canvas.drawLine(cellSize*i, 0, cellSize*i, canvas.getWidth(), paint);
        }
        for (int j=1;j<3;j++){
            canvas.drawLine(0, cellSize*j, canvas.getWidth(), cellSize*j, paint);
        }
    }

    private void drawMarkers(Canvas canvas){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               if (gameLogic.getGameBoard()[i][j] != 0){
                   if (gameLogic.getGameBoard()[i][j] == 1){
                       drawX(canvas,i,j);
                   }
                   else {
                       drawO(canvas, i, j);
                   }
               }
            }
        }
        if (gameLogic.getGameBoard()[0][0]==gameLogic.getGameBoard()[1][1] &
                gameLogic.getGameBoard()[1][1]==gameLogic.getGameBoard()[2][2] &
                    gameLogic.getGameBoard()[0][0]!=0){
            drawLine(canvas, 1);
        }
        if (gameLogic.getGameBoard()[0][0]==gameLogic.getGameBoard()[0][1] &
                gameLogic.getGameBoard()[0][1]==gameLogic.getGameBoard()[0][2] &
                gameLogic.getGameBoard()[0][0]!=0){
            drawLine(canvas, 2);
        }
        if (gameLogic.getGameBoard()[0][0]==gameLogic.getGameBoard()[1][0] &
                gameLogic.getGameBoard()[1][0]==gameLogic.getGameBoard()[2][0] &
                gameLogic.getGameBoard()[0][0]!=0){
            drawLine(canvas, 3);
        }
        if (gameLogic.getGameBoard()[1][0]==gameLogic.getGameBoard()[1][1] &
                gameLogic.getGameBoard()[1][1]==gameLogic.getGameBoard()[1][2] &
                gameLogic.getGameBoard()[1][0]!=0){
            drawLine(canvas, 4);
        }
        if (gameLogic.getGameBoard()[2][0]==gameLogic.getGameBoard()[2][1] &
                gameLogic.getGameBoard()[2][1]==gameLogic.getGameBoard()[2][2] &
                gameLogic.getGameBoard()[2][0]!=0){
            drawLine(canvas, 5);
        }
        if (gameLogic.getGameBoard()[2][0]==gameLogic.getGameBoard()[1][1] &
                gameLogic.getGameBoard()[1][1]==gameLogic.getGameBoard()[0][2] &
                gameLogic.getGameBoard()[0][2]!=0){
            drawLine(canvas, 6);
        }
        if (gameLogic.getGameBoard()[0][1]==gameLogic.getGameBoard()[1][1] &
                gameLogic.getGameBoard()[1][1]==gameLogic.getGameBoard()[2][1] &
                gameLogic.getGameBoard()[0][1]!=0){
            drawLine(canvas, 7);
        }
        if (gameLogic.getGameBoard()[0][2]==gameLogic.getGameBoard()[1][2] &
                gameLogic.getGameBoard()[1][2]==gameLogic.getGameBoard()[2][2] &
                gameLogic.getGameBoard()[0][2]!=0){
            drawLine(canvas, 8);
        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(Xcolor);

        canvas.drawLine((float)((col+1)*cellSize-cellSize*0.15),
                        (float)(row*cellSize+cellSize*0.15),
                        (float)(col*cellSize+cellSize*0.15),
                        (float)((row+1)*cellSize-cellSize*0.15),
                        paint);
        canvas.drawLine((float)(col*cellSize+cellSize*0.15),
                        (float)(row*cellSize+cellSize*0.15),
                        (float)((col+1)*cellSize-cellSize*0.15),
                        (float)((row+1)*cellSize-cellSize*0.15),
                        paint);
    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(Ocolor);

        canvas.drawOval((float) (col*cellSize+cellSize*0.15),
                        (float) (row*cellSize+cellSize*0.15),
                        (float) ((col*cellSize+cellSize)-cellSize*0.15),
                        (float) ((row*cellSize+cellSize)-cellSize*0.15),
                        paint);
    }

    private void drawLine(Canvas canvas, int varLine) {
        paint.setColor(Xcolor);
        switch (varLine){
            case 1:
                canvas.drawLine((float) (0),
                        (float) (0),
                        (float) (3 * cellSize),
                        (float) (3 * cellSize),
                        paint);
                break;
            case 2:
                canvas.drawLine((float) (0),
                        (float) (0+0.5*cellSize),
                        (float) (3 * cellSize),
                        (float) (0+0.5*cellSize),
                        paint);
                break;
            case 3:
                canvas.drawLine((float) (0+0.5*cellSize),
                        (float) (0),
                        (float) (0+0.5*cellSize),
                        (float) (3 * cellSize),
                        paint);
                break;
            case 4:
                canvas.drawLine((float) (0),
                        (float) (cellSize+0.5*cellSize),
                        (float) (3*cellSize),
                        (float) (cellSize+0.5*cellSize),
                        paint);
                break;
            case 5:
                canvas.drawLine((float) (0),
                        (float) (2*cellSize+0.5*cellSize),
                        (float) (3 * cellSize),
                        (float) (2*cellSize+0.5*cellSize),
                        paint);
                break;
            case 6:
                canvas.drawLine((float) (3 * cellSize),
                        (float) (0),
                        (float) (0),
                        (float) (3 * cellSize),
                        paint);
                break;
            case 7:
                canvas.drawLine((float) (1.5 * cellSize),
                        (float) (0),
                        (float) (1.5 * cellSize),
                        (float) (3 * cellSize),
                        paint);
                break;
            case 8:
                canvas.drawLine((float) (2.5 * cellSize),
                        (float) (0),
                        (float) (2.5 * cellSize),
                        (float) (3 * cellSize),
                        paint);
                break;
        }

    }
    public void reset_board(){
        gameLogic.reset_board();
    }

    public void setUpGame(TextView playerDisplay, String[] nameUser){
        gameLogic.setPlayerDisplay(playerDisplay, nameUser);
    }

}
