# Adventure of the Goddess Artemis
 Adventure of the Goddess Artemis is a 2D single-player action-adventure game developed as part of the Object-Oriented Application Design course.  In this game, the player controls the protagonist, Goddess Artemis, as she sets out to rescue her twin brother Apollo from the war god Ares. The game is built with Object-Oriented Programming (OOP) principles, and various design patterns. It incorporates combat, exploration of different levels, and interactions with various objects, including enemies and environmental elements.
## Object-Oriented Design: 
The game's architecture is designed using OOP principles to ensure modularity, reusability, and scalability.
## Design Patterns Used: 
**Singleton Pattern:** Ensures that only one instance of critical game objects like the Hero exists at any given time.

**Factory Pattern:** Used to dynamically create game items and enemies (e.g., ItemFactory, EnemyFactory).

**State Pattern:** Manages the various states of the game, such as menu screens and gameplay states.

**Map Overlay Technique:** The game uses an overlay technique for map rendering to create complex environments.
## Game Levels: 
There are 3 different levels, each with unique enemies, maps, and objects:
## Gameplay Mechanics:
**Top-down Perspective:** The game uses a top-down view for player navigation and interaction with the environment.
**Combat System:** Artemis uses a magical wand that throws flames, and she has a protective shield that can block enemy attacks for 3 seconds.

**Environmental Interaction:** Elements like forests, lakes, and rocks act as obstacles and make the game's world more engaging.
## Technologies and Tools:
**Java:** The game is developed in Java using object-oriented principles.

**SQLite:** A simple database is integrated to store key player data, including: ID, Username, Time Played, Score. Additionally, the game has a "Load Game" feature, allowing players to continue from the exact point where they saved, retaining the same amount of coins and health (lives). 

