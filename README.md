# Super Chance Monsters

* by Alaina Traxler, Justin Kincaid, Tony Fuentes, Zachary Matthewstearn

## Description

A card-based duel game where each player controls an creature of the animal kingdom. Each creature has its own set of stats (HP, Defense, and Power) along with a special deck of 25 cards. Each deck is composed of 10 cards unique to that specific creature.

Cards fall under one of three main types, attack, defend, and other. Attack and defend cards are further subdivided into three subtypes. Attack cards are either physical, special, or throw actions. Defend cards are either block, shield, or dodge actions.

At the start of a game, each player chooses which of their creatures will battle for them. Their decks are constructed and five of the cards are dealt face up. Players have five-seconds to choose a card to play, while their opponent does the same. After the five-second timer elapses, the chosen cards are flipped over and the turn is resolved.

The game ends when one, or both, of the creatures is reduced to 0 HP.

## Commands

* Player One:   
 * A: Card One
 * S: Card Two
 * D: Card Three
 * F: Card Four
 * G: Card Five

* Player Two:
 * H: Card One
 * J: Card Two
 * K: Card Three
 * L: Card Four
 * ;: Card Five


## Specifications

* Can create new player by adding a player name and creature name.

* Can select which players are battling one another, along with which AVATAR they want to use.

* Each turn a card may be selected by each player. Previous selections in the same turn will be overwritten by the new choice before submission.

* Physical attack cards are blocked by block and dodge defense cards, but do 25% more damage to shield defense cards.

* Special attack cards are blocked by shield and dodge defense cards, but do 25% more damage to block defense cards.

* Throw attack cards are blocked by block and shield defense cards, but do 100% more damage to dodge cards.

* Certain cards may alter the Power and Defense of the monsters on the board for the rest of the battle.



## Setup/Installation Requirements
In PSQL:
CREATE DATABASE superchance_populated;

In Terminal:
psql superchance_populated < superchance_populated

## Github Link
  * https://github.com/TeamAlodia/superchance/

## Known Bugs
There are currently no known issues.

## Technologies Used
Java, SQL, Spark, and Javascript.
## License
  * This application is published under the MIT License.

  * Copyright (c) 2016 Alaina Traxler, Justin Kincaid, Tony Fuentes, Zachary Matthewstearn
