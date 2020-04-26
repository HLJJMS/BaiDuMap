package cn.net.hylink.policingcase.mvp.presenter;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.net.hylink.policingcase.app.utils.RxUtils;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackBean;
import cn.net.hylink.policingcase.mvp.model.bean.PullDownBean;
import cn.net.hylink.policingcase.mvp.model.bean.ShortWordBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;
import cn.net.hylink.policingcase.app.utils.NullToll;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import cn.net.hylink.policingcase.mvp.contract.CallBackContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/20/2020 11:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class CallBackPresenter extends BasePresenter<CallBackContract.Model, CallBackContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public CallBackPresenter(CallBackContract.Model model, CallBackContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }


    public void getPullDown(PullDownBean bean) {

        mModel.getPullDown(bean).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<PullDownEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<PullDownEntity> pullDownEntityBaseResponse) {
                if (NullToll.isNotNull(pullDownEntityBaseResponse.getResult())) {
                    mRootView.pullDownSuccess(pullDownEntityBaseResponse.getResult());
                } else {

                }

            }
        });
    }

    public void getWord() {
        ShortWordBean bean = new ShortWordBean();
        mModel.getWord(bean).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<ShortWordEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<ShortWordEntity> objectShortWordEntityBaseResponse) {
                if (NullToll.isNotNull(objectShortWordEntityBaseResponse.getResult())) {
                    mRootView.wordSuccess(objectShortWordEntityBaseResponse.getResult());
                } else {

                }
            }
        });


    }


    public void posCallback(CallBackBean bean) {
        mModel.postCallBack(bean).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<Object> objectBaseResponse) {
                Toast.makeText(mApplication, "提交成功", Toast.LENGTH_LONG).show();
                mRootView.killMyself();
            }
        });


    }


    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
