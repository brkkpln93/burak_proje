package com.viralandroid.futbolistik;

import retrofit.Callback;
import retrofit.http.GET;

public interface RestInterfaceControllerGaleri {
    @GET("/api/haberler.php?islem=galeri")
    void getJsonValues(Callback<RetrofitModel[]> response);
}