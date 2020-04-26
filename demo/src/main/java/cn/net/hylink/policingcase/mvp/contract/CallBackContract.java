package cn.net.hylink.policingcase.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import cn.net.hylink.policingcase.mvp.model.bean.CallBackBean;
import cn.net.hylink.policingcase.mvp.model.bean.PullDownBean;
import cn.net.hylink.policingcase.mvp.model.bean.ShortWordBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;
import io.reactivex.Observable;


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
public interface CallBackContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
       void  pullDownSuccess(PullDownEntity bean);
       void  wordSuccess(ShortWordEntity bean);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<PullDownEntity>> getPullDown(PullDownBean map);
        Observable<BaseResponse<ShortWordEntity>> getWord(ShortWordBean map);
        Observable<BaseResponse<Object>> postCallBack(CallBackBean map);
    }
}
