package com.example.proyectofinal_03;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class ActivityAltas extends Activity {

    EditText cajaNumControl, cajaNombre, cajaPrimerAp, cajaSegundoAp, cajaEdad;
    EditText cajaSemestre, cajaCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        //cajaNumControl = findViewById(R.id.caja_numcontrol_altas);
        //cajaNombre = findViewById(R.id.caja_nombre_altas);

    }

    public void agregarAlumno(View v){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = cm.getActiveNetwork();

        if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null ){
            //Obtener datos
            // String nc = cajaNumControl.getText().toString();
            //String n = cajaNombre.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://10.0.2.2/Programacion_web/Pruebas_PHP/Sistema_ABCC/API_REST_Mysql/api_altas.php";
                    String metodo = "POST";
                    Map<String, String> mapaDatos  =new HashMap<>();
                    mapaDatos.put("nc", "7");
                    mapaDatos.put("n", "7");
                    mapaDatos.put("pa", "7");
                    mapaDatos.put("sa", "7");
                    mapaDatos.put("e", "7");
                    mapaDatos.put("s", "7");
                    mapaDatos.put("c", "7");

                    AnalizadorJSON aJSON = new AnalizadorJSON();

                    JSONObject resultado = aJSON.peticionHTTP(url, metodo, mapaDatos);

                    String res = null;
                    try {
                        res = resultado.getString("exito");
                        String msj ="";
                        if (res.equals("true"))
                            msj = "AGREGADO CON EXITO";
                        else
                            msj = "ERROR EN LA INSERCION";
                        String finalMsj = msj;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), finalMsj, Toast.LENGTH_LONG).show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    /*
    public void agregarAlumno(View v){

        //Verificar que hay comunicacion a traves de WiFi
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()){

            //Obtener datos
            String nc = cajaNumControl.getText().toString();
            String n = cajaNombre.getText().toString();

            new AgregarAlumno().execute("7", "7", "7", "7", "7", "7");

            if(mensajeResultado.equals("true"))
                Toast.makeText(getBaseContext(), "Alumno agregado con EXITO", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "ME DEDICO A LAS REDES  ='(  ", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Error en WIF", Toast.LENGTH_LONG).show();
        }


    }

    //Clase Interna
    class AgregarAlumno extends AsyncTask<String, String, String> {

        @Override                           //VARARGS
        protected String doInBackground(String...datos) {

            String url = "http://10.0.2.2:8888/Semestre_Ago_Dic_2021/Pruebas_PHP/Sistema_ABCC/API_REST_MySQL/api_altas.php";
            String metodo = "POST";
            Map<String, String> mapaDatos  =new HashMap<>();
            mapaDatos.put("nc", datos[0]);
            mapaDatos.put("n", datos[1]);
            mapaDatos.put("n", datos[2]);
            mapaDatos.put("n", datos[3]);
            mapaDatos.put("n", datos[4]);
            mapaDatos.put("n", datos[5]);
            mapaDatos.put("n", datos[6]);



            AnalizadorJSON aJSON = new AnalizadorJSON();

            JSONObject resultado = aJSON.peticionHTTP(url, metodo, mapaDatos);

            String res = null;
            try {
                res = resultado.getString("exito");

                //opcion 2
                ActivityAltas.mensajeResultado = res;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //opcion 1
            return String.valueOf(res);
        }
    }
*/


}

