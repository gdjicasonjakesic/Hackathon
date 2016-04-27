package hr.droidcon.conference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.droidcon.conference.adapters.MainAdapter;
import hr.droidcon.conference.objects.Conference;

/**
 * Created by stefan.tanovic on 4/27/2016.
 */
public class FilteredActivity extends AppCompatActivity {

    @Bind(R.id.main_events_list)
    ListView mainEventsListView;

    private MainAdapter mAdapter;
    private ArrayList<Conference> conferences;

    public static final String ARGS_KEY = "conferences";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_filtered);
        ButterKnife.bind(this);
        Bundle args = getIntent().getExtras();
        conferences = (ArrayList<Conference>)args.getSerializable(ARGS_KEY);
        mAdapter = new MainAdapter(this, 0x00, conferences);

        mainEventsListView.setAdapter(mAdapter);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(conferences.isEmpty()? "" : conferences.get(0).getCategory());
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
//        mainEventsListView.setOnScrollListener(this);
//        mainEventsListView.setOnItemClickListener(this);
    }
}