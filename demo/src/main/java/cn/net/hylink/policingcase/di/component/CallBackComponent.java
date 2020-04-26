package cn.net.hylink.policingcase.di.component;

import cn.net.hylink.policingcase.di.module.CallBackModule;
import cn.net.hylink.policingcase.mvp.contract.CallBackContract;
import cn.net.hylink.policingcase.mvp.ui.activity.CallBackActivity;
import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;


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
@Component(modules = CallBackModule.class, dependencies = AppComponent.class)
public interface CallBackComponent {
    void inject(CallBackActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CallBackComponent.Builder view(CallBackContract.View view);

        CallBackComponent.Builder appComponent(AppComponent appComponent);

        CallBackComponent build();
    }
}