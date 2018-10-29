package sy.touringdecision;

import com.google.android.gms.maps.GoogleMap;

public class MapProperties {

    //倍率が変更されたときに距離倍率を自動変更
    public void MapScaleChange(GoogleMap map) {
        MyLocationListener listener = new MyLocationListener();
        listener.multiple = 1000/map.getMaxZoomLevel();
    }
}
