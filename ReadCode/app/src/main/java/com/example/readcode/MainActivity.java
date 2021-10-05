package com.example.readcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private TextView mFormatTextView;

    private TextView mContentTextView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mFormatTextView = (TextView) findViewById(R.id.scan_format);

        mContentTextView = (TextView) findViewById(R.id.scan_content);

    }
    public void onClick(View view) {

        IntentIntegrator scanIntegrator = new IntentIntegrator(this);

        scanIntegrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,

                                    Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(

                requestCode, resultCode, intent);

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();

            String scanFormat = scanningResult.getFormatName();

            mFormatTextView.setText("FORMAT: " + scanFormat);

            mContentTextView.setText("CONTENT: " + scanContent);

        } else {

            Toast toast = Toast.makeText(getApplicationContext(),

                    "No scan data received!", Toast.LENGTH_SHORT);

            toast.show();

        }

    }

}