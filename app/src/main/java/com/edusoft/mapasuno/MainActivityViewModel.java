package com.edusoft.mapasuno;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<MapaActual> mldMapa;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<MapaActual> getMldMapa() {
        if(mldMapa==null){
            mldMapa=new MutableLiveData<>();
        }
        return mldMapa;
    }

    public void traerMapa(){

        MapaActual ma=new MapaActual();
        mldMapa.setValue(ma);
    }




    public class MapaActual implements OnMapReadyCallback {
        LatLng SANLUIS = new LatLng(-33.280576, -66.332482);
        LatLng ULP = new LatLng(-33.150720, -66.306864);

        @Override
        public void onMapReady(final GoogleMap mapa) {

            CameraPosition camPos = new CameraPosition.Builder().target(SANLUIS).zoom(19).bearing(45).tilt(70).build();
            CameraUpdate camUpdICT = CameraUpdateFactory.newCameraPosition(camPos);

            mapa.animateCamera(camUpdICT);

            mapa.addMarker(new MarkerOptions().position(SANLUIS)).setTitle("San Luis");
            mapa.addMarker(new MarkerOptions().position(ULP)).setTitle("ULP");
            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mapa.addMarker(new MarkerOptions().position(latLng)).setTitle("Marca");
                }
            });

        }


    }

}
