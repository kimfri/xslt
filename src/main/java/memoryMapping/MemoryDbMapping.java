package memoryMapping;

import java.util.Optional;

public interface MemoryDbMapping {

    Optional<UserInfo> getUserInfo(String userInfoId);
    void addUserInfo(UserInfo userInfo);

}
