package me.androidbox.busbybaking.di;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by steve on 5/31/17.
 */

@Singleton
@Component(modules = {MockAndroidModule.class})
public interface TestBusbyBakingComponent extends TestBaseBusbyBakingComponent {
}
