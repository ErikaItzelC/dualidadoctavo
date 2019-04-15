package com.example.emtrucking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button buttonDiesel,buttonReparaciones,buttonViajes,btn_resul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonDiesel=(Button)findViewById(R.id.btncambiar_diesel);
        buttonReparaciones=(Button)findViewById(R.id.btncambiar_reparaciones);
        buttonViajes=(Button)findViewById(R.id.btncambiar_viajes);
        btn_resul=(Button)findViewById(R.id.btn_resul);

        buttonDiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,MainActivity.class));
            }
        });
        buttonReparaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,Reparaciones.class));
            }
        });
        buttonViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,viajes.class));
            }
        });
        btn_resul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,resultados.class));
            }
        });
    }
}
