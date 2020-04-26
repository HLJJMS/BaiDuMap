package cn.net.hylink.policingcase.mvp.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.hylink.policingcase.app.utils.Colors;
import cn.net.hylink.policingcase.R;
import cn.net.hylink.policingcase.mvp.model.entity.JqListEntity;

public class ChuJingAdapter extends BaseQuickAdapter<JqListEntity.ListBean, BaseViewHolder> {
    int position = -1;

    public ChuJingAdapter(int layoutResId, @Nullable List<JqListEntity.ListBean> data) {
        super(layoutResId, data);
    }

    public ChuJingAdapter(@Nullable List<JqListEntity.ListBean> data) {
        super(data);
    }

    public ChuJingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, JqListEntity.ListBean item) {
        helper.setText(R.id.tv_name, item.getBjlbmc()).setText(R.id.tv_time, item.getBjsj().substring(item.getBjsj().indexOf(" "))).setText(R.id.tv_id, item.getJqbh()).setText(R.id.tv_address, item.getAfdd());
        helper.setBackgroundColor(R.id.cl_bg, Colors.COLOR_13326A);
        if (position == helper.getPosition()) {
            helper.setBackgroundColor(R.id.cl_bg, Colors.COLOR_0fa8ce);
        }
    }


    public void setPosition(int i) {
        position = i;
        notifyDataSetChanged();
    }

}
