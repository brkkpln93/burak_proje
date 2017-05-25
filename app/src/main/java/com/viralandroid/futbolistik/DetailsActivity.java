package com.viralandroid.futbolistik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.util.HashMap;
import java.util.Map;

import utils.Constants;

public class DetailsActivity extends AppCompatActivity {
    private ImageView img;
    private TextView baslik;
    private TextView icerik;
    private TextView tarih;
    private TextView yazar;
    RequestQueue requestQueue;
    String insertUrl = "http://burak-kaplan.com.tr/_projects/futbolistik_script/api/haberler.php?islem=guncelle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        Bundle bundle = getIntent().getExtras();
        String photo_name = bundle.getString("image_name");
        String haber_baslik = bundle.getString("baslik");
        String haber_tarih = bundle.getString("tarih");
        String haber_metni = bundle.getString("hbrmetni");
        String haber_okunma = bundle.getString("okunma");
        String haber_yazar = bundle.getString("yazar");
        final String haber_id = bundle.getString("hbrid");

        img = (ImageView)findViewById(R.id.img_detail);
        Picasso.with(getApplicationContext()).load(Constants.URL_IMAGES+photo_name).into(img);
        baslik = (TextView)findViewById(R.id.baslik);
        baslik.setText(haber_baslik);
        tarih = (TextView)findViewById(R.id.tarih);
        tarih.setText(Html.fromHtml("&raquo;") + " Tarih: " + haber_tarih + "  |  " + haber_okunma + " kere okundu.");
        yazar = (TextView)findViewById(R.id.yazar);
        yazar.setText(Html.fromHtml("&raquo;") + " Yazar: " + haber_yazar);
        icerik = (TextView)findViewById(R.id.icerik);
        icerik.setText(Html.fromHtml(haber_metni));

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("haberidsi", haber_id);
                return parameters;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        overridePendingTransition(0, 0);
    }
}