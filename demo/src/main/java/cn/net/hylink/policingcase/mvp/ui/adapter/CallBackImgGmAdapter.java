package cn.net.hylink.policingcase.mvp.ui.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.net.hylink.policingcase.R;

public class CallBackImgGmAdapter extends BaseQuickAdapter<Bitmap, BaseViewHolder> {
    public CallBackImgGmAdapter(int layoutResId, @Nullable List<Bitmap> data) {
        super(layoutResId, data);
    }

    public CallBackImgGmAdapter(@Nullable List<Bitmap> data) {
        super(data);
    }

    public CallBackImgGmAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bitmap item) {
        ImageView img = helper.getView(R.id.img);
        img.setImageBitmap(item);
    }

}
