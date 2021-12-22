package style.code11;

/**
 * @author nuc
 */
public class MysqlCredentialStorageImpl implements CredentialStorage{
    @Override
    public String getPasswordByAppId(String appId) {
        return "mysql_pwd";
    }
}
