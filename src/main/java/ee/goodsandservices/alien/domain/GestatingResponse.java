package ee.goodsandservices.alien.domain;

/**
 * Created by m3l on 19.05.17.
 */
public enum GestatingResponse {

        GREAT_SUCCESS("new viable and sulphurous creature has left the nest"),
        BIG_SUCCESS("new viable and strong creature has left the nest"),
        SUCCESS("new alien has left the nest"),
        FAIL("gestating aborted");

        private String response;

        GestatingResponse(String response) {
            this.response = response;
        }

        public String response() {
            return response;
        }
}
