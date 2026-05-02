package trep.exampleagobaddon;

import net.minecraft.util.Identifier;
import trep.got.api.AgobAddonContext;
import trep.got.api.AgobAddonEntrypoint;
import trep.got.api.kingdom.KingdomCategory;
import trep.got.character.CharacterEquipmentPreset;
import trep.got.character.CharacterSelectionOption;
import trep.got.kingdom.Culture;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.Religion;
import trep.got.religion.ReligionSelectionOption;
import trep.got.skill.SkillAttributeType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ExampleAgobAddon implements AgobAddonEntrypoint {
	public static final String MOD_ID = "exampleagobaddon";
	private static final String RELIGION_ID = MOD_ID + ":sea_of_stars";
	private static final String KINGDOM_PATH = "starhaven";
	private static final String CHARACTER_OPTION_ID = MOD_ID + ":house_tidecaller";

	@Override
	public void register(AgobAddonContext context) {
		context.registerTroopResourceDirectory("data/" + MOD_ID + "/agob/troops");
		context.registerQuestTemplateResource("data/" + MOD_ID + "/agob/quests/starforge_supplies.json");

		Religion seaOfStars = context.registerReligion(RELIGION_ID, "Sea of Stars");
		Kingdom starhaven = new Kingdom(
			"Starhaven",
			Identifier.of(MOD_ID, KINGDOM_PATH),
			seaOfStars,
			Culture.ESSOS
		);
		context.registerKingdom(starhaven, Set.of(
			KingdomCategory.CHARACTER_OPTION,
			KingdomCategory.CASTLELESS_ORIGIN
		));

		context.registerReligionOption(new ReligionSelectionOption(
			RELIGION_ID,
			seaOfStars,
			"Sea of Stars",
			"Navigator-priests who read fate in the tides and constellations.",
			Identifier.of(MOD_ID, "textures/gui/religion/sea_of_stars.png")
		));

		context.registerCharacterOption(new CharacterSelectionOption(
			CHARACTER_OPTION_ID,
			starhaven.getId(),
			Identifier.of(MOD_ID, "textures/gui/houses/house_tidecaller.png"),
			Identifier.of(MOD_ID, "textures/entity/character/tidecaller_founder.png"),
			false,
			"Starhaven",
			"House Tidecaller",
			true,
			new CharacterEquipmentPreset(
				"minecraft:chainmail_helmet",
				"minecraft:leather_chestplate",
				"minecraft:leather_leggings",
				"minecraft:leather_boots",
				"minecraft:iron_sword",
				"minecraft:shield"
			),
			Map.of(
				SkillAttributeType.CHARISMA, 2,
				SkillAttributeType.INTELLIGENCE, 2,
				SkillAttributeType.TRADING, 1
			),
			Map.of(),
			"",
			List.of(
				"minecraft:beach",
				"minecraft:stony_shore"
			)
		));
	}
}
