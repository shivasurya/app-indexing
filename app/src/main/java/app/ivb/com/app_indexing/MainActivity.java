package app.ivb.com.app_indexing;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class MainActivity extends ActionBarActivity {
    GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.APP_INDEX_API).build();
        TextView tv = (TextView)findViewById(R.id.text);
        tv.setText("Sachin Tendulkar (Listeni/ˌsətʃɪn tɛnˈduːlkər/; born 24 April 1973) is a former Indian cricketer widely acknowledged as one of the greatest batsmen of all time.[4][5][6][7] He took up cricket at the age of eleven, made his Test debut on 15 November 1989 against Pakistan in Karachi at the age of sixteen, and went on to represent Mumbai domestically and India internationally for close to twenty-four years. He is the only player to have scored one hundred international centuries, the first batsman to score a double century in a One Day International, the only player to complete more than 30,000 runs in international cricket.[8] and the 16th player and first Indian to aggregate 50,000 runs or more in all forms of domestic and international recognised cricket.[9]\n" +
                "\n" +
                "In 2002 just half way through his career, Wisden Cricketers' Almanack ranked him the second greatest Test batsman of all time, behind Don Bradman, and the second greatest ODI batsman of all time, behind Viv Richards.[10] Later in his career, Tendulkar was a part of the Indian team that won the 2011 World Cup, his first win in six World Cup appearances for India.[11] He had previously been named \"Player of the Tournament\" at the 2003 edition of the tournament, held in South Africa. In 2013, he was the only Indian cricketer included in an all-time Test World XI named to mark the 150th anniversary of Wisden Cricketers' Almanack.[12][13][14]");



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onStop(){
        super.onStop();


            final Uri APP_URI = Uri.parse("android-app://app.ivb.com.app_indexing/crciket/sachin");
            PendingResult<Status> result = AppIndex.AppIndexApi.viewEnd(mClient, this, APP_URI);

            result.setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Log.d("success", "App Indexing API: Recorded recipeview end successfully.");
                    } else {
                        Log.e("error", "App Indexing API: There was an error"
                                + status.toString());
                    }
                }
            });

            mClient.disconnect();

    }
    public void onStart(){
        super.onStart();

            // Connect your client
            mClient.connect();

            // Define a title for your current page, shown in autocompletion UI
            final String TITLE = "sachin tendulkar masterblaster";
            final Uri APP_URI = Uri.parse("android-app://app.ivb.com.app_indexing/cricket/sachin");
            final Uri WEB_URL = null;

            // Call the App Indexing API view method
            PendingResult<Status> result = AppIndex.AppIndexApi.view(mClient, this,
                    APP_URI, TITLE, WEB_URL, null);

            result.setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Log.d("success", "App Indexing API: Recorded recipe view successfully.");
                    } else {
                        Log.e("error", "App Indexing API: There was an error"
                                + status.toString());
                    }
                }
            });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
