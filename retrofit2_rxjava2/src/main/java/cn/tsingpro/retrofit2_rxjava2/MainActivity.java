package cn.tsingpro.retrofit2_rxjava2;

import android.os.Bundle;

import java.util.ArrayList;

import cn.tsingpro.retrofit2_rxjava2.common.BaseActivity;
import cn.tsingpro.retrofit2_rxjava2.http.RetrofitFactory;
import cn.tsingpro.retrofit2_rxjava2.http.base.BaseObserver;
import cn.tsingpro.retrofit2_rxjava2.http.bean.ABean;
import cn.tsingpro.retrofit2_rxjava2.http.bean.BaseEntity;
import cn.tsingpro.retrofit2_rxjava2.utils.UploadUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    public void getData() {
        RetrofitFactory.getInstence().API()
                .getBaidu("我是中国人")
                .compose(this.<BaseEntity<ABean>>setThread())
                .subscribe(new BaseObserver<ABean>() {
                    @Override
                    protected void onSuccees(BaseEntity<ABean> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }

    public void upload() {
        String filepath = "图片本地路径";
        UploadUtil.uploadImage(filepath, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void uploads() {
        ArrayList<String> listFilePath = new ArrayList<>();
        listFilePath.add("图片1路径");
        listFilePath.add("图片2路径");
        UploadUtil.uploadImages(listFilePath, new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
