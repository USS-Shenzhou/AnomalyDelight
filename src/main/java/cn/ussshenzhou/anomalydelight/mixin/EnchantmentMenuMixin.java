package cn.ussshenzhou.anomalydelight.mixin;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

/**
 * @author USS_Shenzhou
 */
@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {

    @Shadow
    protected abstract List<EnchantmentInstance> getEnchantmentList(RegistryAccess registryAccess, ItemStack stack, int slot, int cost);

    @Redirect(method = "lambda$clickMenuButton$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/EnchantmentMenu;getEnchantmentList(Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/world/item/ItemStack;II)Ljava/util/List;", opcode = Opcodes.GETFIELD))
    private List<EnchantmentInstance> anomalyDelightGrandLibraryEssenceCoffee(EnchantmentMenu instance, RegistryAccess registryAccess, ItemStack itemStack, int id, int level, @Local(argsOnly = true) Player player) {
        //noinspection unchecked
        if (player.hasEffect((Holder<MobEffect>) ModEffects.GRAND_LIBRARY_ESSENCE_COFFEE)) {
            return this.getEnchantmentList(registryAccess, itemStack, id, (int) (level * 1.3));
        }
        return this.getEnchantmentList(registryAccess, itemStack, id, level);
    }
}
