package cn.net.hylink.policingcase.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
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
import cn.net.hylink.policingcase.app.utils.NullToll;
import cn.net.hylink.policingcase.di.component.DaggerQinWuKanBanComponent;
import cn.net.hylink.policingcase.mvp.contract.QinWuKanBanContract;
import cn.net.hylink.policingcase.mvp.model.bean.QwkbBean;
import cn.net.hylink.policingcase.mvp.model.entity.QwkbEntity;
import cn.net.hylink.policingcase.mvp.presenter.QinWuKanBanPresenter;
import cn.net.hylink.policingcase.mvp.ui.adapter.QwkbAdapter;
import me.jessyan.autosize.internal.CancelAdapt;

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
public class QinWuKanBanFragment extends BaseFragment<QinWuKanBanPresenter> implements QinWuKanBanContract.View, CancelAdapt {
    PopupWindow popupWindow;
    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    private LayoutInflater layoutInflater;
    private View popView;
    private TextView tvPerson, tvTime, tvPosition, tvCar;
    private int year, month;
    private QwkbAdapter adapter = new QwkbAdapter(R.layout.item_qwkb);
    private List<QwkbEntity.ResultBean.ListBean> dataList = new ArrayList<>();
    private String name="";
    public static QinWuKanBanFragment newInstance() {
        QinWuKanBanFragment fragment = new QinWuKanBanFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerQinWuKanBanComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qin_wu_kan_ban, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        year = calendarView.getCurYear();
        month = calendarView.getCurMonth();
        setTitleDate();
        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(adapter);
        adapter.addData(NullToll.userInfo);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapters, View view, int position) {
                mPresenter.getData(new QwkbBean(adapter.getData().get(position).getIdcard(), year + "-" + month));
                name = adapter.getData().get(position).getGxdwmc();
            }
        });
        setData();
        setPopWindow();
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
//        setDataView();

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

    }

    //    设置popwindow
    private void setPopWindow() {
        popView = layoutInflater.from(getActivity()).inflate(R.layout.pop_jwkb, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        tvCar = popView.findViewById(R.id.tv_car);
        tvPerson = popView.findViewById(R.id.tv_person);
        tvTime = popView.findViewById(R.id.tv_time);
        tvPosition = popView.findViewById(R.id.tv_position);
    }

    //    显示popwindow
    private void showPop(int j) {
        killPop();
        if (j == -1) {
            tvTime.setText("暂无数据");
            tvCar.setVisibility(View.GONE);
            tvPerson.setVisibility(View.GONE);
            tvPosition.setVisibility(View.GONE);
        } else {
            tvCar.setVisibility(View.VISIBLE);
            tvPerson.setVisibility(View.VISIBLE);
            tvPosition.setVisibility(View.VISIBLE);
            tvCar.setText("执勤车辆：" + ifNull(dataList.get(j).getVehicle_license_plate()));
            tvTime.setText("值班时间：" + ifNull(dataList.get(j).getSchedule_date()));
            tvPosition.setText("执勤区域：" + ifNull(dataList.get(j).getLabel_area_name()));
            tvPerson.setText("执勤警员：：" + name);
        }

        popupWindow.showAsDropDown(tvMonth, 0, 0, Gravity.RIGHT);

    }

    public void killPop() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }

    }

    private void setData() {
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

            }
        });

        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                int j = -1;
                for (int i = 0; i < dataList.size(); i++) {
                    String a = calendar.toString();
                    String b = dataList.get(i).getSchedule_date().replace("-", "");
                    if (calendar.toString().equals(dataList.get(i).getSchedule_date().replace("-", ""))) {
                        j = i;
                    }
                }
                showPop(j);

            }
        });

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int years, int months) {
                year = years;
                month = months;
                setTitleDate();
            }
        });
    }


    @OnClick({R.id.tv_month, R.id.img_left, R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_month:
                break;
            case R.id.img_left:
                calendarView.scrollToPre();
                break;
            case R.id.img_right:
                calendarView.scrollToNext();
                break;
        }
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        return calendar;
    }


    private void setTitleDate() {
        tvMonth.setText(String.valueOf(year) + "年" + String.valueOf(month) + "月");
    }


    @Override
    public void getDataSuccess(QwkbEntity bean) {
        Map<String, Calendar> map = new HashMap<>();
        dataList.clear();
        dataList.addAll(bean.getResult().getList());
        for (int i = 0; i < bean.getResult().getList().size(); i++) {
            int y = Integer.valueOf(bean.getResult().getList().get(i).getSchedule_date().substring(0, 4));
            int m = Integer.valueOf(bean.getResult().getList().get(i).getSchedule_date().substring(5, 7));
            int d = Integer.valueOf(bean.getResult().getList().get(i).getSchedule_date().substring(8, 10));
            int color = 0;
//            勤务状态1考勤异常2正常3未开始
            if (bean.getResult().getList().get(i).getStauts() == 1) {
                color = 0xffF28E26;
            } else if (bean.getResult().getList().get(i).getStauts() == 2) {
                color = 0xff0FBA4D;
            } else if (bean.getResult().getList().get(i).getStauts() == 3) {
                color = 0xff1991EB;
            }
            map.put(getSchemeCalendar(y, m, d, color).toString(),
                    getSchemeCalendar(y, m, d, color));
        }
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        calendarView.setSchemeDate(map);
    }


    private String ifNull(String txt) {
        if (null == txt) {
            return "";
        } else {
            return txt;
        }
    }

}
