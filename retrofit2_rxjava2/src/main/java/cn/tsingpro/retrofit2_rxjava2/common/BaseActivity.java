package cn.tsingpro.retrofit2_rxjava2.common;

import android.support.v4.app.FragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yemao
 * @date 2017/4/9
 * @description 写自己的代码, 让别人说去吧!
 */

public class BaseActivity extends FragmentActivity {
    public <T> ObservableTransformer<T,T> setThread(){
       return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T>  apply(Observable<T>  upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
