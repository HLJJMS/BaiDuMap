package cn.net.hylink.policingcase.mvp.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.net.hylink.policingcase.app.utils.Colors;
import cn.net.hylink.policingcase.app.utils.FileUtil;
import cn.net.hylink.policingcase.app.utils.NullToll;
import cn.net.hylink.policingcase.di.component.DaggerCallBackComponent;
import cn.net.hylink.policingcase.mvp.contract.CallBackContract;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackBean;
import cn.net.hylink.policingcase.mvp.model.bean.PullDownBean;
import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;
import cn.net.hylink.policingcase.mvp.presenter.CallBackPresenter;
import cn.net.hylink.policingcase.mvp.ui.adapter.CallBackImgGmAdapter;
import cn.net.hylink.policingcase.mvp.ui.adapter.PullDownAdapter;
import cn.net.hylink.policingcase.mvp.ui.adapter.WordAdapter;
import io.reactivex.functions.Consumer;
import cn.net.hylink.policingcase.R;
import me.jessyan.autosize.internal.CancelAdapt;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/20/2020 11:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class CallBackActivity extends BaseActivity<CallBackPresenter> implements CallBackContract.View, CancelAdapt {
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_form)
    TextView tvForm;
    @BindView(R.id.tv_id)
    EditText tvId;
    @BindView(R.id.tv_form_detail)
    TextView tvFormDetail;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.et_callback)
    EditText etCallback;
    @BindView(R.id.img_mike)
    ImageView imgMike;
    @BindView(R.id.img_site)
    ImageView imgSite;
    @BindView(R.id.img_person)
    ImageView imgPerson;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.view_bottom)
    View viewBottom;
    @BindView(R.id.bt_ok)
    ImageView btOk;
    @BindView(R.id.bt_no)
    ImageView btNo;
    @BindView(R.id.recyclerViewMain)
    RecyclerView recyclerViewMain;
    @BindView(R.id.et_car)
    EditText etCar;
    @BindView(R.id.et_power)
    EditText etPower;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.recycler_site)
    RecyclerView recyclerSite;
    @BindView(R.id.recycler_person)
    RecyclerView recyclerPerson;
    @BindView(R.id.recycler_other)
    RecyclerView recyclerOther;
    @BindView(R.id.tv_jqid)
    TextView tvJqid;
    @BindView(R.id.cl_all)
    ConstraintLayout clAll;
    private String time, name, phone, address, jqid, twoKey = "", oneKey = "", threeKey = "";
    private PullDownBean pullDownBean = new PullDownBean();
    private View popView;
    private PopupWindow popupWindow;
    private PullDownAdapter adapter = new PullDownAdapter(R.layout.item_pop_pulldown);
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private TextView txt, pullDownText;
    private WordAdapter wordAdapter = new WordAdapter(R.layout.item_callback);
    private final int RC_CHOOSE_PHOTO = 2;
    private CallBackBean callBackBean = new CallBackBean();
    private CallBackImgGmAdapter siteAdapter = new CallBackImgGmAdapter(R.layout.item_image);
    private CallBackImgGmAdapter otherAdapter = new CallBackImgGmAdapter(R.layout.item_image);
    private CallBackImgGmAdapter personAdapter = new CallBackImgGmAdapter(R.layout.item_image);
    private int photoPosition = 0; //0 现场图片 1 人图片 2其他图片

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCallBackComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_call_back; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        clAll.setPadding(0, NullToll.getStatusBarHeight(this), 0, 0);
        time = getIntent().getStringExtra("afsj");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        address = getIntent().getStringExtra("address");
        jqid = getIntent().getStringExtra("tv_id");
        tvJqid.setText("警情编号：" + jqid);
        tvData.setText(time);
        tvName.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);
        mPresenter.getWord();
        setPopWindow();
        setImagesAdapter();
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

    @OnClick({R.id.tv_data, R.id.tv_type, R.id.tv_form, R.id.tv_form_detail, R.id.img_mike, R.id.img_site, R.id.img_person, R.id.bt_ok, R.id.bt_no, R.id.img_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_data:
                showDataSelect();
                break;
            case R.id.tv_type:
                pullDownText = tvType;
                getPullDownData("121", "");
                twoKey = "";
                tvFormDetail.setText("");
                threeKey = "";
                tvForm.setText("");
                break;
            case R.id.tv_form:
                if (oneKey == "") {
                    Toast.makeText(this, "请先选择上级列表", Toast.LENGTH_LONG).show();
                } else {
                    pullDownText = tvForm;
                    getPullDownData("161", oneKey);
                    threeKey = "";
                    tvForm.setText("");
                }
                break;
            case R.id.tv_form_detail:
                if (twoKey == "") {
                    Toast.makeText(this, "请先选择上级列表", Toast.LENGTH_LONG).show();
                } else {
                    pullDownText = tvFormDetail;
                    getPullDownData("181", twoKey);
                }
                break;
            case R.id.img_mike:

                break;
            case R.id.img_site:
                photoPosition = 0;
                selectPhoto();
                break;
            case R.id.img_person:
                photoPosition = 1;
                selectPhoto();
                break;
            case R.id.img_other:
                photoPosition = 2;
                selectPhoto();
                break;
            case R.id.bt_ok:
                sendCallBack();
                break;
            case R.id.bt_no:
                finish();
                break;
        }
    }

    @Override
    public void pullDownSuccess(PullDownEntity bean) {
        adapter.setNewData(bean.getList());
        popupWindow.showAsDropDown(pullDownText, 0, 0);
    }

    @Override
    public void wordSuccess(ShortWordEntity bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        recyclerViewMain.setAdapter(wordAdapter);
        wordAdapter.addData(bean.getData());
        wordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                etCallback.setText(etCallback.getText() + wordAdapter.getData().get(position).getName());
            }
        });
    }


    //    设置初始化图片的recycler
    private void setImagesAdapter() {
        LinearLayoutManager siteManager = new LinearLayoutManager(this);
        LinearLayoutManager otherManager = new LinearLayoutManager(this);
        LinearLayoutManager personManager = new LinearLayoutManager(this);
        siteManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        otherManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        personManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerOther.setLayoutManager(otherManager);
        recyclerPerson.setLayoutManager(personManager);
        recyclerSite.setLayoutManager(siteManager);
        recyclerPerson.setAdapter(personAdapter);
        recyclerSite.setAdapter(siteAdapter);
        recyclerOther.setAdapter(otherAdapter);

        personAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.getData().remove(position);
                personAdapter.notifyDataSetChanged();
                showAddImage();
            }
        });
        otherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.getData().remove(position);
                personAdapter.notifyDataSetChanged();
                showAddImage();
            }
        });
        siteAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapter.getData().remove(position);
                personAdapter.notifyDataSetChanged();
                showAddImage();
            }
        });


    }


    //字典id 121类别 161类型  181细类
    private void getPullDownData(String type, String key) {
        pullDownBean.setDictid(type);
        pullDownBean.setDictkey(key);
        pullDownBean.setMjjh("33222006");
        mPresenter.getPullDown(pullDownBean);
    }


    private void setPopWindow() {
        popView = layoutInflater.from(this).inflate(R.layout.pop_recyclerview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        recyclerView = popView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapters, View view, int position) {
                if (pullDownText == tvType) {
                    oneKey = adapter.getData().get(position).getDictkey();
                    tvType.setText(adapter.getData().get(position).getDictvalue());
                } else if (pullDownText == tvForm) {
                    twoKey = adapter.getData().get(position).getDictkey();
                    tvForm.setText(adapter.getData().get(position).getDictvalue());
                } else if (pullDownText == tvFormDetail) {
                    threeKey = adapter.getData().get(position).getDictkey();
                    tvFormDetail.setText(adapter.getData().get(position).getDictvalue());
                }
                popupWindow.dismiss();
            }
        });
    }


    private void showDataSelect() {//Dialog 模式下，在底部弹出
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvData.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setBgColor(Colors.COLOR_0C0D3A)
                .setOutSideColor(Colors.COLOR_4CWHITE)
                .setTitleBgColor(Colors.COLOR_090A37)
                .setTextColorCenter(Colors.WHITE)
                .setTitleColor(Colors.WHITE)
                .setSubmitColor(Colors.COLOR_00CCFF)//确定按钮文字颜色
                .setCancelColor(Colors.COLOR_00CCFF)//取消按钮文字颜色
                .setTitleText("请选择时间")//标题文字
                .isDialog(true)
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


    private void getPhoto() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.INTERNET).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //申请的权限全部允许
//                    Toast.makeText(CallBackActivity.this, "允许了权限!", Toast.LENGTH_SHORT).show();
                    selectPhoto();
                } else {
                    //只要有一个权限被拒绝，就会执行
                    Toast.makeText(CallBackActivity.this, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectPhoto() {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CHOOSE_PHOTO:
                if (null != data && null != data.getData()) {
                    Uri uri = data.getData();
                    String filePath = FileUtil.getFilePathByUri(this, uri);

                    if (!TextUtils.isEmpty(filePath)) {
                        ContentResolver cr = this.getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            switch (photoPosition) {
                                case 0:
                                    siteAdapter.addData(bitmap);
                                    showAddImage();
                                    break;
                                case 1:
                                    personAdapter.addData(bitmap);
                                    showAddImage();
                                    break;
                                case 2:
                                    otherAdapter.addData(bitmap);
                                    showAddImage();
                                    break;
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }


    }

    private void showAddImage() {
        if (siteAdapter.getData().size() == 3) {
            imgSite.setVisibility(View.INVISIBLE);
        } else {
            imgSite.setVisibility(View.VISIBLE);
        }

        if (otherAdapter.getData().size() == 3) {
            imgOther.setVisibility(View.INVISIBLE);
        } else {
            imgOther.setVisibility(View.VISIBLE);
        }

        if (personAdapter.getData().size() == 3) {
            imgPerson.setVisibility(View.INVISIBLE);
        } else {
            imgPerson.setVisibility(View.VISIBLE);
        }

    }

    //    请求接口提交表单
    private void sendCallBack() {

        if (etPower.getText().toString().equals("")) {
            callBackBean.setCdrs(0);//出动人数
        } else {
            callBackBean.setCdrs(Integer.valueOf(etPower.getText().toString()));//出动人数
        }
        if (etCar.getText().toString().equals("")) {
            callBackBean.setCdccs(0);
        } else {
            callBackBean.setCdccs(Integer.valueOf(etCar.getText().toString()));//出动车船数
        }


        callBackBean.setJqlb(oneKey);//警情类别代码
        callBackBean.setMjdwmc(tvType.getText().toString());//警情类别名称
        callBackBean.setJqlx(twoKey);//警情类型代码
        callBackBean.setJqlxmc(tvForm.getText().toString());      //警情类型名称
        callBackBean.setJqlx(threeKey);//警情细类代码
        callBackBean.setJqxlmc(tvFormDetail.getText().toString());//警情细类名称
        callBackBean.setAjfssj(getTimeChuo(tvData.getText().toString()));//案件发生时间
        callBackBean.setAjjssj(getDateNow());//案件接收时间（当前提交时间即可）
        callBackBean.setFsdd(tvAddress.getText().toString());//发生地点
        callBackBean.setCjqk(etCallback.getText().toString());//出境單編號
        for (int i = 0; i < siteAdapter.getData().size(); i++) {
            String base = mPresenter.bitmapToBase64(siteAdapter.getData().get(i));
            if (i == 0) {
                callBackBean.setPic1(base);
            } else if (i == 1) {
                callBackBean.setPic4(base);
            } else {
                callBackBean.setPic7(base);
            }
        }
        for (int i = 0; i < personAdapter.getData().size(); i++) {
            String base = mPresenter.bitmapToBase64(personAdapter.getData().get(i));
            if (i == 0) {
                callBackBean.setPic2(base);
            } else if (i == 1) {
                callBackBean.setPic5(base);
            } else {
                callBackBean.setPic8(base);
            }
        }
        for (int i = 0; i < otherAdapter.getData().size(); i++) {
            String base = mPresenter.bitmapToBase64(otherAdapter.getData().get(i));
            if (i == 0) {
                callBackBean.setPic3(base);
            } else if (i == 1) {
                callBackBean.setPic6(base);
            } else {
                callBackBean.setPic9(base);
            }
        }
        callBackBean.setJqbh(jqid);//警情编号
        callBackBean.setFkdwdm(NullToll.userInfo.getGxdwdm());//反馈单位代码
        callBackBean.setMjdwmc(NullToll.userInfo.getGxdwmc());//民警单位名称
        callBackBean.setFkrbh("0");//反馈人警号
        callBackBean.setFkrxm(NullToll.userInfo.getKqrxm());//反馈人姓名
        callBackBean.setMjjh(String.valueOf(NullToll.userInfo.getXh()));//民警警号
        callBackBean.setZkimei(NullToll.userInfo.getImei());//設備id
        callBackBean.setYzb("");//精度
        callBackBean.setXzb("");//纬度
        if (callBackBean.getCjqk().equals("")) {
            Toast.makeText(this, "请输入反馈内容", Toast.LENGTH_LONG).show();
        } else {
            mPresenter.posCallback(callBackBean);
        }
    }

    //时间转化
    private String getTimeChuo(String time) {
        time = time.replace("/", "-");
        return time;
    }

    //    获取当前日期
    private String getDateNow() {

        Date date = new Date();

        String time = date.toLocaleString();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒 E");

        return dateFormat.format(date);

    }

}
