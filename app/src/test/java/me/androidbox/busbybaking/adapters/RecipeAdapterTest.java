package me.androidbox.busbybaking.adapters;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import me.androidbox.busbybaking.BuildConfig;
import me.androidbox.busbybaking.di.DaggerTestBusbyComponent;
import me.androidbox.busbybaking.di.MockRecipeListModule;
import me.androidbox.busbybaking.di.TestBusbyComponent;
import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.MainActivity;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 7/4/17.
 */
/*
@Config(constants = BuildConfig.class,
        sdk = TestConstants.DEFAULT_ROBOLECTRIC_SDK,
        packageName = "com.agoda.mobile.consumer",
        application = AgodaTestApplication.class)
@RunWith(MockitoRobolectricRunner.class)
*/

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class RecipeAdapterTest {
    @Inject RecipeAdapter recipeAdapter;
    @Inject RecipeListViewHolderFactory recipeListViewHolderFactory;
    private ViewGroup linearLayout;
    private RecipeListViewHolder recipeListViewHolder;
    private MainActivity activity;

    @Before
    public void setup() {

        activity = Robolectric.setupActivity(MainActivity.class);

 /*       activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
*/
        final Context context = ShadowApplication.getInstance().getApplicationContext();
        linearLayout = new LinearLayout(context);

        TestBusbyComponent testBusbyComponent = DaggerTestBusbyComponent.builder()
                .mockRecipeListModule(new MockRecipeListModule())
                .build();

        testBusbyComponent.inject(RecipeAdapterTest.this);

        recipeAdapter.recipeList = createRecipes();
    }

    @Test
    public void testActivityShouldNotBeNull() {
        assertThat(activity, is(notNullValue()));
    }

    @Ignore
    @Test
    public void testRecipeAdapterShouldNotBeNull() {
        assertThat(recipeAdapter, is(notNullValue()));
    }

    @Ignore
    @Test
    public void testRecipeViewHolderFactory() {
        assertThat(recipeListViewHolderFactory, is(notNullValue()));
    }

    @Test
    public void testViewHolderIsCreated() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        assertThat(recipeListViewHolder, is(notNullValue()));
    }

    @Test
    public void testOnBindViewHolder() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        assertThat(recipeListViewHolder, is(notNullValue()));

  //      recipeListViewHolder.populateDate(createRecipes().get(0));
        recipeListViewHolder.tvRecipeName = mock(TextView.class);
        recipeAdapter.onBindViewHolder(recipeListViewHolder, 0);

 //       recipeListViewHolder.tvQuantity = mock(TextView.class);
        //  verify(recipeListViewHolder.tvQuantity).setText("10");

        verify(recipeListViewHolder.tvRecipeName).setText("Test Brownies");

/*

        TextView recipeName = (TextView)activity.findViewById(R.id.tvRecipeName);
        TextView quantity = (TextView)activity.findViewById(R.id.tvQuantity);
        assertThat(recipeName.getText().toString(), is(containsString("Test Brownies")));
*/
    }

    private List<Recipe> createRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);
        recipeList.add(recipe);

        return recipeList;
    }
}

/*
public class TravelerInboxAdapterTest extends BaseAppRobolectricTest {

    private static final int TYPE_INBOX_ITEM = 0;
    private static final int TYPE_LOADING_INDICATOR = 1;
    private static final ZoneId TIME_ZONE = ZoneId.of("Asia/Bangkok");

    @Mock IBookingStatusStringProvider bookingStatusStringProvider;
    @Mock TravelerInboxDateFormatter formatter;
    private Context shadowContext;
    private ViewGroup container;
    private InboxAdapter<TravelerInboxItemViewHolder> adapter;
    private MatrixCursor cursor;

    private OffsetDateTime todayTime;

    private Locale defaultLocale;

    @Before
    public void setUp() {
        ClocksMockAccessor.setMockTimeZone(TIME_ZONE);

        todayTime = Clocks.dateTime();
        cursor = createMockCursor();
        shadowContext = ShadowApplication.getInstance().getApplicationContext();

        InboxCursorTransformer inboxCursorTransformer = new InboxCursorTransformer(new DBReservationCursorTransformer(), new DBPropertyCursorTransformer());
        adapter = new TravelerInboxAdapter(cursor, bookingStatusStringProvider, inboxCursorTransformer, formatter);

        container = new FrameLayout(shadowContext);

        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
    }

    @After
    public void tearDown() {
        ClocksMockAccessor.resetMockClockAndTimeZone();
        Locale.setDefault(defaultLocale);
    }

    @Test
    public void getItemCountNoMoreData() {
        assertThat(adapter.getItemCount(), is(cursor.getCount()));
    }

    @Test
    public void onCreateViewHolder() {
        RecyclerView.ViewHolder holder;

        holder = adapter.onCreateViewHolder(container, TYPE_INBOX_ITEM);
        assertThat(holder, is(instanceOf(TravelerInboxItemViewHolder.class)));

        holder = adapter.onCreateViewHolder(container, TYPE_LOADING_INDICATOR);
        assertThat(holder, is(instanceOf(LoadingIndicatorViewHolder.class)));
    }

    @Test
    public void onBindInboxViewHolder_ifInquiry_andNonNhaTypeProperty_andUnread() {
        TravelerInboxItemViewHolder holder = (TravelerInboxItemViewHolder) adapter.onCreateViewHolder(container, TYPE_INBOX_ITEM);
        holder.userAvatarView = mock(UserAvatarView.class);
        holder.otherParticipantName = mock(TextView.class);
        holder.relativeTime = mock(TextView.class);
        holder.previewMessage = mock(TextView.class);
        holder.reservationStatus = mock(TextView.class);
        holder.propertyName = mock(TextView.class);
        holder.isReadIndicator = mock(ImageView.class);
        holder.dateOfStay = mock(TextView.class);

        when(bookingStatusStringProvider.getInquiryTitle()).thenReturn("Inquiry");
        when(formatter.getFormattedCheckInDay(any(), any())).thenReturn("Jan 4, 2015 - Jan 5");

        adapter.onBindViewHolder(holder, 0);

        verify(holder.userAvatarView).setAvatarImage(R.drawable.ic_host_avatar);
        verify(holder.otherParticipantName).setText("Property A");
        verify(holder.previewMessage).setText("Content");
        verify(holder.reservationStatus).setText("Inquiry");
        verify(holder.propertyName).setText("Property A");
        verify(holder.propertyName).setVisibility(View.GONE);
        verify(holder.relativeTime).setText(StaticDateTimePatterns.TIME.format(todayTime));
        verify(holder.isReadIndicator).setVisibility(View.VISIBLE);
        verify(holder.dateOfStay).setText("Jan 4, 2015 - Jan 5");
    }

    @Test
    public void onBindInboxViewHolder_ifIsACanceledBooking_andNhaTypeProperty() {
        TravelerInboxItemViewHolder holder = (TravelerInboxItemViewHolder) adapter.onCreateViewHolder(container, TYPE_INBOX_ITEM);
        holder.userAvatarView = mock(UserAvatarView.class);
        holder.otherParticipantName = mock(TextView.class);
        holder.relativeTime = mock(TextView.class);
        holder.previewMessage = mock(TextView.class);
        holder.reservationStatus = mock(TextView.class);
        holder.propertyName = mock(TextView.class);
        holder.isReadIndicator = mock(ImageView.class);
        holder.dateOfStay = mock(TextView.class);

        when(formatter.getFormattedCheckInDay(any(), any())).thenReturn("Jan 4, 2015 - Jan 5");

        adapter.onBindViewHolder(holder, 1);

        verify(holder.userAvatarView).setAvatarImage(R.drawable.ic_home_avatar);
        verify(holder.otherParticipantName).setText("Host B");
        verify(holder.previewMessage).setText("Content");
        verify(holder.reservationStatus).setText("Canceled");
        verify(holder.propertyName).setText("Property B");
        verify(holder.propertyName).setVisibility(View.VISIBLE);
        verify(holder.relativeTime).setText("Jan 4");
        verify(holder.isReadIndicator).setVisibility(View.INVISIBLE);
        verify(holder.dateOfStay).setText("Jan 4, 2015 - Jan 5");
    }

    @Test
    public void onBindInboxViewHolder_ifIsAnAcceptedBooking_andNhaTypeProperty() {
        TravelerInboxItemViewHolder holder = (TravelerInboxItemViewHolder) adapter.onCreateViewHolder(container, TYPE_INBOX_ITEM);
        holder.userAvatarView = mock(UserAvatarView.class);
        holder.otherParticipantName = mock(TextView.class);
        holder.relativeTime = mock(TextView.class);
        holder.previewMessage = mock(TextView.class);
        holder.reservationStatus = mock(TextView.class);
        holder.propertyName = mock(TextView.class);
        holder.isReadIndicator = mock(ImageView.class);
        holder.dateOfStay = mock(TextView.class);

        when(formatter.getFormattedCheckInDay(any(), any())).thenReturn("Jan 4, 2015 - Dec 5, 2016");

        adapter.onBindViewHolder(holder, 2);

        verify(holder.userAvatarView).setAvatarImage(R.drawable.ic_home_avatar);
        verify(holder.otherParticipantName).setText("Host C");
        verify(holder.previewMessage).setText("Content");
        verify(holder.reservationStatus).setText("Accepted");
        verify(holder.propertyName).setText("Property C");
        verify(holder.propertyName).setVisibility(View.VISIBLE);
        verify(holder.relativeTime).setText("Dec 4, 16");
        verify(holder.isReadIndicator).setVisibility(View.INVISIBLE);
        verify(holder.dateOfStay).setText("Jan 4, 2015 - Dec 5, 2016");
    }

    @Test
    public void getItemViewType() {
        assertThat(adapter.getItemViewType(0), is(TYPE_INBOX_ITEM));
        assertThat(adapter.getItemViewType(1), is(TYPE_INBOX_ITEM));

        adapter.setHasMoreData(true);
        assertThat(adapter.getItemViewType(2), is(TYPE_INBOX_ITEM));
        assertThat(adapter.getItemViewType(3), is(TYPE_LOADING_INDICATOR));
    }

    private MatrixCursor createMockCursor() {
        MatrixCursor cursor = new MatrixCursor(new String[]{
                UserContract.COLUMN_NAME,

                PropertyContract.COLUMN_PROPERTY_ID,
                PropertyContract.COLUMN_NAME,
                PropertyContract.COLUMN_HOSTNAME,
                PropertyContract.COLUMN_IS_NHA,
                PropertyContract.COLUMN_AVATAR_TYPE,

                MessageContract.COLUMN_CONTENT,
                MessageContract.COLUMN_CREATED_TIMESTAMP_UTC,

                ReservationContract.COLUMN_BOOKING_STATUS,
                ReservationContract.COLUMN_TRAVELER_BOOKING_STATUS,
                ReservationContract.COLUMN_LATEST_UPDATED_BOOKING_STATUS_TIMESTAMP,
                ReservationContract.COLUMN_PRICE,

                ReservationContract.COLUMN_CHECK_IN_DATE,
                ReservationContract.COLUMN_CHECK_OUT_DATE,
                ReservationContract.COLUMN_PROPERTY_ID,
                ReservationContract.COLUMN_CUSTOMER_ID,
                ReservationContract.COLUMN_BOOKING_ID,

                ReservationContract.COLUMN_ADULTS,
                ReservationContract.COLUMN_CHILDREN,
                ReservationContract.COLUMN_PRICE_CURRENCY,

                ConversationContract.COLUMN_NUM_UNREAD_MESSAGES
        });

        //latestMessageDateTime : now
        cursor.addRow(createMessageValues("Host", "Property A", "", 0, 1, "Content",
                DateTimeConverter.toUtcMillis(todayTime), 6, null, DateTimeConverter.toUtcMillis(dateTime(2017,1,2)), 100,
                LocalDate.of(2015, 1, 4), LocalDate.of(2017, 1, 5), 101, 201, 301, 1, 0, "TH", 1));

        //latestMessageDateTime : current year
        cursor.addRow(createMessageValues("Host", "Property B", "Host B", 1, 2,  "Content",
                DateTimeConverter.toUtcMillis(dateTime(2017, 1, 4)), 6, "Canceled", DateTimeConverter.toUtcMillis(dateTime(2017,1,5)), 100,
                LocalDate.of(2015, 1, 4), LocalDate.of(2017, 1, 5), 101, 201, 301, 1, 0, "TH", 0));

        //latestMessageDateTime : other
        cursor.addRow(createMessageValues("Host", "Property C", "Host C", 1, 2, "Content",
                DateTimeConverter.toUtcMillis(dateTime(2016, 12, 4)), 0, "Accepted",  DateTimeConverter.toUtcMillis(dateTime(2017,1,5)), 100,
                LocalDate.of(2015, 1, 4), LocalDate.of(2016, 12, 5), 101, 201, 301, 1, 0, "TH", 0));
        return cursor;
    }

    @Nonnull
    private Object[] createMessageValues(String otherParticipantName,
                                         String propertyName,
                                         String hostName,
                                         int isNha,
                                         int avatarType,
                                         String content,
                                         long timestamp,
                                         int bookingStatus,
                                         String travelerBookingStatus,
                                         long lastUpdated,
                                         int price,
                                         LocalDate checkInDate,
                                         LocalDate checkoutDate,
                                         int propertyId,
                                         int customerId,
                                         int bookingId,
                                         int adultCount,
                                         int childCount,
                                         String currency,
                                         int numUnread
    ) {
        return new Object[]{otherParticipantName, propertyId, propertyName, hostName, isNha, avatarType, content, timestamp, bookingStatus,
                travelerBookingStatus,
                lastUpdated, price,
                checkInDate, checkoutDate, propertyId, customerId, bookingId, adultCount, childCount, currency, numUnread};
    }

    @Nonnull private OffsetDateTime dateTime(int year, int month, int dayOfMonth) {
        return ZonedDateTime.of(year, month, dayOfMonth, 0, 0, 0, 0, TIME_ZONE).toOffsetDateTime();
    }
}
*/


/*
public class TravelerChatAdapterTest extends BaseAppRobolectricTest {

    private static final String HOST_ID = "1";
    private static final String CUSTOMER_ID = "2";
    private static final ZoneId TIME_ZONE = ZoneId.of("Asia/Bangkok");

    private static final int TYPE_INCOMING_MESSAGE = 1;

    @Mock ChatAdapter.Controller controller;

    private ChatAdapter adapter;
    private Context context;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        context = ShadowApplication.getInstance().getApplicationContext();

        MatrixCursor cursor = createMockCursor();
        adapter = new TravelerChatAdapter(controller, cursor, CUSTOMER_ID);
        adapter.setCursor(cursor);
        adapter.setHasMoreData(true);

        Locale.setDefault(Locale.US);

        ClocksMockAccessor.setMockTimeZone(TIME_ZONE);
    }

    @Test
    public void onBindIncomingMessageViewHolder() {
        RecyclerView recyclerView = createRecyclerView();
        IncomingMessageViewHolder holder = (IncomingMessageViewHolder) adapter.onCreateViewHolder(recyclerView, TYPE_INCOMING_MESSAGE);

        holder.avatarView = spy(holder.avatarView);
        adapter.onBindViewHolder(holder, 0);

        assertThat(holder.contentTextView.getText(), is("Message 1"));
        assertThat(holder.timeTextView.getText(), is("00:00"));
        verify(holder.avatarView).setAvatarImage(anyInt());
    }

    @Nonnull private RecyclerView createRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }

    @Nonnull private MatrixCursor createMockCursor() {
        MatrixCursor cursor = new MatrixCursor(new String[] {
                MessageContract.COLUMN_PARTICIPANT_ID,
                UserContract.COLUMN_NAME,
                MessageContract.COLUMN_CONTENT,
                MessageContract.COLUMN_CREATED_TIMESTAMP_UTC,
                MessageContract.COLUMN_CONTENT_TYPE
        });
        cursor.addRow(createMessageValues(HOST_ID, "Host", "Message 1", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 3)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Message 2", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 2)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Message 3", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 1)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Status Message", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 1)), MessageContentType.STATUS_MESSAGE.getIntValue()));
        return cursor;
    }

    @Nonnull private OffsetDateTime dateTime(int year, int month, int dayOfMonth) {
        return ZonedDateTime.of(year, month, dayOfMonth, 0, 0, 0, 0, TIME_ZONE).toOffsetDateTime();
    }

    @Nonnull
    private Object[] createMessageValues(String participantId, String participantName, String content, long timestamp, int messageType) {
        return new Object[] {participantId, participantName, content, timestamp, messageType};
    }

}

public class IncomingMessageViewHolder extends MessageViewHolder {

    @BindView(R2.id.avatarView) public UserAvatarView avatarView;

    public IncomingMessageViewHolder(View view) {
        super(view);
    }
}
*/