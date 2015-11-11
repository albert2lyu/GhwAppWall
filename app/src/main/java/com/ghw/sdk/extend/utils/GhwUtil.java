package com.ghw.sdk.extend.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Created by ghw on 15/5/4.
 */
public class GhwUtil {
    /**
     * 检查所有的权限是否都有设置
     * @param context
     * @param permisions
     * @return
     */
    public static boolean checkPermissions(Context context, Collection<String> permisions) {
        if(null == permisions || permisions.isEmpty()) {
            return true;
        }
        PackageManager pm = context.getApplicationContext().getPackageManager();
        Set<String> requestedPermissions = new TreeSet<String>();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            String [] userPermissions = packageInfo.requestedPermissions;
            if(null != userPermissions) {
                for(String userPermission : userPermissions) {
                    requestedPermissions.add(userPermission);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return requestedPermissions.containsAll(permisions);
    }

}
