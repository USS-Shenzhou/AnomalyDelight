package cn.ussshenzhou.anomalydelight.event;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class LivingEntityTickListener {
    // 每tick旋转的度数
    private static final float ROTATION_SPEED = 100.0f;
    // 插值因子，用于控制平滑度
    private static final float INTERPOLATION_FACTOR = 0.5f;

    @SubscribeEvent
    public static void entityTickEvent(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        Level level = entity.level();

        if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(ModEffects.SPIN_EFFECT)) {
            // 获取当前的 Yaw 角度
            float currentYaw = livingEntity.getYRot();
            // 目标 Yaw 角度，每 tick 增加一定的旋转速度
            float targetYaw = currentYaw + ROTATION_SPEED;
            // 使用线性插值进行平滑过渡
            float interpolatedYaw = lerpYaw(currentYaw, targetYaw, INTERPOLATION_FACTOR);

            // 设置插值后的旋转角度
            livingEntity.setYRot(interpolatedYaw);
            livingEntity.setYHeadRot(interpolatedYaw);
            livingEntity.yBodyRot = interpolatedYaw;

            // 确保在多玩家环境中同步数据，向客户端发送实体的更新
            if (!level.isClientSide()) {
                livingEntity.setYRot(interpolatedYaw);
                livingEntity.setYHeadRot(interpolatedYaw);
                livingEntity.yBodyRot = interpolatedYaw;
            }
        }
    }

    // 插值方法，线性插值用于平滑旋转过渡
    private static float lerpYaw(float startYaw, float targetYaw, float factor) {
        return startYaw + factor * (targetYaw - startYaw);
    }
}
