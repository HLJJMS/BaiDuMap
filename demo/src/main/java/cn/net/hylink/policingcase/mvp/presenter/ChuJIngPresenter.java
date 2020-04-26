package cn.net.hylink.policingcase.mvp.presenter;

import android.app.Application;
import android.widget.Toast;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import com.jess.arms.base.LoadingUtils;
import cn.net.hylink.policingcase.app.utils.RxUtils;
import cn.net.hylink.policingcase.mvp.contract.ChuJIngContract;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.app.utils.NullToll;
import cn.net.hylink.policingcase.mvp.model.bean.CountBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqBtGMBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqListBean;
import cn.net.hylink.policingcase.mvp.model.entity.JqCountEntity;
import cn.net.hylink.policingcase.mvp.model.entity.JqListEntity;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

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
public class ChuJIngPresenter extends BasePresenter<ChuJIngContract.Model, ChuJIngContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    public LoadingUtils loadingUtils;

    @Inject
    public ChuJIngPresenter(ChuJIngContract.Model model, ChuJIngContract.View rootView) {
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

    public void getListData(JqListBean map) {
        mRootView.showLoading();
        mModel.getListdata(map).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<JqListEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<JqListEntity> jqListBeanBaseResponse) {
                mRootView.hideLoading();
                if (NullToll.isNotNull(jqListBeanBaseResponse.getResult())) {
                    mRootView.setListData(jqListBeanBaseResponse.getResult());
                } else {
                    Toast.makeText(mApplication, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ;


    }


    public void getCount(CountBean map) {
//        mRootView.showLoading();
        mModel.getCount(map).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<JqCountEntity>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<JqCountEntity> jqCountEntityBaseResponse) {
//                mRootView.hideLoading();
                if (NullToll.isNotNull(jqCountEntityBaseResponse.getResult())) {
                    mRootView.setCount(jqCountEntityBaseResponse.getResult());
                } else {

                }

            }
        });

    }


    public void getSign(JqBtGMBean map) {
        mModel.getJqQs(map).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<Object> objectBaseResponse) {
                mRootView.signSuccess();
            }
        });
    }

    public void getEnd(JqBtGMBean map) {
        mModel.getJqjqjs(map).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<Object> objectBaseResponse) {
                mRootView.endSuccess();
            }
        });
    }


    public void getAerrvie(JqBtGMBean map) {
        mModel.getJqdcqr(map).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(new ErrorHandleSubscriber<BaseResponse<Object>>(mErrorHandler) {
            @Override
            public void onNext(BaseResponse<Object> objectBaseResponse) {
                mRootView.arriveSuccess();
            }
        });
    }

}
