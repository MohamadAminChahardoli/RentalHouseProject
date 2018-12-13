package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mohamadamin.soft.com.rentalhouseproject.Adapters.SearchResultAdapter;
import mohamadamin.soft.com.rentalhouseproject.Implementations.DownloadSlidesImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.SearchImpl;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.Slide;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.ApiServiceGenerator;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.RentalHouseApi;
import mohamadamin.soft.com.rentalhouseproject.Utils.Networking;
import mohamadamin.soft.com.rentalhouseproject.Utils.SweetDialog;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity extends AppCompatActivity implements SearchImpl.OnSearchResultReceivedListener,
        Networking.NetworkStatusListener, View.OnClickListener, TextWatcher {

    private RecyclerView RecyclerSearchResult;
    private EditText EdtSearch;
    private ImageButton BtnSearch;
    private ImageButton BtnClearSearchBox;
    private TextView TxtNotingFound;
    private SweetDialog Loading;
    private SweetDialog MessageDialog;
    private int DefaultSkipItem = 0;
    private int DefaultTakeItem = 20;
    private SearchResultAdapter SearchAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initializeComponents();
        setupRecyclerSearchResult();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_search_for)
            Networking.checkNetwork(this, this, 2);
        if (view.getId() == R.id.btn_clear_search_box)
            clearSearchBox();
    }

    @Override
    public void onNetworkConnected(int requestCode) {
        if (requestCode == 2) {
            String searchTerm = EdtSearch.getText().toString();
            if (!searchTerm.isEmpty())
            {
                SearchAdapter.clearList();
                search(searchTerm);
            }
        }
    }

    @Override
    public void onNetworkDisconnected() {
        MessageDialog.setContentText(getString(R.string.noInternetText)).show();
    }

    @Override
    public void onSearchResultReceived(Response<List<SecondaryHouse>> response) {
        Loading.hide();
        if (response.body() != null && response.body().size() > 0)
        {
            SearchAdapter.insertNewHousesToList(response.body());
            TxtNotingFound.setVisibility(View.GONE);
        }
        else
        {
            TxtNotingFound.setVisibility(View.VISIBLE);
        }

    }

    private void initializeComponents() {
        RecyclerSearchResult = findViewById(R.id.recycler_view_search_result);
        TxtNotingFound = findViewById(R.id.txtNotFoundSearch);
        EdtSearch = findViewById(R.id.edt_search_for);
        EdtSearch.addTextChangedListener(this);
        BtnSearch = findViewById(R.id.btn_search_for);
        BtnClearSearchBox = findViewById(R.id.btn_clear_search_box);
        BtnSearch.setOnClickListener(this);
        BtnClearSearchBox.setOnClickListener(this);

        Loading = new SweetDialog.Builder()
                .setDialogType(SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(getString(R.string.waiting_text))
                .build(this);
        MessageDialog = new SweetDialog.Builder()
                .setDialogType(SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.dearUserText))
                .build(this);
    }

    private void setupRecyclerSearchResult() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        //recyclerSearchResult.addItemDecoration(new ItemDecorationRecycler(2, 8, true));
        RecyclerSearchResult.setLayoutManager(layoutManager);
        SearchAdapter = new SearchResultAdapter(this);
        RecyclerSearchResult.setAdapter(SearchAdapter);
    }

    private void search(String term) {
        Loading.show();
        RentalHouseApi rentalHouseApi = ApiServiceGenerator.getApiService();
        Call<List<SecondaryHouse>> searchCall = rentalHouseApi.searchForHouse(term, DefaultSkipItem, DefaultTakeItem);
        searchCall.enqueue(new SearchImpl(this));
    }

    private void clearSearchBox() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (EdtSearch.getText().length() > 0)
                {
                    EdtSearch.setText(EdtSearch.getText().toString().substring(0, EdtSearch.getText().length() - 1));
                    clearSearchBox();
                }
            }
        }, 20);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (charSequence.toString().isEmpty()) {
            BtnClearSearchBox.setVisibility(View.INVISIBLE);
        } else {
            BtnClearSearchBox.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
