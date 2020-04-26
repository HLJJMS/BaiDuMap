package com.jess.arms.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.R;


public class LoadingUtils extends Dialog {
    private ImageView img;
    Animation animation;

    public LoadingUtils(@NonNull Context context) {
        super(context);
    }

    public LoadingUtils(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingUtils(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_loading);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        img = findViewById(R.id.ivLoading);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);

    }

    @Override
    public void show() {
        super.show();
        img.startAnimation(animation);
    }

    @Override
    public void hide() {
        super.hide();
        img.clearAnimation();
    }
}
