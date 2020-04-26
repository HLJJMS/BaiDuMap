package cn.net.hylink.policingcase.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;


import cn.net.hylink.policingcase.di.component.DaggerMainComponent;
import cn.net.hylink.policingcase.mvp.presenter.MainPresenter;
import io.reactivex.functions.Consumer;
import cn.net.hylink.policingcase.app.utils.MyLocationListener;

import cn.net.hylink.policingcase.mvp.contract.MainContract;

import cn.net.hylink.policingcase.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/17/2020 08:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    MKOfflineMap mOffline = new MKOfflineMap();
    String cityName = "哈尔滨";
    ArrayList<MKOLSearchRecord> records = new ArrayList<>();
    int cityId;
    MapView mapView;
    private BaiduMap mBaiduMap;
    // 定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mapView.getMap();
        cheakMap();
        mBaiduMap.setMyLocationEnabled(true);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.INTERNET).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //申请的权限全部允许
                    Toast.makeText(MainActivity.this, "允许了权限!", Toast.LENGTH_SHORT).show();
                    initLocation();
                } else {
                    //只要有一个权限被拒绝，就会执行
                    Toast.makeText(MainActivity.this, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pointTest();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    //    检查map是否可用
    private void cheakMap() {
        //         注册下载类
        mOffline.init(new MKOfflineMapListener() {
            @Override
            public void onGetOfflineMapState(int i, int i1) {
                MKOLUpdateElement update = mOffline.getUpdateInfo(i1);
                Log.e(TAG, update.cityName + " ," + update.ratio);
            }
        });
        //        获取哈尔滨城市Id
        records = mOffline.searchCity(cityName);
        if (records != null && records.size() == 1) {
            cityId = records.get(0).cityID;
        }
        //        开始下载
        mOffline.start(cityId);
    }


    private void initLocation() {
        // 定位客户端的设置
        mLocationClient = new LocationClient(this);
//        mLocationListener = new MyLocationListener();
        // 注册监听
        mLocationClient.registerLocationListener(mLocationListener);
        // 配置定位
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");// 坐标类型
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 打开Gps
        option.setScanSpan(1000);// 1000毫秒定位一次
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private void pointTest() {
        //定义Maker坐标点
        LatLng point = new LatLng(45.7607149500, 126.6728262200);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_police_car);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point) //必传参数
                .icon(bitmap) //必传参数
                .draggable(true)
         //设置平贴地图，在地图中双指下拉查看效果
                .flat(true)
                .alpha(1.0f);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }


//        45.7476350800,126.6705806600
//        45.7629798900,126.6493702700
//        45.7795757900,126.6736811800
//        45.7500488600,126.6741921400
    private void linePoints(){

    }


}
