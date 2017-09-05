package me.androidbox.busbybaking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.utils.Constants;

/**
 * Created by steve on 6/7/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {
    @VisibleForTesting
    List<Recipe> recipeList = Collections.emptyList();
    private Map<Integer, RecipeListViewHolderFactory> viewHolderFactories;
    private RecipeItemClickListener recipeItemClickListener;

    @Inject
    public RecipeAdapter(RecipeItemClickListener recipeItemClickListener, Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        this.recipeList = new ArrayList<>();
        this.viewHolderFactories = viewHolderFactories;
        this.recipeItemClickListener = recipeItemClickListener;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        final RecipeListViewHolder recipeListViewHolder = viewHolderFactories.get(Constants.RECIPE_LIST).createViewHolder(view);

        recipeListViewHolder.itemView.setOnClickListener(itemView -> {
            recipeItemClickListener.onRecipeItemClick(getRecipe(recipeListViewHolder.getAdapterPosition()), viewGroup.getContext());
        });

        return recipeListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder recipeListViewHolder, int i) {
        recipeListViewHolder.populateDate(recipeList.get(i));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private Recipe getRecipe(int position) {
        return recipeList.get(position);
    }

    public void fillRecipeData(List<Recipe> recipes) {
        recipeList.clear();
        recipeList.addAll(recipes);
        notifyItemRangeInserted(0, recipeList.size());
    }
}

/* public class TravelerInboxAdapter extends InboxAdapter<TravelerInboxItemViewHolder> {

    private final TravelerInboxDateFormatter formatter;

    public TravelerInboxAdapter(Cursor cursor,
                                IBookingStatusStringProvider bookingStatusStringProvider,
                                InboxCursorTransformer inboxCursorTransformer,
                                TravelerInboxDateFormatter formatter) {
        super(cursor, bookingStatusStringProvider, inboxCursorTransformer);
        this.formatter = formatter;
    }

    @Override
    public int getInboxItemViewLayout() {
        return R.layout.traveler_inbox_item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_INBOX_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(getInboxItemViewLayout(), parent, false);
                TravelerInboxItemViewHolder viewHolder = new TravelerInboxItemViewHolder(view);
                setOnClickEvent(viewHolder);
                return viewHolder;
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void bindInboxItemViewHolder(TravelerInboxItemViewHolder viewHolder, int position) {
        super.bindInboxItemViewHolder(viewHolder, position);
        InboxItem item = getItem(position);
        viewHolder.bind(item, formatter);
    }

    @Override
    protected void setReservationStatus(final TravelerInboxItemViewHolder viewHolder, final InboxItem item) {
        if (item.travelerStatus == null || item.travelerStatus.isEmpty()) {
            viewHolder.reservationStatus.setText(bookingStatusStringProvider.getInquiryTitle());
        } else {
            viewHolder.reservationStatus.setText(item.travelerStatus);
        }
    }

    @Override
    protected void setReservationTime(final TravelerInboxItemViewHolder viewHolder, final InboxItem item) {
        LocalDate messageDate = item.latestMessageDateTime.toLocalDate();
        if (messageDate.isEqual(Clocks.today())) {
            viewHolder.relativeTime.setText(StaticDateTimePatterns.TIME.format(item.latestMessageDateTime));
        } else if (messageDate.getYear() == Clocks.today().getYear()) {
            viewHolder.relativeTime.setText(StaticDateTimePatterns.MONTH_NAME_SHORT_DAY_SHORT.format(item.latestMessageDateTime));
        } else {
            viewHolder.relativeTime.setText(StaticDateTimePatterns.MONTH_NAME_SHORT_DAY_SHORT_YEAR_SHORT.format(item.latestMessageDateTime));
        }
    }

    @Override
    protected void shouldDisplayUnreadNotification(final TravelerInboxItemViewHolder viewHolder, final InboxItem item) {
        if (item.isRead) {
            viewHolder.displayAsRead();
        } else {
            viewHolder.displayAsUnread();
        }
    }
}*/
