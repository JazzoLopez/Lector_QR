package com.example.lector_qr;
//importando librerias
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo; //Orientacion vertical
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {

private TextView showResult;
private Button btnReadQr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Siempre trabjaar despues de estos
        //Variables

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnReadQr=findViewById(R.id.btn_search_QR);
        showResult=findViewById(R.id.show_result);



        //Creando escuchador para el boton
        btnReadQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrater= new IntentIntegrator(MainActivity.this);
                //Todas estas vienen de la libreria de zxing.
                integrater.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES); //Todos los formatos de codigo
                integrater.setCameraId(0); //= es la camara trasera y 1 la frontal
                integrater.setBarcodeImageEnabled(true);
                integrater.setBeepEnabled(false);
                integrater.setOrientationLocked(false);
                integrater.initiateScan(); //Iniciamos


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result_Scan = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if( result_Scan !=null) //Cuando regresa informacion
        {
            if(result_Scan.getContents() == null){ // Se cancelo el proceso
                Toast.makeText(this,"Cancelada",Toast.LENGTH_LONG).show();
            }
            else{
                showResult.setText(result_Scan.getContents());
                Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show();

            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data); //Si no hicimos nada esta igual
        }
    }
}