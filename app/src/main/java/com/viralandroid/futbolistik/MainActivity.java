package com.viralandroid.futbolistik;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Constants;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener{
    private ListView listview;
    private RestAdapter restAdapter;
    private RestAdapter restAdapterTB;
    private RestAdapter restAdapterG;
    private RestInterfaceController restInterface;
    private RestInterfaceControllerTB restInterfaceTB;
    private RestInterfaceControllerGaleri restInterfaceG;
    private ProgressDialog progressDialog;
    private List<Items> list = new ArrayList<Items>();
    private List<Items> listTB = new ArrayList<Items>();
    private List<Items> listG = new ArrayList<Items>();
    private CustomAdapter customAdapter;
    boolean isDataLoaded = false;
    boolean isDataLoadedTB = false;
    boolean isDataLoadedG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ab.addTab(ab.newTab().setText("HABERLER").setTabListener(this));
        ab.addTab(ab.newTab().setText("TARİHTE BUGÜN").setTabListener(this));
        ab.addTab(ab.newTab().setText("GALERİ").setTabListener(this));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
        .setMessage("Futbolistik uygulamasından çıkmak istediğinize emin misiniz?")
        .setCancelable(false)
        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        })
        .setNegativeButton("Hayır", null)
        .show();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                setContentView(R.layout.tab_icerik);
                listview = (ListView)findViewById(R.id.listview);

                restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL)
                    .build();

                restInterface = restAdapter.create(RestInterfaceController.class);
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Yükleniyor..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                restInterface.getJsonValues(new Callback<RetrofitModel[]>() {
                    @Override
                    public void success(RetrofitModel[] model, Response response) {
                        if(isDataLoaded==false) {
                            for (RetrofitModel modelValues : model) {
                                Items items = new Items();
                                items.sethbrbaslik(modelValues.hbrbaslik);
                                items.sethbrmanset(modelValues.hbrmanset);
                                items.setokunma(modelValues.okunma.toString());
                                items.sethbrid(modelValues.hbrid);
                                items.sethbrresim(modelValues.hbrresim);
                                items.sethbrtarih(modelValues.hbrtarih);
                                items.setyzradi(modelValues.yzradi);
                                items.setHbrmetni(modelValues.hbrmetni);
                                list.add(items);
                            }
                            isDataLoaded=true;
                        }
                        progressDialog.cancel();
                        customAdapter = new CustomAdapter(getApplicationContext(),list);
                        listview.setAdapter(customAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String merror = error.getMessage();
                        Toast.makeText(getApplicationContext(),merror,Toast.LENGTH_LONG).show();
                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("image_name",list.get(position).gethbrresim());
                    intent.putExtra("baslik", list.get(position).gethbrbaslik());
                    intent.putExtra("tarih", list.get(position).gethbrtarih());
                    intent.putExtra("yazar", list.get(position).getyzradi());
                    intent.putExtra("okunma", list.get(position).getokunma());
                    intent.putExtra("hbrmetni", list.get(position).getHbrmetni());
                    intent.putExtra("hbrid", list.get(position).gethbrid());
                    startActivity(intent);
                    }
                });
                break;
            case 1:
                setContentView(R.layout.tab_icerik);
                listview = (ListView)findViewById(R.id.listview);

                restAdapterTB = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL)
                    .build();

                restInterfaceTB = restAdapterTB.create(RestInterfaceControllerTB.class);
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Yükleniyor..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                restInterfaceTB.getJsonValues(new Callback<RetrofitModel[]>() {
                    @Override
                    public void success(RetrofitModel[] model, Response response) {
                        if(isDataLoadedTB==false) {
                            for (RetrofitModel modelValues : model) {
                                Items items = new Items();
                                items.sethbrbaslik(modelValues.hbrbaslik);
                                items.sethbrmanset(modelValues.hbrmanset);
                                items.setokunma(modelValues.okunma.toString());
                                items.sethbrid(modelValues.hbrid);
                                items.sethbrresim(modelValues.hbrresim);
                                items.sethbrtarih(modelValues.hbrtarih);
                                items.setyzradi(modelValues.yzradi);
                                items.setHbrmetni(modelValues.hbrmetni);
                                listTB.add(items);
                            }
                            isDataLoadedTB=true;
                        }
                        progressDialog.cancel();
                        customAdapter = new CustomAdapter(getApplicationContext(),listTB);
                        listview.setAdapter(customAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String merror = error.getMessage();
                        Toast.makeText(getApplicationContext(),merror,Toast.LENGTH_LONG).show();
                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("image_name",listTB.get(position).gethbrresim());
                    intent.putExtra("baslik", listTB.get(position).gethbrbaslik());
                    intent.putExtra("tarih", listTB.get(position).gethbrtarih());
                    intent.putExtra("yazar", listTB.get(position).getyzradi());
                    intent.putExtra("okunma", listTB.get(position).getokunma());
                    intent.putExtra("hbrmetni", listTB.get(position).getHbrmetni());
                    intent.putExtra("hbrid", listTB.get(position).gethbrid());
                    startActivity(intent);
                    }
                });
                break;
            case 2:
                setContentView(R.layout.tab_icerik);
                listview = (ListView)findViewById(R.id.listview);

                restAdapterG = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL)
                    .build();

                restInterfaceG = restAdapterG.create(RestInterfaceControllerGaleri.class);
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Yükleniyor..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                restInterfaceG.getJsonValues(new Callback<RetrofitModel[]>() {
                    @Override
                    public void success(RetrofitModel[] model, Response response) {
                        if(isDataLoadedG==false) {
                            for (RetrofitModel modelValues : model) {
                                Items items = new Items();
                                items.sethbrbaslik(modelValues.hbrbaslik);
                                items.sethbrmanset(modelValues.hbrmanset);
                                items.setokunma(modelValues.okunma.toString());
                                items.sethbrid(modelValues.hbrid);
                                items.sethbrresim(modelValues.hbrresim);
                                items.sethbrtarih(modelValues.hbrtarih);
                                items.setyzradi(modelValues.yzradi);
                                items.setHbrmetni(modelValues.hbrmetni);
                                listG.add(items);
                            }
                            isDataLoadedG=true;
                        }
                        progressDialog.cancel();
                        customAdapter = new CustomAdapter(getApplicationContext(),listG);
                        listview.setAdapter(customAdapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String merror = error.getMessage();
                        Toast.makeText(getApplicationContext(),merror,Toast.LENGTH_LONG).show();
                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("image_name",listG.get(position).gethbrresim());
                    intent.putExtra("baslik", listG.get(position).gethbrbaslik());
                    intent.putExtra("tarih", listG.get(position).gethbrtarih());
                    intent.putExtra("yazar", listG.get(position).getyzradi());
                    intent.putExtra("okunma", listG.get(position).getokunma());
                    intent.putExtra("hbrmetni", listG.get(position).getHbrmetni());
                    intent.putExtra("hbrid", listG.get(position).gethbrid());
                    startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                isDataLoaded=true;
                break;
            case 1:
                isDataLoadedTB=true;
                break;
            case 2:
                isDataLoadedG=true;
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Called when a tab is selected again.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
