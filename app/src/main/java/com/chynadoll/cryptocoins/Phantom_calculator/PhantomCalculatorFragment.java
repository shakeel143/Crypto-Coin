package com.chynadoll.cryptocoins.Phantom_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.mariuszgromada.math.mxparser.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chynadoll.cryptocoins.R;


public class PhantomCalculatorFragment extends Fragment {
    //variables\
    EditText display;
    Button btn1, btn2, btn3, btnPlus,
            btn4, btn5, btn6, btnSub,
            btn7, btn8, btn9, btnMul,
            btnParanth, btnExpo, btnDiv,
            btnClear, btnZero, btnPoint, btnEq;
    ImageButton btnBackspace;

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_calculator, null);

        btn1 = root.findViewById(R.id.btn_one);
        btn2 = root.findViewById(R.id.btn_two);
        btn3 = root.findViewById(R.id.btn_three);
        btnPlus = root.findViewById(R.id.btn_plus);

        btn4 = root.findViewById(R.id.btn_four);
        btn5 = root.findViewById(R.id.btn_five);
        btn6 = root.findViewById(R.id.btn_six);
        btnSub = root.findViewById(R.id.btn_minus);

        btn7 = root.findViewById(R.id.btn_seven);
        btn8 = root.findViewById(R.id.btn_eight);
        btn9 = root.findViewById(R.id.btn_nine);
        btnMul = root.findViewById(R.id.btn_mul);

        btnBackspace = root.findViewById(R.id.btn_backspace);
        btnParanth = root.findViewById(R.id.btn_paran);
        btnZero = root.findViewById(R.id.btn_zero);
        btnPoint = root.findViewById(R.id.btn_point);

        btnEq = root.findViewById(R.id.btn_eq);
        btnExpo = root.findViewById(R.id.btn_expo);
        btnDiv = root.findViewById(R.id.btn_divide);
        btnClear = root.findViewById(R.id.btn_clear);

        display = root.findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(view -> {
            if (getString(R.string.display).equals(display.getText().toString())) {
                display.setText(" ");
            }
        });

        btn1.setOnClickListener(view -> {
            updateText(getString(R.string.one));
        });
        btn2.setOnClickListener(view -> {
            updateText(getString(R.string.two));
        });
        btn3.setOnClickListener(view -> {
            updateText(getString(R.string.three));
        });

        btn4.setOnClickListener(view -> {
            updateText(getString(R.string.four));
        });
        btn5.setOnClickListener(view -> {
            updateText(getString(R.string.five));
        });
        btn6.setOnClickListener(view -> {
            updateText(getString(R.string.six));
        });

        btn7.setOnClickListener(view -> {
            updateText(getString(R.string.seven));
        });
        btn8.setOnClickListener(view -> {
            updateText(getString(R.string.eight));
        });
        btn9.setOnClickListener(view -> {
            updateText(getString(R.string.nine));
        });
        btnZero.setOnClickListener(view -> {
            updateText(getString(R.string.zero));
        });

        btnClear.setOnClickListener(view -> {
            display.setText(" ");
        });
        btnParanth.setOnClickListener(view -> {
            int curPos = display.getSelectionStart();
            int openPar = 0;
            int closePar = 0;
            int textLen = display.getText().length();

            for (int i = 0; i < curPos; i++) {
                if (display.getText().toString().substring(i, i + 1).equals("(")) {
                    openPar += 1;
                }
                if (display.getText().toString().substring(i, i + 1).equals(")")) {
                    closePar += 1;
                }
            }

            if (openPar == closePar || display.getText().toString().substring(textLen - 1, textLen)
                    .equals("(")) {
                updateText("(");
            } else if (closePar < openPar && !display.getText().toString().substring(textLen - 1, textLen)
                    .equals("(")) {
                updateText(")");
            }
            display.setSelection(curPos + 1);

        });
        btnExpo.setOnClickListener(view -> {
            updateText(getString(R.string.exponent));
        });


        btnPlus.setOnClickListener(view -> {
            updateText(getString(R.string.add));
        });
        btnMul.setOnClickListener(view -> {
            updateText(getString(R.string.mul));
        });
        btnSub.setOnClickListener(view -> {
            updateText(getString(R.string.minus));
        });

        btnDiv.setOnClickListener(view -> {
            updateText(getString(R.string.divide));
        });
        btnPoint.setOnClickListener(view -> {
            updateText(getString(R.string.point));
        });

        //eq
        btnEq.setOnClickListener(view -> {

            String userExp = display.getText().toString();

            //
            userExp = userExp.replaceAll(getString(R.string.divide), "/");
            userExp = userExp.replaceAll(getString(R.string.mul), "*");

            Expression exp = new Expression(userExp);
            String result = String.valueOf(exp.calculate());

            display.setText(result);

            display.setSelection(result.length());
        });
        btnBackspace.setOnClickListener(view -> {
            //  updateText(getString(R.string.mul));
            int cursorPos = display.getSelectionStart();
            int textLen = display.getText().length();
            if (cursorPos != 0 && textLen != 0) {
                SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                selection.replace(cursorPos - 1, cursorPos, "");
                display.setText(selection);
                display.setSelection(cursorPos - 1);
            }
        });

        return root;
    }

    public void updateText(String strtoAdd) {
        String odlStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = odlStr.substring(0, cursorPos);
        String rightStr = odlStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strtoAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strtoAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }
}