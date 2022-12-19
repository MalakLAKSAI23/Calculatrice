package com.example.malakcalcularice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;
    String workings="";
    String formula="";
    String tempFormula="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }
    private void setWorkings(String giveValue){
        workings=workings+giveValue;
        workingsTV.setText(workings);
    }

    private void initTextViews() {
        workingsTV=(TextView) findViewById(R.id.workingsTextView);
        resultsTV=(TextView) findViewById(R.id.resultTextView);
    }
    public void equalsOnClick(View view) {
        Double result=null;
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("rhino");
        checkpowerOf();
        try {
            result=(double)engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
        if(result !=null){
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkpowerOf() {
        ArrayList<Integer> indexOfPowers=new ArrayList<>();
        for(int i=0;i<workings.length();i++){
            if(workings.charAt(i)=='^'){
                indexOfPowers.add(i);
            }
        }
        formula=workings;
        tempFormula=workings;
        for(Integer index:indexOfPowers){
            changeformula(index);
        }
        formula=tempFormula;
    }

    private void changeformula(Integer index){
        String numberLeft="";
        String numberRight="";
        for(int i=index+1;i<workings.length();i++){
            if(isNumeric(workings.charAt(i))){
                numberRight=numberRight+workings.charAt(i);
            }
            else
                break;
        }

        for(int i=index-1;i>=0;i--){
            if(isNumeric(workings.charAt(i))){
                numberLeft=numberLeft+workings.charAt(i);
            }
            else
                break;
        }

        String original=numberLeft+"^"+numberRight;
        String changed="Math.pow("+numberLeft+","+numberRight+")";
        tempFormula=tempFormula.replace(original,changed);

    }

    private boolean isNumeric(char c){
        if((c<='9'&& c>='0')|| c=='.'){
            return true;
        }
        else{
            return false;
        }
    }

    public void clearOnclick(View view) {
        workingsTV.setText("");
        workings="";
        resultsTV.setText("");
        leftBracket=true;
    }

    boolean leftBracket=true;


    public void braketsOnClick(View view) {
        if(leftBracket){
            setWorkings("(");
            leftBracket=false;
        }
        else{
            setWorkings(")");
            leftBracket=true;
        }
    }

    public void powerOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view){
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }


}