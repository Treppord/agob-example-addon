package trep.exampleagobaddon;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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

	private ExampleItems() {
	}

	public static void initialize() {
		ExampleArmorMaterials.initialize();
	}

	private static <T extends Item> T register(String path, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(ExampleAgobAddon.MOD_ID, path), item);
	}
}
