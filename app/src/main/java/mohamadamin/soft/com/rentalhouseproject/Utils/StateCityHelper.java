package mohamadamin.soft.com.rentalhouseproject.Utils;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.CityModel;
import mohamadamin.soft.com.rentalhouseproject.Models.StateCityModel;

public class StateCityHelper
{
    public static ArrayList<StateCityModel> GetStateCityList(Context Context)
    {
        ArrayList<StateCityModel> StateList = new ArrayList<>();
        JSONArray CityArray;
        try
        {
            CityArray = new JSONArray(LoadJson(Context, "states_cities.json"));

            for (int i = 0; i < CityArray.length(); ++i)
            {

                JSONObject StateCityJson = CityArray.getJSONObject(i);
                StateCityModel StateCityObj = new StateCityModel();
                StateCityObj.StateCode = StateCityJson.getInt("StateCode");
                StateCityObj.StateName = StateCityJson.getString("StateName");
                JSONArray ArrayCity = StateCityJson.getJSONArray("City");
                StateCityObj.CityCollection = new ArrayList<>();
                for (int k = 0; k < ArrayCity.length(); ++k)
                {
                    JSONObject CityJson = ArrayCity.getJSONObject(k);
                    CityModel City = new CityModel();
                    City.CityCode = CityJson.getInt("CityCode");
                    City.CityName = CityJson.getString("CityName");
                    StateCityObj.CityCollection.add(City);
                }
                StateList.add(StateCityObj);
            }
        } catch (Exception ignored)
        {
        }
        //----------------------
        return StateList;
    }

    private static String LoadJson(Context context, String JsonFileAssetName)
    {
        String json;
        try
        {
            InputStream is = context.getAssets().open(JsonFileAssetName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ignored)
        {
            return null;
        }
        return json;
    }

}
