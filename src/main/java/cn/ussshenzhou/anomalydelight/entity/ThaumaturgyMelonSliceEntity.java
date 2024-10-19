package cn.ussshenzhou.anomalydelight.entity;

import cn.ussshenzhou.anomalydelight.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyMelonSliceEntity extends PathfinderMob {

    public ThaumaturgyMelonSliceEntity(Level level) {
        super(ModEntityTypes.THAUMATURGY_MELON_SLICE.get(), level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 16, 2.5, 2.5, entity -> true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult success = tryCapture(itemstack);
        if (success != null) {
            return success;
        }
        return super.mobInteract(player, hand);
    }

    private @Nullable InteractionResult tryCapture(ItemStack itemstack) {
        if (itemstack.is(ModItems.TSCP.get())) {
            var meal = itemstack.getOrDefault(ModDataComponents.MEAL, ItemStackWrapper.EMPTY).getStack();
            if (meal.isEmpty() || meal.is(ModItems.THAUMATURGIC_WATERMELON_JUICE.get())) {
                itemstack.set(ModDataComponents.MEAL, new ItemStackWrapper(new ItemStack(ModItems.THAUMATURGIC_WATERMELON_JUICE.get(), meal.getCount() + 1)));
                this.remove(RemovalReason.DISCARDED);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        }
        return null;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            tryCapture(player.getItemInHand(InteractionHand.MAIN_HAND));
        }
        return super.hurt(source, amount);
    }

    @Override
    public void tick() {
        super.tick();
        //TODO tail particle
    }

    @Override
    public void remove(RemovalReason reason) {
        //TODO blast particle
        super.remove(reason);
    }
}
