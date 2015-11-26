/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chunsun.redenvelope.listeners;


import android.content.Context;

public interface BaseMultiLoadedListener<T> {

    /**
     * when data call back success
     *
     * @param event_tag
     * @param data
     */
    void onSuccess(int event_tag, T data);

    /**
     * when data call back error
     *
     * @param msg
     */
    void onError(String msg);

    void onError(int event_tag, String msg);

    void onError(String msg, Context context, String from);

    /**
     * when data call back occurred exception
     *
     * @param msg
     */
    void onException(String msg);
}
