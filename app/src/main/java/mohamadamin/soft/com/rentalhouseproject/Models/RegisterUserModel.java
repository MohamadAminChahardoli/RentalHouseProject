package mohamadamin.soft.com.rentalhouseproject.Models;

public class RegisterUserModel
{
    public static  final int UserSignedUp = 3;
    public static  final int UserActivated = 2;
    public static  final int UserNotActivated = 1;
    public static  final int UserExist = 0;
    public static  final int UserNotExist = -1;

    public String UserFirstName;
    public String UserLastName;
    public int UserStateCode;
    public int UserCityCode ;
    public int UserRole;
    public String UserMobileNumber;
    public String UserPassword;
}
