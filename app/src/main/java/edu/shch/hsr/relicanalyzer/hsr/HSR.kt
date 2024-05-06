package edu.shch.hsr.relicanalyzer.hsr

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import edu.shch.hsr.relicanalyzer.R

@Suppress("SpellCheckingInspection")
enum class Relic(
	@StringRes val text: Int,
	@DrawableRes val head: Int,
	@DrawableRes val hands: Int,
	@DrawableRes val body: Int,
	@DrawableRes val feet: Int
) {
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
enum class Ornament(
	@StringRes val text: Int,
	@DrawableRes val planarSphere: Int,
	@DrawableRes val linkRope: Int
) {
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

