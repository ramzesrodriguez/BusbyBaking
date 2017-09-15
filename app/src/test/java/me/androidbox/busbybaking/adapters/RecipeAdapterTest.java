package me.androidbox.busbybaking.adapters;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolder;
import me.androidbox.busbybaking.testrunner.BaseRobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by smason on 7/4/17.
 */
public class RecipeAdapterTest extends BaseRobolectricTestRunner {
    @Inject RecipeAdapter recipeAdapter;
    private ViewGroup linearLayout;
    private RecipeListViewHolder recipeListViewHolder;

    @Before
    public void setup() {
        linearLayout = new LinearLayout(ShadowApplication.getInstance().getApplicationContext());

        getTestComponent().inject(RecipeAdapterTest.this);

        recipeAdapter.recipeList = createRecipes();
    }

    @Test
    public void testRecipeAdapterShouldNotBeNull() {
        assertThat(recipeAdapter, is(notNullValue()));
    }

    @Test
    public void testViewHolderIsCreated() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        assertThat(recipeListViewHolder, is(notNullValue()));
    }

    @Test
    public void testOnBindViewHolder() {
        recipeListViewHolder = recipeAdapter.onCreateViewHolder(linearLayout, 0);
        recipeListViewHolder.tvRecipeName = mock(TextView.class);
        recipeListViewHolder.tvQuantity = mock(TextView.class);

        recipeAdapter.onBindViewHolder(recipeListViewHolder, 0);

        verify(recipeListViewHolder.tvRecipeName).setText("Test Brownies");
        verify(recipeListViewHolder.tvQuantity).setText("Quantity: 10");
    }

    private List<Recipe> createRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setName("Test Brownies");
        recipe.setServings(10);
        recipeList.add(recipe);

        return recipeList;
    }
}public class TravelerChatAdapterTest extends BaseAppRobolectricTest {

    private static final String HOST_ID = "1";
    private static final String CUSTOMER_ID = "2";
    private static final ZoneId TIME_ZONE = ZoneId.of("Asia/Bangkok");

    private static final int TYPE_INCOMING_MESSAGE = 1;
    private static final int TYPE_SPECIAL_REQUESTS = 5;

    @Mock ChatAdapter.Controller controller;

    private ChatAdapter adapter;
    private Context context;

    @Before
    public void setUp() {

        context = ShadowApplication.getInstance().getApplicationContext();

        MatrixCursor cursor = createMockCursor();
        adapter = new TravelerChatAdapter(controller, cursor, CUSTOMER_ID);
        adapter.setCursor(cursor);
        adapter.setHasMoreData(true);
        adapter.setChatViewModel(mockChatViewModel());

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

    @Test
    public void testSpecialRequest_isPopulatedWithValidData() {
        final SpecialRequestsViewHolder specialRequestsViewHolder = Mockito.mock(SpecialRequestsViewHolder.class);

        adapter.onBindViewHolder(specialRequestsViewHolder, 4);

        verify(specialRequestsViewHolder).populateSpecialRequestContent("Nonsmoking, largeroom", "00:00");
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
                MessageContract.COLUMN_CONTENT_TYPE,
        });
        cursor.addRow(createMessageValues(HOST_ID, "Host", "Message 1", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 3)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Message 2", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 2)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Message 3", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 1)), MessageContentType.TEXT.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Status Message", DateTimeConverter.toUtcMillis(dateTime(2015, 1, 1)), MessageContentType.STATUS_MESSAGE.getIntValue()));
        cursor.addRow(createMessageValues(CUSTOMER_ID, "Guest", "Nonsmoking, largeroom", DateTimeConverter.toUtcMillis(dateTime(2017, 9, 15)), MessageContentType.SPECIAL_REQUEST.getIntValue()));
        return cursor;
    }

    @Nonnull private OffsetDateTime dateTime(int year, int month, int dayOfMonth) {
        return ZonedDateTime.of(year, month, dayOfMonth, 0, 0, 0, 0, TIME_ZONE).toOffsetDateTime();
    }

    @Nonnull
    private Object[] createMessageValues(String participantId, String participantName, String content, long timestamp, int messageType) {
        return new Object[] {participantId, participantName, content, timestamp, messageType};
    }

    private ChatViewModel mockChatViewModel() {
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.avatarType = 1;

        ChatViewModel chatViewModel = new ChatViewModel();
        chatViewModel.propertyInfo = propertyInfo;

        return chatViewModel;
    }
}

