package cn.net.hylink.policingcase.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.net.hylink.policingcase.R;
import cn.net.hylink.policingcase.app.CustomLoadMoreView;
import cn.net.hylink.policingcase.app.utils.Colors;

import com.jess.arms.base.LoadingUtils;

import cn.net.hylink.policingcase.app.utils.NullToll;

import cn.net.hylink.policingcase.di.component.DaggerChuJIngComponent;
import cn.net.hylink.policingcase.mvp.contract.ChuJIngContract;
import cn.net.hylink.policingcase.mvp.model.bean.CountBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqBtGMBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqListBean;
import cn.net.hylink.policingcase.mvp.model.entity.JqCountEntity;
import cn.net.hylink.policingcase.mvp.model.entity.JqListEntity;
import cn.net.hylink.policingcase.mvp.presenter.ChuJIngPresenter;
import cn.net.hylink.policingcase.mvp.ui.activity.CallBackActivity;
import cn.net.hylink.policingcase.mvp.ui.activity.LookCallBackActivity;
import cn.net.hylink.policingcase.mvp.ui.adapter.ChuJingAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/17/2020 16:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class ChuJIngFragment extends BaseFragment<ChuJIngPresenter> implements ChuJIngContract.View {

    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_arrive)
    TextView tvArrive;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.cl_left)
    ConstraintLayout clLeft;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.tv_point_sign)
    TextView tvPointSign;
    @BindView(R.id.tv_point_arrive)
    TextView tvPointArrive;
    @BindView(R.id.tv_point_end)
    TextView tvPointEnd;
    @BindView(R.id.tv_point_callback)
    TextView tvPointCallback;
    @BindView(R.id.tv_point_my)
    TextView tvPointMy;
    @BindView(R.id.tv_point_all)
    TextView tvPointAll;
    @BindView(R.id.txt_arrive)
    TextView txtArrive;
    @BindView(R.id.txt_end)
    TextView txtEnd;
    @BindView(R.id.txt_callback)
    TextView txtCallback;
    private ChuJingAdapter adapter;
    private List<TextView> listText = new ArrayList<>();
    private PopupWindow popupWindow = new PopupWindow();
    private PopupWindow popupWindowThree = new PopupWindow();
    private List<TextView> listRound = new ArrayList<>();
    private JqBtGMBean gmBean = new JqBtGMBean();
    private LayoutInflater layoutInflater;
    private CountBean countBean = new CountBean();
    Map<String, String> mapPop = new HashMap<>();
    private View view, viewThree;
    private String zhanwei = "   ";
    int page = 1;
    TextView tv_name, tv_time, tv_id, tv_baoantime, tv_address, tv_phone, tv_type, tv_team, tv_detail, bt_ok, bt_no, bt_look;
    String type = "01"; //01代签收，02待到场，03待结束， 04待反馈
    boolean all = false;//辖区和待签收
    private int adapterPosition;
    private String jjdzt;//接警单状态//01代签收，02待到场，03待结束， 04待反馈
    private ChujingListener chujingListener;

    public static ChuJIngFragment newInstance() {
        ChuJIngFragment fragment = new ChuJIngFragment();
        return fragment;
    }

    public interface ChujingListener {
        public void sentXY(Double x, Double y);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        chujingListener = (ChujingListener) getActivity();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChuJIngComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chu_j_ing, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        addTextView();
        addRequestGMMap();
        adapter = new ChuJingAdapter(R.layout.item_chujing);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(BaseQuickAdapter adapters, View view, int position) {
                adapter.setPosition(position);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                adapterPosition = position;
                jjdzt = adapter.getData().get(position).getJjdzt();

                mapPop.put("cjbh", NullToll.isNotNullString(adapter.getData().get(position).getCjdbh()));
                mapPop.put("jsjy", NullToll.isNotNullString(adapter.getData().get(position).getJsjyxm()));
                mapPop.put("jssj", NullToll.isNotNullString(adapter.getData().get(position).getJssj()));
                mapPop.put("dcsj", NullToll.isNotNullString(adapter.getData().get(position).getDcsj()));
                mapPop.put("dcjy", NullToll.isNotNullString(adapter.getData().get(position).getDcjyxm()));
                mapPop.put("qssj", NullToll.isNotNullString(adapter.getData().get(position).getQssj()));
                mapPop.put("qsjyxm", NullToll.isNotNullString(adapter.getData().get(position).getQsjyxm()));
                mapPop.put("jjdwmc", NullToll.isNotNullString(adapter.getData().get(position).getJjdwmc()));
                mapPop.put("afsj", NullToll.isNotNullString(adapter.getData().get(position).getAfsj()));
                mapPop.put("tv_id", NullToll.isNotNullString(adapter.getData().get(position).getJqbh()));
                mapPop.put("tv_name", NullToll.isNotNullString(adapter.getData().get(position).getBjr()));
                mapPop.put("tv_time", NullToll.isNotNullString(adapter.getData().get(position).getBjsj().substring(adapter.getData().get(position).getBjsj().indexOf(" "))));
                mapPop.put("tv_baoantime", NullToll.isNotNullString(adapter.getData().get(position).getBjsj()));
                mapPop.put("tv_address", NullToll.isNotNullString(adapter.getData().get(position).getAfdd()));
                mapPop.put("tv_phone", NullToll.isNotNullString(adapter.getData().get(position).getBjdh()));
                mapPop.put("tv_type", NullToll.isNotNullString(adapter.getData().get(position).getBjlbmc()));
                mapPop.put("tv_team", NullToll.isNotNullString(adapter.getData().get(position).getCjdwmc()));
                mapPop.put("tv_detail", NullToll.isNotNullString(adapter.getData().get(position).getBjnr()));
                String number = "";
                if (type.equals("")) {
                    number = "";
                } else {
                    number = "0" + (Integer.valueOf(type) + 1);
                }
                showPopWindow(mapPop, 0, number);
                repeatRightImage();
                chujingListener.sentXY(Double.valueOf(adapter.getData().get(position).getLat()), Double.valueOf(adapter.getData().get(position).getLng()));
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        });

//        getData();
    }

    @Override
    public void onResume() {
        mPresenter.getCount(countBean);
        super.onResume();
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
//        loadingUtils.show();
    }

    @Override
    public void hideLoading() {
//        loadingUtils.hide();
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

    }

    private void addRequestGMMap() {
        countBean.setCarno(NullToll.userInfo.getCarno());
        countBean.setMjdwdm(NullToll.userInfo.getGxdwdm());
        countBean.setMjdwmc(NullToll.userInfo.getGxdwmc());
        countBean.setMjjh(String.valueOf(NullToll.userInfo.getXh()));
    }

    //    去签收
    private void goSing() {
        gmBean.setJqbh(mapPop.get("tv_id"));
        gmBean.setMjdwdm(NullToll.userInfo.getGxdwdm());
        gmBean.setMjdwmc(NullToll.userInfo.getGxdwmc());
        gmBean.setMjjh(String.valueOf(NullToll.userInfo.getXh()));
        gmBean.setMjxm(NullToll.userInfo.getKqrxm());
        gmBean.setZkimei(NullToll.userInfo.getImei());
        gmBean.setXzb("");
        gmBean.setYzb("");
        mPresenter.getSign(gmBean);
    }

    public void getData() {
        JqListBean bean = new JqListBean();
        bean.setCurrentPage(page);
        bean.setShowCount(10);
        JqListBean.PdBean pdBean = new JqListBean.PdBean();
        pdBean.setGxdwdm(NullToll.userInfo.getGxdwdm());
        pdBean.setImei(NullToll.userInfo.getImei());
        pdBean.setIsgx(1);
        pdBean.setIsnow(0);
        if (all) {
            pdBean.setCarno("");
        } else {
            pdBean.setCarno(NullToll.userInfo.getCarno());
        }
        pdBean.setJjdzt(type);
        pdBean.setMjdwmc(NullToll.userInfo.getGxdwmc());
        bean.setPd(pdBean);
        mPresenter.getListData(bean);
    }


    @Override
    public void setListData(JqListEntity bean) {
//        if (bean.getList().size() == 0) {
//            adapter.loadMoreEnd();
//            if (page == 1) {
//                adapter.setNewData(bean.list);
//            }
//        } else {
//            if (page == 1) {
//                adapter.setNewData(bean.list);
//            } else {
//                adapter.addData(bean.list);
//            }
//
//            adapter.loadMoreComplete();
//        }

        if (bean.getPage().getTotalPage() <= page) {
            adapter.loadMoreEnd();
            if (page == 1) {
                adapter.setNewData(bean.list);
            }
        } else {
            if (page == 1) {
                adapter.setNewData(bean.list);
            } else {
                adapter.addData(bean.list);
            }

            adapter.loadMoreComplete();
        }
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCount(JqCountEntity bean) {
        llRight.setVisibility(View.GONE);
        if (bean.getData().getBsjqs() > 0) {
            llRight.setVisibility(View.VISIBLE);
            tvPointAll.setVisibility(View.VISIBLE);
            tvPointAll.setText(String.valueOf(bean.getData().getBsjqs()));
        } else {
            tvPointAll.setVisibility(View.GONE);
        }
        if (bean.getData().getBzjqs() > 0) {
            llRight.setVisibility(View.VISIBLE);
            tvPointMy.setVisibility(View.VISIBLE);
            tvPointMy.setText(String.valueOf(bean.getData().getBzjqs()));
        } else {
            tvPointMy.setVisibility(View.GONE);
        }
        if (bean.getData().getJq01() > 0) {
            tvPointSign.setVisibility(View.VISIBLE);
            tvPointSign.setText(String.valueOf(bean.getData().getJq01()));
        } else {
            tvPointSign.setVisibility(View.GONE);
        }
        if (bean.getData().getJq02() > 0) {
            tvPointArrive.setVisibility(View.VISIBLE);
            tvPointArrive.setText(String.valueOf(bean.getData().getJq02()));
        } else {
            tvPointArrive.setVisibility(View.GONE);
        }
        if (bean.getData().getJq03() > 0) {
            tvPointEnd.setVisibility(View.VISIBLE);
            tvPointEnd.setText(String.valueOf(bean.getData().getJq03()));
        } else {
            tvPointEnd.setVisibility(View.GONE);
        }
        if (bean.getData().getJq04() > 0) {
            tvPointCallback.setVisibility(View.VISIBLE);
            tvPointCallback.setText(String.valueOf(bean.getData().getJq04()));
        } else {
            tvPointCallback.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void signSuccess() {
        turnRoundColor("2");
        killPopupWindow();
        mPresenter.getCount(countBean);
        showPopWindow(mapPop, 1, "03");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void endSuccess() {
        turnRoundColor("4");
        killPopupWindow();
        mPresenter.getCount(countBean);
        showPopWindow(mapPop, 1, "05");
        txtEnd.setText("已结束");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void arriveSuccess() {
        turnRoundColor("3");
        killPopupWindow();
        mPresenter.getCount(countBean);
        showPopWindow(mapPop, 1, "04");
        txtArrive.setText("已到场");
    }


    public void goToCallBack() {
        Intent intent = new Intent(getActivity(), CallBackActivity.class);
        intent.putExtra("afsj", mapPop.get("afsj"));
        intent.putExtra("time", mapPop.get("tv_time"));
        intent.putExtra("name", mapPop.get("tv_name"));
        intent.putExtra("phone", mapPop.get("tv_phone"));
        intent.putExtra("address", mapPop.get("tv_address"));
        intent.putExtra("idCard", mapPop.get("idCard"));
        intent.putExtra("tv_id", mapPop.get("tv_id"));
        startActivity(intent);
        killPopupWindow();
    }

    @OnClick({R.id.tv_get, R.id.tv_arrive, R.id.tv_end, R.id.tv_feedback, R.id.tv_my, R.id.tv_all, R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_five, R.id.ll_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get:
                type = "01";
                all = true;
                turnColor(1);
                killPopupWindow();

                break;
            case R.id.tv_arrive:
                type = "02";
                all = false;
                turnColor(2);
                killPopupWindow();

                break;
            case R.id.tv_end:
                type = "03";
                all = false;
                turnColor(3);
                killPopupWindow();

                break;
            case R.id.tv_feedback:
                type = "04";
                all = false;
                turnColor(4);

                killPopupWindow();
                break;
            case R.id.tv_my:
                type = "";
                all = false;
                turnColor(5);
                killPopupWindow();

                break;
            case R.id.tv_all:
                type = "";
                all = true;
                turnColor(6);
                killPopupWindow();

                break;
            case R.id.tv_one:

            case R.id.tv_two:

            case R.id.tv_three:

            case R.id.tv_four:

            case R.id.tv_five:

            case R.id.ll_right:

                if(mapPop.size()!=0){
                    killPopupWindow();
                    String number = "";
                    if (type.equals("")) {
                        number = "";
                    } else {
                        number = "0" + (Integer.valueOf(type) + 1);
                    }
                    showPopWindow(mapPop, 1, number);
                }
                break;
        }
        page = 1;
        getData();
    }


    private void addTextView() {
        listText.add(tvGet);
        listText.add(tvArrive);
        listText.add(tvEnd);
        listText.add(tvFeedback);
        listText.add(tvMy);
        listText.add(tvAll);
        listRound.add(tvTwo);
        listRound.add(tvThree);
        listRound.add(tvFour);
        listRound.add(tvFive);
    }

    private void turnColor(int i) {
        i = i - 1;
        for (int j = 0; j < listText.size(); j++) {
            listText.get(j).setBackgroundColor(0);
        }
        listText.get(i).setBackgroundColor(Colors.COLOR_8055E6FC);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPopWindow(Map<String, String> map, int showPosition, String type) {
        view = layoutInflater.from(mContext).inflate(R.layout.pop_jq_dialog_threebutton, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, 400);
        popupWindow.setFocusable(false);
        tv_name = view.findViewById(R.id.tv_name);
        tv_time = view.findViewById(R.id.tv_time);
        tv_id = view.findViewById(R.id.tv_id);
        tv_baoantime = view.findViewById(R.id.tv_baoantime);
        tv_address = view.findViewById(R.id.tv_address);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_type = view.findViewById(R.id.tv_type);
        tv_team = view.findViewById(R.id.tv_team);
        tv_detail = view.findViewById(R.id.tv_detail);
        bt_ok = view.findViewById(R.id.bt_ok);
        bt_no = view.findViewById(R.id.bt_no);
        bt_look = view.findViewById(R.id.bt_look);
        bt_look.setVisibility(View.GONE);
        tv_address.setText(map.get("tv_address"));
        tv_time.setText(map.get("tv_time"));
        tv_id.setText("警情编号：" + zhanwei + map.get("tv_id"));
        tv_baoantime.setText("报案时间：" + zhanwei + map.get("tv_baoantime"));
        tv_phone.setText(map.get("tv_phone"));
        tv_type.setText("警情类别：" + zhanwei + map.get("tv_type"));
        tv_team.setText("出警单位：" + zhanwei + map.get("tv_team"));
        tv_detail.setText("报警内容：" + zhanwei + map.get("tv_detail"));
        if (type.equals("03")) {
            bt_ok.setText("到场");
            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getAerrvie(gmBean);
                    recyclerView.setVisibility(View.GONE);
                }
            });
        } else if (type.equals("04")) {
            bt_ok.setText("结束");
            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getEnd(gmBean);
                    recyclerView.setVisibility(View.GONE);
                }
            });
        } else if (type.equals("05")) {
            bt_ok.setText("反馈");
            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToCallBack();
                    recyclerView.setVisibility(View.GONE);
                }
            });
        } else if (type.equals("")) {

            if (jjdzt.equals("02")) {
                bt_ok.setText("到场");
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.getAerrvie(gmBean);
                        recyclerView.setVisibility(View.GONE);
                    }
                });
            } else if (jjdzt.equals("03")) {
                bt_ok.setText("结束");
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.getEnd(gmBean);
                        recyclerView.setVisibility(View.GONE);
                    }
                });
            } else if (jjdzt.equals("04")) {
                bt_ok.setText("反馈");
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        goToCallBack();
                        recyclerView.setVisibility(View.GONE);

                    }
                });


            } else if (jjdzt.equals("01")) {
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.getSign(gmBean);
                        recyclerView.setVisibility(View.GONE);
                    }
                });
            } else {
                bt_ok.setText("反馈");
                bt_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        recyclerView.setVisibility(View.GONE);
                        goToCallBack();
                    }
                });
                bt_look.setVisibility(View.VISIBLE);
                bt_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        recyclerView.setVisibility(View.GONE);
                        goToCallBackLook();
                    }
                });
            }

        } else {
            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goSing();
                    recyclerView.setVisibility(View.GONE);
                }
            });
        }
        if (showPosition == 0) {
            popupWindow.showAsDropDown(recyclerView, 0, -460, Gravity.RIGHT|Gravity.TOP);
        } else {
            popupWindow.showAtLocation(llRight, Gravity.RIGHT, llRight.getWidth(), 0);
        }
        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void goToCallBackLook() {
        Intent intent = new Intent(getActivity(), LookCallBackActivity.class);
        intent.putExtra("jsjy", mapPop.get("jsjy"));
        intent.putExtra("jssj", mapPop.get("jssj"));
        intent.putExtra("dcsj", mapPop.get("dcsj"));
        intent.putExtra("dcjy", mapPop.get("dcjy"));
        intent.putExtra("qssj", mapPop.get("qssj"));
        intent.putExtra("qsjyxm", mapPop.get("qsjyxm"));
        intent.putExtra("jjdwmc", mapPop.get("jjdwmc"));
        intent.putExtra("tv_time", mapPop.get("tv_time"));
        intent.putExtra("tv_id", mapPop.get("tv_id"));
        intent.putExtra("tv_name", mapPop.get("tv_name"));
        intent.putExtra("tv_baoantime", mapPop.get("tv_baoantime"));
        intent.putExtra("tv_address", mapPop.get("tv_address"));
        intent.putExtra("tv_phone", mapPop.get("tv_phone"));
        intent.putExtra("tv_type", mapPop.get("tv_type"));
        intent.putExtra("tv_address", mapPop.get("tv_address"));
        intent.putExtra("tv_team", mapPop.get("tv_team"));
        intent.putExtra("tv_detail", mapPop.get("tv_detail"));
        intent.putExtra("cjbh", mapPop.get("cjbh"));
        startActivity(intent);
    }


    private void turnRoundColor(String type) {
        int i = Integer.valueOf(type);
        for (int j = 0; j < 4; j++) {
            if (j < i) {
                listRound.get(j).setBackgroundResource(R.mipmap.bg_red);
            } else {
                listRound.get(j).setBackgroundResource(R.mipmap.bg_blue);
            }
        }
    }

    public void killPopupWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }


    @Override
    public void onPause() {
        killPopupWindow();
        super.onPause();
    }

    //    复位右侧图片和图标
    private void repeatRightImage() {
        txtArrive.setText("待到场");
        txtEnd.setText("待结束");
        listRound.get(1).setBackgroundResource(R.mipmap.bg_blue);
        listRound.get(2).setBackgroundResource(R.mipmap.bg_blue);
        listRound.get(3).setBackgroundResource(R.mipmap.bg_blue);
    }
}
