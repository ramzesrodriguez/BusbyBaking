package me.androidbox.busbybaking.adapters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import me.androidbox.busbybaking.recipieslist.MainActivity;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;

import static org.junit.Assert.*;

/**
 * Created by smason on 7/4/17.
 */
public class RecipeAdapterTest {
    private RecipeAdapter recipeAdapter;
    @Mock MainActivity mainActivity;
    @Mock Map<Integer, RecipeListViewHolderFactory> viewHolder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeAdapter = new RecipeAdapter(mainActivity, viewHolder);
    }

    @Test
    public void testRecipeAdapterShouldNotBeNull() {
        assertNotNull(recipeAdapter);
    }
}








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
*/