package cn.ussshenzhou.anomalydelight.mixin;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author USS_Shenzhou
 */
@Mixin(Warden.class)
public class WardenMixin {

    @SuppressWarnings("unchecked")
    @ModifyExpressionValue(method = "canTargetEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/WorldBorder;isWithinBounds(Lnet/minecraft/world/phys/AABB;)Z"))
    private boolean anomalyDelightWutheringDepth(boolean original, @Local LivingEntity entity) {
        return original && !(entity.hasEffect((Holder<MobEffect>) ModEffects.WUTHERING_DEPTH));
    }
}
