package cn.net.hylink.policingcase.mvp.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.R;

public class PullDownAdapter extends BaseQuickAdapter<PullDownEntity.ListBean, BaseViewHolder> {
    public PullDownAdapter(int layoutResId, @Nullable List<PullDownEntity.ListBean> data) {
        super(layoutResId, data);
    }

    public PullDownAdapter(@Nullable List<PullDownEntity.ListBean> data) {
        super(data);
    }

    public PullDownAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PullDownEntity.ListBean item) {
        helper.setText(R.id.txt, item.getDictvalue());
    }
}
