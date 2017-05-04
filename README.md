# LPOO1617_T1G3

[![BCH compliance](https://bettercodehub.com/edge/badge/gitinho/LPOO1617_T1G3?token=a9f436d5baec9e7f75c180d9acd03253f89cb92e)](https://bettercodehub.com/)

Luís Miguel da Rocha Alves, up201405308, up201405308@fe.up.pt

Miguel Rodrigues Pires, up201406989, up201406989@fe.up.pt


## Architecture Design
#### Package and class diagram
![Package and class diagram](Classes.png)
#### Behavioural Aspects
![Behavioural Aspects](Behavioural%20Aspects.png)
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
![Main Menu](MonopolyMainMenu.jpg)
![Play Mode](MonopolyPlayMode.jpg)
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
