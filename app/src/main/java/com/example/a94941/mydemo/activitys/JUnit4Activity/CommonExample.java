package com.example.a94941.mydemo.activitys.JUnit4Activity;

import java.io.File;

/**
 * @创建者 94941
 * @创建时间 2018/2/22
 * @描述 ${TODO}
 */
public class CommonExample {

    public boolean callArgumentInstance(File file) {
        return file.exists();
    }

    public boolean callArgumentInstance(String path) {
        File file = new File(path);
        return file.exists();
    }
}
