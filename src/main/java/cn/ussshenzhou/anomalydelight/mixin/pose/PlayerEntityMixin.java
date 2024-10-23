package cn.ussshenzhou.anomalydelight.mixin.pose;

import cn.ussshenzhou.anomalydelight.util.PoseMixinHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }
    @Inject(at = @At("RETURN"), method = "updatePlayerPose")
    private void init(CallbackInfo ci) {
        if (PoseMixinHandler.getEntityValue(this.getId()) > 0) {
            // 从 getFlagMap 提取 1 或 2
            int flag = PoseMixinHandler.getFlagMap(this.getId());

            // 根据 flag 值选择姿势：1 = SLEEPING，2 = SWIMMING
            Pose randomPose = (flag == 1) ? Pose.SLEEPING : Pose.SWIMMING;

            // 设置玩家姿势
            this.setPose(randomPose);

            // 更新 EntityValue
            PoseMixinHandler.storeEntityValue(this.getId(), PoseMixinHandler.getEntityValue(this.getId()) - 1);

        }
    }
    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void init2(Vec3 movementInput, CallbackInfo ci) {
        if (this.getPose() == Pose.SLEEPING && PoseMixinHandler.getEntityValue(this.getId()) > 0) {
            // 取消移动
            ci.cancel();
        }
    }
    // 玩家受到伤害时，解除睡眠状态
    @Inject(method = "hurt", at = @At("HEAD"))
    private void init3(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.getPose() == Pose.SLEEPING && PoseMixinHandler.getEntityValue(this.getId()) > 0) {
            // 解除睡眠状态
            PoseMixinHandler.storeEntityValue(this.getId(), 0);
            // 恢复玩家为站立姿势
            this.setPose(Pose.STANDING);
        }
    }
}
