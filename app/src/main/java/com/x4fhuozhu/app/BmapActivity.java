package com.x4fhuozhu.app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

/***
 * 本类代码同定位业务本身无关，负责现实列表
 *
 * @author baidu
 *
 *
 */
public class BmapActivity extends Activity {
    private MapView mapView = null;
    private LocationManager locationManager;
    private String provider;
    BaiduMap baiduMap;
    boolean ifFrist = true;
    public static void start(Context context) {
        Intent intent = new Intent(context, BmapActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.bmap_test);
        mapView = (MapView) findViewById(R.id.map_view);
        // 获取baiduMap对象
        baiduMap = mapView.getMap();
        // 设置可改变地图位置
        baiduMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(this, "当前不能提供位置信息", Toast.LENGTH_LONG).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            navigateTo(location);
        }
        locationManager.requestLocationUpdates(provider, 5000, 1,
                locationListener);

    }

    private void navigateTo(Location location) {
        // 按照经纬度确定地图位置
        if (ifFrist) {
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomBy(5f);
            // 放大
            baiduMap.animateMapStatus(update);

            ifFrist = false;
        }
        // 显示个人位置图标
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData data = builder.build();
        baiduMap.setMyLocationData(data);
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            // 位置改变则重新定位并显示地图
            navigateTo(arg0);
        }
    };

    @Override
    protected void onDestroy() {
        // 释放资源
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }

        mapView.onDestroy();

        baiduMap.setMyLocationEnabled(false);

    }
}
