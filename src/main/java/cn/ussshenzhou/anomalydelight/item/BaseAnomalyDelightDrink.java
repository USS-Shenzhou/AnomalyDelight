package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.block.entity.ThaumaturgyStandardCookingPotBlockEntity;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BaseAnomalyDelightDrink extends BaseAnomalyDelightMeal {
    public BaseAnomalyDelightDrink(Properties properties, @Nullable Component description, Component from, @Nullable Predicate<ThaumaturgyStandardCookingPotBlockEntity> extraCookingRequire, @Nullable Consumer<LivingEntity> extraEffect) {
        super(properties, description, from, extraCookingRequire, extraEffect);
    }

    public BaseAnomalyDelightDrink(Properties properties, @Nullable Component description, Component from) {
        super(properties, description, from);
    }

    public BaseAnomalyDelightDrink(Properties properties, @Nullable Component description, Component from, @Nullable Predicate<ThaumaturgyStandardCookingPotBlockEntity> extraCookingRequire) {
        super(properties, description, from, extraCookingRequire);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.getFoodProperties(player) != null) {
            if (player.canEat(heldStack.getFoodProperties(player).canAlwaysEat())) {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(heldStack);
            } else {
                return InteractionResultHolder.fail(heldStack);
            }
        } else {
            return ItemUtils.startUsingInstantly(level, player, hand);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        super.finishUsingItem(stack, level, consumer);
        ItemStack containerStack = stack.getCraftingRemainingItem();
        Player player;
        if (stack.getFoodProperties(consumer) != null) {
            super.finishUsingItem(stack, level, consumer);
        } else {
            player = consumer instanceof Player ? (Player)consumer : null;
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
            }

            if (player != null) {
                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }

        if (stack.isEmpty()) {
            return containerStack;
        } else {
            if (consumer instanceof Player) {
                player = (Player)consumer;
                if (!((Player)consumer).getAbilities().instabuild && !player.getInventory().add(containerStack)) {
                    player.drop(containerStack, false);
                }
            }

            return stack;
        }
    }
}
