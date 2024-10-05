package cn.ussshenzhou.anomalydelight.recipe;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, AnomalyDelight.MODID);

    public static final Supplier<RecipeType<ThaumaturgyStandardCookingPotRecipe>> TSCP = RECIPE_TYPES.register("tscp", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return AnomalyDelight.MODID + ":tscp";
        }
    });
}
