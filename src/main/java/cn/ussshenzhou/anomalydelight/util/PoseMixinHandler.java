package cn.ussshenzhou.anomalydelight.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mafuyu33
 */
public class PoseMixinHandler {
    // 创建一个静态Map来存储实体UUID和值
    private static final Map<Integer, Integer> entityValueMap = new HashMap<>();

    // 在适当的时候将实体UUID和值添加到Map中
    public static void storeEntityValue(int entityID, int value) {
        entityValueMap.put(entityID, value);
    }
    // 在需要时从Map中检索值
    public static int getEntityValue(int entityID) {
        // 默认值为0，如果未找到实体UUID
        return entityValueMap.getOrDefault(entityID, 0);
    }

    // 创建一个静态Map来存储实体UUID和值
    private static final Map<Integer, Integer> flagMap = new HashMap<>();

    // 在适当的时候将实体UUID和值添加到Map中
    public static void storeFlagMap(int entityID, int flag) {
        flagMap.put(entityID, flag);
    }
    // 在需要时从Map中检索值
    // 默认值为-1
    public static int getFlagMap(int entityID) {
        return flagMap.getOrDefault(entityID, -1);
    }
}
