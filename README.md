# mario-s1-2022

# Requirement 4

**Title**:
_Random Populator_

**Description**:
_Random Populator is a feature that allows the game to randomly
populate any gamemap with any of the three main game object types (i.e. Actors, Items, Grouds).
It can be called to do so at the start of the game, or ay any time, such as when reset a reset occurs.
The three variants (child classes) of the random populator class are GroundRandomPopulator, ActorRandomPopulator, and ItemRandomPopulator 
(This is mainly done due to the three game objects, which are actors, grounds, and items, extending different types in engine even though they all can be placed on the game map).

Note: There is no override/interface that specifies each Populator's populate() auxiliary method, as method's overriden from parent class/ implemented from interface needs
to have same argument signature. However, populate() auxiliary method's forth parameter's type changes based on what type of game object is be populated to the gamemap (It could be Ground ground, Actor actor, or Item item, or any new Game object type)._

**Explanation why it adheres to SOLID principles** (WHY):
Firstly, Random Populator does adheres to the Single Responsibility Principle, as each variant (child class) of Random Populator is in charge of randomly populating the map with it's respective game object type, rather than random populator itself handling all 3.
Secondly,it also adheres to the Open-close Principle, as the RandomPopulator parent class is open for extensions for other types of random populator. For Example, if there is a forth game object type, Heirlooms, that need to randomly appear on the map. A HeriloomRandomPopulator can be easily made by extending Random Populator parent class.
Thirdly, it adheres to the Liskov Substitution Principle: An instance of the a random populator variant (child class) is called in place of the Random Populator parent class, which is abstract. Thus, the functionality of RandomPopulator is preserved by it's variants.
Forthly, it also adheres to the Interface Segregation Principle, as the parent and child classes of random populator does not implement interfaces that are not required by the class.
Random Populator also adheres to the Dependency Inversion Principle, as none of it's concrete class depends on another concrete class. All concrete classes of Random Populator are child classes that extend an abstract class.

-

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                                                                                                                                                                                                                                                          |
| ----------------------------------------------------------------------------------------------------------------------- |--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | RandomPopulator primarily makes used of two engine classes, which are GameMap and Location, via simple dependency relationships in order to identify locations on the gamemap which are suitable for population. Other than that, it's child classes also takes in Actors, Ground, or Items as arguments to populate the map with.                                                               |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | RandomPopulator is an extension of the random tree populator from Assignment 2's REQ 1 (where trees must be randomly initialized on the map), and extends Assignment 3's REQ 1 (where warp pipes must be randomly initialized on the map) too.  It has been upgraded to be able to now randomly populate not just REQ1's trees, but any of the three game object types (grounds, actors, items). |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | RandomPopulator creates new abstractions as requires variants for each game object (grounds, actors, etc.). Thus, it is a parent class which can be extended by child classes which are random populators for a specific type. For example, GroundRandomPopulator extends RandomPopulator, and specializes in randomly populating a map with any given ground.                                   |
| Must use existing or create new capabilities                                                                            | The findPopulateLocations() method in RandomPopulator parent class makes use of Ground's FERTILE capability to detect if a ground is suitable to be populated.                                                                                                                                                                                                                                   |