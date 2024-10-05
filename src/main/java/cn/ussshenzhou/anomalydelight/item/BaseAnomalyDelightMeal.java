package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.block.entity.ThaumaturgyStandardCookingPotBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BaseAnomalyDelightMeal extends Item {
    @Nullable
    private final Component description;
    @Nonnull
    private final Component from;
    @Nullable
    private final Predicate<ThaumaturgyStandardCookingPotBlockEntity> extraCookingRequire;

    public BaseAnomalyDelightMeal(Properties properties, @Nullable Component description, Component from) {
        super(properties);
        this.description = description;
        this.from = from;
        this.extraCookingRequire = null;
    }

    public BaseAnomalyDelightMeal(Properties properties, @Nullable Component description, Component from, @Nullable Predicate<ThaumaturgyStandardCookingPotBlockEntity> extraCookingRequire) {
        super(properties);
        this.description = description;
        this.from = from;
        this.extraCookingRequire = extraCookingRequire;
    }

    public boolean satisfyExtraCookingRequire(ThaumaturgyStandardCookingPotBlockEntity entity) {
        return extraCookingRequire == null || extraCookingRequire.test(entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (description != null) {
            tooltipComponents.add(description);
        }
        tooltipComponents.add(from);
    }
}
