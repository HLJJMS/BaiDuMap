package cn.net.hylink.policingcase.di.module;

import cn.net.hylink.policingcase.mvp.contract.CallBackContract;
import cn.net.hylink.policingcase.mvp.model.CallBackModel;
import dagger.Binds;
import dagger.Module;


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
@Module
public abstract class CallBackModule {

    @Binds
    abstract CallBackContract.Model bindCallBackModel(CallBackModel model);
}