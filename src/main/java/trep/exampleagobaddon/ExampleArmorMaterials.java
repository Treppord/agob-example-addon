package trep.exampleagobaddon;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public final class ExampleArmorMaterials {
	public static final RegistryEntry<ArmorMaterial> DOTHRAKI = register("dothraki_armor", new ArmorMaterial(
		defense(2, 4, 5, 0),
		12,
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
		() -> Ingredient.ofItems(Items.LEATHER, Items.IRON_INGOT),
		List.of(new ArmorMaterial.Layer(Identifier.of(ExampleAgobAddon.MOD_ID, "dothraki_armor"))),
		0.0F,
		0.0F
	));

	private ExampleArmorMaterials() {
	}

	public static void initialize() {
	}

	private static Map<ArmorItem.Type, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
		Map<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
		defense.put(ArmorItem.Type.BOOTS, boots);
		defense.put(ArmorItem.Type.LEGGINGS, leggings);
		defense.put(ArmorItem.Type.CHESTPLATE, chestplate);
		defense.put(ArmorItem.Type.HELMET, helmet);
		defense.put(ArmorItem.Type.BODY, chestplate);
		return defense;
	}

	private static RegistryEntry<ArmorMaterial> register(String path, ArmorMaterial material) {
		return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(ExampleAgobAddon.MOD_ID, path), material);
	}
}
