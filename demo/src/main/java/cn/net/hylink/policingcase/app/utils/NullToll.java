package cn.net.hylink.policingcase.app.utils;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import cn.net.hylink.policingcase.mvp.model.entity.UserEntity;

public class NullToll {
    public static boolean isNotNull(Object j) {
        if (j == null || j.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static String isNotNullString(Object j) {
        if (null==j){
            return "";
        }else{
            return j.toString();
        }



    }

    public static List<UserEntity> loginBean = new ArrayList<>();
    public static UserEntity userInfo = new UserEntity();
}
