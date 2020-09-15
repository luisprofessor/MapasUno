package com.edusoft.mapasuno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {
private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solicitarPermiso();
        inicializar();
    }


    public void inicializar(){

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getMldMapa().observe(this, new Observer<MainActivityViewModel.MapaActual>() {
            @Override
            public void onChanged(MainActivityViewModel.MapaActual mapaActual) {
                SupportMapFragment smf=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.frMap);
                smf.getMapAsync(mapaActual);
            }
        });
        vm.traerMapa();

    }


    public void solicitarPermiso(){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.INTERNET},1000);
        }
    }

}