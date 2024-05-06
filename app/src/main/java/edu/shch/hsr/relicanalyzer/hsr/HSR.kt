package edu.shch.hsr.relicanalyzer.hsr

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import edu.shch.hsr.relicanalyzer.R

@Suppress("SpellCheckingInspection")
enum class Relic (
	@StringRes val text: Int,
	@DrawableRes val head: Int,
	@DrawableRes val hands: Int,
	@DrawableRes val body: Int,
	@DrawableRes val feet: Int
) : GenericRelic {
	PASSERBY_OF_WANDERING_CLOUD(R.string.relic_101, R.drawable.relic_101_0, R.drawable.relic_101_1, R.drawable.relic_101_2, R.drawable.relic_101_3),
	MUSKETEER_OF_WILD_WHEAT(R.string.relic_102, R.drawable.relic_102_0, R.drawable.relic_102_1, R.drawable.relic_102_2, R.drawable.relic_102_3),
	KNIGHT_OF_PURITY_PALACE(R.string.relic_103, R.drawable.relic_103_0, R.drawable.relic_103_1, R.drawable.relic_103_2, R.drawable.relic_103_3),
	HUNTER_OF_GLACIAL_FOREST(R.string.relic_104, R.drawable.relic_104_0, R.drawable.relic_104_1, R.drawable.relic_104_2, R.drawable.relic_104_3),
	CHAMPION_OF_STREETWISE_BOXING(R.string.relic_105, R.drawable.relic_105_0, R.drawable.relic_105_1, R.drawable.relic_105_2, R.drawable.relic_105_3),
	GUARD_OF_WUTHERING_SNOW(R.string.relic_106, R.drawable.relic_106_0, R.drawable.relic_106_1, R.drawable.relic_106_2, R.drawable.relic_106_3),
	FIRESMITH_OF_LAVA_FORGING(R.string.relic_107, R.drawable.relic_107_0, R.drawable.relic_107_1, R.drawable.relic_107_2, R.drawable.relic_107_3),
	GENIUS_OF_BRILLIANT_STARS(R.string.relic_108, R.drawable.relic_108_0, R.drawable.relic_108_1, R.drawable.relic_108_2, R.drawable.relic_108_3),
	BAND_OF_SIZZLING_THUNDER(R.string.relic_109, R.drawable.relic_109_0, R.drawable.relic_109_1, R.drawable.relic_109_2, R.drawable.relic_109_3),
	EAGLE_OF_TWILIGHT_LINE(R.string.relic_110, R.drawable.relic_110_0, R.drawable.relic_110_1, R.drawable.relic_110_2, R.drawable.relic_110_3),
	THIEF_OF_SHOOTING_METEOR(R.string.relic_111, R.drawable.relic_111_0, R.drawable.relic_111_1, R.drawable.relic_111_2, R.drawable.relic_111_3),
	WASTELANDER_OF_BANDITRY_DESERT(R.string.relic_112, R.drawable.relic_112_0, R.drawable.relic_112_1, R.drawable.relic_112_2, R.drawable.relic_112_3),
	LONGEVOUS_DISCIPLE(R.string.relic_113, R.drawable.relic_113_0, R.drawable.relic_113_1, R.drawable.relic_113_2, R.drawable.relic_113_3),
	MESSENGER_TRAVERSING_HACKERSPACE(R.string.relic_114, R.drawable.relic_114_0, R.drawable.relic_114_1, R.drawable.relic_114_2, R.drawable.relic_114_3),
	THE_ASHBLAZING_GRAND_DUKE(R.string.relic_115, R.drawable.relic_115_0, R.drawable.relic_115_1, R.drawable.relic_115_2, R.drawable.relic_115_3),
	PRISONER_IN_DEEP_CONFINEMENT(R.string.relic_116, R.drawable.relic_116_0, R.drawable.relic_116_1, R.drawable.relic_116_2, R.drawable.relic_116_3),
	PIONEER_DIVER_OF_DEAD_WATERS(R.string.relic_117, R.drawable.relic_117_0, R.drawable.relic_117_1, R.drawable.relic_117_2, R.drawable.relic_117_3),
	WATCHMAKER_MASTER_OF_DREAM_MACHINATIONS(R.string.relic_118, R.drawable.relic_118_0, R.drawable.relic_118_1, R.drawable.relic_118_2, R.drawable.relic_118_3),
}

@Suppress("SpellCheckingInspection")
enum class Ornament (
	@StringRes val text: Int,
	@DrawableRes val planarSphere: Int,
	@DrawableRes val linkRope: Int
) : GenericRelic {
	SPACE_SEALING_STATION(R.string.ornament_301, R.drawable.ornament_301_0, R.drawable.ornament_301_1),
	FLEET_OF_THE_AGELESS(R.string.ornament_302, R.drawable.ornament_302_0, R.drawable.ornament_302_1),
	PAN_COSMIC_COMMERCIAL_ENTERPRISE(R.string.ornament_303, R.drawable.ornament_303_0, R.drawable.ornament_303_1),
	BELOBOG_OF_THE_ARCHITECTS(R.string.ornament_304, R.drawable.ornament_304_0, R.drawable.ornament_304_1),
	CELESTIAL_DIFFERENTIATOR(R.string.ornament_305, R.drawable.ornament_305_0, R.drawable.ornament_305_1),
	INERT_SALSOTTO(R.string.ornament_306, R.drawable.ornament_306_0, R.drawable.ornament_306_1),
	TALIA_KINGDOM_OF_BANDITRY(R.string.ornament_307, R.drawable.ornament_307_0, R.drawable.ornament_307_1),
	SPRIGHTLY_VONWACQ(R.string.ornament_308, R.drawable.ornament_308_0, R.drawable.ornament_308_1),
	RUTILANT_ARENA(R.string.ornament_309, R.drawable.ornament_309_0, R.drawable.ornament_309_1),
	BROKEN_KEEL(R.string.ornament_310, R.drawable.ornament_310_0, R.drawable.ornament_310_1),
	FIRMAMENT_FRONTLINE_GLAMOTH(R.string.ornament_311, R.drawable.ornament_311_0, R.drawable.ornament_311_1),
	PENACONY_LAND_OF_THE_DREAMS(R.string.ornament_312, R.drawable.ornament_312_0, R.drawable.ornament_312_1),
	SIGONIA_THE_UNCLAIMED_DESOLATION(R.string.ornament_313, R.drawable.ornament_313_0, R.drawable.ornament_313_1),
	IZUMO_GENSEI_AND_TAKAMA_DIVINE_REALM(R.string.ornament_314, R.drawable.ornament_314_0, R.drawable.ornament_314_1),
}

@Suppress("SpellCheckingInspection")
enum class CavernOfCorrosion(vararg val sets: Relic) {
	PATH_OF_GELID_WIND(Relic.HUNTER_OF_GLACIAL_FOREST, Relic.EAGLE_OF_TWILIGHT_LINE),
	PATH_OF_JABBING_PUNCH(Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.THIEF_OF_SHOOTING_METEOR),
	PATH_OF_DRIFTING(Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.MUSKETEER_OF_WILD_WHEAT),
	PATH_OF_PROVIDENCE(Relic.GUARD_OF_WUTHERING_SNOW, Relic.GENIUS_OF_BRILLIANT_STARS),
	PATH_OF_HOLY_HYMN(Relic.KNIGHT_OF_PURITY_PALACE, Relic.BAND_OF_SIZZLING_THUNDER),
	PATH_OF_CONFLAGRATION(Relic.FIRESMITH_OF_LAVA_FORGING, Relic.WASTELANDER_OF_BANDITRY_DESERT),
	PATH_OF_ELIXIR_SEEKERS(Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE),
	PATH_OF_DARKNESS(Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT),
	PATH_OF_DREAMDIVE(Relic.PIONEER_DIVER_OF_DEAD_WATERS, Relic.WATCHMAKER_MASTER_OF_DREAM_MACHINATIONS),
}

enum class SimulatedUniverse(vararg val sets: Ornament) {
	WORLD_0(Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS),
	WORLD_1(Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.SPRIGHTLY_VONWACQ),
	WORLD_2(Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, Ornament.CELESTIAL_DIFFERENTIATOR),
	WORLD_3(Ornament.BELOBOG_OF_THE_ARCHITECTS, Ornament.INERT_SALSOTTO),
	WORLD_4(Ornament.RUTILANT_ARENA, Ornament.BROKEN_KEEL),
	WORLD_5(Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, Ornament.PENACONY_LAND_OF_THE_DREAMS),
	WORLD_6(Ornament.SIGONIA_THE_UNCLAIMED_DESOLATION, Ornament.IZUMO_GENSEI_AND_TAKAMA_DIVINE_REALM),
}

@Suppress("SpellCheckingInspection")
enum class Character(
	@StringRes val characterName: Int,
	@DrawableRes val img: Int,
	vararg val preferredRelics: GenericRelic
) {
	AVENTURINE(R.string.character_aventurine, R.drawable.character_aventurine, ),
	ACHERON(R.string.character_acheron, R.drawable.character_acheron, ),
	BLACK_SWAN(R.string.character_black_swan, R.drawable.character_black_swan, ),
	SPARKLE(R.string.character_sparkle, R.drawable.character_sparkle, ),
	MISHA(R.string.character_misha, R.drawable.character_misha, ),
	RUAN_MEI(R.string.character_ruan_mei, R.drawable.character_ruan_mei, ),
	DR_RATIO(R.string.character_dr_ratio, R.drawable.character_dr_ratio, ),
	XUEYI(R.string.character_xueyi, R.drawable.character_xueyi, ),
	GALLAGHER(R.string.character_gallagher, R.drawable.character_gallagher, ),
	ARGENTI(R.string.character_argenti, R.drawable.character_argenti, ),
	ARLAN(R.string.character_arlan, R.drawable.character_arlan, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.BAND_OF_SIZZLING_THUNDER, Relic.LONGEVOUS_DISCIPLE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.RUTILANT_ARENA, ),
	ASTA(R.string.character_asta, R.drawable.character_asta, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.FIRESMITH_OF_LAVA_FORGING, Relic.THIEF_OF_SHOOTING_METEOR, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	BAILU(R.string.character_bailu, R.drawable.character_bailu, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	BLADE(R.string.character_blade, R.drawable.character_blade, Relic.EAGLE_OF_TWILIGHT_LINE, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, ),
	BRONYA(R.string.character_bronya, R.drawable.character_bronya, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.EAGLE_OF_TWILIGHT_LINE, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	CLARA(R.string.character_clara, R.drawable.character_clara, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.LONGEVOUS_DISCIPLE, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, ),
	DAN_HENG_IL(R.string.character_dan_heng_il, R.drawable.character_dan_heng_il, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.WASTELANDER_OF_BANDITRY_DESERT, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, ),
	DAN_HENG(R.string.character_dan_heng, R.drawable.character_dan_heng, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.GENIUS_OF_BRILLIANT_STARS, Relic.EAGLE_OF_TWILIGHT_LINE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
	FU_XUAN(R.string.character_fu_xuan, R.drawable.character_fu_xuan, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.GUARD_OF_WUTHERING_SNOW, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	GEPARD(R.string.character_gepard, R.drawable.character_gepard, Relic.KNIGHT_OF_PURITY_PALACE, Relic.GUARD_OF_WUTHERING_SNOW, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.BELOBOG_OF_THE_ARCHITECTS, Ornament.SPRIGHTLY_VONWACQ, ),
	GUINAIFEN(R.string.character_guinaifen, R.drawable.character_guinaifen, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.FIRESMITH_OF_LAVA_FORGING, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, ),
	HANYA(R.string.character_hanya, R.drawable.character_hanya, ),
	HERTA(R.string.character_herta, R.drawable.character_herta, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.HUNTER_OF_GLACIAL_FOREST, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, ),
	HIMEKO(R.string.character_himeko, R.drawable.character_himeko, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.FIRESMITH_OF_LAVA_FORGING, Relic.THIEF_OF_SHOOTING_METEOR, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, ),
	HOOK(R.string.character_hook, R.drawable.character_hook, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.FIRESMITH_OF_LAVA_FORGING, Relic.THIEF_OF_SHOOTING_METEOR, Relic.PRISONER_IN_DEEP_CONFINEMENT, Relic.PIONEER_DIVER_OF_DEAD_WATERS, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, ),
	HUO_HUO(R.string.character_huo_huo, R.drawable.character_huo_huo, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	JING_YUAN(R.string.character_jing_yuan, R.drawable.character_jing_yuan, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.BAND_OF_SIZZLING_THUNDER, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.TALIA_KINGDOM_OF_BANDITRY, ),
	JINGLIU(R.string.character_jingliu, R.drawable.character_jingliu, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.HUNTER_OF_GLACIAL_FOREST, Relic.GENIUS_OF_BRILLIANT_STARS, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.SPACE_SEALING_STATION, Ornament.RUTILANT_ARENA, ),
	KAFKA(R.string.character_kafka, R.drawable.character_kafka, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.BAND_OF_SIZZLING_THUNDER, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
	LUKA(R.string.character_luka, R.drawable.character_luka, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, ),
	LUOCHA(R.string.character_luocha, R.drawable.character_luocha, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	LYNX(R.string.character_lynx, R.drawable.character_lynx, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	MARCH_7TH(R.string.character_march_7th, R.drawable.character_march_7th, Relic.KNIGHT_OF_PURITY_PALACE, Relic.GUARD_OF_WUTHERING_SNOW, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.BELOBOG_OF_THE_ARCHITECTS, Ornament.SPRIGHTLY_VONWACQ, ),
	NATASHA(R.string.character_natasha, R.drawable.character_natasha, Relic.PASSERBY_OF_WANDERING_CLOUD, Relic.GUARD_OF_WUTHERING_SNOW, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	PELA(R.string.character_pela, R.drawable.character_pela, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.HUNTER_OF_GLACIAL_FOREST, Relic.GUARD_OF_WUTHERING_SNOW, Relic.EAGLE_OF_TWILIGHT_LINE, Relic.LONGEVOUS_DISCIPLE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.INERT_SALSOTTO, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, Ornament.IZUMO_GENSEI_AND_TAKAMA_DIVINE_REALM, ),
	QINGQUE(R.string.character_qingque, R.drawable.character_qingque, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.GENIUS_OF_BRILLIANT_STARS, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, ),
	SAMPO(R.string.character_sampo, R.drawable.character_sampo, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.EAGLE_OF_TWILIGHT_LINE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.SPRIGHTLY_VONWACQ, Ornament.RUTILANT_ARENA, ),
	SEELE(R.string.character_seele, R.drawable.character_seele, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.GENIUS_OF_BRILLIANT_STARS, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
	SERVAL(R.string.character_serval, R.drawable.character_serval, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.BAND_OF_SIZZLING_THUNDER, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, Ornament.SIGONIA_THE_UNCLAIMED_DESOLATION, ),
	SILVER_WOLF(R.string.character_silver_wolf, R.drawable.character_silver_wolf, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.GENIUS_OF_BRILLIANT_STARS, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, Ornament.INERT_SALSOTTO, Ornament.SPRIGHTLY_VONWACQ, Ornament.PENACONY_LAND_OF_THE_DREAMS, Ornament.IZUMO_GENSEI_AND_TAKAMA_DIVINE_REALM, ),
	SUSHANG(R.string.character_sushang, R.drawable.character_sushang, Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.THIEF_OF_SHOOTING_METEOR, Ornament.SPACE_SEALING_STATION, Ornament.INERT_SALSOTTO, Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.RUTILANT_ARENA, Ornament.BROKEN_KEEL, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
	TINGYUN(R.string.character_tingyun, R.drawable.character_tingyun, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.BAND_OF_SIZZLING_THUNDER, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.FLEET_OF_THE_AGELESS, Ornament.SPRIGHTLY_VONWACQ, Ornament.BROKEN_KEEL, Ornament.PENACONY_LAND_OF_THE_DREAMS, ),
	TOPAZ(R.string.character_topaz, R.drawable.character_topaz, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.FIRESMITH_OF_LAVA_FORGING, Relic.THE_ASHBLAZING_GRAND_DUKE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.INERT_SALSOTTO, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
	TRAILBLAZER_FIRE(R.string.character_trailblazer_fire, R.drawable.character_trailblazer_fire, Relic.KNIGHT_OF_PURITY_PALACE, Relic.KNIGHT_OF_PURITY_PALACE, Relic.GUARD_OF_WUTHERING_SNOW, Relic.GUARD_OF_WUTHERING_SNOW, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Ornament.FLEET_OF_THE_AGELESS, Ornament.FLEET_OF_THE_AGELESS, Ornament.BELOBOG_OF_THE_ARCHITECTS, Ornament.BELOBOG_OF_THE_ARCHITECTS, Ornament.SPRIGHTLY_VONWACQ, Ornament.SPRIGHTLY_VONWACQ, ),
	TRAILBLAZER_PHYSICAL(R.string.character_trailblazer_physical, R.drawable.character_trailblazer_physical, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.CHAMPION_OF_STREETWISE_BOXING, Relic.GUARD_OF_WUTHERING_SNOW, Relic.GUARD_OF_WUTHERING_SNOW, Relic.PRISONER_IN_DEEP_CONFINEMENT, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.INERT_SALSOTTO, Ornament.RUTILANT_ARENA, Ornament.RUTILANT_ARENA, ),
	WELT(R.string.character_welt, R.drawable.character_welt, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.THIEF_OF_SHOOTING_METEOR, Relic.WASTELANDER_OF_BANDITRY_DESERT, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.PAN_COSMIC_COMMERCIAL_ENTERPRISE, Ornament.INERT_SALSOTTO, Ornament.SPRIGHTLY_VONWACQ, Ornament.RUTILANT_ARENA, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, Ornament.SIGONIA_THE_UNCLAIMED_DESOLATION, Ornament.IZUMO_GENSEI_AND_TAKAMA_DIVINE_REALM, ),
	YANQING(R.string.character_yanqing, R.drawable.character_yanqing, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.HUNTER_OF_GLACIAL_FOREST, Relic.THIEF_OF_SHOOTING_METEOR, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.CELESTIAL_DIFFERENTIATOR, Ornament.INERT_SALSOTTO, Ornament.TALIA_KINGDOM_OF_BANDITRY, ),
	YUKONG(R.string.character_yukong, R.drawable.character_yukong, Relic.MUSKETEER_OF_WILD_WHEAT, Relic.MESSENGER_TRAVERSING_HACKERSPACE, Relic.PRISONER_IN_DEEP_CONFINEMENT, Ornament.SPACE_SEALING_STATION, Ornament.FLEET_OF_THE_AGELESS, Ornament.TALIA_KINGDOM_OF_BANDITRY, Ornament.SPRIGHTLY_VONWACQ, Ornament.FIRMAMENT_FRONTLINE_GLAMOTH, ),
}

