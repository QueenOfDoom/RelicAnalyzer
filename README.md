# Relic Analyzer (*Draft Name*)

## Installation Instructions
Right now, this is not installable unless you compile it yourself
or ask the dev (@QueenOfDoom) for the binary (APK).

As soon as this app matures and includes features like an auto-update,
yours truly will start to create actual releases.
Right now this can be considered in **very early** alpha.

## Resources
Resources are retrieved from various sites:
- [Relics & Ornaments](https://www.mobilemeta.gg/honkai-starrail/database/relic)
- [Relic Grouping (Caverns of Corrosion)](https://honkai-star-rail.fandom.com/wiki/Cavern_of_Corrosion)
- [Ornament Grouping (Simulated Universe)](https://honkai-star-rail.fandom.com/wiki/Simulated_Universe/Worlds)
- [Characters & Builds](https://www.prydwen.gg/star-rail/characters/) [[JSON Dump](https://www.prydwen.gg/page-data/star-rail/characters/page-data.json)]
  - [Individual Character (i.e. Acheron) JSON Dump](https://www.prydwen.gg/page-data/star-rail/characters/acheron/page-data.json)
- [Light Cones](https://www.prydwen.gg/star-rail/light-cones) [[JSON Dump](https://www.prydwen.gg/page-data/star-rail/light-cones/page-data.json)]
  - [Individual Images (i.e. Boundless Choreo)](https://honkai-star-rail.fandom.com/wiki/Boundless_Choreo)

The above resources are mined by the Gradle `scrapeResources` task and thus cannot
be found in the repository.

What can be found in the repository are images that are not updated regularly and thus
are 'always up-to-date'.
That includes:
- [Types (referred to as 'Elements' in this project)](https://honkai-star-rail.fandom.com/wiki/Type)
- [Paths](https://honkai-star-rail.fandom.com/wiki/Path)
- [Stat Icons](https://act.hoyolab.com/app/community-game-records-sea/rpg/index.html) (Note: you may have to log into HoyoLab to see this page)
- [WIP Gif](https://github.com/duiqt/herta_kuru/blob/59f40822ad8862d2a1487a41a27d32bb538d5326/static/img/hertaa_github.gif)

## Fonts
- [Saiba Font](https://github.com/YuurinBee/SAIBA-45)

## Color Variables
This section describes the color palette, the corresponding
Material UI identifiers and the implications of a change
in any particular color.

By default all 6-digit hex colors assume a transparency of 0xFF.

| Material Color Identifier | Implications                                                              | Personal Identifier | Color Code |
|---------------------------|---------------------------------------------------------------------------|---------------------|------------|
| primary                   | primary font color & background-aspect color for all prefab UI components | Purple80            | 0xD0BCFF   |
|                           |                                                                           |                     |            |
|                           |                                                                           |                     |            |
|                           |                                                                           |                     |            |
|                           |                                                                           |                     |            |


## Credits
Graphics: **猫**
Dev: Yours Truly