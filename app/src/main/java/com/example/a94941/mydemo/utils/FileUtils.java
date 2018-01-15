package com.example.a94941.mydemo.utils;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @创建者 94941
 * @创建时间 2018/1/11
 * @描述 ${TODO}
 */
public class FileUtils {

    public static void save(String fileName, String str) throws IOException {

        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(sdCardDir, fileName);

        if (!file.exists()) {
            file.createNewFile();
        }
        // 打开文件输出流
        FileOutputStream fileOut = new FileOutputStream(file);
        // 写入数据
        fileOut.write(str.getBytes());
        // 关闭文件输出流
        fileOut.close();
    }

    /*
     * 定义文件读取的方法
     * */
    public static String read(String fileName) throws IOException {

        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(sdCardDir, fileName);
        if (!file.exists()) {
            return "";
        } else {
            //打开文件输入流
            FileInputStream input = new FileInputStream(file);
            //定义1M的缓冲区
            byte[] temp = new byte[1024];
            //定义字符串变量
            StringBuilder sb = new StringBuilder("");
            int len = 0;
            //读取文件内容，当文件内容长度大于0时，
            while ((len = input.read(temp)) > 0) {
                //把字条串连接到尾部
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
            //返回字符串
            return sb.toString();
        }
    }


    /* 此方法为android程序写入sd文件文件，用到了android-annotation的支持库@
     *
     * @param buffer   写入文件的内容
     * @param folder   保存文件的文件夹名称,如log；可为null，默认保存在sd卡根目录
     * @param fileName 文件名称，默认app_log.txt
     * @param append   是否追加写入，true为追加写入，false为重写文件
     * @param autoLine 针对追加模式，true为增加时换行，false为增加时不换行
     */
    public synchronized static void writeFileToSDCard(@NonNull final byte[] buffer, @Nullable final String folder,
                                                      @Nullable final String fileName, final boolean append, final boolean autoLine) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean sdCardExist = Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED);
                String folderPath = "";
                if (sdCardExist) {
                    //TextUtils为android自带的帮助类
                    if (TextUtils.isEmpty(folder)) {
                        //如果folder为空，则直接保存在sd卡的根目录
                        folderPath = Environment.getExternalStorageDirectory()
                                + File.separator;
                    } else {
                        folderPath = Environment.getExternalStorageDirectory()
                                + File.separator + folder + File.separator;
                    }
                } else {
                    return;
                }

                File fileDir = new File(folderPath);
                if (!fileDir.exists()) {
                    if (!fileDir.mkdirs()) {
                        return;
                    }
                }
                File file;
                //判断文件名是否为空
                if (TextUtils.isEmpty(fileName)) {
                    file = new File(folderPath + "Bluetooth.txt");
                } else {
                    file = new File(folderPath + fileName);
                }
                RandomAccessFile raf = null;
                FileOutputStream out = null;
                try {
                    if (append) {
                        //如果为追加则在原来的基础上继续写文件
                        raf = new RandomAccessFile(file, "rw");
                        raf.seek(file.length());
                        raf.write(buffer);
                        if (autoLine) {
                            raf.write("\n".getBytes());
                        }
                    } else {
                        //重写文件，覆盖掉原来的数据
                        out = new FileOutputStream(file);
                        out.write(buffer);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (raf != null) {
                            raf.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
