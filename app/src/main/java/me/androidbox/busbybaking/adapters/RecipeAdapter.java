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

public class TravelerChatAdapter extends ChatAdapter {
    public TravelerChatAdapter(ChatAdapter.Controller controller, Cursor cursor, String hostId) {
        super(controller, cursor, hostId);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TYPE_SPECIAL_REQUEST: {
                final View view = inflater.inflate(R.layout.chat_special_request_message_item, parent, false);
                return new SpecialRequestsViewHolder(view);
            }
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch(getItemViewType(position)) {
            case TYPE_SPECIAL_REQUEST: {
                onBindSpecialRequestViewHolder((SpecialRequestsViewHolder) holder, position);
                break;
            }
            default:
                super.onBindViewHolder(holder, position);
        }
    }

    private void onBindSpecialRequestViewHolder(SpecialRequestsViewHolder holder, int position) {
        final ChatMessage chatMessage = getItem(position);
        holder.populateSpecialRequestContent(chatMessage.content, getTimeFromDate(chatMessage.timestamp));
    }

    @Override
    protected void onBindIncomingMessageHolder(IncomingMessageViewHolder holder, int position) {
        super.onBindIncomingMessageHolder(holder, position);
        holder.avatarView.setAvatarImage(chatViewModel.propertyInfo.getAvatarIcon());
    }
}

