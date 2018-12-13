package mohamadamin.soft.com.rentalhouseproject.Implementations;

import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.Slide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadSlidesImpl implements Callback<List<Slide>> {

    private OnSliderReceivedListener SliderReceivedListener;

    public DownloadSlidesImpl(OnSliderReceivedListener sliderReceivedListener) {
        SliderReceivedListener = sliderReceivedListener;
    }

    @Override
    public void onResponse(Call<List<Slide>> call, Response<List<Slide>> response) {
        SliderReceivedListener.onSliderReceived(response);
    }

    @Override
    public void onFailure(Call<List<Slide>> call, Throwable t) {

    }

    public interface OnSliderReceivedListener
    {
        void onSliderReceived(Response<List<Slide>> response);
    }
}
