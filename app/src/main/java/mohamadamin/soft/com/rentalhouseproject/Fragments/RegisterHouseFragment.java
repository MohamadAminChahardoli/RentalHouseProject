package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.RegisterHouseFragmentDataModel;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.Utils.SharedPreferencesHelper;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorDrawablePreLollipopHelper;
import mohamadamin.soft.com.rentalhouseproject.Utils.VectorView;

public class RegisterHouseFragment extends Fragment implements View.OnClickListener
{
    private ImageView imgHouseFirstPic;
    private ImageView imgHouseSecondPic;
    private ImageView imgHouseThirdPic;
    private ImageView imgHouseForthPic;
    private ImageView ChosenImageView;
    //---------------
    private Button btnContinueRegisterHouse;
    private RadioButton rdDorm;
    private RadioButton rdRentalHouse;
    private Spinner spSexType;
    private EditText edtHouseTitle;
    private EditText edtHouseZone;
    private EditText edtHouseAddress;
    private EditText edtHouseDescription;
    //---------------
    private RegisterHouseFragmentDataModel registerHouseFragmentDataMode;
    private final int PICK_PICTURE_REQUEST_CODE =105;

    public RegisterHouseFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_register_house, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferencesHelper.InitPreferences(view.getContext());
        initViews(view);
        initViewsEvents();
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(new View.OnKeyListener()
//        {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event)
//            {
//                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
//                {
//                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    private void initViews(View view)
    {
        imgHouseFirstPic = view.findViewById(R.id.img_house_first_pic);
        imgHouseSecondPic = view.findViewById(R.id.img_house_second_pic);
        imgHouseThirdPic = view.findViewById(R.id.img_house_third_pic);
        imgHouseForthPic = view.findViewById(R.id.img_house_forth_pic);
        rdDorm = view.findViewById(R.id.rd_dorm);
        rdRentalHouse = view.findViewById(R.id.rd_rental_house);
        spSexType = view.findViewById(R.id.sp_sex_type);
        edtHouseTitle = view.findViewById(R.id.edt_house_title);
        edtHouseZone = view.findViewById(R.id.edt_house_zone);
        edtHouseAddress = view.findViewById(R.id.edt_house_address);
        edtHouseDescription = view.findViewById(R.id.edt_house_description);
        btnContinueRegisterHouse = view.findViewById(R.id.btn_continue_register_house);
        VectorDrawablePreLollipopHelper.SetVectors(getResources(), new VectorView(R.drawable.ic_manage_list, view.findViewById(R.id.txt_complete_info), VectorDrawablePreLollipopHelper.MyDirType.end));
        String RawSerializeObject = SharedPreferencesHelper.ReadString("registerHouseFragmentDataModel");
        if (!RawSerializeObject.equals("def"))
        {
            registerHouseFragmentDataMode = SharedPreferencesHelper.ReadSerializableObject("registerHouseFragmentDataModel", new TypeToken<RegisterHouseFragmentDataModel>()
            {
            }.getType());
            SetSavedDataToViews();
        }
        else
        {
            registerHouseFragmentDataMode = new RegisterHouseFragmentDataModel();
        }
    }

    private void SetSavedDataToViews()
    {
        if (registerHouseFragmentDataMode.HouseType == 1)
        {
            rdDorm.setChecked(true);
        }
        else if (registerHouseFragmentDataMode.HouseType == 2)
        {
            rdRentalHouse.setChecked(true);
        }
        edtHouseTitle.setText(registerHouseFragmentDataMode.HouseTitle);
        Glide.with(getContext()).load(Uri.parse(registerHouseFragmentDataMode.HouseImage[0]).toString().isEmpty() ? R.drawable.house_main : Uri.parse(registerHouseFragmentDataMode.HouseImage[0])).into(imgHouseFirstPic);
        Glide.with(getContext()).load(Uri.parse(registerHouseFragmentDataMode.HouseImage[1]).toString().isEmpty() ? R.drawable.house_second : Uri.parse(registerHouseFragmentDataMode.HouseImage[1])).into(imgHouseSecondPic);
        Glide.with(getContext()).load(Uri.parse(registerHouseFragmentDataMode.HouseImage[2]).toString().isEmpty() ? R.drawable.house_third : Uri.parse(registerHouseFragmentDataMode.HouseImage[2])).into(imgHouseThirdPic);
        Glide.with(getContext()).load(Uri.parse(registerHouseFragmentDataMode.HouseImage[3]).toString().isEmpty() ? R.drawable.house_forth : Uri.parse(registerHouseFragmentDataMode.HouseImage[3])).into(imgHouseForthPic);
        edtHouseAddress.setText(registerHouseFragmentDataMode.HouseAddress);
        edtHouseDescription.setText(registerHouseFragmentDataMode.HouseDescription);
        edtHouseZone.setText(registerHouseFragmentDataMode.HouseZone);
    }

    private void initViewsEvents()
    {
        imgHouseFirstPic.setOnClickListener(this);
        imgHouseSecondPic.setOnClickListener(this);
        imgHouseThirdPic.setOnClickListener(this);
        imgHouseForthPic.setOnClickListener(this);
        btnContinueRegisterHouse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //---------------------------
                if (rdDorm.isChecked())
                {
                    registerHouseFragmentDataMode.HouseType = 1;
                }
                else if (rdRentalHouse.isChecked())
                {
                    registerHouseFragmentDataMode.HouseType = 2;
                }
                if (spSexType.getSelectedItemPosition() == 0)
                {
                    registerHouseFragmentDataMode.SexHouseType = 1;
                }
                else if (spSexType.getSelectedItemPosition() == 1)
                {
                    registerHouseFragmentDataMode.SexHouseType = 2;
                }
                registerHouseFragmentDataMode.HouseTitle = edtHouseTitle.getText().toString();
                registerHouseFragmentDataMode.HouseAddress = edtHouseAddress.getText().toString();
                registerHouseFragmentDataMode.HouseDescription = edtHouseDescription.getText().toString();
                registerHouseFragmentDataMode.HouseZone = edtHouseZone.getText().toString();
                SharedPreferencesHelper.WriteSerializableObject("registerHouseFragmentDataModel", registerHouseFragmentDataMode);
                //-------------------------------------------
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.owner_management_placeholder, new HousePriceMortgageDetailFragment())
                        .addToBackStack(HousePriceMortgageDetailFragment.class.getName())
                        .commit();
            }
        });
        //------------------
    }

    private void PickPicture()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "انتخاب عکس ملک"), PICK_PICTURE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PICTURE_REQUEST_CODE && resultCode== Activity.RESULT_OK)
        {
            if (ChosenImageView.getId() == imgHouseFirstPic.getId())
            {
                registerHouseFragmentDataMode.HouseImage[0] = data.getData().toString();
            }
            else if (ChosenImageView.getId() == imgHouseSecondPic.getId())
            {
                registerHouseFragmentDataMode.HouseImage[1] = data.getData().toString();
            }
            else if (ChosenImageView.getId() == imgHouseThirdPic.getId())
            {
                registerHouseFragmentDataMode.HouseImage[2] = data.getData().toString();
            }
            else if (ChosenImageView.getId() == imgHouseForthPic.getId())
            {
                registerHouseFragmentDataMode.HouseImage[3] = data.getData().toString();
            }
            //--------------------------------------------------
            Glide.with(getContext()).load(data.getData()).into(ChosenImageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101)
        {
            if (grantResults.length > 0)
            {
                PickPicture();
            }
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view == imgHouseFirstPic || view == imgHouseSecondPic || view == imgHouseThirdPic || view == imgHouseForthPic)
        {
            ChosenImageView = ((ImageView) view);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
            }
            else
            {
                PickPicture();
            }
        }
    }
}
