package finki.ukim.mk.soatickets.core;

/**
 * Created by DarkoM on 24.11.2017.
 */
public class Constants {
    public static final String SECRET = "7b4cea49-9645-4818-8d83-a5c76dad69df";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/";
    public static final String SIGN_IN_URL = "/login";
}
