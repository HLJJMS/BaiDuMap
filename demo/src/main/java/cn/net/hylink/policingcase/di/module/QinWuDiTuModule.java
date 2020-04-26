package cn.net.hylink.policingcase.di.module;

import dagger.Binds;
import dagger.Module;

import cn.net.hylink.policingcase.mvp.contract.QinWuDiTuContract;
import cn.net.hylink.policingcase.mvp.model.QinWuDiTuModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/17/2020 16:33
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class QinWuDiTuModule {

    @Binds
    abstract QinWuDiTuContract.Model bindQinWuDiTuModel(QinWuDiTuModel model);
}