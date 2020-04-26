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
package cn.net.hylink.policingcase.mvp.model.api;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import cn.net.hylink.policingcase.mvp.model.entity.UserEntity;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by JessYan on 08/05/2016 11:25
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://192.168.3.118:7701/";
    String REQUEST_SUCCESS = "200";
    String GET_JQ_LIST = "jq/getJqList";
    String GET_JQCOUNT = "jq/getJqCount";
    String GET_JQQS = "jq/jqQs";
    String GET_JQJQJS = "jq/jqJqjs";
    String GET_JQDCQR = "jq/jqDcqr";
    String GET_JQDICT = "jq/jqDict";
    String GET_JQJQFK = "jq/jqJqfk";
    String GET_YWDICT = "jq/ywDict";
    String GET_JQJQFKLIST = "jq/getJqFkList";
    String GET_QWKB = "jq/dutyAttendanceStatistics";

}
