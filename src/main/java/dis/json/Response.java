package dis.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    public Response(boolean success, String content) {
        this.success = success;
        this.content = content;
    }

    @JsonProperty
    public boolean getSuccess() {
        return success;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    private boolean success;
    private String content;
}
