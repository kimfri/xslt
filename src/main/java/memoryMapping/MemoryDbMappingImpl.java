package memoryMapping;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Optional;

public class MemoryDbMappingImpl implements MemoryDbMapping {
    private final HashMap<String, UserInfo> memoryMap;

    @Inject
    public MemoryDbMappingImpl(HashMap<String, UserInfo> memoryMap,
                               HashMap<String, Long> timeMap) {
        this.memoryMap = memoryMap;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userInfoId) {
        if(!this.memoryMap.containsKey(userInfoId)) return Optional.empty();

        UserInfo userInfo = memoryMap.get(userInfoId);
        if (userInfo.isItemTooOld()) {
            this.memoryMap.remove(userInfoId);
            return Optional.empty();
        }
        System.out.println("Record is ok: id " + userInfoId);
        return Optional.of(this.memoryMap.get(userInfoId));
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        this.memoryMap.put(userInfo.getUserId(), userInfo);
    }
}
