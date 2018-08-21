package com.example.arcgisapp.arcGIS;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.example.arcgisapp.R;

/**
 * Created by wjf on 2018/7/23.
 */

public class ArcGISActivity extends AppCompatActivity {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcgis);

        //在Layout中获取到MapView控件，记得在外层创建mMapView对象
        mMapView = findViewById(R.id.mapView);
        //创建一个地图对象
        ArcGISMap map = new ArcGISMap();
        //创建并添加地图服务
        String strMapUrl = "http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer";
        ArcGISTiledLayer arcGISTiledLayer = new ArcGISTiledLayer(strMapUrl);
        //创建底图、并设置底图图层
        Basemap basemap = new Basemap();
        basemap.getBaseLayers().add(arcGISTiledLayer);
        //设置地图底图
        map.setBasemap(basemap);
        //设置map地图对象在MapView控件中显示
        mMapView.setMap(map);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.dispose();
    }
}
