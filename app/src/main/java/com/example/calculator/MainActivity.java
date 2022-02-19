package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }

    private void updateText(String stringToAdd){
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(stringToAdd);
            display.setSelection(cursorPosition + 1);
        } else {
            display.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
            display.setSelection(cursorPosition + 1);
        }

    }

    public void zeroBTN(View view) {
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void subtractBTN(View view){
        updateText("-");
    }

    public void divideBTN(View view){
        updateText("/");
    }

    public void multiplyBTN(View view){
        updateText("x");
    }

    public void clearBTN(View view){
        display.setText("");
    }

    public void parBTN(View view){
        int cursorPosition = display.getSelectionStart();
        int openParanthesis = 0;
        int closedParanthesis = 0;
        int textLength = display.getText().length();

        for(int i = 0; i < cursorPosition; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")) {
                openParanthesis += 1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")) {
                closedParanthesis += 1;
            }
        }

        if (openParanthesis == closedParanthesis || display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateText("(");
        }
        else if (closedParanthesis < openParanthesis || !display.getText().toString().substring(textLength - 1, textLength).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPosition + 1);
    }

    public void expBTN(View view){
        updateText("^");
    }

    public void plusMinusBTN(View view){
        updateText("-");
    }

    public void decimalBTN(View view){
        updateText(".");
    }

    public void equalsBTN(View view){
        String userExpression = display.getText().toString();

        userExpression = userExpression.replaceAll("x", "*");

        Expression exp = new Expression(userExpression);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view){
        int cursorPosition = display.getSelectionStart();
        int textLength = display.getText().length();

        if (cursorPosition != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            display.setText(selection);
            display.setSelection(cursorPosition - 1);
        }
    }
}