package com.pranav.android.task;

import android.content.Context;

import com.pranav.android.interfaces.*;
import com.pranav.android.task.java.*;

public class JavaBuilder implements Builder {

    public final ClassLoader classloader;

    public Context mContext;

    public JavaBuilder(Context context, ClassLoader loader) {
        mContext = context;
        classloader = loader;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public ClassLoader getClassloader() {
        return this.classloader;
    }
}
