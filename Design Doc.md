# Design Documentation
Our program includes 5 classes in total, each of which plays a role in making up the entire Blackjack game, as well as extending to some other card games. The four classes are Blackjack, Card, cardGroup, Player, and Board.(main entrance). In the following article we are going to describe the properties and methods designed within each class.

## Blackjack

## Card

### Variables
Card is the fundemental component of every card game, without which any game makes no sense. As it is known, there are at least two properties of a card: its *suit* and its *rank*. Both of them determines the ranking of a card itself. However, in some games, for example, Black jack, both the properties don't matter. Rather, people care more about the real value of a card that it really stands for. (e.g. King, Queen and Jack all represents the value of 10, which is no superior to any number card of 10.) Given this scenario, we provide another variable called "*num*", representing the true value of every card in this game while not affecting the extensiveness and re-usability of the class. In addition, in a game the look of a card can never be changed except you are a magician who is able to manipulate the stuff. Thus, it is safe to set all the cards to be unmutable, which is achieved by declaring the variable *suit*, *rank*, *num* **final** and not providing with modifiers/setters of them three.

Meanwhile, we provide three lists: *SUITS*, *RANKS*, and *NUM* each of which contains respectively the index of suit
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTExODE1NTg3NCwtMTU1Mjg0MDE1MywtOT
k3NTYxNzI0LC05MTExNTk2MzcsMTI5ODI5NTg2Ml19
-->