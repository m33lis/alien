package ee.goodsandservices.alien.domain;

/**
 * Created by m3l on 19.05.17.
 */

public class GestatingResponse {

    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "GestatingResponse{" +
            "message='" + message + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
