package controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class AnalizadorJSON {

    InputStream is = null;
    OutputStream os = null;
    JSONObject jsonObject = null;

    HttpURLConnection conexion = null;
    URL url = null;

    //---------- METODO PARA Altas, Bajas y Cambios
    public JSONObject peticionHTTP(String cadenaURL, String metodo, Map datos){

        //{"nc":"1", "n":"1", "pa":"1", "sa":"1", "e":1, "s":1, "c":"1"}

        try {

            //Enviar PETICION ----------------------------
            String ncCodificado = URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8");

            String cadenaJSON = "{\"nc\":\"" + URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8") +
                    "\", \"n\":\"" + URLEncoder.encode(String.valueOf(datos.get("n")), "UTF-8") +
                    "\", \"pa\":\"" + URLEncoder.encode(String.valueOf(datos.get("pa")), "UTF-8") +
                    "\", \"sa\":\"" + URLEncoder.encode(String.valueOf(datos.get("sa")), "UTF-8") +
                    "\", \"e\":\"" + URLEncoder.encode(String.valueOf(datos.get("e")), "UTF-8") +
                    "\", \"s\":\"" + URLEncoder.encode(String.valueOf(datos.get("s")), "UTF-8") +
                    "\", \"c\":\"" + URLEncoder.encode(String.valueOf(datos.get("c")), "UTF-8") + "\"}";

            url = new URL(cadenaURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activa el envio a traves de HTTP
            conexion.setDoOutput(true);

            //indicar el metodo de evio
            conexion.setRequestMethod(metodo);

            //tamaño preestablecido o fijo para la cadena a enviar
            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            //Establecer el formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());

            os.flush();
            os.close();

        } catch (UnsupportedEncodingException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Recibir RESPUESTA ----------------------
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila = null;
            while ((fila = br.readLine()) != null ){
                cad.append(fila+"\n");
            }
            is.close();

            jsonObject = new JSONObject(String.valueOf(cad));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;

    }//metodo PETICION HTTP


    //----------METODO PARA Consultas
    public JSONObject peticionHTTPConsultas(String cadenaURL, String metodo, String filtro){

        //FILTRO

        filtro = "{\"nc\":\""+filtro+"\"}";

        try {

            //Enviar PETICION ----------------------------

            //String filtroCodificado = URLEncoder.encode(String.valueOf(filtro), "UTF-8");
            //completar para busquedas con FILTRO

            url = new URL(cadenaURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activa el envio a traves de HTTP
            conexion.setDoOutput(true);

            //indicar el metodo de evio
            conexion.setRequestMethod(metodo);

            //tamao preestablecido o fijo para la cadena a enviar
            conexion.setFixedLengthStreamingMode(filtro.length());
            //NECESARIO PARA CONSUTLAS CON FILTRO

            //Establecer el formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(filtro.getBytes());

            /*

            //NECESARIO PARA CONSUTLAS CON FILTRO */
            os.flush();
            os.close();

        } catch (UnsupportedEncodingException | MalformedURLException e) {
            Log.d("--->", "trono");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("--->", "trono");
            e.printStackTrace();
        }

        //Recibir RESPUESTA ----------------------
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila = null;
            while ((fila = br.readLine()) != null ){
                cad.append(fila+"\n");
            }
            is.close();

            String cadena = cad.toString();
            //Log.d("--->", cadena);
            jsonObject = new JSONObject(cadena);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;

    }//consultas

    public JSONObject peticionHTTPmod(String cadenaURL, String metodo, Map datos){

        //{"nc":"1", "n":"1", "pa":"1", "sa":"1", "e":1, "s":1, "c":"1"}

        try {

            //Enviar PETICION ----------------------------
            String ncCodificado = URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8");

            String cadenaJSON = "{\"nc\":\"" + URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8") +
                    "\", \"n\":\"" + URLEncoder.encode(String.valueOf(datos.get("n")), "UTF-8") +
                    "\", \"pa\":\"" + URLEncoder.encode(String.valueOf(datos.get("pa")), "UTF-8") +
                    "\", \"sa\":\"" + URLEncoder.encode(String.valueOf(datos.get("sa")), "UTF-8") +
                    "\", \"e\":\"" + URLEncoder.encode(String.valueOf(datos.get("e")), "UTF-8") +
                    "\", \"s\":\"" + URLEncoder.encode(String.valueOf(datos.get("s")), "UTF-8") +
                    "\", \"c\":\"" + URLEncoder.encode(String.valueOf(datos.get("c")), "UTF-8") + "\"}";

            url = new URL(cadenaURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activa el envio a traves de HTTP
            conexion.setDoOutput(true);

            //indicar el metodo de evio
            conexion.setRequestMethod(metodo);

            //tamaño preestablecido o fijo para la cadena a enviar
            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            //Establecer el formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());

            os.flush();
            os.close();

        } catch (UnsupportedEncodingException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Recibir RESPUESTA ----------------------
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila = null;
            while ((fila = br.readLine()) != null ){
                cad.append(fila+"\n");
            }
            is.close();

            jsonObject = new JSONObject(String.valueOf(cad));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;

    }//metodo PETICION HTTP
    public JSONObject peticionHTTPelim(String cadenaURL, String metodo, Map datos){

        //{"nc":"1", "n":"1", "pa":"1", "sa":"1", "e":1, "s":1, "c":"1"}

        try {

            //Enviar PETICION ----------------------------
            String ncCodificado = URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8");

            String cadenaJSON = "{\"nc\":\"" + URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8")+ "\"}";

            url = new URL(cadenaURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activa el envio a traves de HTTP
            conexion.setDoOutput(true);

            //indicar el metodo de evio
            conexion.setRequestMethod(metodo);

            //tamaño preestablecido o fijo para la cadena a enviar
            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            //Establecer el formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());

            os.flush();
            os.close();

        } catch (UnsupportedEncodingException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Recibir RESPUESTA ----------------------
        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila = null;
            while ((fila = br.readLine()) != null ){
                cad.append(fila+"\n");
            }
            is.close();

            jsonObject = new JSONObject(String.valueOf(cad));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return  jsonObject;

    }//metodo PETICION HTTP
}//class


