package cn.ussshenzhou.anomalydelight.recipe;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, AnomalyDelight.MODID);

    public static final Supplier<RecipeSerializer<?>> TSCP = RECIPE_SERIALIZERS.register("tscp", ThaumaturgyStandardCookingPotRecipe.Serializer::new);
}
