package style.code11;


import java.util.HashMap;

public interface ApiAuthenticator {
    void auth(String url);

    void auth(ApiRequest apiRequest);
}
