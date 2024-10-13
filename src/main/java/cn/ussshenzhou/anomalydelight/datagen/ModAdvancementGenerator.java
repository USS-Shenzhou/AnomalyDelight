package cn.ussshenzhou.anomalydelight.datagen;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
public class ModAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        var root = Advancement.Builder.advancement()
                .display(
                        ModItems.TSCP.get(),
                        Component.translatable("advancements.ad.tad"),
                        Component.translatable("advancements.ad.tad.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("eat",
                        ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item()
                                .of(BuiltInRegistries.ITEM.keySet()
                                        .stream()
                                        .filter(resourceLocation -> resourceLocation.getNamespace().equals(AnomalyDelight.MODID))
                                        .map(BuiltInRegistries.ITEM::get)
                                        .toArray(ItemLike[]::new)
                                )
                        )
                )
                .save(saver, ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "try_and_die"), existingFileHelper);

        var coldStart = Advancement.Builder.advancement()
                .display(
                        ModItems.SHANGHAI_SUNRISE_COCKTAIL.get(),
                        Component.translatable("advancements.ad.cs")
                                .withColor(0xf4b084),
                        Component.translatable("advancements.ad.cs.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .parent(root)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("eat",
                        ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item()
                                .of(
                                        ModItems.STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH.get(),
                                        ModItems.LONG_AWAITED_CENTURY_SOUP.get(),
                                        ModItems.RADISH_CANE_SEA_BREEZE_SWEET_SOUP.get(),
                                        ModItems.HOMESTYLE_CANNED_CHEESE_BAKED_RICE.get(),
                                        ModItems.GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE.get(),
                                        ModItems.SHANGHAI_SUNRISE_COCKTAIL.get()
                                )
                        )
                )
                .save(saver, ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "cold_start"), existingFileHelper);

        var welcomeToJupiterDomain = Advancement.Builder.advancement()
                .display(
                        ModItems.ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD.get(),
                        Component.translatable("advancements.ad.data")
                                .withColor(0x000099),
                        Component.translatable("advancements.ad.data.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .parent(root)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("eat",
                        ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item()
                                .of(
                                        ModItems.YOURSELF.get(),
                                        ModItems.ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD.get()
                                )
                        )
                )
                .save(saver, ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "welcome_to_jupiter_domain"), existingFileHelper);
    }
}
