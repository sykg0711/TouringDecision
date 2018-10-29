package sy.touringdecision;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MyLocationListener implements LocationListener {

    //距離の算出方法はkilometer(定数値)×multiple(倍率)
    //現状は倍率も低数値だがそのうち可変とする。
    //地球の直径
    final double kilometer = 0.010966404715491394;
    //距離倍率
    public float multiple = 50;

    //緯度を適当に決定する
    private double RandomLat(double lat) {
        Random r = new Random();
        double rLat = 0;

        if (r.nextDouble() < 0.5) {
            rLat = lat + kilometer * multiple * r.nextDouble();
        } else {
            rLat = lat - kilometer * multiple * r.nextDouble();
        }
        while (lat + kilometer * multiple > rLat && lat + kilometer * multiple < rLat) {
            rLat = rLat + kilometer * r.nextDouble();
        }

        return rLat;
    }

    //経度を適当に決定する
    private double RandomLon(double Lon) {
        Random r = new Random();
        double rLon = 0;

        if (r.nextDouble() < 0.5) {
            rLon = Lon + kilometer * multiple * r.nextDouble();
        } else {
            rLon = Lon - kilometer * multiple * r.nextDouble();
        }

        while (Lon + kilometer * multiple > rLon && Lon + kilometer * multiple < rLon) {
            rLon = rLon + r.nextDouble();
        }

        return rLon;
    }

    //適当に計算した座標にピンを立てる
    private void RandomPoint(double latitude, double longitude) {
        LatLng point = new LatLng(latitude, longitude);
        TouringActivity.mMap.addMarker(new MarkerOptions().position(point).title("今日のお前の目的地"));
        TouringActivity.mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
    }

    //LocationListenerの実装
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double rLat = RandomLat(latitude);
        double rLon = RandomLon(longitude);
        RandomPoint(rLat, rLon);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
