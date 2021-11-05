package memoryMapping;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Optional;

public class MemoryDbMappingImpl implements MemoryDbMapping {
    private final HashMap<String, UserInfo> memoryMap;
    private final HashMap<String, Long> timeMap;

    @Inject
    public MemoryDbMappingImpl(HashMap<String, UserInfo> memoryMap,
                               HashMap<String, Long> timeMap) {
        this.memoryMap = memoryMap;
        this.timeMap = timeMap;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userInfoId) {
        if(isInfoOld(userInfoId)){
            System.out.println("Record is too old: id " + userInfoId);
            return Optional.empty();
        }
        System.out.println("Record is ok: id " + userInfoId);
        return Optional.ofNullable(this.memoryMap.get(userInfoId));
    }

    private boolean isInfoOld(String userInfoId) {
        if(!this.timeMap.containsKey(userInfoId)) return true;
        long recordTime = this.timeMap.get(userInfoId);
        if (System.currentTimeMillis() - recordTime > 2000) {
            System.out.println("Record will be removed: id " + userInfoId);
            this.timeMap.remove(userInfoId);
            this.memoryMap.remove(userInfoId);
            return true;
        }
        return false;
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        this.memoryMap.put(userInfo.getUserId(), userInfo);
        this.timeMap.put(userInfo.getUserId(), System.currentTimeMillis());
    }

}
