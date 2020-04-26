/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.net.hylink.policingcase.mvp.model.api.service;

import cn.net.hylink.policingcase.mvp.model.bean.CallBackLookBean;
import cn.net.hylink.policingcase.mvp.model.bean.QwkbBean;
import cn.net.hylink.policingcase.mvp.model.entity.BaseResponse;
import cn.net.hylink.policingcase.mvp.model.entity.CallBackListEntity;
import cn.net.hylink.policingcase.mvp.model.entity.QwkbEntity;
import io.reactivex.Observable;
import cn.net.hylink.policingcase.mvp.model.api.Api;
import cn.net.hylink.policingcase.mvp.model.bean.CallBackBean;
import cn.net.hylink.policingcase.mvp.model.bean.CountBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqBtGMBean;
import cn.net.hylink.policingcase.mvp.model.bean.JqListBean;
import cn.net.hylink.policingcase.mvp.model.bean.PullDownBean;
import cn.net.hylink.policingcase.mvp.model.bean.ShortWordBean;
import cn.net.hylink.policingcase.mvp.model.entity.JqCountEntity;
import cn.net.hylink.policingcase.mvp.model.entity.JqListEntity;
import cn.net.hylink.policingcase.mvp.model.entity.PullDownEntity;
import cn.net.hylink.policingcase.mvp.model.entity.ShortWordEntity;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * ================================================
 * 存放通用的一些 API
 * <p>
 * Created by JessYan on 08/05/2016 12:05
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface CommonService {

    //    @Headers("Content-Type:application/json; charset=utf-8")
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQ_LIST)
    Observable<BaseResponse<JqListEntity>> getJqList(@Body JqListBean map);

    //    @FormUrlEncoded
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQCOUNT)
    Observable<BaseResponse<JqCountEntity>> getJqCount(@Body CountBean map);

    //    @FormUrlEncoded
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQQS)
    Observable<BaseResponse<Object>> getJqQs(@Body JqBtGMBean map);

    //    @FormUrlEncoded
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQJQJS)
    Observable<BaseResponse<Object>> getJqjqjs(@Body JqBtGMBean map);

    //    @FormUrlEncoded
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQDCQR)
    Observable<BaseResponse<Object>> getJqdcqr(@Body JqBtGMBean map);

    //    @FormUrlEncoded
    @Headers("Authorization: authorization")
    @POST(Api.GET_JQJQFK)
    Observable<BaseResponse<Object>> getJqjqkf(@Body CallBackBean map);

    @Headers("Authorization: authorization")
    @POST(Api.GET_YWDICT)
    Observable<BaseResponse<ShortWordEntity>> getGmWord(@Body ShortWordBean map);

    @Headers("Authorization: authorization")
    @POST(Api.GET_JQDICT)
    Observable<BaseResponse<PullDownEntity>> getListPullDown(@Body PullDownBean map);

    @Headers("Authorization: authorization")
    @POST(Api.GET_JQJQFKLIST)
    Observable<BaseResponse<CallBackListEntity>> getCallBackList(@Body CallBackLookBean map);

    @Headers("Authorization: authorization")
    @POST(Api.GET_QWKB)
    Observable<BaseResponse<QwkbEntity>> getQwkbData(@Body QwkbBean map);

}
