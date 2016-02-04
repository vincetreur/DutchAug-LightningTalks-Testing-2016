package com.appsingularity.testing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mInputView;
    private TextView mResultView;
    private TextView mErrorView;

    private Model mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputView = (EditText) findViewById(R.id.input);
        mResultView = (TextView) findViewById(R.id.result);
        mErrorView = (TextView) findViewById(R.id.error);

        View btn = findViewById(R.id.btn_calculate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(mInputView.getText().toString());
            }
        });

        mModel = new Model();
    }


    private void calculateResult(@NonNull String input) {
        try {
            int number = Integer.valueOf(input);
            showResult(mModel.isNumberEven(number));
        } catch (NumberFormatException nfe) {
            showError();
        }
    }

    private void showError() {
        mResultView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    private void showResult(boolean isEven) {
        mResultView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mResultView.setText(isEven ? R.string.result_even : R.string.result_odd);
    }

}
