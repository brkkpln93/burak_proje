package com.viralandroid.futbolistik;

import retrofit.Callback;
import retrofit.http.GET;

public interface RestInterfaceControllerTB {
    @GET("/api/haberler.php?islem=tarihtebugun")
    void getJsonValues(Callback<RetrofitModel[]> response);
}