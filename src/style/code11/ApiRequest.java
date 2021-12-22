package style.code11;

/**
 * @author nuc
 */
public class ApiRequest {
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ApiRequest createFromFullUrl(String url){
        // 解析路径略过
        return new ApiRequest("http://www.rr.com","05710ce150f945edddffaccb9e8eb383","appId",1640183691L);
    }
}
