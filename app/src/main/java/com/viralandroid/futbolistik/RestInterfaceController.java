package com.viralandroid.futbolistik;

import retrofit.Callback;
import retrofit.http.GET;

public interface RestInterfaceController {
    @GET("/api/haberler.php?islem=son50")
    void getJsonValues(Callback<RetrofitModel[]> response);
}