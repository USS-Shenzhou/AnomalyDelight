package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.item.CookingPotItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

import java.util.Optional;

/**
 * @author USS_Shenzhou
 */
public class ThaumaturgyStandardCookingPotBlockItem extends CookingPotItem {
    private static final int BAR_COLOR = Mth.color(0.5F, 0.0F, 0.5F);
    public ThaumaturgyStandardCookingPotBlockItem(Block block, Properties properties) {
        super(block, properties);
    }
    @Override
    public boolean isBarVisible(ItemStack stack) {
        int servingCount = getServingCount(stack);
        return servingCount > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int servingCount = getServingCount(stack);
        int barWidth = Math.min(1 + 12 * servingCount / 64, 13);
        return barWidth;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        ItemStack mealStack = getMealFromItemAd(stack);
        return Optional.of(new CookingPotTooltip.CookingPotTooltipComponent(mealStack));
    }

    // Helper method
    private static int getServingCount(ItemStack stack) {
        ItemStack mealStack = getMealFromItemAd(stack);
        return mealStack.getCount();
    }

    public static ItemStack getMealFromItemAd(ItemStack cookingPotStack) {
        return !cookingPotStack.is((Item) ModItems.TSCP.get()) ? ItemStack.EMPTY : ((ItemStackWrapper)cookingPotStack.getOrDefault(ModDataComponents.MEAL, ItemStackWrapper.EMPTY)).getStack();
    }
}
