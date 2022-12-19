package com.example.btquatrinh_4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;


import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.btquatrinh_4.DTO.Official;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {
    private SearchView ViewTimKiem;
    private String endQuery;
    private TextView txtLocation;
    private ListView dsOfficials;
    List<Official> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dsOfficials = findViewById(R.id.lvOfficial);
        txtLocation = findViewById(R.id.txtLocation);

        if(checkInternetConnection()== false){
            noNetworkDialog("Stocks Cannot Be Update Without A Network Connection");
        }

        getItemListView();
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_activity, menu);

        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        this.ViewTimKiem = (SearchView) menu.findItem(R.id.menuItem_search).getActionView();

        this.ViewTimKiem.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // Need click "search" icon to expand SearchView.
        this.ViewTimKiem.setIconifiedByDefault(true);


        ViewTimKiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // Typing search text.
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
//                Log.i(LOG_TAG, "onQueryTextChange: " + newText);
                return true;
            }

            // Press Enter to search (Or something to search).
            public boolean onQueryTextSubmit(String query) {
                // IMPORTANT!
                // Prevent onQueryTextSubmit() method called twice.
                // https://stackoverflow.com/questions/34207670
                ViewTimKiem.clearFocus();

                //reset ViewTimKiem
                ViewTimKiem.setQuery("", false);
                ViewTimKiem.setIconified(true);

                if(checkInternetConnection()== false){
                    noNetworkDialog("Stocks Cannot Be Update Without A Network Connection");
                    txtLocation.setText("No Data For Location");
                }

                OfficialLoader o = new OfficialLoader(MainActivity.this);
                o.execute(query);

//                Log.i(LOG_TAG, "onQueryTextSubmit: " + query);
                return doSearch(query);
            }
        });

        ViewTimKiem.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i(LOG_TAG, "SearchView.onSearchClickListener!" );
            }
        }) ;

        return super.onCreateOptionsMenu(menu);
    }


    private boolean doSearch(String query) {
        if (query == null || query.isEmpty()) {
            return false; // Cancel search.
        }
        this.endQuery = query;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuItem_information)
        {
            openInformationActivity();
        }

        return true;
    }

    private void openInformationActivity() {
        Intent intent = new Intent(this, ThongTinActivity.class);
        startActivity(intent);
    }
    private boolean checkInternetConnection() {

        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            //Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
           // Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            //Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
       // Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;
    }
    public void updateOfficialData(ArrayList<Official> tempList)
    {
        list = new ArrayList<>();
        if(tempList.size()!=0)
        {
            list.addAll(tempList);
        }

        Adapter adapter = new Adapter(MainActivity.this, list); //truyền list vào adapter
        dsOfficials.setAdapter(adapter); //truyền adapter vào listview
    }
    public void getItemListView(){
        dsOfficials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Official temp = list.get(position);

                Intent intent = new Intent(MainActivity.this, OfficialActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("location", (String) txtLocation.getText());
                bundle.putSerializable("official", (Serializable) temp);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void noNetworkDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.no_network);
        builder.setTitle(R.string.networkErrorTitle);
        builder.setMessage(message);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}