package mohamadamin.soft.com.rentalhouseproject.Implementations;

import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadAllHousesImpl implements Callback<List<SecondaryHouse>> {

    private OnHousesDownloadedListener HousesDownloadedListener;

    public DownloadAllHousesImpl(OnHousesDownloadedListener housesDownloadedListener) {
        HousesDownloadedListener = housesDownloadedListener;
    }

    @Override
    public void onResponse(Call<List<SecondaryHouse>> call, Response<List<SecondaryHouse>> response) {
        HousesDownloadedListener.onHousesDownloaded(response);
    }

    @Override
    public void onFailure(Call<List<SecondaryHouse>> call, Throwable t) {

    }

    public interface OnHousesDownloadedListener
    {
        void onHousesDownloaded(Response<List<SecondaryHouse>> response);
    }
}
