package memoryMapping;

import lombok.Data;

@Data
public class UserInfo {
    private final String userId;
    private String name;
    private long creationTime;
    private long maxAge = 2000;

    public UserInfo(String userId) {
        this.userId = userId;
        creationTime = System.currentTimeMillis();
    }

    boolean isItemTooOld() {
        return (System.currentTimeMillis() - creationTime) > maxAge;
    }
}
