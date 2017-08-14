package me.androidbox.busbybaking.di;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import me.androidbox.busbybaking.TestBusbyBakingApplication;

/**
 * Created by steve on 7/29/17.
 */

public class MockBusbyBakingTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        return super.newApplication(cl, TestBusbyBakingApplication.class.getName(), context);
    }
}
