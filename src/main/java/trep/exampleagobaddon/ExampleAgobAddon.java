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
	private static final String RELIGION_ID = MOD_ID + ":great_shepherd";
	private static final String KINGDOM_PATH = "dothraki";

	@Override
	public void register(AgobAddonContext context) {
		context.registerTroopResourceDirectory("data/" + MOD_ID + "/agob/troops");
		context.registerQuestTemplateResource("data/" + MOD_ID + "/agob/quests/khalasar_supplies.json");

		Religion greatShepherd = context.registerReligion(RELIGION_ID, "Great Shepherd");
		Kingdom dothraki = new Kingdom(
			"Dothraki",
			Identifier.of(MOD_ID, KINGDOM_PATH),
			greatShepherd,
			Culture.ESSOS
		);
		context.registerKingdom(dothraki, Set.of(
			KingdomCategory.CHARACTER_OPTION,
			KingdomCategory.CASTLELESS_ORIGIN
		));

		context.registerReligionOption(new ReligionSelectionOption(
			RELIGION_ID,
			greatShepherd,
			"Great Shepherd",
			"Faith carried across the grasslands by shepherds, riders, and captives of the eastern plains.",
			Identifier.of(MOD_ID, "textures/gui/religion/great_shepherd.png")
		));

		context.registerCharacterOption(createKhalasarOption(
			dothraki,
			"house_drogo",
			"house_drogo",
			"khal_drogo",
			"Dothraki",
			"Drogo",
			true,
			Map.of(
				SkillAttributeType.CHARISMA, 2,
				SkillAttributeType.RIDING, 2,
				SkillAttributeType.LUCK, 1
			)
		));
		context.registerCharacterOption(createKhalasarOption(
			dothraki,
			"house_jhaqo",
			"house_jhaqo",
			"khal_jhaqo",
			"Dothraki",
			"Jhaqo",
			false,
			Map.of(
				SkillAttributeType.RIDING, 2,
				SkillAttributeType.HEALTH, 1,
				SkillAttributeType.LUCK, 1
			)
		));
		context.registerCharacterOption(createKhalasarOption(
			dothraki,
			"house_pono",
			"house_pono",
			"khal_pono",
			"Dothraki",
			"Pono",
			false,
			Map.of(
				SkillAttributeType.RIDING, 2,
				SkillAttributeType.HEALTH, 1,
				SkillAttributeType.CHARISMA, 1
			)
		));
	}

	private static CharacterSelectionOption createKhalasarOption(
		Kingdom kingdom,
		String optionPath,
		String houseTexturePath,
		String previewTexturePath,
		String displayName,
		String houseDisplayName,
		boolean primary,
		Map<SkillAttributeType, Integer> startingSkillPoints
	) {
		return new CharacterSelectionOption(
			MOD_ID + ":" + optionPath,
			kingdom.getId(),
			Identifier.of(MOD_ID, "textures/gui/houses/" + houseTexturePath + ".png"),
			Identifier.of(MOD_ID, "textures/entity/character/" + previewTexturePath + ".png"),
			false,
			displayName,
			houseDisplayName,
			primary,
			new CharacterEquipmentPreset(
				"",
				"minecraft:leather_chestplate",
				"minecraft:leather_leggings",
				"minecraft:leather_boots",
				"minecraft:bow",
				"minecraft:iron_sword"
			),
			startingSkillPoints,
			Map.of(),
			"",
			List.of(
				"minecraft:savanna",
				"minecraft:plains"
			)
		);
	}
}
