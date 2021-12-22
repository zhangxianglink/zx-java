package style.code11;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 *
 * @author nuc
 */
public class AuthToken {

    public static final long default_time_interval = 6000;
    private String token;
    private long createTime;
    private long expiredTime = default_time_interval;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTime) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTime = expiredTime;
    }

    public static AuthToken create(String baseUrl, long createTime, Map<String,String> params){
        String appId = params.computeIfAbsent("appId", e -> "default_app");
        String password = params.computeIfAbsent("password", e -> "pwd");
        String token = "";
        try {
            byte[] md5s = MessageDigest.getInstance("md5").digest((baseUrl + appId + password + createTime).getBytes());
            String md5code = new BigInteger(1, md5s).toString(16);
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code = "0" + md5code;
            }
            token = md5code;

        }catch (Exception e){
            token = "error";
        }
        return new AuthToken(token, createTime);
    }

    public String getToken(){
        return this.token;
    }

    public boolean isExpired() {
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        return (second - createTime) > expiredTime;
    }

    public boolean match(AuthToken authToken){
        return token.equals(authToken.getToken());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        byte[] md5s = MessageDigest.getInstance("md5").digest(("http://www.rr.com" + "appId" + "mysql_pwd" + "1640183691").getBytes());
        String md5code = new BigInteger(1, md5s).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        System.out.println(md5code);
    }
}
