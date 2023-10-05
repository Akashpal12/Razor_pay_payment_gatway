package com.example.paymentgetway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener{

    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPay=findViewById(R.id.btnPay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();

                Checkout.preload(getApplicationContext());// build in loader

                Checkout.clearUserData(getApplicationContext());// shered prefrence clear

                Checkout checkout=new Checkout();

                checkout.setKeyID("rzp_test_HNgCYoQZ9HmHb2");

                try {
                    JSONObject options = new JSONObject();

                    options.put("name", "Payment Gatway");
                    options.put("description", "Reference No. #123456");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                    options.put("order_id", "order_LCIQIkSvEYCBgS");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    options.put("amount", "500");//pass amount in currency subunits
                    options.put("prefill.email", "firozm613@gmail.com");
                    options.put("prefill.contact","9936211796");
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);

                    checkout.open(MainActivity.this, options);

                } catch(Exception e) {
                    Log.d("paymetngatway", "Error in starting Razorpay Checkout", e);
                }




            }
        });

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
    }
}