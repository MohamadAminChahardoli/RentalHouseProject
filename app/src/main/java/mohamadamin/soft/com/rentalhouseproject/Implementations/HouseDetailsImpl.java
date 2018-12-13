package mohamadamin.soft.com.rentalhouseproject.Implementations;

import android.widget.Toast;

import mohamadamin.soft.com.rentalhouseproject.App.MyApplication;
import mohamadamin.soft.com.rentalhouseproject.Models.House;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseDetailsImpl implements Callback<House> {

    private OnHouseDetailsReceivedListener HouseDetailsReceivedListener;

    public HouseDetailsImpl(OnHouseDetailsReceivedListener houseDetailsReceivedListener) {
        HouseDetailsReceivedListener = houseDetailsReceivedListener;
    }

    @Override
    public void onResponse(Call<House> call, Response<House> response) {
        HouseDetailsReceivedListener.onHouseDetailsReceived(response);
    }

    @Override
    public void onFailure(Call<House> call, Throwable t) {
        Toast.makeText(MyApplication.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    public interface OnHouseDetailsReceivedListener
    {
        void onHouseDetailsReceived(Response<House> response);
    }
}
