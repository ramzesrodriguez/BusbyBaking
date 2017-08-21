package me.androidbox.busbybaking;

import me.androidbox.busbybaking.di.BusbyBakingApplication;
import me.androidbox.busbybaking.di.DaggerTestBusbyBakingComponent;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.TestBusbyBakingComponent;
import me.androidbox.busbybaking.di.TestRecipeListComponent;

/**
 * Created by steve on 7/29/17.
 */

public class TestBusbyBakingApplication extends BusbyBakingApplication {
    private TestBusbyBakingComponent testBusbyBakingComponent;
    private TestRecipeListComponent testRecipeListComponent;

    @Override
    public TestBusbyBakingComponent createApplicationComponent() {
        testBusbyBakingComponent = createTestBusbyBakingComponent();

        return testBusbyBakingComponent;
    }

    @Override
    public TestRecipeListComponent createRecipeListComponent() {
        testRecipeListComponent = testBusbyBakingComponent.add(new MockRecipeListModule());

        return testRecipeListComponent;
    }

    @Override
    public TestRecipeListComponent getRecipeListComponent() {
        return testRecipeListComponent;
    }

    private TestBusbyBakingComponent createTestBusbyBakingComponent() {
        testBusbyBakingComponent = DaggerTestBusbyBakingComponent.builder()
                .build();

        return testBusbyBakingComponent;
    }
}
