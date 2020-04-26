package cn.net.hylink.policingcase.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import cn.net.hylink.policingcase.app.utils.RxUtils;
import cn.net.hylink.policingcase.mvp.contract.LookCallBackContract;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackLookBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.CallBackListEntity;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/23/2020 16:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class LookCallBackPresenter extends BasePresenter<LookCallBackContract.Model, LookCallBackContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LookCallBackPresenter(LookCallBackContract.Model model, LookCallBackContract.View rootView) {
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



    public void getData(CallBackLookBean bean){
        mModel.getData(bean).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<CallBackListEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<CallBackListEntity> callBackListEntityBaseResponse) {
                mRootView.dataSuccess(callBackListEntityBaseResponse.getResult());
            }
        });
    }
}
