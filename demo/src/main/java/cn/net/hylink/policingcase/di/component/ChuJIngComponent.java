package cn.net.hylink.policingcase.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import cn.net.hylink.policingcase.di.module.ChuJIngModule;
import cn.net.hylink.policingcase.mvp.contract.ChuJIngContract;

import com.jess.arms.di.scope.FragmentScope;

import cn.net.hylink.policingcase.mvp.ui.fragment.ChuJIngFragment;


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
@Component(modules = ChuJIngModule.class, dependencies = AppComponent.class)
public interface ChuJIngComponent {
    void inject(ChuJIngFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ChuJIngComponent.Builder view(ChuJIngContract.View view);

        ChuJIngComponent.Builder appComponent(AppComponent appComponent);

        ChuJIngComponent build();
    }
}