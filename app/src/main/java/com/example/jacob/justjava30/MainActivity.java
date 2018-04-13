package com.example.jacob.justjava30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText caja;
    private Button siguiente, reiniciar;
    private TextView resultado;
    private RadioButton rb_secante, rb_newton, rb_falsaPosicion, rb_biseccion;
    private double datos[]; //el arreglo donde se guardan los datos pedidos
    private String variablesAPedir[]; //define los datos que se van a pedir como strin, mas abajo
    int j = 1; //contador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caja = (EditText) findViewById(R.id.main_et_cajita);
        siguiente = (Button) findViewById(R.id.siguiente);
        reiniciar = (Button) findViewById(R.id.reiniciar);
        resultado = (TextView) findViewById(R.id.resultado);
        rb_secante = (RadioButton) findViewById(R.id.main_rb_secante);
        rb_newton = (RadioButton) findViewById(R.id.main_rb_newton);
        rb_falsaPosicion = (RadioButton) findViewById(R.id.main_rb_falsaposicion);
        rb_biseccion = (RadioButton) findViewById(R.id.main_rb_biseccion);
        //en vez de usar onClick en las vistas, lo asigno desde el codigo, con la interfaz onClickListener
        //el metodo onClick es el redefinido de la interfaz, por eso uso this, para referenciarlo
        reiniciar.setOnClickListener(this);
        siguiente.setOnClickListener(this);
        rb_secante.setOnClickListener(this);
        rb_newton.setOnClickListener(this);
        rb_falsaPosicion.setOnClickListener(this);
        rb_biseccion.setOnClickListener(this);
    }
    //decido que hacer, segun aquello a lo que se haga click
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.siguiente)
            capturarVariables();
        else if(id == R.id.reiniciar)
            hacerReinicio();
        else
            seleccionMetodo();
    }
    //defino las variables que se pediran, segun el metodo, luego defino el tamaño de el arreglo datos
    private void seleccionMetodo() {
        if (rb_biseccion.isChecked())
            variablesAPedir = new String[]{"A", "B", "C", "D", "E", "X", "Y", "N"};
        else if (rb_newton.isChecked())
            variablesAPedir = new String[]{"A", "B", "C", "D", "E", "P0", "N", "tolerancia", "X", "Y"};
        else if (rb_falsaPosicion.isChecked())
            variablesAPedir = new String[]{"A", "B", "C", "D", "E", "X", "Y"};
        else if (rb_secante.isChecked())
            variablesAPedir = new String[]{"A", "B", "C", "D", "E", "P0", "Pi", "N"};
        datos = new double[variablesAPedir.length];
        preparar();
    }
    /*metodo que mando a llamar si se presiona siguiente, manda un toast si esta vacio, sino,
        lo guarda en el arreglo datos, y asigna el hint al dato siguiente, en caso de que se
        hayan pedido todos los datos, manda la ejecucion del resultado*/
    private void capturarVariables() {
        String s = caja.getText().toString();
        if (s.isEmpty()) {
            Toast.makeText(this, "Esta vacio", Toast.LENGTH_SHORT).show();
        }else {
            datos[j - 1] = Double.parseDouble(s);
            caja.setText("");
            if(j < datos.length )
                caja.setHint("Ingrese " + variablesAPedir[j]);
            else
                hacerResultado();
            j++;
        }
    }
    //manda a llamar el metodo correspondiente a la clase Metodos, con sus respectivos datos
    private void hacerResultado(){
        Metodos m = new Metodos();
        double res = 0;
        if (rb_biseccion.isChecked()) {
            res = m.Biseccion(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7]);
        } else if (rb_newton.isChecked()) {
            res =m.Newton(datos[8],datos[9],datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7]);
        } else if (rb_falsaPosicion.isChecked()) {
            res = m.ReglaFalsa(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6]);
        } else if (rb_secante.isChecked()) {
            res = m.Secante(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7]);
        }
        resultado.setVisibility(View.VISIBLE);
        resultado.setText(String.valueOf(res));
        hacerReinicio();
    };
    //prepara la caja para ingresar datos
    public void preparar() {
        j = 1;
        caja.setVisibility(View.VISIBLE);
        caja.setHint("Ingrese " + variablesAPedir[j - 1]);
    }
    //devuelve a la normalidad, menos el resultado, eso se puede modificar..
    private void hacerReinicio(){
        j = 1;
        caja.setVisibility(View.INVISIBLE);
        rb_biseccion.setChecked(false);
        rb_secante.setChecked(false);
        rb_falsaPosicion.setChecked(false);
        rb_newton.setChecked(false);
    }
}
