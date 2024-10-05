package cn.ussshenzhou.anomalydelight.recipe;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Map;
import java.util.Objects;

/**
 * @author USS_Shenzhou
 */
@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyStandardCookingPotRecipeBuilder extends CookingPotRecipeBuilder {
    private static final VarHandle tab;
    private static final VarHandle ingredients;
    private static final VarHandle resultStack;
    private static final VarHandle cookingTime;
    private static final VarHandle experience;
    private static final VarHandle container;
    private static final VarHandle criteria;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(CookingPotRecipeBuilder.class, MethodHandles.lookup());
            tab = lookup.findVarHandle(CookingPotRecipeBuilder.class, "tab", CookingPotRecipeBookTab.class);
            ingredients = lookup.findVarHandle(CookingPotRecipeBuilder.class, "ingredients", NonNullList.class);
            resultStack = lookup.findVarHandle(CookingPotRecipeBuilder.class, "resultStack", ItemStack.class);
            cookingTime = lookup.findVarHandle(CookingPotRecipeBuilder.class, "cookingTime", int.class);
            experience = lookup.findVarHandle(CookingPotRecipeBuilder.class, "experience", float.class);
            container = lookup.findVarHandle(CookingPotRecipeBuilder.class, "container", ItemStack.class);
            criteria = lookup.findVarHandle(CookingPotRecipeBuilder.class, "criteria", Map.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public CookingPotRecipeBookTab getTab() {
        return (CookingPotRecipeBookTab) tab.get(this);
    }

    public void setTab(CookingPotRecipeBookTab value) {
        tab.set(this, value);
    }

    @SuppressWarnings("unchecked")
    public NonNullList<Ingredient> getIngredients() {
        return (NonNullList<Ingredient>) ingredients.get(this);
    }

    public ItemStack getResultStack() {
        return (ItemStack) resultStack.get(this);
    }

    public int getCookingTime() {
        return (int) cookingTime.get(this);
    }

    public float getExperience() {
        return (float) experience.get(this);
    }

    public ItemStack getContainer() {
        return (ItemStack) container.get(this);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Criterion<?>> getCriteria() {
        return (Map<String, Criterion<?>>) criteria.get(this);
    }


    public ThaumaturgyStandardCookingPotRecipeBuilder(ItemLike result, int count, int cookingTime, float experience, @Nullable ItemLike container) {
        super(result, count, cookingTime, experience, container);
    }

    public ThaumaturgyStandardCookingPotRecipeBuilder(ItemStack resultIn, int cookingTime, float experience, @Nullable ItemLike container) {
        super(resultIn, cookingTime, experience, container);
    }

    public static ThaumaturgyStandardCookingPotRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience) {
        return new ThaumaturgyStandardCookingPotRecipeBuilder(mainResult, count, cookingTime, experience, null);
    }

    public static ThaumaturgyStandardCookingPotRecipeBuilder cookingPotRecipe(ItemLike mainResult, int count, int cookingTime, float experience, ItemLike container) {
        return new ThaumaturgyStandardCookingPotRecipeBuilder(mainResult, count, cookingTime, experience, container);
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(itemIn));
        }

        return this;
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.getIngredients().add(ingredientIn);
        }

        return this;
    }

    @Override
    public RecipeBuilder group(@org.jetbrains.annotations.Nullable String p_176495_) {
        return this;
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder setRecipeBookTab(CookingPotRecipeBookTab tab) {
        this.setTab(tab);
        return this;
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger) {
        this.getCriteria().put(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder unlockedByItems(String criterionName, ItemLike... items) {
        return this.unlockedBy(criterionName, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    @Override
    public ThaumaturgyStandardCookingPotRecipeBuilder unlockedByAnyIngredient(ItemLike... items) {
        this.getCriteria().put("has_any_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items).build()));
        return this;
    }

    @Override
    public void build(RecipeOutput output) {
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(this.getResult());
        this.save(output, ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, location.getPath()));
    }

    @Override
    public void build(RecipeOutput outputIn, String save) {
        ResourceLocation resourcelocation = BuiltInRegistries.ITEM.getKey(this.getResult());
        if (ResourceLocation.parse(save).equals(resourcelocation)) {
            throw new IllegalStateException("Cooking Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(outputIn, ResourceLocation.parse(save));
        }
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation recipeId = id.withPrefix("tscp/");
        Advancement.Builder advancementBuilder = output.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(advancementBuilder);
        this.getCriteria().forEach(advancementBuilder::addCriterion);
        ThaumaturgyStandardCookingPotRecipe recipe = new ThaumaturgyStandardCookingPotRecipe("", this.getTab(), this.getIngredients(), this.getResultStack(), this.getContainer(), this.getExperience(), this.getCookingTime());
        output.accept(recipeId, recipe, advancementBuilder.build(id.withPrefix("recipes/tscp/")));
    }
}
