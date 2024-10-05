package cn.ussshenzhou.anomalydelight.recipe;

import cn.ussshenzhou.anomalydelight.item.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

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
public class ThaumaturgyStandardCookingPotRecipe extends CookingPotRecipe {
    private static final VarHandle group;
    private static final VarHandle tab;
    private static final VarHandle inputItems;
    private static final VarHandle output;
    private static final VarHandle container;
    private static final VarHandle containerOverride;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(CookingPotRecipe.class, MethodHandles.lookup());
            group = lookup.findVarHandle(CookingPotRecipe.class, "group", String.class);
            tab = lookup.findVarHandle(CookingPotRecipe.class, "tab", CookingPotRecipeBookTab.class);
            inputItems = lookup.findVarHandle(CookingPotRecipe.class, "inputItems", NonNullList.class);
            output = lookup.findVarHandle(CookingPotRecipe.class, "output", ItemStack.class);
            container = lookup.findVarHandle(CookingPotRecipe.class, "container", ItemStack.class);
            containerOverride = lookup.findVarHandle(CookingPotRecipe.class, "containerOverride", ItemStack.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public CookingPotRecipeBookTab getTab() {
        return (CookingPotRecipeBookTab) tab.get(this);
    }

    @SuppressWarnings("unchecked")
    public NonNullList<Ingredient> getInputItems() {
        return (NonNullList<Ingredient>) inputItems.get(this);
    }

    public ItemStack getOutput() {
        return (ItemStack) output.get(this);
    }

    public ItemStack getContainer() {
        return (ItemStack) container.get(this);
    }

    public void setContainerOverride(ItemStack value) {
        containerOverride.set(this, value);
    }

    public ThaumaturgyStandardCookingPotRecipe(String group, @Nullable CookingPotRecipeBookTab tab, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime) {
        super(group, tab, inputItems, output, container, experience, cookTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.TSCP.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.TSCP.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.TSCP.get());
    }

    public static class Serializer implements RecipeSerializer<ThaumaturgyStandardCookingPotRecipe> {
        private static final MapCodec<ThaumaturgyStandardCookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec((inst) -> {
            return inst.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(ThaumaturgyStandardCookingPotRecipe::getGroup),
                            CookingPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab")
                                    .xmap((optional) -> optional.orElse(null), Optional::of)
                                    .forGetter(ThaumaturgyStandardCookingPotRecipe::getRecipeBookTab),
                            Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap((ingredients) -> {
                                        NonNullList<Ingredient> nonNullList = NonNullList.create();
                                        nonNullList.addAll(ingredients);
                                        return nonNullList;
                                    }, (ingredients) -> ingredients)
                                    .forGetter(ThaumaturgyStandardCookingPotRecipe::getIngredients),
                            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(ThaumaturgyStandardCookingPotRecipe::getOutput),
                            ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(ThaumaturgyStandardCookingPotRecipe::getContainerOverride),
                            Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(ThaumaturgyStandardCookingPotRecipe::getExperience),
                            Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(ThaumaturgyStandardCookingPotRecipe::getCookTime))
                    .apply(inst, ThaumaturgyStandardCookingPotRecipe::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf, ThaumaturgyStandardCookingPotRecipe> STREAM_CODEC = StreamCodec.of(ThaumaturgyStandardCookingPotRecipe.Serializer::toNetwork, ThaumaturgyStandardCookingPotRecipe.Serializer::fromNetwork);

        public Serializer() {
        }

        @Override
        public MapCodec<ThaumaturgyStandardCookingPotRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ThaumaturgyStandardCookingPotRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ThaumaturgyStandardCookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf();
            CookingPotRecipeBookTab tabIn = CookingPotRecipeBookTab.findByName(buffer.readUtf());
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);
            inputItemsIn.replaceAll((ignored) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            float experienceIn = buffer.readFloat();
            int cookTimeIn = buffer.readVarInt();
            return new ThaumaturgyStandardCookingPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ThaumaturgyStandardCookingPotRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            //noinspection ConstantValue
            buffer.writeUtf(recipe.getTab() != null ? recipe.getTab().toString() : "");
            buffer.writeVarInt(recipe.getInputItems().size());
            for (Ingredient ingredient : recipe.getInputItems()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.getOutput());
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.getContainer());
            buffer.writeFloat(recipe.getExperience());
            buffer.writeVarInt(recipe.getCookTime());
        }
    }
}
