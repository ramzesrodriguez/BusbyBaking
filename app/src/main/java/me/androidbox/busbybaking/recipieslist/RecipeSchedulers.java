package me.androidbox.busbybaking.recipieslist;

import rx.Scheduler;

/**
 * Created by steve on 6/24/17.
 */

public interface RecipeSchedulers {
    Scheduler getBackgroundScheduler();
    Scheduler getUIScheduler();
}
