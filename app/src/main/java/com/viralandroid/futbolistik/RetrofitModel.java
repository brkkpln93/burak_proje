package com.viralandroid.futbolistik;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitModel {
    @SerializedName("hbrid")
    @Expose
    public String hbrid;
    @SerializedName("hbrbaslik")
    @Expose
    public String hbrbaslik;
    @SerializedName("hbrresim")
    @Expose
    public String hbrresim;
    @SerializedName("okunma")
    @Expose
    public Integer okunma;
    @SerializedName("hbrmetni")
    @Expose
    public String hbrmetni;
    @SerializedName("yzradi")
    @Expose
    public String yzradi;
    @SerializedName("hbrmanset")
    @Expose
    public String hbrmanset;
    @SerializedName("hbrtarih")
    @Expose
    public String hbrtarih;
}