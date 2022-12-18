# World's end Dreamer 

## A 2D visual exploration game 

**World's end Dreamer** is a visual exploration game inspired by other games such as Omori,
Lisa the Painful, and Yume Nikki. The game is unconventional as there is no story or 
way to "win" or "lose" the game. The game takes place inside the model's room. The only
interactable objects in the room is a door and a bed. Leaving the door causes the game to
end, and sleeping in the bed lets the model enter a dream world. In the dream world
the model can explore different rooms and interact with different objects and characters.
At any point in the game the model can decide to wake up and leave the bedroom. 

This game would most likely be played by people looking for a more surreal and non-conventional
video game experience. As there is no explicit story the enjoyment of this game comes from
being immersed in the game's atmosphere, as well as coming to their own conclusion of the 
game's greater meaning. This project interests me as exploration games like Omori and Yume Nikki
have been very impactful to me. I want to use this project as a means to express myself and 
to have experience creating a similar kind of game. 


Expected features:
- Custom Sprite art and environments
- Custom/Appropriate Royalty Free Music
- At least three different rooms 

## User Stories
- As a user I want to be able to beat the game.
- As a user I want to interact with the described environment.
- As a user I want to move around the environment.
- As a user I want to interact with characters and read dialogue.
- As a user I want to use objects to solve puzzles.
- As a user I want to save my progress and return at any point.
- As a user I want to feel emotions. 

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by...
    - In the game there are different collectibles you can add to the model's inventory panel.
    - The two items that are addable are a weird creature on a bed and a sword by the window.
- You can generate the second required event related to adding Xs to a Y by...
  - In the game you can right click on an addable object and select "Take" on it. This
  - only works on certain objects.
- You can locate my visual component by...
  - Going to project_o8u4p/Images
- You can save the state of my application by...
  - Right clicking any interactable object and selecting the save option!
- You can reload the state of my application by...
  - Pressing load game from the title screen!

# Phase 4: Task 2
**NOTE TO GRADER**

The eventlog is only printed to the console when the player decides to quit the game through 
leaving the door in starting room.

Sample:

"Tue Nov 29 15:45:31 PST 2022
Player has changed rooms.

Tue Nov 29 15:45:34 PST 2022
Player has added an item to inventory!

Tue Nov 29 15:45:37 PST 2022
Player has impacted the environment.

Tue Nov 29 15:45:40 PST 2022
Player has changed rooms.

Tue Nov 29 15:45:41 PST 2022
Player has changed rooms."


# Phase 4: Task 3

After learning about design patterns, I feel as though the singleton pattern would greatly
benefit my project. In my design, every class is instantiated with a single GameManager. 
Through the gamemanager, every class is able to access methods from other relevant classes.
Since it's very important that every class refers to the exact same GameManager object
using the getInstance() method would 100% ensure that every class stays connected with eachother.