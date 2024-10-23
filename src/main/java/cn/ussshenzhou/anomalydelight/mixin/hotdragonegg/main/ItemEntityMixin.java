package cn.ussshenzhou.anomalydelight.mixin.hotdragonegg.main;

import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.networking.packet.S2C.HotDragonEggRandomMoveS2CPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Mafuyu33
 */

//TODO 继承一个ItemEntity然后overridetick方法、然后改掉HOT_DRAGON_EGG对应的生成
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements TraceableEntity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract void setNeverPickUp();

    @Shadow public abstract ItemStack getItem();

    @Shadow private int pickupDelay;

    @Unique
    private boolean anomalyDelight$hasMoved = false;

    @Inject(at = @At("HEAD"), method = "tick")
    private void init(CallbackInfo info) {
        if (!this.level().isClientSide()) {
            if (this.getItem().is(ModItems.HOT_DRAGON_EGG) && this.pickupDelay != 32767) {
                this.setNeverPickUp();
            }
            if (this.getItem().is(ModItems.HOT_DRAGON_EGG)) {
                if (this.onGround() && !anomalyDelight$hasMoved) {
                    // 生成随机方向并施加速度
                    double angle = random.nextDouble() * 2 * Math.PI;
                    double x = Math.cos(angle);
                    double z = Math.sin(angle);
                    Vec3 direction = new Vec3(x, 0.0, z);
                    double horizontalSpeed = 0.4f;
                    Vec3 finalVelocity = new Vec3(direction.x * horizontalSpeed, 0.4, direction.z * horizontalSpeed);
                    this.setDeltaMovement(finalVelocity);
                    // 标记已经施加过速度
                    anomalyDelight$hasMoved = true;

                    // 同步客户端的运动矢量
                    PacketDistributor.sendToAllPlayers(new HotDragonEggRandomMoveS2CPacket(this.getId(), finalVelocity));
                }
                if (!this.onGround()) {
                    // 当物体在空中时，重置标志
                    anomalyDelight$hasMoved = false;
                }
            }
        }
    }
}

