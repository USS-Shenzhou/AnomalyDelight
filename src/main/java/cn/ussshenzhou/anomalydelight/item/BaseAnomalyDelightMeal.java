package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

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

    public BaseAnomalyDelightMeal(Properties properties, @Nullable Component description, Component from) {
        super(properties);
        this.description = description;
        this.from = from;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (description != null) {
            tooltipComponents.add(description);
        }
        tooltipComponents.add(from);
    }
}
