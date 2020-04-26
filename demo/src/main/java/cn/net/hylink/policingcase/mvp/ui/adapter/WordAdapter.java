package cn.net.hylink.policingcase.mvp.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.hylink.policingcase.R;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;

public class WordAdapter extends BaseQuickAdapter<ShortWordEntity.DataBean, BaseViewHolder> {
    public WordAdapter(int layoutResId, @Nullable List<ShortWordEntity.DataBean> data) {
        super(layoutResId, data);
    }

    public WordAdapter(@Nullable List<ShortWordEntity.DataBean> data) {
        super(data);
    }

    public WordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShortWordEntity.DataBean item) {
        helper.setText(R.id.txt,item.getName());
    }
}
