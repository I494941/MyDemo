package wjf.com.mykotlin.base.singleton;

/**
 * Created by wjf on 2018/8/31.
 */

public class SingletonJ {

    private static SingletonJ instance = null;

    private SingletonJ() {
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new SingletonJ();
        }
    }

    public static SingletonJ getInstance() {
        if (instance == null)
            createInstance();
        return instance;
    }
}
