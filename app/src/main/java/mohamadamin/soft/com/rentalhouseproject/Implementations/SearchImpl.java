package mohamadamin.soft.com.rentalhouseproject.Implementations;

import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchImpl implements Callback<List<SecondaryHouse>> {

    private OnSearchResultReceivedListener SearchResultReceivedListener;

    public SearchImpl(OnSearchResultReceivedListener searchResultReceivedListener) {
        SearchResultReceivedListener = searchResultReceivedListener;
    }

    @Override
    public void onResponse(Call<List<SecondaryHouse>> call, Response<List<SecondaryHouse>> response) {
        SearchResultReceivedListener.onSearchResultReceived(response);
    }

    @Override
    public void onFailure(Call<List<SecondaryHouse>> call, Throwable t) {

    }

    public  interface OnSearchResultReceivedListener
    {
        void onSearchResultReceived(Response<List<SecondaryHouse>> response);
    }
}
