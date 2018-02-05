package tsingpro.com.zxingnetdemo.app;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by ws on 2017/8/22 0014.
 */

public class MyApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        //zxing
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
