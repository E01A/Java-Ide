package com.pranav.lib_android.util;

import android.content.Context;

import java.nio.charset.Charset;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private static Context mContext;

    public static void initializeContext(Context context) {
        mContext = context;
    }

    public static boolean createDirectory(String path) {
        return new File(path).mkdir();
    }
    
    public static void writeFile(InputStream in, String path) throws IOException {
        File file = new File(path);
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void writeFile(String path, String content) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        Files.write(file.toPath(), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static void deleteFile(String path) {
        File file = new File(path);

        if (!file.exists()) return;

        if (file.isFile()) {
            file.delete();
            return;
        }

        File[] fileArr = file.listFiles();

        if (fileArr != null) {
            for (File subFile : fileArr) {
                if (subFile.isDirectory()) {
                    deleteFile(subFile.getAbsolutePath());
                }

                if (subFile.isFile()) {
                    subFile.delete();
                }
            }
        }

        file.delete();
    }

    public static String getFileName(String path) {
        String[] splited = path.split("/");
        return splited[splited.length - 1];
    }

    private static String getDataDir() {
        return mContext.getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getJavaDir() {
        return getDataDir() + "/java/";
    }

    public static String getBinDir() {
        return getDataDir() + "/bin/";
    }
    
    public static String getCacheDir() {
        // write caches to external storage because we don't want android system to delete index files
        return getDataDir() + "/cache/";
    }

    public static String getClasspathDir() {
        return getDataDir() + "/classpath/";
    }
}