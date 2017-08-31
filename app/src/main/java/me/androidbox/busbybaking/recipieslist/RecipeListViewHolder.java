package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.androidbox.busbybaking.R;
import me.androidbox.busbybaking.model.Recipe;
import timber.log.Timber;

/**
 * Created by steve on 6/7/17.
 */
@AutoFactory(implementing = IRecipeListViewHolderFactory.class)
public class RecipeListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecipeName) public TextView tvRecipeName;
    @BindView(R.id.tvQuantity) public TextView tvQuantity;

    public RecipeListViewHolder(View view) {
        super(view);

        ButterKnife.bind(RecipeListViewHolder.this, view);
    }

    public void populateDate(Recipe recipe) {
        tvRecipeName.setText(recipe.getName());
        final String quantity = "Quantity: " + recipe.getServings();
        Timber.d(recipe.getName());
        tvQuantity.setText(quantity);
    }
}

/*
public class TravelerInboxItemViewHolder extends InboxItemViewHolder {

    private final String hostNameDefault;

    @BindView(R.id.user_avatar)
    public UserAvatarView userAvatarView;

    @BindView(R.id.user_display_name)
    public TextView otherParticipantName;

    @BindView(R.id.relative_time)
    public TextView relativeTime;

    @BindView(R.id.preview_message)
    public TextView previewMessage;

    @BindView(R.id.reservation_status)
    public TextView reservationStatus;

    @BindView(R.id.property_name)
    public TextView propertyName;

    @BindView(R.id.is_read_indicator)
    public ImageView isReadIndicator;

    @BindView(R.id.date_of_stay)
    public TextView dateOfStay;

    public TravelerInboxItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        hostNameDefault = itemView.getContext().getString(R.string.traveler_chat_default_nha_title);
    }

    public void bind(InboxItem inboxItem, TravelerInboxDateFormatter formatter) {
        previewMessage.setText(inboxItem.previewMessageContent);

        PropertyInfo propertyInfo = inboxItem.propertyInfo;
        setPropertyInfo(propertyInfo);

        ConversationId conversationId = inboxItem.conversationId;
        dateOfStay.setText(formatter.getFormattedCheckInDay(conversationId.checkInDate(), conversationId.checkOutDate()));
    }

    private void setPropertyInfo(PropertyInfo conversationInfo) {
        userAvatarView.setAvatarImage(conversationInfo.getAvatarIcon());
        otherParticipantName.setText(composePropertyName(conversationInfo));
        propertyName.setText(conversationInfo.propertyName);
        propertyName.setVisibility(conversationInfo.isNha ? View.VISIBLE : View.GONE);
    }

    private String composePropertyName(PropertyInfo property) {
        if (!property.isNha) {
            return property.propertyName;
        }

        return property.hostName != null && property.hostName.isEmpty()
                ? getNhaHostDefaultTitle()
                : property.hostName;
    }

    private String getNhaHostDefaultTitle() {
        return hostNameDefault;
    }

    public void displayAsUnread() {
        isReadIndicator.setVisibility(View.VISIBLE);
        relativeTime.setTextColor(ContextCompat.getColor(itemView.getContext(), com.agoda.mobile.core.R.color.red_400));
        otherParticipantName.setTypeface(AgodaTypefaceUtils.getTypeface(itemView.getContext(), Typeface.BOLD));
        previewMessage.setTypeface(AgodaTypefaceUtils.getTypeface(itemView.getContext(), Typeface.BOLD));
    }

    public void displayAsRead() {
        isReadIndicator.setVisibility(View.INVISIBLE);
        relativeTime.setTextColor(ContextCompat.getColor(itemView.getContext(), com.agoda.mobile.core.R.color.color_light_gray));
        otherParticipantName.setTypeface(AgodaTypefaceUtils.getTypeface(itemView.getContext(), Typeface.NORMAL));
        previewMessage.setTypeface(AgodaTypefaceUtils.getTypeface(itemView.getContext(), Typeface.NORMAL));
    }
}
*/

