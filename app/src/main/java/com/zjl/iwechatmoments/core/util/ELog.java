package com.zjl.iwechatmoments.core.util;

import android.util.Log;

public class ELog {

	public static final String TAG = "IWeChat_Moments_App";
	private static boolean DEBUG = true;

	public static void v(String tag, String msg) {
		if(msg == null){return;}
		if (DEBUG)
			Log.v(tag, msg);
	}

	public static void d(String tag, String msg) {
		if(msg == null){return;}
		if (DEBUG)
			Log.d(tag, msg);
	}

	public static void i(String tag, String msg) {
		if(msg == null){return;}
		if (DEBUG)
			Log.i(tag, msg);
	}

	public static void w(String tag, String msg) {
		if(msg == null){return;}
		if (DEBUG)
			Log.w(tag, msg);
	}

	public static void e(String tag, String msg) {
		if(msg == null){return;}
		if (DEBUG)
			Log.e(tag, msg);
	}
}