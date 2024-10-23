package cn.ussshenzhou.anomalydelight.event;

import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.networking.packet.C2S.PickUpHotDragonEGGC2SPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class PickUpHotDragonEgg {
    @SubscribeEvent
    public static void LeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        Player player = event.getEntity();
        Level level = player.level();

        pickUpHotDragonEgg(level, player);
    }

    @SubscribeEvent
    public static void RightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        Level level = player.level();

        pickUpHotDragonEgg(level, player);
    }

    @OnlyIn(Dist.CLIENT)
    private static void pickUpHotDragonEgg(Level level, Player player) {
        //客户端
        if(level.isClientSide()) {
            // 射线检测参数：开始位置、方向、最大距离
            Vec3 startVec = player.getEyePosition(1.0F);
            // 获取玩家视线方向
            Vec3 lookVec = player.getViewVector(1.0F);
            // 射线检测最大范围 2.0 块
            Vec3 endVec = startVec.add(lookVec.scale(1.5D));

            // 进行实体射线检测
            EntityHitResult hitResult = getEntityLookingAt(player, startVec, endVec, level);

            if (hitResult != null && hitResult.getEntity() instanceof ItemEntity itemEntity) {
                // 检查实体是不是特定的掉落物
                //给服务端发包，执行逻辑
                if (itemEntity.getItem().is(ModItems.HOT_DRAGON_EGG)) {
                    PacketDistributor.sendToServer(new PickUpHotDragonEGGC2SPacket(player.getUUID(),itemEntity.getId()));
                }
            }
        }
    }

    /**
     * 射线检测获取玩家视线中的实体。
     */
    private static EntityHitResult getEntityLookingAt(Player player, Vec3 startVec, Vec3 endVec, Level level) {
        return ProjectileUtil.getEntityHitResult(
                level,
                player,
                startVec,
                endVec,
                player.getBoundingBox().expandTowards(player.getViewVector(1.0F).scale(5.0D)).inflate(1.0D),
                entity -> !entity.isSpectator()
                // 射线检测的包围盒，设置 5 格范围
                // 忽略旁观者
        );
    }
}
