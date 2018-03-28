package com.rainbow.laundry.receicer;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.rainbow.laundry.ui.index.MainActivity;
import com.rainbow.laundry.ui.order.OrderStateQueryActivity;
import com.rainbow.laundry.util.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			String dsd = printBundle(bundle);
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));


				// processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);

				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");
				Log.i("NotificationReceiver", "the app process is dead111");
				String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
				String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
				Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
				msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
				String indentid ="";
				if (!ExampleUtil.isEmpty(extras)) {
					try {
						JSONObject extraJson = new JSONObject(extras);
						if (extraJson.length() > 0) {
							String ds =extraJson.getString("androidJson");
							JSONObject jsonObject = new JSONObject(ds);
							  indentid =  jsonObject.optString("indentid");
							//msgIntent.putExtra(MainActivity.KEY_EXTRAS, indentid);
						}
					} catch (JSONException e) {

					}
				}
				//LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);

				String pName = "com.rainbow.laundry";
				int uid =  getPackageUid(context, pName);
				if(uid > 0){
					boolean rstA = isAppRunning(context, pName);
					boolean rstB = isProcessRunning(context, uid);
					if(rstA||rstB){
						//指定包名的程序正在运行中
						Intent mainIntent = new Intent(context, MainActivity.class);
						//将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
						//如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
						//如果Task栈不存在MainActivity实例，则在栈顶创建
						mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						Intent detailIntent = new Intent(context, OrderStateQueryActivity.class);
						detailIntent.putExtra("orderId", indentid);
						Intent[] intents = {mainIntent, detailIntent};
						context.startActivities(intents);
					}else{
						//指定包名的程序未在运行中
						Log.i("NotificationReceiver", "the app process is dead");
						Intent launchIntent = context.getPackageManager().
								getLaunchIntentForPackage("com.rainbow.laundry");
						launchIntent.setFlags(
								Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
						Bundle args = new Bundle();
						args.putString("orderId", indentid);
						launchIntent.putExtra("EXTRA_BUNDLE", args);
						context.startActivity(launchIntent);
					}
				}else{
					//应用未安装
				}

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}


	public   boolean isProcessRunning(Context context, int uid) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
		if (runningServiceInfos.size() > 0) {
			for (ActivityManager.RunningServiceInfo appProcess : runningServiceInfos){
				if (uid == appProcess.uid) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 方法描述：判断某一应用是否正在运行
	 * Created by cafeting on 2017/2/4.
	 * @param context     上下文
	 * @param packageName 应用的包名
	 * @return true 表示正在运行，false 表示没有运行
	 */
	public   boolean isAppRunning(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
		if (list.size() <= 0) {
			return false;
		}
		for (ActivityManager.RunningTaskInfo info : list) {
			if (info.baseActivity.getPackageName().equals(packageName)) {
				return true;
			}
		}
		return false;
	}

	//获取已安装应用的 uid，-1 表示未安装此应用或程序异常
	public   int getPackageUid(Context context, String packageName) {
		try {
			ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
			if (applicationInfo != null) {
				return applicationInfo.uid;
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}




	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		Common.log("-------");
		if(MainActivity.isForeground){
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						String ds =extraJson.getString("json");
//						JSONObject jsonObject = new JSONObject(ds);
//						String indentid =  jsonObject.optString("indentid");
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, indentid);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}

	}

}
