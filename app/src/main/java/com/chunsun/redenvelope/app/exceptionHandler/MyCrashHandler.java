package com.chunsun.redenvelope.app.exceptionHandler;

import android.content.Context;
import android.os.Environment;

import com.chunsun.redenvelope.utils.manager.AppManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
	// 需求是 整个应用程序 只有一个 MyCrash-Handler
	private static MyCrashHandler myCrashHandler;
	private Context context;

	// 1.私有化构造方法
	private MyCrashHandler() {

	}

	public static synchronized MyCrashHandler getInstance() {
		if (myCrashHandler != null) {
			return myCrashHandler;
		} else {
			myCrashHandler = new MyCrashHandler();
			return myCrashHandler;
		}
	}

	public void init(Context context) {
		this.context = context;
	}

	public void uncaughtException(Thread arg0, Throwable arg1) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(),
					"log_" + System.currentTimeMillis() + ".txt");
			PrintWriter pw = new PrintWriter(file);
			arg1.printStackTrace(pw);
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();

		}

		AppManager.getAppManager().finishAllActivityAndExit();

		// 干掉当前的程序
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}