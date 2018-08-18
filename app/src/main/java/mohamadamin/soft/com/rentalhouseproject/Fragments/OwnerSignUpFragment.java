package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.StateCityModel;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.Utils.ModifiedTextWatcher;
import mohamadamin.soft.com.rentalhouseproject.Utils.StateCityHelper;

public class OwnerSignUpFragment extends Fragment
{

    private ArrayList<String> CityList;
    private ArrayList<StateCityModel> StateCityList;
    private ArrayAdapter<String> CityAdapter;
    private Spinner sp_States;
    private Spinner sp_Cities;
    private EditText edt_OwnerName;
    private EditText edt_OwnerFamily;
    private EditText edt_OwnerMobile;
    private int StateCode, CityCode;

    public OwnerSignUpFragment()
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
        return inflater.inflate(R.layout.fragment_owner_sign_up, container, false);
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
        edt_OwnerName = view.findViewById(R.id.edt_owner_name);
        edt_OwnerFamily = view.findViewById(R.id.edt_owner_family);
        edt_OwnerMobile = view.findViewById(R.id.edt_owner_mobile);
        sp_States = view.findViewById(R.id.sp_states);
        sp_Cities = view.findViewById(R.id.sp_cities);
        //--------------------
        LoadStateCity();
    }

    private void initViewsEvents()
    {
        edt_OwnerMobile.addTextChangedListener(new ModifiedTextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {
                if(s.length()==11)
                    sp_States.requestFocus();
            }
        });
        sp_States.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                StateCode = StateCityList.get(position).StateCode;
                CityList.clear();
                CityAdapter.clear();
                for (int p = 0; p < StateCityList.get(position).CityCollection.size(); ++p)
                {
                    CityList.add(StateCityList.get(position).CityCollection.get(p).CityName);
                }
                CityAdapter.addAll(CityList);
                CityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
        sp_Cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                CityCode = StateCityList.get(sp_States.getSelectedItemPosition()).CityCollection.get(position).CityCode;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }


    private void LoadStateCity()
    {
        CityList = new ArrayList<>();
        StateCityList = new ArrayList<>();
        StateCityList = StateCityHelper.GetStateCityList(getContext());
        //---------------------------------------------------------
        final String[] StateNames = new String[StateCityList.size()];
        for (int i = 0; i < StateCityList.size(); ++i)
        {
            StateNames[i] = StateCityList.get(i).StateName;
        }
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_item, StateNames);
        CityAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_spinner_item);
        sp_States.setAdapter(StateAdapter);
        sp_Cities.setAdapter(CityAdapter);
        //-----------------------------
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }


}
