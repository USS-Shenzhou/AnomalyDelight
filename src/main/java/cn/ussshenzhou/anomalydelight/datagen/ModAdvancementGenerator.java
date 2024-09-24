package cn.ussshenzhou.anomalydelight.datagen;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
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
                        //TODO
                        Items.BARRIER,
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
    }
}
