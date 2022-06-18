package com.admiralgroup.kalkulyator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.admiralgroup.kalkulyator.R;


public class MainActivity extends AppCompatActivity {
    Button btnClear, btnBackspace, btnDel, btnMultiply, btnMinus,
    btnPlus, btnDot, btnEqual;
    EditText editText;
    TextView result;
    String total = "";
    String res = "";
    boolean operator_inserted = false;
    boolean dot_inserted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect();

    }

    public void connect(){
        btnClear = findViewById(R.id.btnClear);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnDel = findViewById(R.id.btnDel);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnDot = findViewById(R.id.btnDot);
        btnEqual = findViewById(R.id.btnEqual);
        editText = findViewById(R.id.editText);
        result = findViewById(R.id.result);
    }

    public void pressButton(View view){
        Button btn = (Button) view;
        String text = (String) btn.getText();
        switch (btn.getId()){

            case R.id.btnBackspace:
                total = total.substring(0, total.length()-1);
                res = "0";
                break;

            case R.id.btnClear:
                total = "";
                res = "0";
                operator_inserted = false;
                break;

            case R.id.btnEqual:
                btEqual();
                break;

            case R.id.btnDot:
                hasDot();
                break;

            case R.id.btnPlus:
                total = total + " + ";
                operator_inserted = true;
                break;
            case R.id.btnDel:
                total = total + " / ";
                operator_inserted = true;
                break;
            case R.id.btnMultiply:
                total = total + " * ";
                operator_inserted = true;
                break;
            case R.id.btnMinus:
                total = total + " - ";
                operator_inserted = true;
                break;


            default:
                total = total + text;

        }
        editText.setText(total);
        result.setText(res);

    }

    public void btEqual(){

        if (operator_inserted==true&&!(total.charAt(total.length()-1)==' ')) {
            String [] tokens=total.split(" ");

            switch (tokens[1].charAt(0)) {
                case '+':
                    res = Double.toString(Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[2]));
                    break;
                case '-':
                    res = Double.toString(Double.parseDouble(tokens[0]) - Double.parseDouble(tokens[2]));
                    break;
                case '*':
                    res = Double.toString(Double.parseDouble(tokens[0]) * Double.parseDouble(tokens[2]));
                    break;
                case '/':
                    res = Double.toString(Double.parseDouble(tokens[0]) / Double.parseDouble(tokens[2]));
                    break;
            }
            result.setText(res);
        }
    }

    private void hasDot() {


        if (total.isEmpty()){
            total="0.";
        }else {
            String [] tokens=total.split(" ");
            for (int i = 0; i < tokens[tokens.length - 1].length(); i++) {
                if (tokens[tokens.length - 1].charAt(i) == '.') {
                    dot_inserted = true;
                    break;
                }else {
                    char a=tokens[tokens.length - 1].charAt(i);
                    if (a=='-'||a=='+'||a=='/'||a=='*'){
                        total = total + "0.";
                        dot_inserted = true;
                    }else {
                        dot_inserted = false;
                    }
                }

            }
            if (!dot_inserted){
                total = total +".";
            }

        }
    }

}