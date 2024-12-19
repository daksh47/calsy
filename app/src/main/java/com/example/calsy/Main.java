package com.example.calsy;

import static java.lang.Math.round;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void cal(View v){
        String val=((Button)v).getText().toString().trim();
        switch (val)
        {
            case "C":((TextView)findViewById(R.id.textView2)).setText("0");
                break;
            case "1":append('1',0);
                break;
            case "2":append('2',0);
                break;
            case "3":append('3',0);
                break;
            case "4":append('4',0);
                break;
            case "5":append('5',0);
                break;
            case "6":append('6',0);
                break;
            case "7":append('7',0);
                break;
            case "8":append('8',0);
                break;
            case "9":append('9',0);
                break;
            case "0":append('0',0);
                break;
            case ".":append('.',2);
                break;
            case "⌫":String d=((TextView)findViewById(R.id.textView2)).getText().toString().trim();
                     if(d.length()==1) ((TextView)findViewById(R.id.textView2)).setText("0");
                     else ((TextView)findViewById(R.id.textView2)).setText(d.substring(0,d.length()-1));
                break;
            case "÷":append('÷',1);
                break;
            case "+":append('+',1);
                break;
            case "-":append('-',1);
                break;
            case "X":append('x',1);
                break;
            case "=":calsy(((TextView)findViewById(R.id.textView2)).getText().toString().trim());
                break;
        }
    }
    private boolean isNum(char c){
        try{
            int a = Integer.parseInt(String.valueOf(c));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    private void calsy(String prev){
        String expression = prev.replaceAll("x","*").replaceAll("÷","/");
        Expression exp = new ExpressionBuilder(expression).build();
        double result = exp.evaluate();
        ((TextView) findViewById(R.id.textView2)).setText("= "+result);
    }

    private void append(char c,int code) {
        String prev = ((TextView) findViewById(R.id.textView2)).getText().toString().trim();
        if (prev.length() == 1) {
            if (prev.equals("0")) {
                if (code == 0) {
                    ((TextView) findViewById(R.id.textView2)).setText(c + "");
                } else {
                    ((TextView) findViewById(R.id.textView2)).setText(prev + c);
                }
            } else {
                if (code == 0) {
                    ((TextView) findViewById(R.id.textView2)).setText(prev + c);
                } else {
                    try {
                        int a = Integer.parseInt(String.valueOf(prev.charAt(prev.length() - 1)));
                        ((TextView) findViewById(R.id.textView2)).setText(prev + c);
                    } catch (Exception e) {
                        ((TextView) findViewById(R.id.textView2)).setText(prev.substring(0, prev.length() - 1) + c);
                    }
                }
            }
        } else {
            if (code == 0) {
                ((TextView) findViewById(R.id.textView2)).setText(prev + c);
            } else if (code == 2) {
                String su="";
                for(int i=prev.length()-1;i>=0;i--){
                    if(!isNum(prev.charAt(i))){
                        su=prev.substring(i,prev.length());
                        break;
                    }
                }
                if(!su.contains("."))((TextView) findViewById(R.id.textView2)).setText(prev + c);
            } else {
                try {
                    int a = Integer.parseInt(String.valueOf(prev.charAt(prev.length() - 1)));
                    ((TextView) findViewById(R.id.textView2)).setText(prev + c);
                } catch (Exception e) {
                    ((TextView) findViewById(R.id.textView2)).setText(prev.substring(0, prev.length() - 1) + c);
                }
            }
        }
    }
}
