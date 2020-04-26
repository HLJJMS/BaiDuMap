package cn.net.hylink.policingcase.mvp.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.net.hylink.policingcase.mvp.model.entity.CallBackListEntity;
import cn.net.hylink.policingcase.R;

public class LookCallBackAdapter extends BaseQuickAdapter<CallBackListEntity.ListBean, BaseViewHolder> {
    public LookCallBackAdapter(int layoutResId, @Nullable List<CallBackListEntity.ListBean> data) {
        super(layoutResId, data);
    }

    public LookCallBackAdapter(@Nullable List<CallBackListEntity.ListBean> data) {
        super(data);
    }

    public LookCallBackAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallBackListEntity.ListBean item) {
        helper.setText(R.id.tv_callback, "反馈警员：" + item.getFkrxm());
        helper.setText(R.id.tv_fksj, "反馈时间：" + item.getFksj());
        helper.setText(R.id.tv_fwsj, "发生时间：" + item.getAjfssj());
        helper.setText(R.id.tv_fkdbh, "反馈单编号：" + item.getJqbh());
        helper.setText(R.id.tv_fkbjr, "反馈报警人：" + item.getBJR());
        helper.setText(R.id.tv_fkrzjh, "反馈人证件号：" + item.getCdrs());
        helper.setText(R.id.tv_bjrdh, "反馈报警电话：" + item.getBJDH());
        helper.setText(R.id.tv_fkjqlb, "反馈警情类别：" + item.getJqfklbmc());
        helper.setText(R.id.tv_fkjqxl, "反馈警情细类：" + item.getJqfkxlmc());
        helper.setText(R.id.tv_fkjqlx, "反馈警情类型：" + item.getJqfklxmc());
        helper.setText(R.id.tv_fsdd, "发生地点：" + item.getFsdd());
        helper.setText(R.id.tv_cdrs, "出动人数：" + item.getCdrs());
        helper.setText(R.id.tv_cdcs, "出动车船数：" + item.getCdccs());
        String position = String.valueOf(helper.getAdapterPosition() + 5);
        helper.setText(R.id.img, position);
//        helper.setText(R.id.tv_fknr, "反馈内容：" + item.);
//        List<String> xcimg = item.getXctp();
//        List<String> xyrimg = item.getXyrtp();
//        List<String> qttp = item.getQttp();
        //其他图片，现场图片，嫌疑人图片
        List<ImageView> other = new ArrayList<>();
        List<ImageView> site = new ArrayList<>();
        List<ImageView> person = new ArrayList<>();
        person.add(helper.getView(R.id.img_ren_1));
        person.add(helper.getView(R.id.img_ren_2));
        person.add(helper.getView(R.id.img_ren_3));
        other.add(helper.getView(R.id.img_other_1));
        other.add(helper.getView(R.id.img_other_2));
        other.add(helper.getView(R.id.img_other_3));
        site.add(helper.getView(R.id.img_site_1));
        site.add(helper.getView(R.id.img_site_2));
        site.add(helper.getView(R.id.img_site_3));
        forImg(site, item.getXctp());
        forImg(person, item.getXyrtp());
        forImg(other, item.getQttp());
    }


    //    显示base64图片
    private void showPhoto(ImageView img, String photoback) {
        Log.d("pmftest", "onResponse: 图片" + photoback);
        Bitmap bitmap = null;
        byte[] bitmapArray;
        bitmapArray = Base64.decode(photoback, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        img.setImageBitmap(bitmap);
        bitmap = null;
    }

    private void forImg(List<ImageView> img, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            showPhoto(img.get(i), list.get(i));
        }
    }


}
