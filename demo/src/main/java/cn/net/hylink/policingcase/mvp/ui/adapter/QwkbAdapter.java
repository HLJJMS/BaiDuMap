package cn.net.hylink.policingcase.mvp.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.hylink.policingcase.R;
import cn.net.hylink.policingcase.app.utils.NullToll;
import cn.net.hylink.policingcase.mvp.model.entity.UserEntity;

public class QwkbAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {


    public QwkbAdapter(int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    public QwkbAdapter(@Nullable List<UserEntity> data) {
        super(data);
    }

    public QwkbAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
        helper.setText(R.id.tv_name,item.getKqrxm());
        helper.setText(R.id.tv_id,String.valueOf(item.getXh()));
        Glide.with(mContext).load(item.getRltpdz()).into((ImageView) helper.getView(R.id.img));
    }
}
