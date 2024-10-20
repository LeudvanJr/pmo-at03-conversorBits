/*
 *@author:Leudvan Guedes
 */
package br.edu.fateczl.conversorbits;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EditText etQtdBits;
    private Spinner spUnidade;
    private Button btnConverter;
    private final String[] unidade = {"B", "KB", "MB", "GB", "TB"};
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        etQtdBits = findViewById(R.id.etQtdBits);
        spUnidade = findViewById(R.id.spUnidade);
        btnConverter = findViewById(R.id.btnConverter);
        tvResultado = findViewById(R.id.tvResultado);

        preencherSpinnerUnidades();
        btnConverter.setOnClickListener(op -> converter());
    }

    private void converter() {
        String etToString = etQtdBits.getText().toString();
        if(etToString.isEmpty()){
            tvResultado.setText(R.string.semValores);
            return;
        }
        double qtdBits = Double.parseDouble(etToString);
        int posUnidade = spUnidade.getSelectedItemPosition();
        BigDecimal res = new BigDecimal(qtdBits/Math.pow(2,3+(posUnidade*10)));

        tvResultado.setText(res+unidade[posUnidade]);
    }

    private void preencherSpinnerUnidades() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,unidade);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnidade.setAdapter(adapter);
    }
}