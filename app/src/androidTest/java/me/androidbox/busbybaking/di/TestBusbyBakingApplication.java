package me.androidbox.busbybaking.di;

/**
 * Created by steve on 5/31/17.
 */

public class TestBusbyBakingApplication extends BusbyBakingApplication {
 //   private BusbyBakingComponent testBusbyBakingComponent;
 //   private TestRecipeListComponent testRecipeListComponent;

    @Override
    public BusbyBakingComponent createApplicationComponent() {
        return  DaggerTestBusbyBakingComponent.builder()
                .mockAndroidModule(new MockAndroidModule())
                .build();
    }


/*

    @Override
    public TestBusbyBakingComponent createApplicationComponent() {
        return DaggerTestBusbyBakingComponent
                .builder()
                .mockAndroidModule(new MockAndroidModule(TestBusbyBakingApplication.this))
                .build();
    }
*/

    /*
    private void createBusbyBakingComponent() {
        testBusbyBakingComponent = DaggerTestBusbyBakingComponent.builder()
                .mockAndroidModule(new MockAndroidModule(TestBusbyBakingApplication.this))
                .build();
    }
*/

  /*  @Override
    public void createApplicationComponent() {
        testBusbyBakingComponent = DaggerTestBusbyBakingComponent.builder()
                .mockAndroidModule(new MockAndroidModule(TestBusbyBakingApplication.this))
                .build();
    }

    public BusbyBakingComponent getApplicationComponent() {
        return testBusbyBakingComponent;
    }

    public TestRecipeListComponent createRecipeListComponent() {
        testRecipeListComponent = getApplicationComponent().add(new MockRecipeListModule());
        return testRecipeListComponent;
    }

    public void createBusbyBakingComponent() {
    }*/
}
