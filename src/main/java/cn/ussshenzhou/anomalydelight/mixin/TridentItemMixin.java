package cn.ussshenzhou.anomalydelight.mixin;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TridentItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author USS_Shenzhou
 */
@Mixin(TridentItem.class)
public class TridentItemMixin {

    @ModifyExpressionValue(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWaterOrRain()Z"))
    private boolean anomalyDelightAlwaysAllowRiptide(boolean arg, @Local Player player) {
        //noinspection unchecked
        return arg || player.hasEffect((Holder<MobEffect>) ModEffects.HASOK);
    }
}
