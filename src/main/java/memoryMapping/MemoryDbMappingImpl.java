package memoryMapping;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Optional;

public class MemoryDbMappingImpl implements MemoryDbMapping {
    private final HashMap<String, UserInfo> memoryMap;
    private static final Object SEMAPHORE = new Object();

    @Inject
    public MemoryDbMappingImpl(HashMap<String, UserInfo> memoryMap,
                               HashMap<String, Long> timeMap) {
        this.memoryMap = memoryMap;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userInfoId) {
        UserInfo userInfo;
        synchronized (SEMAPHORE) {
            userInfo = memoryMap.get(userInfoId);
        }
        if (userInfo == null || userInfo.isItemTooOld()) {
            return Optional.empty();
        }
        return Optional.of(userInfo);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        synchronized (SEMAPHORE) {
            this.memoryMap.put(userInfo.getUserId(), userInfo);
        }
    }
}
