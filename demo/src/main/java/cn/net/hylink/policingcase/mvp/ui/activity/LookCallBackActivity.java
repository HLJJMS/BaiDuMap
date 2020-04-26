package cn.net.hylink.policingcase.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.net.hylink.policingcase.di.component.DaggerLookCallBackComponent;
import cn.net.hylink.policingcase.mvp.contract.LookCallBackContract;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackLookBean;
import cn.net.hylink.policingcase.mvp.model.entity.CallBackListEntity;
import cn.net.hylink.policingcase.mvp.ui.adapter.LookCallBackAdapter;
import cn.net.hylink.policingcase.R;

import cn.net.hylink.policingcase.mvp.presenter.LookCallBackPresenter;
import me.jessyan.autosize.internal.CancelAdapt;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/23/2020 16:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LookCallBackActivity extends BaseActivity<LookCallBackPresenter> implements LookCallBackContract.View, CancelAdapt {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_jjdh)
    TextView tvJjdh;
    @BindView(R.id.tv_bjsj)
    TextView tvBjsj;
    @BindView(R.id.tv_bjrname)
    TextView tvBjrname;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_bjdd)
    TextView tvBjdd;
    @BindView(R.id.tv_jqlb)
    TextView tvJqlb;
    @BindView(R.id.tv_cjdw)
    TextView tvCjdw;
    @BindView(R.id.tv_bjnr)
    TextView tvBjnr;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_one)
    TextView imgOne;
    @BindView(R.id.tv_pfsj)
    TextView tvPfsj;
    @BindView(R.id.img_two)
    TextView imgTwo;
    @BindView(R.id.tv_qsjy)
    TextView tvQsjy;
    @BindView(R.id.tv_qssj)
    TextView tvQssj;
    @BindView(R.id.img_three)
    TextView imgThree;
    @BindView(R.id.tv_dcjy)
    TextView tvDcjy;
    @BindView(R.id.tv_dcsj)
    TextView tvDcsj;
    @BindView(R.id.img_four)
    TextView imgFour;
    @BindView(R.id.tv_jsjy)
    TextView tvJsjy;
    @BindView(R.id.tv_jssj)
    TextView tvJssj;
    @BindView(R.id.line_4)
    View line4;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.view_bottom)
    View viewBottom;
    @BindView(R.id.img_back)
    ImageView imgBack;
    LookCallBackAdapter adapter = new LookCallBackAdapter(R.layout.item_callback_look);

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLookCallBackComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_look_call_back; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvType.setText("已反馈  " + getIntent().getStringExtra("tv_id"));
        tvJjdh.setText("接警单编号：" + getIntent().getStringExtra("cjbh"));
        tvBjsj.setText("报警时间：" + getIntent().getStringExtra("tv_baoantime"));
        tvBjrname.setText("报警人姓名：" + getIntent().getStringExtra("tv_name"));
        tvPhone.setText(getIntent().getStringExtra("tv_phone"));
        tvBjdd.setText("报警地点  " + getIntent().getStringExtra("tv_address"));
        tvJqlb.setText("警情类别：" + getIntent().getStringExtra("tv_type"));
        tvCjdw.setText("出警单位：" + getIntent().getStringExtra("tv_team"));
        tvBjnr.setText("报警内容：" + getIntent().getStringExtra("tv_detail"));
        tvPfsj.setText("派发时间：" + getIntent().getStringExtra("tv_baoantime"));
        tvQsjy.setText("签收警员：" + getIntent().getStringExtra("qsjyxm"));
        tvQssj.setText("签收时间：" + getIntent().getStringExtra("jssj"));
        tvDcjy.setText("到场警员：" + getIntent().getStringExtra("dcjy"));
        tvDcsj.setText("到场时间：" + getIntent().getStringExtra("dcsj"));
        tvJsjy.setText("结束警员：" + getIntent().getStringExtra("jsjy"));
        tvJssj.setText("结束时间：" + getIntent().getStringExtra("jssj"));
        getData();
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

    @Override
    public void dataSuccess(CallBackListEntity bean) {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        adapter.addData(bean.getList());
    }


    private void getData() {
        CallBackLookBean bean = new CallBackLookBean();
        bean.setCurrentPage(1);
        bean.setShowCount(999);
        bean.setPd(new CallBackLookBean.PdBean(getIntent().getStringExtra("tv_id"), "tempHylinkCid"));
        mPresenter.getData(bean);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


}
