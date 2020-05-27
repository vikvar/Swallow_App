package com.example.swallow_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardInformationActivity extends AppCompatActivity {

    private TextView cardName, cardNumber;
    private ImageView QRcode, barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);
        TextView toolbar = findViewById(R.id.toolbar_text);
        String name = getIntent().getStringExtra("Name");
        String number = getIntent().getStringExtra("Number");
        toolbar.setText(name);

        initData(number);
        cardName = findViewById(R.id.cardName);
        cardNumber = findViewById(R.id.cardNumber);
        cardName.setText(name);
        cardNumber.setText(number);

        ImageView toolBarBtn = findViewById(R.id.action_back);
        toolBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CardsActivity.class);
        startActivity(intent);
        finish();
    }

    private void initData(String number)
    {
        QRcode = findViewById(R.id.QRCode);
        barcode = findViewById(R.id.barcode);

        try
        {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(number, BarcodeFormat.QR_CODE, 350, 300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            QRcode.setImageBitmap(bitmap);
            bitMatrix = multiFormatWriter.encode(number, BarcodeFormat.CODE_128, 400, 170, null);
            barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            barcode.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
        }
    }
}
