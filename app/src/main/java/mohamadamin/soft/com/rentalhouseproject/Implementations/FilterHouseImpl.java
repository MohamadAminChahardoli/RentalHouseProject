package mohamadamin.soft.com.rentalhouseproject.Implementations;

import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterHouseImpl implements Callback<List<SecondaryHouse>> {

    private OnHousesFilteredListener HousesFilteredListener;

    public FilterHouseImpl(OnHousesFilteredListener housesFilteredListener) {
        HousesFilteredListener = housesFilteredListener;
    }

    @Override
    public void onResponse(Call<List<SecondaryHouse>> call, Response<List<SecondaryHouse>> response) {
        HousesFilteredListener.onHousesFiltered(response);
    }

    @Override
    public void onFailure(Call<List<SecondaryHouse>> call, Throwable t) {

    }

    public  interface OnHousesFilteredListener
    {
        void onHousesFiltered(Response<List<SecondaryHouse>> response);
    }
}
