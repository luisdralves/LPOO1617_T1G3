# LPOO1617_T1G3

[![BCH compliance](https://bettercodehub.com/edge/badge/gitinho/LPOO1617_T1G3?token=a9f436d5baec9e7f75c180d9acd03253f89cb92e)](https://bettercodehub.com/)

Luís Miguel da Rocha Alves, up201405308, up201405308@fe.up.pt

Miguel Rodrigues Pires, up201406989, up201406989@fe.up.pt

## Setup/ Installation procedures
### APK file:
- Download the "monopoly.apk" file (only for Android, for now) on this branch or you can also download it here: https://feupload.fe.up.pt/get/2CiCjG0dzOwD6Rl;
- Copy the .apk file into anywhere of your mobile device's storage;
- Open your device's file manager, look for the .apk file that you copied;
- Click on it and install the game;
- Have fun!

### Jar file:
(having difficulties creating it)
## Architecture Design
#### Package and class diagram
![Package and class diagram](README_rsc/Classes.png)
#### Behavioural Aspects
![Behavioural Aspects](README_rsc/Behavioural%20Aspects.png)
#### Design Patterns
The design pattern we decided to use is the **Abstract Factory Pattern** because we find it the best to suit our needs.
## GUI Design
#### Main Functionalities
- New game
  - Number of Players
  - Choose pawn
  - Buy houses/hotels
  - Mortgage/unmortgage properties
  - Roll dice
  - Buy properties
  - Auction properties
- High Scores
- Rules
- Exit
#### Mock-up
##### Imagens de apresentação meramente sugestivas.
![Main Menu](README_rsc/MonopolyMainMenu.jpg)
![Play Mode](README_rsc/MonopolyPlayMode.jpg)
## Test Design
- Manually select dice value and check if the square on which the player lands is the expected
- Check if dice probability distribuition is equivalent to 2d6
- Check if next player is selected after the end of turn
- Check if player lands on another player's property, has enough money to cover the rent, and pays
- Check if player lands on another player's property, doesn't have enough money to cover the rent, has properties, and can mortgage them
- Check if player can acquire properties
- Check if player can't acquire houses without acquiring a property first
- Check if player can't acquire houses if not enough houses are available in the board
- Check if player can acquire houses for his properties
- Check if player acquires hotel after acquiring 4 houses
- Check if player can mortgage his properties
- Check if player plays again after double dice roll
- Check if player goes to jail after 3 double dice rolls in succession
- Check if other players can acquire property via auction after player refuses to buy it
#### Difficulties
- Time managment, because the final delivery of the project was schedule during final exams time, which limits our availability and dedication to the project;
- We started working using Libgdx, but then we found out that if we used Native Android would make things a lot easier;
- Some aspects of the game would be much better, such as the dice scene, if we could use 3D capabilities;
- Making tests to try to cover the majority of the code;
- Trying to make the .Jar file.
#### Lessons learned
- We learned to program in Java and to make the most of the code, developing good code practices;
- We learned how to work using Libgdx;
- We learned how to do an Android application/ game, using android capabilities;
- We learned about design knowledge, while working with sprites and making interfaces.
#### Time spent
We tried to work everyday in conciliation with the exams period and we worked the majority of the time together at FEUP.
With the time given, we did our best to make an almost complete, playable game. If we had more time, we would implement the AI, make better tests, and improve some aspects of the game.
We really enjoyed doing this project! After the presentation, maybe we will make a refined finished version.
#### Work distribution
Both of us worked together and equally, so we decide to assign 50% to each of us.
