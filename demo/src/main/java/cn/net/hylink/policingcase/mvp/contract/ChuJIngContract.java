package cn.net.hylink.policingcase.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import io.reactivex.Observable;
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
public interface ChuJIngContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setListData(JqListEntity bean);

        void setCount(JqCountEntity bean);

        void signSuccess();

        void endSuccess();

        void arriveSuccess();

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<JqListEntity>> getListdata(JqListBean map);
        Observable<BaseResponse<JqCountEntity>> getCount(CountBean map);
        Observable<BaseResponse<Object>> getJqQs(JqBtGMBean map);
        Observable<BaseResponse<Object>> getJqjqjs(JqBtGMBean map);
        Observable<BaseResponse<Object>> getJqdcqr(JqBtGMBean map);

    }
}
