public class user {

    private static String username;
    private static String userAddress;

    public static String getUser(){

        return username;
    }

    public static String getAddress(){
        return userAddress;
    }
    public static void setUser(String name, String address){

        username = name;
        userAddress = address;
    }
}