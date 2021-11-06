package memoryMapping;

import lombok.Data;

@Data
public class UserInfo {
    private final String userId;
    private String name;

    public UserInfo(String userId) {
        this.userId = userId;
    }
}
