package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by steve on 6/3/17.
 */

public class MockBusbyBakingTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestBusbyBakingApplication.class.getName(), context);
    }
}
