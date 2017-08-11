package me.androidbox.busbybaking.di;

/**
 * Created by steve on 7/29/17.
 */

public class MockBusbyBakingApplication extends BusbyBakingApplication {
    private TestBusbyBakingComponent testBusbyBakingComponent;
    private TestRecipeListComponent testRecipeListComponent;

    @Override
    public TestBusbyBakingComponent createApplicationComponent() {
        testBusbyBakingComponent = createTestBusbyBakingComponent();
        testRecipeListComponent = createTestRecipeListComponent();

        return testBusbyBakingComponent;
    }

    private TestBusbyBakingComponent createTestBusbyBakingComponent() {
        testBusbyBakingComponent = DaggerTestBusbyBakingComponent.builder()
                .build();

        return testBusbyBakingComponent;
    }

    private TestRecipeListComponent createTestRecipeListComponent() {
        return testBusbyBakingComponent.add(new MockRecipeListModule());
    }

    public TestRecipeListComponent getTestRecipeListComponent() {
        return testRecipeListComponent;
    }

}
