package com.ignacio.godrive.actividades.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.ignacio.godrive.R;
import com.ignacio.godrive.actividades.PrincipalActividad;
import com.ignacio.godrive.includes.MyToolBar;
import com.ignacio.godrive.proveedores.ProveedoresAutenticacion;
public class MapaClienteActividad extends AppCompatActivity implements OnMapReadyCallback {

    ProveedoresAutenticacion proveedoresAutenticacion;
    private GoogleMap mapa;
    private SupportMapFragment mapaFragmento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_mapa_cliente_actividad);
        MyToolBar.show(this, "Cliente", false);
        proveedoresAutenticacion = new ProveedoresAutenticacion();
        mapaFragmento = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapaFragmento.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapa.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(-30.602966125971275, -71.20295857297063))
                        .zoom(17f)
                        .build()
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cliente_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cerrarSesion){
            cerrarSesion();
        }
        return super.onOptionsItemSelected(item);
    }

    void cerrarSesion() {
        proveedoresAutenticacion.cerrarSesion();
        Intent intent = new Intent(MapaClienteActividad.this, PrincipalActividad.class);
        startActivity(intent);
        finish();
    }
};