package memoryMapping;

public class TimedUserContainer {
    private final UserInfo userInfo;
    private long creationTime;
    private long maxAgeMs = 2000;

    public TimedUserContainer(UserInfo userInfo){
        this.userInfo = userInfo;
        this.creationTime = System.currentTimeMillis();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    boolean isItemTooOld() {
        return (System.currentTimeMillis() - creationTime) > maxAgeMs;
    }

}
