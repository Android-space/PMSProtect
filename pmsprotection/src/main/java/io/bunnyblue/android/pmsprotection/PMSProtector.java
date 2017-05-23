package io.bunnyblue.android.pmsprotection;

import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by bunnyblue on 5/23/17.
 */

public class PMSProtector {
    public static void watchPMS(Context context) {
        Class<?> activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod =
                    activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);
            // 获取ActivityThread里面原始的sPackageManager
            Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
            sPackageManagerField.setAccessible(true);
            Object sPackageManager = sPackageManagerField.get(currentActivityThread);
            if (sPackageManager instanceof Proxy) {
                System.err.println(" found bad pms");
                //TODO sPackageManager is hooked in proxy
            }
            //  sPackageManager.getClass().getDeclaredField("location");
            PackageManager pm = context.getPackageManager();
            if (pm.getClass().getClassLoader().getClass().getName().equals("java.lang.BootClassLoader")) {
                //TODO throw exception,PackageManager should in BootClassLoader
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
//
//result = {Class@582} "class android.content.pm.IPackageManager$Stub$Proxy"
//        accessFlags = 524288
//        annotationType = null
//        classFlags = 0
//        classLoader = null
//        classSize = 848
//        clinitThreadId = 0
//        componentType = null
//        copiedMethodsOffset = 168
//        dexCache = {DexCache@4623}
//        dex = {Dex@4637}
//        dexFile = 2911552112
//        location = "/system/framework/framework.jar"
