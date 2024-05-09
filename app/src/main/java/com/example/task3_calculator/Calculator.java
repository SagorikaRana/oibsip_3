package com.example.task3_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {
    TextView allInput, input;
    MaterialButton c, ac, dot;
    MaterialButton devide, multiply, minus, plus, equals;
    MaterialButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    double firstNum, secondNum, result;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        allInput = findViewById(R.id.allIn);
        input = findViewById(R.id.input);

        c = findViewById(R.id.key_C);
        ac = findViewById(R.id.key_AC);
        dot = findViewById(R.id.key_dot);
        devide = findViewById(R.id.key_devide);
        multiply = findViewById(R.id.key_multiply);
        minus = findViewById(R.id.key_minus);
        plus = findViewById(R.id.key_plus);
        equals = findViewById(R.id.key_equals);
        b0 = findViewById(R.id.key_0);
        b1 = findViewById(R.id.key_1);
        b2 = findViewById(R.id.key_2);
        b3 = findViewById(R.id.key_3);
        b4 = findViewById(R.id.key_4);
        b5 = findViewById(R.id.key_5);
        b6 = findViewById(R.id.key_6);
        b7 = findViewById(R.id.key_7);
        b8 = findViewById(R.id.key_8);
        b9 = findViewById(R.id.key_9);


        ArrayList<Button> nums = new ArrayList<>();
        nums.add(b0);
        nums.add(b1);
        nums.add(b2);
        nums.add(b3);
        nums.add(b4);
        nums.add(b5);
        nums.add(b6);
        nums.add(b7);
        nums.add(b8);
        nums.add(b9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (!input.getText().toString().equals("0") && !input.getText().toString().equals("0.0")) {
                    input.setText(input.getText().toString() + b.getText().toString());
                } else {
                    input.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> opers = new ArrayList<>();
        opers.add(devide);
        opers.add(multiply);
        opers.add(minus);
        opers.add(plus);

        for (Button b : opers) {
            b.setOnClickListener(view -> {
                try {
                    operation = b.getText().toString();
                    firstNum = Double.parseDouble(input.getText().toString());
                    allInput.setText(input.getText().toString() + " " + operation);
                    if (allInput.getText().toString().endsWith("+") || allInput.getText().toString().endsWith("-") || allInput.getText().toString().endsWith("*") || allInput.getText().toString().endsWith("/")) {
                        result = firstNum;
                    }
                    input.setText(" ");
                } catch (Exception e) {
                    input.setText("Error");
                }
            });
        }

        ac.setOnClickListener(v -> {
            firstNum = 0;
            allInput.setText("");
            input.setText("0");
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = input.getText().toString();
                if (num.length() > 1) {
                    input.setText(num.substring(0, num.length() - 1));
                    if (input.getText().toString().equals("0")) {
                        allInput.setText("");
                    }
                } else if (num.length() <= 1 && !num.equals("0")) {
                    input.setText("0");
                    allInput.setText("");
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.getText().toString().contains(".")) {
                    input.setText(input.getText().toString() + ".");
                }
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    secondNum = Double.parseDouble(input.getText().toString());
                    allInput.setText(allInput.getText().toString() + " " + secondNum);


                    if (input.getText().toString().equals("0") || input.getText().toString().equals("0.0")) {
                        result = 0;
                    } else if (allInput.getText().toString().endsWith("+") || allInput.getText().toString().endsWith("-") || allInput.getText().toString().endsWith("*") || allInput.getText().toString().endsWith("/")) {
                        result = firstNum;
                    } else {
                        switch (operation) {
                            case "/":
                                result = firstNum / secondNum;
                                break;

                            case "*":
                                result = firstNum * secondNum;
                                break;

                            case "-":
                                result = firstNum - secondNum;
                                break;

                            case "+":
                                result = firstNum + secondNum;
                                break;

                            case "":
                                result = firstNum;
                                input.setText("");
                                allInput.setText("");
                                break;

                            default:
                                result = 0;
                        }
                    }

                    input.setText(String.valueOf(result));
                    firstNum = result;
                    operation = "";

                } catch (Exception e) {
                }
            }

        });

    }
}