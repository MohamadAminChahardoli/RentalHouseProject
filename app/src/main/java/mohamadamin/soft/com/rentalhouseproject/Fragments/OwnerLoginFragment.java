package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.Utils.ModifiedTextWatcher;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorDrawablePreLollipopHelper;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorView;


public class OwnerLoginFragment extends Fragment
{
    private Button btnCreateAccount;
    private Button btnLogin;
    private EditText edt_UserName;
    private EditText edt_Password;

    public OwnerLoginFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_owner_login, container, false);
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
        edt_UserName = view.findViewById(R.id.edt_username);
        edt_Password = view.findViewById(R.id.edt_password);
        btnCreateAccount = view.findViewById(R.id.btn_create_account);
        btnLogin = view.findViewById(R.id.btn_login);
        //-------------------------------------------------------------
        ArrayList<VectorView> VectorViewList = new ArrayList<>
                (Arrays.asList(
                        new VectorView(R.drawable.ic_phone, edt_UserName, VectorDrawablePreLollipopHelper.MyDirType.end),
                        new VectorView(R.drawable.ic_lock, edt_Password, VectorDrawablePreLollipopHelper.MyDirType.end),
                        new VectorView(R.drawable.ic_chevron_left, btnLogin, VectorDrawablePreLollipopHelper.MyDirType.start)
                ));
        VectorDrawablePreLollipopHelper.SetVectors(getResources(), VectorViewList);
    }

    private void initViewsEvents()
    {
        edt_UserName.addTextChangedListener(new ModifiedTextWatcher()
        {
            @Override
            public void afterTextChanged(Editable s)
            {
               if(s.length()==11)
                   edt_Password.requestFocus();
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.owner_registration_placeholder, new OwnerSignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
