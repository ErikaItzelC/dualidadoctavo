package com.example.emtrucking;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class viajes extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edtfecha_viaje,edtmillas,edtpago;
    Button btnagregar_viaje,btnbuscar_viaje,btneditar_viaje,btneliminar_viaje,btnmenuviajes;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viajes);

        edtfecha_viaje=(EditText)findViewById(R.id.edtfecha_viaje);
        edtmillas=(EditText)findViewById(R.id.edtmillas);
        edtpago=(EditText)findViewById(R.id.edtpago);
        btnagregar_viaje=(Button) findViewById(R.id.btnagregar_viaje);
        btnbuscar_viaje=(Button) findViewById(R.id.btnbuscar_viaje);
        btneditar_viaje=(Button) findViewById(R.id.btneditar_viaje);
        btneliminar_viaje=(Button) findViewById(R.id.btneliminar_viaje);
        btnmenuviajes=(Button)findViewById(R.id.btnmenu_viajes);

        findViewById(R.id.datepicker_viajes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        edtfecha_viaje.setText(date);

        btnmenuviajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viajes.this,Menu.class));
            }
        });

        btnagregar_viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.43.20/webservices_viajes/insertar_producto.php");
            }
        });

        btnbuscar_viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.43.20/webservices_viajes/buscar_producto.php?fecha_viaje="+edtfecha_viaje.getText()+"");
            }
        });

        btneditar_viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.43.20/webservices_viajes/editar_producto.php");
            }
        });

        btneliminar_viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDiesel("http://192.168.43.20/webservices_viajes/eliminar_producto.php");
            }
        });
    }
    private void ejecutarServicio (String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("fecha_viaje",edtfecha_viaje.getText().toString());
                parametros.put("millas",edtmillas.getText().toString());
                parametros.put("pago",edtpago.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtfecha_viaje.setText(jsonObject.getString("fecha_viaje"));
                        edtmillas.setText(jsonObject.getString("millas"));
                        edtpago.setText(jsonObject.getString("pago"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void eliminarDiesel (String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EL REGISTRO FUE ELIMINADO", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("fecha_viaje",edtfecha_viaje.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void limpiarFormulario(){
        edtfecha_viaje.setText("");
        edtmillas.setText("");
        edtpago.setText("");

    }




}
