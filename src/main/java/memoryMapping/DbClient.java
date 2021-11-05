package memoryMapping;

import com.google.inject.Inject;

import java.util.Optional;

public class DbClient {
    private final MemoryDbMapping memoryDbMapping;

    @Inject
    public DbClient(MemoryDbMapping memoryDbMapping) {
        this.memoryDbMapping = memoryDbMapping;
    }

    public void doSomeMapping(String id) {
        System.out.println("*****************************************'");
        UserInfo userInfo = getUserMapping(id);
        System.out.println(userInfo);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private UserInfo getUserMapping(String id) {
        Optional<UserInfo> userInfoOptional = memoryDbMapping.getUserInfo(id);
        return userInfoOptional.orElseGet(() ->getUserInfoFromDbAndUpdateMemoryMapping(id));
    }

    private UserInfo getUserInfoFromDbAndUpdateMemoryMapping(String id) {
        System.out.println("Will create id: " + id);
        UserInfo userInfo = getUserInfoFromDb(id);
        this.memoryDbMapping.addUserInfo(userInfo);
        return userInfo;
    }

    private UserInfo getUserInfoFromDb(String userInfoId) {
        UserInfo userInfo = new UserInfo(userInfoId);
        userInfo.setName("MyName_" + userInfoId);
        return userInfo;
    }
}
