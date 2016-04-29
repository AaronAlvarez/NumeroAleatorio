package com.example.aaron.numeroaleatorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

     EditText txtNum;
     TextView ganados,type;
     int wscore,win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        txtNum=(EditText)findViewById(R.id.txtNum);
        ganados=(TextView)findViewById(R.id.ganados);
        type=(TextView)findViewById(R.id.lbl_type);

        random();
        board();
    }

    public void random(){
        wscore = 1 + (int) (Math.random() * ((50 - 1) + 1));
    }
    public int getWgame(){
        return wscore;
    }
    public void setWin(int win) {
        this.win = win;
    }
    public int getWin() {
        return win;
    }

    public void board(){
        SharedPreferences overwall_score=getSharedPreferences("puntos", Context.MODE_PRIVATE);
        setWin(overwall_score.getInt("ganados",0));
        if(getWin()==0){
            saveScore(0);
        }
        ganados.setText("Juegos Ganados: "+getWin() );
    }

    public void saveScore(int number){
        SharedPreferences preferencias=getSharedPreferences("puntos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putInt("wins",number);
        editor.commit();
    }


    public void game(int user_answer){
        if(getWgame()==user_answer){
            random();
            setWin(getWin()+1);
            saveScore(getWin());

            ganados.setText("Juegos Ganados: " + getWin());
            type.setText("Resultado:"+"\n"+"Lo adivinaste felicidades");
            txtNum.setText("");
        }else{
            if(user_answer>getWgame()){
                type.setText("Resultado :"+"\n"+"El numero es menor");
            }else{
                type.setText("Resultado :"+"\n"+"El numero es mayor");
            }
        }
    }

    public void check(View view){
        String num = txtNum.getText().toString();
        if (!num.equals("")) {
            int answer_n = Integer.parseInt(num);
            game(answer_n);
        }else{
            type.setText("Resultado:"+"\n"+"Captura un numero");
        }
    }
}


