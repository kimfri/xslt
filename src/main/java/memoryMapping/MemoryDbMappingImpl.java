package memoryMapping;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Optional;

public class MemoryDbMappingImpl implements MemoryDbMapping {
    private final HashMap<String, TimedUserContainer> memoryMap;
    private static final Object SEMAPHORE = new Object();

    @Inject
    public MemoryDbMappingImpl(HashMap<String, TimedUserContainer> memoryMap,
                               HashMap<String, Long> timeMap) {
        this.memoryMap = memoryMap;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userInfoId) {
        TimedUserContainer timedUserContainer;
        synchronized (SEMAPHORE) {
            timedUserContainer = memoryMap.get(userInfoId);
        }
        if (timedUserContainer == null || timedUserContainer.isItemTooOld()) {
            return Optional.empty();
        }
        return Optional.of(timedUserContainer.getUserInfo());
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        synchronized (SEMAPHORE) {
            this.memoryMap.put(userInfo.getUserId(), new TimedUserContainer(userInfo));
        }
    }
}
