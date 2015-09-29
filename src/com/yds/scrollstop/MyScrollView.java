/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yds.scrollstop;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 这是一个 ScrollView 对于滚动的监听器设置
 */
public class MyScrollView extends ScrollView {
	private OnScrollBack mCallbacks;

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mCallbacks != null) {
			mCallbacks.onScrollChanged(t);
		}
	}

	@Override
	public int computeVerticalScrollRange() {
		return super.computeVerticalScrollRange();
	}

	public void setOnScrollBack(OnScrollBack listener) {
		mCallbacks = listener;
	}

	public static interface OnScrollBack {
		public void onScrollChanged(int scrollY);
	}
}