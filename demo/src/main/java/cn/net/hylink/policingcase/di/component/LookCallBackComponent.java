package cn.net.hylink.policingcase.di.component;

import cn.net.hylink.policingcase.di.module.LookCallBackModule;
import cn.net.hylink.policingcase.mvp.contract.LookCallBackContract;
import cn.net.hylink.policingcase.mvp.ui.activity.LookCallBackActivity;
import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;


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
@Component(modules = LookCallBackModule.class, dependencies = AppComponent.class)
public interface LookCallBackComponent {
    void inject(LookCallBackActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LookCallBackComponent.Builder view(LookCallBackContract.View view);

        LookCallBackComponent.Builder appComponent(AppComponent appComponent);

        LookCallBackComponent build();
    }
}