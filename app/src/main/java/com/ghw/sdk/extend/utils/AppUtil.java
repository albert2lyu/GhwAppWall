package com.ghw.sdk.extend.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * App utils
 * Created by yinglovezhuzhu@gmail.com on 2015/11/4.
 */
public class AppUtil {

    private AppUtil() {

    }

    /**
     * 获取已安装的应用信息（返回List）
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledPackages(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        return packageInfos;
    }

    /**
     * 获取已安装的应用信息（返回Map）
     * @param context
     * @return
     */
    public static Map<String, PackageInfo> getInstalledPackagesAsMap(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        Map<String, PackageInfo> packageInfoMap = new HashMap<String, PackageInfo>();
        for(PackageInfo info :packageInfos) {
            packageInfoMap.put(info.packageName, info);
        }
        return packageInfoMap;
    }

    /**
     * 判断否已安装某个应用是
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if(null == packageInfo) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }

    /**
     * 打开应用程序
     * @param context Context
     * @param packageName 要打开应用的包名
     * @throws PackageManager.NameNotFoundException 如果设备没有安装该应用，那么将出现找不到应用的异常
     */
    public static void launchApp(Context context, String packageName) throws PackageManager.NameNotFoundException{
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if(null == intent) {
            throw new PackageManager.NameNotFoundException("Package '" + packageName + "' not found in device");
        }
        context.startActivity(intent);
    }
}
