package souress.lab_web4.auth;

import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import souress.lab_web4.database.dao.UserDao;
import souress.lab_web4.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Stateful
@NoArgsConstructor
public class AuthBean {
    private String login;
    private byte[] password;

    @EJB
    private UserDao userDao;

    private boolean isValid() {
        return login != null && password != null;
    }

    public AuthStatus signIn(String jsonStr) throws NoSuchAlgorithmException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        this.login = jsonObject.getString("login");
        this.password = messageDigest.digest(jsonObject.getString("password").getBytes(StandardCharsets.UTF_8));
        if (isValid()) {
            if (login.equals("")) {
                return AuthStatus.TOO_SHORT_LOGIN;
            } else if (jsonObject.getString("password").equals("")) return AuthStatus.TOO_SHORT_PASSWORD;
            User user = userDao.findUser(login);
            if (user == null) return AuthStatus.NO_USER_FOUND;
            if (!Arrays.equals(user.getPassword(), password)) return AuthStatus.WRONG_PASSWORD;
            return AuthStatus.OK;
        } else return AuthStatus.NO_USER_FOUND;
    }

    public AuthStatus signUp(String jsonStr) throws NoSuchAlgorithmException {
        JSONObject jsonObject = new JSONObject(jsonStr);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        this.login = jsonObject.getString("login");
        this.password = messageDigest.digest(jsonObject.getString("password").getBytes(StandardCharsets.UTF_8));
        if (login.equals("")) {
            return AuthStatus.TOO_SHORT_LOGIN;
        } else if (jsonObject.getString("password").equals("")) return AuthStatus.TOO_SHORT_PASSWORD;
        User user = userDao.findUser(login);
        if (user == null) {
            userDao.saveUser(new User(login, password));
            return AuthStatus.OK;
        } else return AuthStatus.USER_ALREADY_EXISTS;
    }

    public Response handleError(AuthStatus status, Response.ResponseBuilder responseBuilder) {
        responseBuilder.entity(String.format("{\"data\": \"%s\", \"status\": \"%b\"}", status.getDescription(), false));
        switch (status) {
            case USER_ALREADY_EXISTS:
            case TOO_SHORT_LOGIN:
            case TOO_SHORT_PASSWORD:
            case NO_USER_FOUND:
            case WRONG_PASSWORD:
                responseBuilder.status(401);
                break;
            case REQUEST_ERROR:
                responseBuilder.status(500);
                break;
            case UNDEFINED_ERROR:
                responseBuilder.status(520);
                break;
        }
        return responseBuilder.build();
    }
}
