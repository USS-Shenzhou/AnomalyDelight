package cn.ussshenzhou.anomalydelight.datagen;

import cn.ussshenzhou.anomalydelight.recipe.ThaumaturgyStandardCookingPotRecipeBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.InfestedBlock;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

import static cn.ussshenzhou.anomalydelight.item.ModItems.*;
import static net.minecraft.tags.BlockTags.CORALS;
import static net.minecraft.world.item.Items.*;
import static vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab.*;
import static vectorwing.farmersdelight.common.registry.ModItems.*;
import static vectorwing.farmersdelight.common.tag.CommonTags.*;
import static vectorwing.farmersdelight.data.recipe.CookingRecipes.*;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyStandardCookingPotRecipeGenerator extends RecipeProvider {
    public static final String HAS_TSCP = "has_tscp";

    public ThaumaturgyStandardCookingPotRecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(GRAND_LIBRARY_ESSENCE_COFFEE.get(), 1, NORMAL_COOKING, LARGE_EXP, GLASS_BOTTLE)
                .addIngredient(COCOA_BEANS)
                .addIngredient(FOODS_MILK)
                .addIngredient(SUGAR)
                .addIngredient(ENCHANTED_BOOK)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(DRINKS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(THAUMATURGIC_WATERMELON_JUICE.get(), 1, NORMAL_COOKING, LARGE_EXP, GLASS_BOTTLE)
                .addIngredient(GLISTERING_MELON_SLICE)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(DRINKS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(SPRING_BREATH_QINGTUAN.get(), 1, NORMAL_COOKING, LARGE_EXP)
                .addIngredient(CROPS_RICE)
                .addIngredient(FERN)
                .addIngredient(SUGAR)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(AGED_SAKURA_SWEET_SAKE.get(), 1, NORMAL_COOKING, LARGE_EXP, GLASS_BOTTLE)
                .addIngredient(COOKED_RICE.get())
                .addIngredient(CHERRY_LEAVES)
                .addIngredient(SUGAR)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(DRINKS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(SALMON)
                .addIngredient(SAND)
                .addIngredient(SEAGRASS)
                .addIngredient(RAIL)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(LONG_AWAITED_CENTURY_SOUP.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(CROPS_ONION)
                .addIngredient(FOODS_CABBAGE)
                .addIngredient(FOODS_RAW_BEEF)
                .addIngredient(PUMPKIN_SLICE.get())
                .addIngredient(ENDER_PEARL)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(RADISH_CANE_SEA_BREEZE_SWEET_SOUP.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(CARROT)
                .addIngredient(FOODS_MILK)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(HOMESTYLE_CANNED_CHEESE_BAKED_RICE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, BOWL)
                .addIngredient(CROPS_RICE)
                .addIngredient(FOODS_MILK)
                .addIngredient(CROPS_TOMATO)
                .addIngredient(BROWN_MUSHROOM)
                .addIngredient(FOODS_RAW_BACON)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        //TODO count
        CookingPotRecipeBuilder.cookingPotRecipe(GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, PAPER)
                .addIngredient(WHEAT)
                .addIngredient(FOODS_MILK)
                .addIngredient(SUGAR)
                .addIngredient(GOLD_NUGGET)
                .addIngredient(EGG)
                .addIngredient(COCOA_BEANS)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(SHANGHAI_SUNRISE_COCKTAIL.get(), 1, NORMAL_COOKING, MEDIUM_EXP, GLASS_BOTTLE)
                .addIngredient(GLOW_BERRIES)
                .addIngredient(BLUE_ICE)
                .addIngredient(SWEET_BERRIES)
                .addIngredient(SUGAR_CANE)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(DRINKS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(ROMANCE_AND_ENCOUNTER.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(WHEAT)
                .addIngredient(FOODS_MILK)
                .addIngredient(SUGAR)
                .addIngredient(ACACIA_LEAVES)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        BuiltInRegistries.BLOCK.stream()
                .filter(block -> block instanceof InfestedBlock)
                .filter(block -> block.asItem() != AIR)
                .forEach(block -> BuiltInRegistries.BLOCK.stream()
                        .filter(b -> b.defaultBlockState().is(CORALS))
                        .map(Block::asItem)
                        .filter(item -> item != AIR)
                        .forEach(item -> ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD.get(), 1, NORMAL_COOKING, LARGE_EXP, item)
                                .addIngredient(block)
                        )
                );
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE.get(), 1, NORMAL_COOKING, MEDIUM_EXP, BOWL)
                .addIngredient(WHEAT)
                .addIngredient(ROSE_BUSH)
                .addIngredient(SUGAR)
                .addIngredient(ECHO_SHARD, 2)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(WUTHERING_DEPTH.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(SCULK_SHRIEKER)
                .addIngredient(POTATO)
                .addIngredient(CROPS_ONION)
                .addIngredient(CARROT)
                .addIngredient(FOODS_RAW_BEEF)
                .addIngredient(FOODS_CABBAGE)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(MULTIDIMENSIONAL_SHULKER_BISQUE.get(), 1, NORMAL_COOKING, LARGE_EXP, BOWL)
                .addIngredient(SHULKER_SHELL)
                .addIngredient(POPPED_CHORUS_FRUIT)
                .addIngredient(SUGAR)
                .addIngredient(CARROT)
                .addIngredient(CROPS_ONION)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
        ThaumaturgyStandardCookingPotRecipeBuilder.cookingPotRecipe(DEEP_OCEAN_BURGER.get(), 1, NORMAL_COOKING, LARGE_EXP)
                .addIngredient(BREAD)
                .addIngredient(INK_SAC)
                .addIngredient(PRISMARINE_SHARD)
                .unlockedByItems(HAS_TSCP, TSCP.get())
                .setRecipeBookTab(MEALS)
                .build(recipeOutput);
    }
}
