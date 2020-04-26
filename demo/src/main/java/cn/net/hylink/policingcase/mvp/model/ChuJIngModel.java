package cn.net.hylink.policingcase.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import cn.net.hylink.policingcase.mvp.model.api.service.CommonService;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import io.reactivex.Observable;
import cn.net.hylink.policingcase.mvp.contract.ChuJIngContract;
import cn.net.hylink.policingcase.mvp.model.bean.CountBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqBtGMBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqListBean;
import cn.net.hylink.policingcase.mvp.model.entity.JqCountEntity;
import cn.net.hylink.policingcase.mvp.model.entity.JqListEntity;


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
public class ChuJIngModel extends BaseModel implements ChuJIngContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ChuJIngModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<JqListEntity>> getListdata(JqListBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqList(map);
    }

    @Override
    public Observable<BaseResponse<JqCountEntity>> getCount(CountBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqCount(map);
    }

    @Override
    public Observable<BaseResponse<Object>> getJqQs(JqBtGMBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqQs(map);
    }

    @Override
    public Observable<BaseResponse<Object>> getJqjqjs(JqBtGMBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqjqjs(map);
    }

    @Override
    public Observable<BaseResponse<Object>> getJqdcqr(JqBtGMBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqdcqr(map);
    }


}