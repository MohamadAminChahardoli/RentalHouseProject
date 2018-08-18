package mohamadamin.soft.com.rentalhouseproject.Models;

import android.net.Uri;
import java.io.Serializable;
import java.util.ArrayList;

public class RegisterHouseFragmentDataModel implements Serializable
{
    public int HouseType;//1 for Dorm 2 for RentalHouse
    public int SexHouseType;//1 for men 2 for women
    public String[] HouseImage={"","","",""};
    public String HouseTitle;
    public String HouseZone;
    public String HouseAddress;
    public String HouseDescription;

}
