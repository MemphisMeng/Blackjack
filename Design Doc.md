# Design Documentation
Our program includes 5 classes in total, each of which plays a role in making up the entire Blackjack game, as well as extending to some other card games. The four classes are Blackjack, Card, Player, cardGroup and Board.(main entrance). In the following article we are going to describe the properties and methods designed within each class.

## Blackjack


## Card
Card is the fundemental component of every card game, without which any game makes no sense. The UML diagram is below.
![UML of class Card](https://drive.google.com/file/d/162TFY-bVUtJW3FIFT85Ul4lmwbgxLWtW/view?usp=drivesdk)
### Variables
As it is known, there are at least two properties of a card: its *suit* and its *rank*. Both of them determines the ranking of a card itself. However, in some games, for example, Black jack, both the properties don't matter. Rather, people care more about the real value of a card that it really stands for. (e.g. King, Queen and Jack all represents the value of 10, which is no superior to any number card of 10.) Given this scenario, we provide another variable called "*num*", which encodes the card to a certain value. In addition, in a game the look of a card can never be changed except you are a magician who is able to manipulate the stuff. Thus, it is safe to set all the cards to be unmutable, which is achieved by declaring the variable *suit*, *rank*, *num* **final** and not providing with modifiers/setters of them three.

Meanwhile, we provide three lists: *SUITS*, *RANKS*, and *NUM* each of which contains respectively the index of *suit*, *rank* and *num*. Therefore, when we instanize the 3 integer variables, what we are going to send the indeces and asking the machine to find the object in responding list. Similar to *suit*, *rank* and *num*, they are supposed to be unchanged, and be shared among classes, so they are declared **static final**.

The remaining variables are *marker*, *faced* and *belong*. *marker* represents to which card group it  really belongs, since we assume there are more than one deck in Black jack. *faced* keeps track of whther the face of a card is down or up. When it is true, it's up; otherwise, down. *belong* implicates who is having this card now.

### Methods
We provide two different constructors, one is no-argument and the other comes with two parameters. The no-arg constructor simply sets the *num*, *rank* and *suit* as default zero. And the other one can help the user customize these three variables. 

We also provide accessors and modifiers. Just note that we will not modify the card's properties so there are no modifiers for *suit*, *rank* and *num*. 

The Card class is in charge of the comparison between the cards. That's why we provide two functions compareTo and sameCard. They are helpful in some card games.

## Player
Players are also of significance to the game. This class does not describe how a gambler operate the cards on board. Instead, it mainly takes charge of recording the moeny flow of a player. The UML diagram is shown below. In this case, as far as we concerned, the class will have good extensiveness.
![UML of class Player](https://drive.google.com/file/d/115M31wAjA5ri2x-SyT28lqUj-eznTyBk/view?usp=drivesdk)
### Variables
*name* is no more than an identifier of a player. And *money* resembles the total cash that a customer brings into a casino. 

What is worth noting is *first_hand* and *second_hand*. Both of them are a two-unit-long float-type array. We set the first place of the both to respectively contain the identity/marker of this hand, the second the money bet on this hand. The reason why we only set two hands is that our rule only allows a player to split once, only if their first cards have the same value.

### Methods
*show_Marker*, *show_Money* and *show_Name* are just the accessors to certain private variables. 

*forfeitMoney* and *moneyBack* describe two major changes in term of money. Before a new round gets started, the dealer will ask all the players on board to bet money before (which is conventionally considered as the bet on their first hands later), so that is the only circumstance where a person' money may be deducted. On the other hand, when the player wins, they will take all bets on board into his/her pocket; or when there is a draw, they will get the bet returned. That is the only time when their account would witness an increase.

bet, doubleUp and split are the common movement 
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTE3OTQxMzU4NywtMTQxNzQ3MjYzNSwtND
M0NzE2ODQ5LDMzOTgyODAzMCwtMzg4ODk2OTUxLC0yMDEwNDUy
NDk2LC0xNTUyODQwMTUzLC05OTc1NjE3MjQsLTkxMTE1OTYzNy
wxMjk4Mjk1ODYyXX0=
-->