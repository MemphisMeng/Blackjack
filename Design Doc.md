# Design Documentation
Our program includes 5 classes in total, each of which plays a role in making up the entire Blackjack game, as well as extending to some other card games. The four classes are Blackjack, Card, cardGroup, Player, and Board.(main entrance). In the following article we are going to describe the properties and methods designed within each class.

## Blackjack


## Card
Card is the fundemental component of every card game, without which any game makes no sense. The UML diagram is below.
|Card    |
|--|--|
|-num: int   |
|-

### Variables
As it is known, there are at least two properties of a card: its *suit* and its *rank*. Both of them determines the ranking of a card itself. However, in some games, for example, Black jack, both the properties don't matter. Rather, people care more about the real value of a card that it really stands for. (e.g. King, Queen and Jack all represents the value of 10, which is no superior to any number card of 10.) Given this scenario, we provide another variable called "*num*", representing the true value of every card in this game while not affecting the extensiveness and re-usability of the class. In addition, in a game the look of a card can never be changed except you are a magician who is able to manipulate the stuff. Thus, it is safe to set all the cards to be unmutable, which is achieved by declaring the variable *suit*, *rank*, *num* **final** and not providing with modifiers/setters of them three.

Meanwhile, we provide three lists: *SUITS*, *RANKS*, and *NUM* each of which contains respectively the index of *suit*, *rank* and *num*. Therefore, when we instanize the 3 integer variables, what we are going to send the indeces and asking the machine to find the object in responding list. 

The remaining variables are *marker*, *faced* and *belong*. *marker* represents to which card group it  really belongs, since we assume there are more than one deck in Black jack. *faced* keeps track of whther the face of a card is down or up. When it is true, it's up; otherwise, down. *belong* implicates who is having this card now.

### Methods
We provide two different constructors, one is no-argument and the other comes with two parameters. The no-arg constructor simply sets the *num*, *rank* and *suit* as default zero. And the other co
<!--stackedit_data:
eyJoaXN0b3J5IjpbMzQ4Mjk2MzU1LC0yMDEwNDUyNDk2LC0xNT
UyODQwMTUzLC05OTc1NjE3MjQsLTkxMTE1OTYzNywxMjk4Mjk1
ODYyXX0=
-->