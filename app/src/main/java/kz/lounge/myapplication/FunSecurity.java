package kz.lounge.myapplication;

public class FunSecurity {

    private FunSecurity() {
    }

    public static String token;

    public static boolean isAuthenticated() {
        return !(token == null || token.trim().length() == 0);
    }
}
