package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.aldoapps.autoformatedittext.AutoFormatEditText;

import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorDrawablePreLollipopHelper;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorView;

public class HousePriceMortgageDetailFragment extends Fragment implements View.OnClickListener
{
    private AutoFormatEditText edtMortgage;
    private AutoFormatEditText edtRent;
    //---------------
    private CheckBox chkSingleBed;
    private CheckBox chkTwoBed;
    private CheckBox chkThreeBed;
    private CheckBox chkFourBed;
    private CheckBox chkSixBed;
    private CheckBox chkEightBed;
    private CheckBox chkTenBed;
    //---------------
    private AutoFormatEditText edtSingleBed;
    private AutoFormatEditText edtTwoBed;
    private AutoFormatEditText edtThreeBed;
    private AutoFormatEditText edtFourBed;
    private AutoFormatEditText edtSixBed;
    private AutoFormatEditText edtEightBed;
    private AutoFormatEditText edtTenBed;

    public HousePriceMortgageDetailFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_house_price_mortgage_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initViewsEvents();
    }

    private void initViews(View view)
    {
        edtMortgage = view.findViewById(R.id.edt_mortgage);
        edtRent = view.findViewById(R.id.edt_rent);
        chkSingleBed = view.findViewById(R.id.chk_single_bed);
        chkTwoBed = view.findViewById(R.id.chk_two_bed);
        chkThreeBed = view.findViewById(R.id.chk_three_bed);
        chkFourBed = view.findViewById(R.id.chk_four_bed);
        chkSixBed = view.findViewById(R.id.chk_six_bed);
        chkEightBed = view.findViewById(R.id.chk_eight_bed);
        chkTenBed = view.findViewById(R.id.chk_ten_bed);
        edtSingleBed = view.findViewById(R.id.edt_single_bed);
        edtTwoBed = view.findViewById(R.id.edt_two_bed);
        edtThreeBed = view.findViewById(R.id.edt_three_bed);
        edtFourBed = view.findViewById(R.id.edt_four_bed);
        edtSixBed = view.findViewById(R.id.edt_six_bed);
        edtEightBed = view.findViewById(R.id.edt_eight_bed);
        edtTenBed = view.findViewById(R.id.edt_ten_bed);
        VectorDrawablePreLollipopHelper.SetVectors(getResources(), new VectorView(R.drawable.ic_monetization, view.findViewById(R.id.txt_house_mortgage_price_info), VectorDrawablePreLollipopHelper.MyDirType.end));
        VectorDrawablePreLollipopHelper.SetVectors(getResources(), new VectorView(R.drawable.ic_bed, view.findViewById(R.id.txt_house_mortgage_bed_info), VectorDrawablePreLollipopHelper.MyDirType.end));
    }

    private void initViewsEvents()
    {
        chkSingleBed.setOnClickListener(this);
        chkTwoBed.setOnClickListener(this);
        chkThreeBed.setOnClickListener(this);
        chkFourBed.setOnClickListener(this);
        chkSixBed.setOnClickListener(this);
        chkEightBed.setOnClickListener(this);
        chkTenBed.setOnClickListener(this);
    }

    private void ViewToEnableOrDisable(View YourView)
    {
        if (!YourView.isEnabled())
        {
            YourView.setEnabled(true);
        }
        else
        {
            YourView.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.chk_single_bed:
                ViewToEnableOrDisable(edtSingleBed);
                break;
            case R.id.chk_two_bed:
                ViewToEnableOrDisable(edtTwoBed);
                break;
            case R.id.chk_three_bed:
                ViewToEnableOrDisable(edtThreeBed);
                break;
            case R.id.chk_four_bed:
                ViewToEnableOrDisable(edtFourBed);
                break;
            case R.id.chk_six_bed:
                ViewToEnableOrDisable(edtSixBed);
                break;
            case R.id.chk_eight_bed:
                ViewToEnableOrDisable(edtEightBed);
                break;
            case R.id.chk_ten_bed:
                ViewToEnableOrDisable(edtTenBed);
                break;
        }

    }
}
