package me.androidbox.busbybaking.di;

/**
 * Created by steve on 7/29/17.
 */

public class MockBusbyBakingApplication extends BusbyBakingApplication {

    @Override
    public TestBusbyBakingComponent createApplicationComponent() {
        return DaggerTestBusbyBakingComponent.builder()
                .mockRecipeListModule(new MockRecipeListModule())
                .build();
    }
}
