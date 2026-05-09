package trep.exampleagobaddon;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import trep.got.item.BrewItem;
import trep.got.registry.GotItems;

public final class ExampleItems {
	public static final Item PLATE_DOTHRAKI = register(
		"plate_dothraki",
		new Item(new Item.Settings())
	);
	public static final ArmorItem DOTHRAKI_CHESTPLATE = register(
		"dothraki_chestplate",
		new ArmorItem(ExampleArmorMaterials.DOTHRAKI, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1))
	);
	public static final ArmorItem DOTHRAKI_LEGGINGS = register(
		"dothraki_leggings",
		new ArmorItem(ExampleArmorMaterials.DOTHRAKI, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1))
	);
	public static final ArmorItem DOTHRAKI_BOOTS = register(
		"dothraki_boots",
		new ArmorItem(ExampleArmorMaterials.DOTHRAKI, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1))
	);
	public static final Item GOAT_MILK = register(
		"goat_milk",
		new BrewItem(new Item.Settings().maxCount(1), brewFood(3, 0.5F, new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0)), GotItems.EMPTY_CUP, BrewItem.itemLoreLine("Absorption for (2:00)"))
	);

	private static FoodComponent brewFood(int nutrition, float saturation, StatusEffectInstance effect) {
		return new FoodComponent.Builder()
			.nutrition(nutrition)
			.saturationModifier(saturation)
			.alwaysEdible()
			.statusEffect(effect, 1.0F)
			.build();
	}

	private ExampleItems() {
	}

	public static void initialize() {
		ExampleArmorMaterials.initialize();
	}

	private static <T extends Item> T register(String path, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(ExampleAgobAddon.MOD_ID, path), item);
	}
}
