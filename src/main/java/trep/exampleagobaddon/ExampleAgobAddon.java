package trep.exampleagobaddon;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

public final class ExampleAgobAddon implements AgobAddonEntrypoint {
	public static final String MOD_ID = "exampleagobaddon";
	private static final String RELIGION_ID = MOD_ID + ":great_shepherd";
	private static final String KINGDOM_PATH = "dothraki";
	private static final List<String> DOTHRAKI_SPAWN_BIOMES = List.of(
		"dothraki_sea",
		"dothraki_sea_steppe"
	);

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
			"Khal Drogo",
			true,
			Map.of(
				SkillAttributeType.HEALTH, 14,
				SkillAttributeType.INTELLIGENCE, 2,
				SkillAttributeType.CHARISMA, 17,
				SkillAttributeType.LUCK, 10,
				SkillAttributeType.TRADING, 5,
				SkillAttributeType.SMITHING, 5,
				SkillAttributeType.RIDING, 25
			),
			Map.of(
				SkillAttributeType.CHARISMA, 10,
				SkillAttributeType.RIDING, 10
			)
		));
		context.registerCharacterOption(createKhalasarOption(
			dothraki,
			"house_jhaqo",
			"house_jhaqo",
			"khal_jhaqo",
			"Dothraki",
			"Khal Jhaqo",
			false,
			Map.of(
				SkillAttributeType.HEALTH, 15,
				SkillAttributeType.INTELLIGENCE, 1,
				SkillAttributeType.CHARISMA, 13,
				SkillAttributeType.LUCK, 11,
				SkillAttributeType.TRADING, 5,
				SkillAttributeType.SMITHING, 5,
				SkillAttributeType.RIDING, 25
			),
			Map.of(
				SkillAttributeType.HEALTH, 10,
				SkillAttributeType.RIDING, 10
			)
		));
		context.registerCharacterOption(createKhalasarOption(
			dothraki,
			"house_pono",
			"house_pono",
			"khal_pono",
			"Dothraki",
			"Khal Pono",
			false,
			Map.of(
				SkillAttributeType.HEALTH, 13,
				SkillAttributeType.INTELLIGENCE, 8,
				SkillAttributeType.CHARISMA, 14,
				SkillAttributeType.LUCK, 0,
				SkillAttributeType.TRADING, 6,
				SkillAttributeType.SMITHING, 5,
				SkillAttributeType.RIDING, 25
			),
			Map.of(
				SkillAttributeType.CHARISMA, 10,
				SkillAttributeType.RIDING, 10
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
		,
		Map<SkillAttributeType, Integer> maxSkillCapBonuses
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
				"minecraft:chainmail_leggings",
				"minecraft:chainmail_boots",
				"minecraft:bow",
				"minecraft:iron_sword"
			),
			startingSkillPoints,
			maxSkillCapBonuses,
			"",
			DOTHRAKI_SPAWN_BIOMES
		);
	}
}
