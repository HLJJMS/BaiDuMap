package cn.net.hylink.policingcase.mvp.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.net.hylink.policingcase.R;
import cn.net.hylink.policingcase.app.utils.Colors;
import cn.net.hylink.policingcase.app.utils.LoadingUtils;
import cn.net.hylink.policingcase.app.utils.NullToll;
import cn.net.hylink.policingcase.app.utils.SPUtils;
import cn.net.hylink.policingcase.di.component.DaggerHomeComponent;
import cn.net.hylink.policingcase.mvp.contract.HomeContract;
import cn.net.hylink.policingcase.mvp.model.api.Api;
import cn.net.hylink.policingcase.mvp.model.entity.UserEntity;
import cn.net.hylink.policingcase.mvp.presenter.HomePresenter;
import cn.net.hylink.policingcase.mvp.ui.fragment.ChuJIngFragment;
import cn.net.hylink.policingcase.mvp.ui.fragment.QinWuDiTuFragment;
import cn.net.hylink.policingcase.mvp.ui.fragment.QinWuKanBanFragment;
import me.jessyan.autosize.internal.CancelAdapt;
import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/17/2020 16:13
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View, ChuJIngFragment.ChujingListener, CancelAdapt {

    @BindView(R.id.baiduMap)
    MapView baiduMap;
    @BindView(R.id.img_cj)
    ImageView imgCj;
    @BindView(R.id.bt_cj)
    ConstraintLayout btCj;
    @BindView(R.id.img_kanban)
    ImageView imgKanban;
    @BindView(R.id.txt_kanban)
    TextView txtKanban;
    @BindView(R.id.bt_kanban)
    ConstraintLayout btKanban;
    @BindView(R.id.img_map)
    ImageView imgMap;
    @BindView(R.id.txt_map)
    TextView txtMap;
    @BindView(R.id.bt_map)
    ConstraintLayout btMap;
    @BindView(R.id.img_warn)
    ImageView imgWarn;
    @BindView(R.id.txt_warn)
    TextView txtWarn;
    @BindView(R.id.bt_warn)
    ConstraintLayout btWarn;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.fl_view)
    FrameLayout flView;
    @BindView(R.id.txt_chujing)
    TextView txtChujing;
    @BindView(R.id.cl_all)
    ConstraintLayout clAll;
    LocationClient mLocationClient;
    @BindView(R.id.tv_switch)
    TextView tvSwitch;
    private BaiduMap mBaiduMap;
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private List<Fragment> list = new ArrayList<>();
    List<ImageView> listImg = new ArrayList<>();
    List<TextView> listTxt = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    List<Integer> listIcOn = new ArrayList<>();
    List<Integer> listIcOff = new ArrayList<>();

    List<ConstraintLayout> listCl = new ArrayList<>();
    //离线地图相关
    MKOfflineMap mOffline = new MKOfflineMap();
    String cityName = "哈尔滨";
    ArrayList<MKOLSearchRecord> records = new ArrayList<>();
    int cityId;
    public static final String AUTHORITY = "com.login.provider.LoginContentProvider";
    public static final Uri CONTENT_URI_FIRST = Uri.parse("content://" + AUTHORITY + "/first");
    boolean buttonGroup = true, mapOpenNight = false;
    private static final String CUSTOM_FILE_NAME_GRAY = "custom_map_config.json";//百度地图json变量
    ChuJIngFragment chuJIngFragment = new ChuJIngFragment();
    QinWuKanBanFragment qinWuKanBanFragment = new QinWuKanBanFragment();
    QinWuDiTuFragment qinWuDiTuFragment = new QinWuDiTuFragment();
    LoadingUtils loadingUtils;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        cheakMap();
        getUserData();
        //真正的全屏体验
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        fragmentManager = getSupportFragmentManager();
        setListData();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_view, list.get(0));
        fragmentTransaction.add(R.id.fl_view, list.get(1));
        fragmentTransaction.add(R.id.fl_view, list.get(2));
        fragmentTransaction.commit();
        hideFragment();
        setCenterPoint(45.7647334400, 126.6390000200);//默认标点哈站
    }

    //    获取用户信息
    private void getUserData() {
        String data = this.getContentResolver().getType(CONTENT_URI_FIRST);
        if (null == data) {
            Log.e("哈哈哈哈", "不行");
            Toast.makeText(this, "用户未登录", Toast.LENGTH_LONG).show();
        } else {
            Log.e("哈哈哈哈", data);
            NullToll.loginBean = new Gson().fromJson(data, new TypeToken<List<UserEntity>>() {
            }.getType());
            for (int i = 0; i < NullToll.loginBean.size(); i++) {
                NullToll.userInfo = NullToll.loginBean.get(i);
            }

        }
    }

    private void setListData() {

        list.add(chuJIngFragment);
        list.add(qinWuKanBanFragment);
        list.add(qinWuDiTuFragment);
        listTxt.add(txtChujing);
        listTxt.add(txtKanban);
        listTxt.add(txtMap);
        listTxt.add(txtWarn);
        listImg.add(imgCj);
        listImg.add(imgKanban);
        listImg.add(imgMap);
        listImg.add(imgWarn);
        listIcOff.add(R.mipmap.ic_chujing_no);
        listIcOff.add(R.mipmap.ic_kanban_no);
        listIcOff.add(R.mipmap.ic_map_no);
        listIcOff.add(R.mipmap.ic_warn_no);
        listIcOn.add(R.mipmap.ic_chujing);
        listIcOn.add(R.mipmap.ic_kanban);
        listIcOn.add(R.mipmap.ic_map);
        listIcOn.add(R.mipmap.ic_warn);
        listCl.add(btCj);
        listCl.add(btKanban);
        listCl.add(btMap);
        listCl.add(btWarn);
        stringList.add("处警");
        stringList.add("警务看板");
        stringList.add("警务地图");
        stringList.add("警务预警");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 隐藏所有的Fragment
     **/
    private void hideFragment() {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.hide(list.get(0));
        transaction.hide(list.get(1));
        transaction.hide(list.get(2));
        transaction.commit();
    }

    private void setButton(int position) {
        if (buttonGroup == false) {
            buttonGroup = true;
            for (int i = 0; i < listTxt.size(); i++) {
                if (position != i) {
                    listTxt.get(i).setTextColor(Color.WHITE);
                    listImg.get(i).setImageResource(listIcOff.get(i));
                } else {
                    listTxt.get(i).setTextColor(Colors.COLOR_55E6FC);
                    listImg.get(i).setImageResource(listIcOn.get(i));
                }
//                listCl.get(i).setBackgroundResource(0);
                listTxt.get(i).setText(stringList.get(i));
                listCl.get(i).setVisibility(View.VISIBLE);
            }

            ll.setBackgroundResource(R.mipmap.bg_up);

        } else {
            buttonGroup = false;
            for (int i = 0; i < listTxt.size(); i++) {
                if (position != i) {
                    listTxt.get(i).setTextColor(Color.WHITE);
                    listImg.get(i).setImageResource(listIcOff.get(i));
                    listCl.get(i).setVisibility(View.GONE);
                    listCl.get(i).setBackgroundResource(0);
                } else {
                    listTxt.get(i).setTextColor(Colors.COLOR_55E6FC);
                    listImg.get(i).setImageResource(listIcOn.get(i));
                    listCl.get(i).setVisibility(View.VISIBLE);
//                    listCl.get(i).setBackgroundResource(R.mipmap.bg_circle);
                }
                listTxt.get(i).setText(stringList.get(i));
            }

            ll.setBackgroundResource(R.mipmap.bg_circle);

        }

    }


    @OnClick({R.id.bt_cj, R.id.bt_kanban, R.id.bt_map, R.id.bt_warn, R.id.tv_switch})
    public void onViewClicked(View view) {
        hideFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        switch (view.getId()) {
            case R.id.bt_cj:
                setButton(0);
                transaction.show(list.get(0));
                qinWuKanBanFragment.killPop();
                break;
            case R.id.bt_kanban:
                setButton(1);
                transaction.show(list.get(1));
                chuJIngFragment.killPopupWindow();
                break;
            case R.id.bt_map:
                setButton(2);
                transaction.show(list.get(2));
                chuJIngFragment.killPopupWindow();
                qinWuKanBanFragment.killPop();
                break;
//            case R.id.bt_warn:
//            setButton(3);
//                transaction.show(list.get(3));
//                break;
            case R.id.tv_switch:
                if (mapOpenNight) {
                    mapOpenNight = false;
                    tvSwitch.setText("打开夜间模式");
                } else {
                    mapOpenNight = true;
                    tvSwitch.setText("打开日间模式");
                }
                mapCustomSwitch();
                break;
        }

        transaction.commit();
    }

    private void setCenterPoint(Double x, Double y) {
        mBaiduMap = baiduMap.getMap();
        LatLng cenpt = new LatLng(x, y);//设定中心点坐标
        MapStatus mMapStatus = new MapStatus.Builder()//定义地图状态
                .target(cenpt)
                .zoom(15)
                .build();//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);//改变地图状态
    }


    @Override
    protected void onResume() {
        baiduMap.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        baiduMap.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mBaiduMap.setMyLocationEnabled(false);
        baiduMap.onDestroy();
        baiduMap = null;
        super.onDestroy();
    }

    //    地图标点
    private void point(Double x, Double y) {
        //定义Maker坐标点
        LatLng point = new LatLng(x, y);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_mark2);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point) //必传参数
                .icon(bitmap) //必传参数
                .draggable(true)
                //设置平贴地图，在地图中双指下拉查看效果
                .flat(true)
                .alpha(1.0f);
        //在地图上添加Marker，并显示
        mBaiduMap.clear();

        mBaiduMap.addOverlay(option);
    }


    //    地图模式开关
    private void mapCustomSwitch() {
        String customStyleFilePath = getCustomStyleFilePath(this, CUSTOM_FILE_NAME_GRAY);
        // 设置个性化地图样式文件的路径和加载方式
        baiduMap.setMapCustomStylePath(customStyleFilePath);
        // 动态设置个性化地图样式是否生效
        baiduMap.setMapCustomStyleEnable(mapOpenNight);
        chuJIngFragment.killPopupWindow();

    }


    /**
     * 读取json路径
     */
    private String getCustomStyleFilePath(Context context, String customStyleFileName) {
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String parentPath = null;
        try {
            inputStream = context.getAssets().open("baidu/" + customStyleFileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            parentPath = context.getFilesDir().getAbsolutePath();
            File customStyleFile = new File(parentPath + "/" + customStyleFileName);
            if (customStyleFile.exists()) {
                customStyleFile.delete();
            }
            customStyleFile.createNewFile();

            outputStream = new FileOutputStream(customStyleFile);
            outputStream.write(buffer);
        } catch (IOException e) {
            Log.e("CustomMapDemo", "Copy custom style file failed", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.e("CustomMapDemo", "Close stream failed", e);
                return null;
            }
        }
        return parentPath + "/" + customStyleFileName;
    }

    //    返回处警坐标值标点
    @Override
    public void sentXY(Double x, Double y) {
        point(x, y);
        setCenterPoint(x, y);
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
}
