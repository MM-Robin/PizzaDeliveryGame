<div align="center">

# Pizza Delivery Game

**Java · Swing GUI · OOP · Text-Based Adventure**

[![Java](https://img.shields.io/badge/Java-OOP-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-5C2D91?style=flat-square)]()
[![BlueJ](https://img.shields.io/badge/IDE-BlueJ-0078D4?style=flat-square)]()
[![HAW Hamburg](https://img.shields.io/badge/HAW-Hamburg-004F9F?style=flat-square)]()

</div>

---

## Overview

A **text-based adventure game with a Java Swing GUI**, built as part of a Software Engineering course at **HAW Hamburg**. The player takes on the role of a pizza delivery boy — navigating through rooms and streets to find the correct destination and deliver the pizza before time runs out.

Based on the *Objects First with Java* (Barnes & Kölling) adventure game framework, extended with a custom GUI, player model, inventory system, and game history.

---

## Gameplay

- Navigate a map of interconnected rooms (kitchen, street blocks A–E, streets)
- Pick up the pizza and find the correct delivery destination
- Use directional buttons in the GUI to move between rooms
- Each room displays a description and image
- Game ends when the pizza is successfully delivered — or you get lost

---

## Project Structure

```
PizzaDeliveryGame/
│
├── Game.java            # Core game logic & room initialization
├── Environement.java    # Map layout — rooms and connections
├── Room.java            # Room entity with exits and image
├── Player.java          # Player state — location, inventory
├── Item.java            # Item model (pizza)
├── Parser.java          # Command input parser
├── Command.java         # Command model
├── CommandWords.java     # Valid command definitions
├── Playable.java        # Game interface
├── UI.java              # UI controller
├── GUI.java             # Swing GUI — buttons, panels, room images
├── GameHistory.java     # Session history tracking
└── robin.txt            # Dev notes
```

---

## Object-Oriented Design

| Class | Responsibility |
|---|---|
| `Game` | Orchestrates game state, room transitions, win condition |
| `Environement` | Builds and connects the room graph |
| `Room` | Node in the room graph — exits, description, image path |
| `Player` | Tracks current location and inventory |
| `GUI` | Swing interface — displays room info, navigation buttons |
| `Parser` | Parses text commands from UI input |
| `GameHistory` | Records visited rooms and actions |

---

## How to Run

**Prerequisites:** Java 8+, BlueJ or any Java IDE

### BlueJ
1. Open the project folder in **BlueJ**
2. Right-click `Game` → `new Game("YourName")`
3. Call the `play()` method on the created instance

### Command Line
```bash
javac *.java
java Game
```

---

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| GUI | Java Swing |
| IDE | BlueJ |
| Paradigm | Object-Oriented Programming |

---

## Author

<div align="center">

**Mainuddin Monsur Robin**
*M.Sc. Information and Communication Engineering — HAW Hamburg*

[![GitHub](https://img.shields.io/badge/GitHub-MM--Robin-181717?style=flat-square&logo=github)](https://github.com/MM-Robin)

</div>
