package cn.ussshenzhou.anomalydelight.gui;

import cn.ussshenzhou.anomalydelight.recipe.ThaumaturgyStandardCookingPotRecipe;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.recipebook.RecipeBookTabButton;
import net.minecraft.client.gui.screens.recipebook.RecipeCollection;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import vectorwing.farmersdelight.client.gui.CookingPotRecipeBookComponent;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;

import java.util.List;
import java.util.Locale;

/**
 * @author USS_Shenzhou
 */
public class ThaumaturgyStandardCookingPotRecipeBookComponent extends CookingPotRecipeBookComponent {
    public ThaumaturgyStandardCookingPotRecipeBookComponent() {
    }

    @Override
    public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);
        if (slots.get(6).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(resultStack), slots.get(6).x, slots.get(6).y);
        }
        if (recipe.value() instanceof ThaumaturgyStandardCookingPotRecipe cookingRecipe) {
            ItemStack containerStack = cookingRecipe.getOutputContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(containerStack), slots.get(7).x, slots.get(7).y);
            }
        }
        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
    }

    @Override
    public void updateCollections(boolean resetPageNumber) {
        List<RecipeCollection> list = this.book.getCollection(RecipeBookCategories.UNKNOWN)
                .parallelStream()
                .filter(collection -> !collection.getRecipes().isEmpty()
                        && collection.getRecipes().getFirst().value() instanceof ThaumaturgyStandardCookingPotRecipe
                        && matchTab(((ThaumaturgyStandardCookingPotRecipe) collection.getRecipes().getFirst().value()).getTab(), this.selectedTab.getCategory())
                )
                .toList();
        list.forEach(collection -> collection.canCraft(this.stackedContents, this.menu.getGridWidth(), this.menu.getGridHeight(), this.book));
        List<RecipeCollection> list1 = Lists.newArrayList(list);
        list1.removeIf(collection -> !collection.hasKnownRecipes());
        list1.removeIf(collection -> !collection.hasFitting());
        String s = this.searchBox.getValue();
        if (!s.isEmpty()) {
            ClientPacketListener clientpacketlistener = this.minecraft.getConnection();
            if (clientpacketlistener != null) {
                ObjectSet<RecipeCollection> objectset = new ObjectLinkedOpenHashSet<>(
                        clientpacketlistener.searchTrees().recipes().search(s.toLowerCase(Locale.ROOT))
                );
                list1.removeIf(collection -> !objectset.contains(collection));
            }
        }

        if (this.book.isFiltering(this.menu)) {
            list1.removeIf(collection -> !collection.hasCraftable());
        }

        this.recipeBookPage.updateCollections(list1, resetPageNumber);
    }

    private static final String COOKING_SEARCH = "FARMERSDELIGHT_COOKING_SEARCH";
    private static final String COOKING_MEALS = "FARMERSDELIGHT_COOKING_MEALS";
    private static final String COOKING_DRINKS = "FARMERSDELIGHT_COOKING_DRINKS";
    private static final String COOKING_MISC = "FARMERSDELIGHT_COOKING_MISC";

    private boolean matchTab(CookingPotRecipeBookTab tab, RecipeBookCategories categories) {
        return switch (categories.name()) {
            case COOKING_SEARCH -> true;
            case COOKING_MEALS -> tab == CookingPotRecipeBookTab.MEALS;
            case COOKING_DRINKS -> tab == CookingPotRecipeBookTab.DRINKS;
            case COOKING_MISC -> tab == CookingPotRecipeBookTab.MISC;
            default -> false;
        };
    }

    @Override
    public void updateTabs() {
        int i = (this.width - 147) / 2 - this.xOffset - 30;
        int j = (this.height - 166) / 2 + 3;
        int k = 27;
        int l = 0;
        for (RecipeBookTabButton recipebooktabbutton : this.tabButtons) {
            recipebooktabbutton.setPosition(i, j + 27 * l++);
            recipebooktabbutton.startAnimation(this.minecraft);
            recipebooktabbutton.visible = true;
        }
    }

    @Override
    protected void initFilterButtonTextures() {
        //TODO
        super.initFilterButtonTextures();
    }
}
