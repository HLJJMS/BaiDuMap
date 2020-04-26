package cn.net.hylink.policingcase.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import cn.net.hylink.policingcase.app.utils.RxUtils;
import cn.net.hylink.policingcase.mvp.model.bean.QwkbBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.QwkbEntity;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import cn.net.hylink.policingcase.mvp.contract.QinWuKanBanContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/17/2020 16:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class QinWuKanBanPresenter extends BasePresenter<QinWuKanBanContract.Model, QinWuKanBanContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public QinWuKanBanPresenter(QinWuKanBanContract.Model model, QinWuKanBanContract.View rootView) {
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

    public void getData(QwkbBean bean){
        mModel.getData(bean).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<QwkbEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<QwkbEntity> objectBaseResponse) {
                mRootView.getDataSuccess(objectBaseResponse.getResult());
            }
        });
    }
}
