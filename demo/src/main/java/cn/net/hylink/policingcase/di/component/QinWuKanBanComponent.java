package cn.net.hylink.policingcase.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import cn.net.hylink.policingcase.di.module.QinWuKanBanModule;
import cn.net.hylink.policingcase.mvp.contract.QinWuKanBanContract;

import com.jess.arms.di.scope.FragmentScope;

import cn.net.hylink.policingcase.mvp.ui.fragment.QinWuKanBanFragment;


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
@Component(modules = QinWuKanBanModule.class, dependencies = AppComponent.class)
public interface QinWuKanBanComponent {
    void inject(QinWuKanBanFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        QinWuKanBanComponent.Builder view(QinWuKanBanContract.View view);

        QinWuKanBanComponent.Builder appComponent(AppComponent appComponent);

        QinWuKanBanComponent build();
    }
}