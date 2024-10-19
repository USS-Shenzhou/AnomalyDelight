package cn.ussshenzhou.anomalydelight.block.entity;

import cn.ussshenzhou.anomalydelight.block.container.ThaumaturgyStandardCookingPotMenu;
import cn.ussshenzhou.anomalydelight.entity.ThaumaturgyMelonSliceEntity;
import cn.ussshenzhou.anomalydelight.item.BaseAnomalyDelightMeal;
import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.recipe.ModRecipeTypes;
import cn.ussshenzhou.anomalydelight.recipe.ThaumaturgyStandardCookingPotRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Optional;

/**
 * @author USS_Shenzhou
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyStandardCookingPotBlockEntity extends CookingPotBlockEntity {

    private static final VarHandle inputHandler;
    private static final VarHandle outputHandler;
    private static final VarHandle cookTime;
    private static final VarHandle cookTimeTotal;
    private static final VarHandle mealContainerStack;

    private final RecipeManager.CachedCheck<RecipeWrapper, ThaumaturgyStandardCookingPotRecipe> quickCheck;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(CookingPotBlockEntity.class, MethodHandles.lookup());
            inputHandler = lookup.findVarHandle(CookingPotBlockEntity.class, "inputHandler", IItemHandler.class);
            outputHandler = lookup.findVarHandle(CookingPotBlockEntity.class, "outputHandler", IItemHandler.class);
            cookTime = lookup.findVarHandle(CookingPotBlockEntity.class, "cookTime", int.class);
            cookTimeTotal = lookup.findVarHandle(CookingPotBlockEntity.class, "cookTimeTotal", int.class);
            mealContainerStack = lookup.findVarHandle(CookingPotBlockEntity.class, "mealContainerStack", ItemStack.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public IItemHandler getInputHandler() {
        return (IItemHandler) inputHandler.get(this);
    }

    public IItemHandler getOutputHandler() {
        return (IItemHandler) outputHandler.get(this);
    }

    public int getCookTime() {
        return (int) cookTime.get(this);
    }

    public void setCookTime(int cookTime) {
        ThaumaturgyStandardCookingPotBlockEntity.cookTime.set(this, cookTime);
    }

    public int getCookTimeTotal() {
        return (int) cookTimeTotal.get(this);
    }

    public void setCookTimeTotal(int cookTimeTotal) {
        ThaumaturgyStandardCookingPotBlockEntity.cookTimeTotal.set(this, cookTimeTotal);
    }

    public ItemStack getMealContainerStack() {
        return (ItemStack) mealContainerStack.get(this);
    }

    public void setMealContainerStack(ItemStack mealContainerStack) {
        ThaumaturgyStandardCookingPotBlockEntity.mealContainerStack.set(this, mealContainerStack);
    }

    public ThaumaturgyStandardCookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        quickCheck = RecipeManager.createCheck(ModRecipeTypes.TSCP.get());
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.TSCP.get();
    }

    @Override
    protected boolean canCook(CookingPotRecipe recipe) {
        if (recipe instanceof ThaumaturgyStandardCookingPotRecipe && this.hasInput()) {
            @SuppressWarnings("DataFlowIssue")
            ItemStack resultStack = recipe.getResultItem(this.level.registryAccess());
            if (resultStack.isEmpty()) {
                return false;
            }
            if (resultStack.getItem() instanceof BaseAnomalyDelightMeal baseAnomalyDelightMeal && !baseAnomalyDelightMeal.satisfyExtraCookingRequire(this)) {
                return false;
            }
            ItemStack storedMealStack = this.getInventory().getStackInSlot(6);
            if (storedMealStack.isEmpty()) {
                return true;
            } else if (!ItemStack.isSameItem(storedMealStack, resultStack)) {
                return false;
            } else if (storedMealStack.getCount() + resultStack.getCount() <= this.getInventory().getSlotLimit(6)) {
                return true;
            } else {
                return storedMealStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
            }
        }
        return false;
    }

    private boolean hasInput() {
        for (int i = 0; i < 6; ++i) {
            if (!this.getInventory().getStackInSlot(i).isEmpty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
        return new ThaumaturgyStandardCookingPotMenu(id, player, this, this.cookingPotData);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, CookingPotBlockEntity cookingPot) {
        if (cookingPot.isHeated(level, pos)) {
            RandomSource random = level.random;
            double x;
            double y;
            double z;
            if (random.nextFloat() < 0.2F) {
                x = (double) pos.getX() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
                y = (double) pos.getY() + 0.95;
                z = (double) pos.getZ() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
                level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0, 0.0, 0.0);
            }

            if (random.nextFloat() < 0.05F) {
                x = (double) pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                y = (double) pos.getY() + 0.75;
                z = (double) pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                double motionY = random.nextBoolean() ? 0.015 : 0.005;
                level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0, motionY, 0.0);
            }
        }

    }

    @Override
    public boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.getBlock() == Blocks.CRYING_OBSIDIAN;
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, ThaumaturgyStandardCookingPotBlockEntity thiz) {
        boolean isHeated = thiz.isHeated(level, pos);
        boolean didInventoryChange = false;
        if (isHeated && thiz.hasInput()) {
            Optional<RecipeHolder<ThaumaturgyStandardCookingPotRecipe>> recipe = thiz.getMatchingRecipe(new RecipeWrapper(thiz.getInventory()));
            if (recipe.isPresent() && thiz.canCook((ThaumaturgyStandardCookingPotRecipe) ((RecipeHolder<?>) recipe.get()).value())) {
                didInventoryChange = thiz.processCooking(recipe.get(), thiz);

                // ThaumaturgyMelonSlice
                if (recipe.get().value().getOutput().is(ModItems.THAUMATURGIC_WATERMELON_JUICE.get()) && level.random.nextDouble() < 0.005) {
                    for (int i = 0; i < 6; ++i) {
                        ItemStack slotStack = thiz.getInventory().getStackInSlot(i);
                        if (slotStack.is(Items.GLISTERING_MELON_SLICE)) {
                            slotStack.shrink(1);
                            var melonSlice = new ThaumaturgyMelonSliceEntity(level);
                            melonSlice.setPos(thiz.getBlockPos().getX() + 0.5, thiz.getBlockPos().getY() + 14 / 16.0, thiz.getBlockPos().getZ() + 0.5);
                            melonSlice.setDeltaMovement(
                                    level.random.nextDouble() * 0.1 * (level.random.nextBoolean() ? 1 : -1),
                                    level.random.nextDouble() * 0.5,
                                    level.random.nextDouble() * 0.1 * (level.random.nextBoolean() ? 1 : -1)
                            );
                            level.addFreshEntity(melonSlice);
                            break;
                        }
                    }
                }

            } else {
                thiz.setCookTime(0);
            }
        } else if (thiz.getCookTime() > 0) {
            thiz.setCookTime(Mth.clamp(thiz.getCookTime() - 2, 0, thiz.getCookTimeTotal()));
        }

        ItemStack mealStack = thiz.getMeal();
        if (!mealStack.isEmpty()) {
            if (!thiz.doesMealHaveContainer(mealStack)) {
                thiz.moveMealToOutput();
                didInventoryChange = true;
            } else if (!thiz.getInventory().getStackInSlot(7).isEmpty()) {
                thiz.useStoredContainersOnMeal();
                didInventoryChange = true;
            }
        }

        if (didInventoryChange) {
            thiz.inventoryChanged();
        }

    }

    private boolean processCooking(RecipeHolder<ThaumaturgyStandardCookingPotRecipe> recipe, ThaumaturgyStandardCookingPotBlockEntity tscp) {
        if (this.level == null) {
            return false;
        } else {
            this.setCookTime(this.getCookTime() + 1);
            this.setCookTimeTotal(recipe.value().getCookTime());
            if (this.getCookTime() < this.getCookTimeTotal()) {
                return false;
            } else {
                this.setCookTime(0);
                this.setMealContainerStack(recipe.value().getOutputContainer());
                ItemStack resultStack = recipe.value().getResultItem(this.level.registryAccess());
                ItemStack storedMealStack = this.getInventory().getStackInSlot(6);
                if (storedMealStack.isEmpty()) {
                    this.getInventory().setStackInSlot(6, resultStack.copy());
                } else if (ItemStack.isSameItem(storedMealStack, resultStack)) {
                    storedMealStack.grow(resultStack.getCount());
                }
                tscp.setRecipeUsed(recipe);
                for (int i = 0; i < 6; ++i) {
                    ItemStack slotStack = this.getInventory().getStackInSlot(i);
                    if (slotStack.hasCraftingRemainingItem()) {
                        this.ejectIngredientRemainder(slotStack.getCraftingRemainingItem());
                    } else if (INGREDIENT_REMAINDER_OVERRIDES.containsKey(slotStack.getItem())) {
                        this.ejectIngredientRemainder(INGREDIENT_REMAINDER_OVERRIDES.get(slotStack.getItem()).getDefaultInstance());
                    }
                    if (!slotStack.isEmpty()) {
                        slotStack.shrink(1);
                    }
                }
                return true;
            }
        }
    }

    private boolean doesMealHaveContainer(ItemStack meal) {
        return !this.getMealContainerStack().isEmpty() || meal.hasCraftingRemainingItem();
    }

    private void moveMealToOutput() {
        ItemStack mealStack = this.getInventory().getStackInSlot(6);
        ItemStack outputStack = this.getInventory().getStackInSlot(8);
        int mealCount = Math.min(mealStack.getCount(), mealStack.getMaxStackSize() - outputStack.getCount());
        if (outputStack.isEmpty()) {
            this.getInventory().setStackInSlot(8, mealStack.split(mealCount));
        } else if (outputStack.getItem() == mealStack.getItem()) {
            mealStack.shrink(mealCount);
            outputStack.grow(mealCount);
        }
    }

    private void useStoredContainersOnMeal() {
        ItemStack mealStack = this.getInventory().getStackInSlot(6);
        ItemStack containerInputStack = this.getInventory().getStackInSlot(7);
        ItemStack outputStack = this.getInventory().getStackInSlot(8);
        if (this.isContainerValid(containerInputStack) && outputStack.getCount() < outputStack.getMaxStackSize()) {
            int smallerStackCount = Math.min(mealStack.getCount(), containerInputStack.getCount());
            int mealCount = Math.min(smallerStackCount, mealStack.getMaxStackSize() - outputStack.getCount());
            if (outputStack.isEmpty()) {
                containerInputStack.shrink(mealCount);
                this.getInventory().setStackInSlot(8, mealStack.split(mealCount));
            } else if (outputStack.getItem() == mealStack.getItem()) {
                mealStack.shrink(mealCount);
                containerInputStack.shrink(mealCount);
                outputStack.grow(mealCount);
            }
        }

    }

    private Optional<RecipeHolder<ThaumaturgyStandardCookingPotRecipe>> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
        if (this.level == null) {
            return Optional.empty();
        } else {
            return this.hasInput() ? this.quickCheck.getRecipeFor(inventoryWrapper, this.level) : Optional.empty();
        }
    }
}
