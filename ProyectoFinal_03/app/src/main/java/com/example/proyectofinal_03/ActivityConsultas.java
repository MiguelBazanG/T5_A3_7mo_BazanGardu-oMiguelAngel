package com.example.proyectofinal_03;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controlador.AnalizadorJSON;

public class ActivityConsultas extends Activity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    EditText consulta ;
    static ArrayList registros = new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        recyclerView = findViewById(R.id.rv_con);
        recyclerView.setHasFixedSize(true);
        consulta = (EditText) findViewById(R.id.consulta);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        registros.clear();

        consulta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String url = "http://10.0.2.2/Programacion_web/Pruebas_PHP/Sistema_ABCC/API_REST_Mysql/api_consultas.php";
                        String metodo = "POST";
                        AnalizadorJSON aJSON = new AnalizadorJSON();
                        JSONObject resultado = aJSON.peticionHTTPConsultas(url, metodo, consulta.getText().toString());

                        // Log.d("---->", resultado.toString());

                        try {
                            JSONArray datos = resultado.getJSONArray("alumnos");
                            String cad = "";
                            for (int i = 0; i < datos.length(); i++) {
                                cad = datos.getJSONObject(i).getString("nc")+ " | " +
                                        datos.getJSONObject(i).getString("n")+ " | " +
                                        datos.getJSONObject(i).getString("pa")+ " | " +
                                        datos.getJSONObject(i).getString("sa")+ " | " +
                                        datos.getJSONObject(i).getString("e")+ " | " +
                                        datos.getJSONObject(i).getString("s")+ " | " +
                                        datos.getJSONObject(i).getString("c");
                                ActivityConsultas.registros.add(cad);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new AdaptadorRegistros(registros);
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }//run
                }).start();

                //---------------------------------------------------

                adapter = new AdaptadorRegistros(registros);
                recyclerView.setAdapter(adapter);
                registros.clear();
            }
        });

        //String registros[] = {"Padme", "Leia", "Luke", "Han"}; //registros de prueba

        // ------------- Obtener los registros ---------------

        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = "http://10.0.2.2/Programacion_web/Pruebas_PHP/Sistema_ABCC/API_REST_Mysql/api_consultas.php";
                String metodo = "POST";
                AnalizadorJSON aJSON = new AnalizadorJSON();
                JSONObject resultado = aJSON.peticionHTTPConsultas(url, metodo, consulta.getText().toString());

                // Log.d("---->", resultado.toString());

                try {
                    JSONArray datos = resultado.getJSONArray("alumnos");
                    String cad = "";
                    for (int i = 0; i < datos.length(); i++) {
                        cad = datos.getJSONObject(i).getString("nc")+ " | " +
                                datos.getJSONObject(i).getString("n")+ " | " +
                                datos.getJSONObject(i).getString("pa")+ " | " +
                                datos.getJSONObject(i).getString("sa")+ " | " +
                                datos.getJSONObject(i).getString("e")+ " | " +
                                datos.getJSONObject(i).getString("s")+ " | " +
                                datos.getJSONObject(i).getString("c");
                        ActivityConsultas.registros.add(cad);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new AdaptadorRegistros(registros);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }//run
        }).start();

        //---------------------------------------------------

        adapter = new AdaptadorRegistros(registros);
        recyclerView.setAdapter(adapter);
        registros.clear();



    }
}

class AdaptadorRegistros extends RecyclerView.Adapter<AdaptadorRegistros.MyViewHolder>{
    private ArrayList datos;
    public AdaptadorRegistros(ArrayList datos){
        this.datos = datos;
    }
    @NonNull
    @Override
    public AdaptadorRegistros.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_recyclerview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(textView);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.textView.setText((Integer) datos.get(position));
        holder.textView.setText((CharSequence) datos.get(position));
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    //clase INTERNA
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(TextView t){
            super(t);
            textView = t;
        }
    }
}//Clase AdaptadorRegistros