# Elevating IQ

A Java-based arcade game project built with Swing. The game features multiple difficulty levels, a menu system, audio, profile/login screens, and a leaderboard experience.

## Overview

Elevating IQ is a desktop game where players progress through different levels and difficulties. The project includes:

- Easy, Hard, and Expert modes
- Main menu and gameplay screens
- Login/sign-up flow
- Audio and settings support
- Leaderboard/profile integration

## About the Project

Elevating IQ is an educational desktop game project created to explore how a complete game system can be designed and built using Java. Its purpose is to give players a simple but engaging experience while also helping developers practice core programming and game development skills.

This is an academic project designed for learning and demonstration. It shows how a game can be built using Java, object-oriented programming, GUI development, and game-state management in a practical way.

The project combines gameplay, user interaction, sound, difficulty levels, and a basic profile/leaderboard system in one application. It is especially useful as an academic project because it demonstrates how different software components can be organized into a working system.

## Credits and Resources

All resources used in this project are respectfully credited to the owner. We sincerely acknowledge the owner for the assets, materials, and support used in creating this project.


## How the System Works

The game is built as a small game system with different screens and states. When the program starts, it opens the main game window and moves through several stages such as:

1. Login or sign-up screen
2. Main menu
3. Difficulty selection
4. Gameplay screen
5. Pause, game over, or level-complete overlays
6. Leaderboard or profile sections

The system uses a state-based approach, which means the game knows what screen or mode it is currently in and updates the correct part of the program accordingly.

## About the Code

The code is organized into different packages to keep the project easier to understand:

- `src/stacking_rectangle/` contains the main game class and the main window setup
- `src/gamestates/` handles the different game states and transitions between screens
- `src/entities/` contains game objects such as the player and enemies
- `src/ui/` contains buttons, menus, and overlays
- `src/Easy/`, `src/Hard/`, and `src/Expert/` contain level-specific content for each difficulty mode
- `src/Profile/` and `src/LeaderBoard/` handle user profile and leaderboard-related features

In simple terms, the game loop keeps the program running, the state manager decides what screen is active, and each package handles a specific part of the game experience.

## Requirements

- Java JDK 8 or newer
- A Java IDE such as NetBeans or IntelliJ IDEA

## How to Run

1. Open the project in your Java IDE.
2. Build the project.
3. Run the main class:
   - `stacking_rectangle.Stacking_rectangle`

If you prefer the command line, make sure Java is installed and run the project through your IDE or build tool.

## Gameplay Notes

The game provides a menu-driven experience with different game states, including:

- Sign-in and registration screens
- Difficulty selection
- Level-based gameplay
- Audio and settings options
- Leaderboard viewing

## Notes

This project appears to be an educational game project and may be updated over time. If you want to extend it, the most natural places to start are:

- `src/levels/` for level logic
- `src/entities/` for player/enemy behavior
- `src/ui/` for visual overlays and buttons
- `src/gamestates/` for flow and screen transitions

