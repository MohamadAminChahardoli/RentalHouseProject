package mohamadamin.soft.com.rentalhouseproject.ServerApi;

import java.util.List;

import mohamadamin.soft.com.rentalhouseproject.Models.ActiveUserModel;
import mohamadamin.soft.com.rentalhouseproject.Models.FilterModel;
import mohamadamin.soft.com.rentalhouseproject.Models.House;
import mohamadamin.soft.com.rentalhouseproject.Models.LoginUserModel;
import mohamadamin.soft.com.rentalhouseproject.Models.RegisterUserModel;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.Slide;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RentalHouseApi {

    @Headers("User-Agent: <RentalHouse>")
    @GET("Api/HouseApi/GetHouseSliders")
    Call<List<Slide>> getSlides();/*done*/

    @Headers("User-Agent: <RentalHouse>")
    @GET("Api/HouseApi/SearchHouse")
    Call<List<SecondaryHouse>> searchForHouse(@Query("searchTerm") String searchTerm, @Query("skipItem")int skipItem, @Query("takeItem")int takeItem);/*done*/

    @Headers("User-Agent: <RentalHouse>")
    @GET("Api/HouseApi/GetHouseDetails")
    Call<House> getHouseDetails(@Query("houseId")int houseId);/*done*/

    @Headers("User-Agent: <RentalHouse>")
    @GET("Api/HouseApi/GetAllHouses")
    Call<List<SecondaryHouse>> getAllHouses(@Query("skipNumber")int skipNumber, @Query("takeNumber")int takeNumber);/*done*/

    @Headers("User-Agent: <RentalHouse>")
    @POST("Api/HouseApi/GetHousesWithFilter")
    Call<List<SecondaryHouse>> getHousesWithFilter(@Body FilterModel filter, @Query("skipNumber")int skipNumber, @Query("takeNumber")int takeNumber);/*???*/

    @Headers("User-Agent: <RentalHouse>")
    @POST("Api/RegisterationApi/RegisterUser")
    Call<Integer> signUp(@Body RegisterUserModel registerUserModel);/*???*/
    /*Conditions:
    If Success=>  return ActiveCode and SMS ActiveCode;
    ELSE => RegisterUserState(ENUM)*/

    @Headers("User-Agent: <RentalHouse>")
    @POST("Api/RegisterationApi/LoginUser")
    Call<Integer> signIn(@Body LoginUserModel loginUserObject);
    /*IF UserExistAnd Active THEN
    IF USERNAME AND PASSWORD ARE CORRECT THEN
 		 	return user.UserId;
    ELSE
  		return 0;//password incorrect
    ELSE => RegisterUserState(ENUM)*/

    @Headers("User-Agent: <RentalHouse>")
    @POST("Api/RegisterationApi/ActiveUser")
    Call<Boolean> activeUser(@Body ActiveUserModel activeUserObject);

    @Headers("User-Agent: <RentalHouse>")
    @POST("Api/RegisterationApi/GenerateActiveCode")
    Call<Integer> generateActivationCode(@Query("") String userMobileNumber);
    /*CONDITION->LIKE ARACHOOB*/

}
