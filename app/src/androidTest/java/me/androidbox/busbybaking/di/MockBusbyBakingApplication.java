package me.androidbox.busbybaking.di;

/**
 * Created by steve on 5/31/17.
 */

public class MockBusbyBakingApplication extends BusbyBakingApplication {
    private TestingBusbyBakingComponent testingBusbyBakingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        createBusbyBakingComponent();
    }

    @Override
    public RecipeListComponent createRecipeListComponent() {
        return null;
    }

    public void createBusbyBakingComponent() {

    }

    public TestingBusbyBakingComponent getBusbyBakingComponent() {
        return testingBusbyBakingComponent;
    }
}
