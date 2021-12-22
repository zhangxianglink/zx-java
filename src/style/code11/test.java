package style.code11;

/**
 * @author nuc
 */
public class test {
    public static void main(String[] args) {
        ApiAuthenticator apiAuthenticator = new DefaultApiAuthenticatorImpl();
        apiAuthenticator.auth("虚拟路径");
        System.out.println("校验通过");
    }
}
