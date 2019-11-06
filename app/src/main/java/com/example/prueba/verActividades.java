package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class verActividades extends AppCompatActivity {

    private ArrayList<String> actividad;
    private ArrayAdapter<String> adaptador1;
    private ListView listViewv1;
    private EditText editTextt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_actividades);

        actividad=new ArrayList<String>();
        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, actividad);
        listViewv1=(ListView)findViewById(R.id.listview);
        listViewv1.setAdapter(adaptador1);

        editTextt1=(EditText)findViewById(R.id.editText);
        listViewv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(verActividades.this);
                dialogo1.setTitle("! OJO YA REALIZASTE LA TAREA !");
                dialogo1.setMessage("¿ QUITAR LA ACTIVIDAD YA REALIZADA DE LA LISTA ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        actividad.remove(posicion);
                        adaptador1.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });

    }

    public void agregar(View v) {
        if(editTextt1.getText().toString().equals("")){
            System.out.println("INGRESA UNA ACTIVIDAD!");
            String titulo = "NO SE GUARDO LA ACTIVIDAD";
            String mensajeAlerta= "DEBES INGRESAR UNA NUEVA ACTIVIDAD";
            alertas(titulo,mensajeAlerta);
        }else {
            String act = editTextt1.getText().toString();
            actividad.add(editTextt1.getText().toString());

            adaptador1.notifyDataSetChanged();
            editTextt1.setText("");
        }
    }


    public void alertas(String titulo, String cadena) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(cadena);
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.create().show();
    }
}
