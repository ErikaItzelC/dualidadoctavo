package com.example.emtrucking;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText fecha_diesel,lts_diesel,precio_diesel;
    Button btnagregar_diesel,btnbuscar_diesel,btneditar_diesel,btneliminar_diesel,btnmenudiesel;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lts_diesel=(EditText)findViewById(R.id.lts_diesel);
        precio_diesel=(EditText)findViewById(R.id.precio_diesel);
        btnagregar_diesel=(Button) findViewById(R.id.btnagregar_diesel);
        btnbuscar_diesel=(Button) findViewById(R.id.btnbuscar_diesel);
        btneditar_diesel=(Button) findViewById(R.id.btneditar_diesel);
        btneliminar_diesel=(Button) findViewById(R.id.btneliminar_diesel);
        btnmenudiesel=(Button)findViewById(R.id.btnmenu_diesel);
        fecha_diesel=(EditText) findViewById(R.id.fecha_diesel);

        findViewById(R.id.datepicker_diesel).setOnClickListener(new View.OnClickListener() {
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
        String date=month +"/" + dayOfMonth+ "/" +year;
        fecha_diesel.setText(date);

        btnmenudiesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Menu.class));
            }
        });

        btnagregar_diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.43.20/webservices_controlgastos/insertar_producto.php");
            }
        });

        btnbuscar_diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.43.20/webservices_controlgastos/buscar_producto.php?fecha="+fecha_diesel.getText()+"");
            }
        });

        btneditar_diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.43.20/webservices_controlgastos/editar_producto.php");
            }
        });

        btneliminar_diesel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDiesel("http://192.168.43.20/webservices_controlgastos/eliminar_producto.php");
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
                parametros.put("fecha",fecha_diesel.getText().toString());
                parametros.put("litros",lts_diesel.getText().toString());
                parametros.put("precio",precio_diesel.getText().toString());
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
                        fecha_diesel.setText(jsonObject.getString("fecha"));
                        lts_diesel.setText(jsonObject.getString("litros"));
                        precio_diesel.setText(jsonObject.getString("precio"));
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
                parametros.put("fecha",fecha_diesel.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void limpiarFormulario(){
        fecha_diesel.setText("");
        lts_diesel.setText("");
        precio_diesel.setText("");

    }


}
