package cn.net.hylink.policingcase.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import cn.net.hylink.policingcase.mvp.model.api.service.CommonService;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackBean;
import cn.net.hylink.policingcase.mvp.model.bean.PullDownBean;
import cn.net.hylink.policingcase.mvp.model.bean.ShortWordBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;
import io.reactivex.Observable;
import cn.net.hylink.policingcase.mvp.contract.CallBackContract;


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
public class CallBackModel extends BaseModel implements CallBackContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public CallBackModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<PullDownEntity>> getPullDown(PullDownBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getListPullDown(map);
    }

    @Override
    public Observable<BaseResponse<ShortWordEntity>> getWord(ShortWordBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getGmWord(map);
    }

    @Override
    public Observable<BaseResponse<Object>> postCallBack(CallBackBean map) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJqjqkf(map);
    }
}