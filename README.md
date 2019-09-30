# README


This program is coded together by Yicheng Li (U2950397) and Anzhe Meng (U50590533). 
To help you understand our design logic, here is an instruction of how to play the game for you.
Enjoy winning money!

## Sample inputs

Welcome to BlackJack1.0
How many player do you have?**2**
**********************************************************
Player 1, please enter your name.**Jack**

 How many money will you bet?**3**
********************************************
Player 2, please enter your name.**Jim**

 How many money will you bet?**3**
********************************************
New turn begin
Player Jack is your turn.
********************************************
The card of you is
SpadeKing.
ClubKing.
********************************************
Please make your bet.**1**
********************************************
Do you want to split?(Y/N)?**y**
********************************************
New turn begin
Player Jim is your turn.
********************************************
The card of you is
ClubQueen.
Spade10.
********************************************
Please make your bet.**1**
The first card of Dealer is Club7.
********************************************
Player Jack is your first hand's turn.
Now you have 1 dollars.
********************************************
The card of you is
ClubKing.
********************************************
Player Jack now make your move.
1.hit
2.double up
3.stand
********************************************
**2**
********************************************
Now you can't bet anymore as you run out of your money.
********************************************
Player Jack is your second hand's turn.
Now you have 0 dollars.
********************************************
The card of you is
SpadeKing.
********************************************
Player Jack now make your move.
1.hit
2.double up
3.stand
********************************************
**1**
The card of you is
SpadeKing.
Heart6.
Please make your bet.**1**
Sorry, you can only use money you have.
Please make your bet.**0**
********************************************
********************************************
Now you can't bet anymore as you run out of your money.
********************************************
Player Jim is your turn.
Now you have 2 dollars.
********************************************
The card of you is
ClubQueen.
Spade10.
********************************************
Player Jim now make your move.
1.hit
2.double up
3.stand
********************************************
**3**
********************************************
Player's round ends. Now is dealer's round.
********************************************
********************************************
The card of Dealer is Club7.
********************************************
The card of Dealer is Heart4.
*******************************************
The card of Dealer is Diamond6.
********************************************
The winner is DEALER.%n
********************************************
********************************************
Player Jack now you have 0 dollars.
********************************************
Player Jim now you have 2 dollars.
********************************************
New turn begin
Player Jack is your turn.
********************************************
The card of you is
Diamond3.
ClubJack.
********************************************
Please make your bet.**2**
Sorry, you can only use money you have.
Please make your bet.**1**
Sorry, you can only use money you have.
Please make your bet.**0**
********************************************
New turn begin
Player Jim is your turn.
********************************************
The card of you is
HeartAce.
Diamond9.
********************************************
Please make your bet.

## Rules
1. If and only if a player (not dealer) encounter two cards with the same rank, they have the option to split. So at most they have two hands of cards.
2. No budget limit for a dealer. 
3. When a player loses, his/her bet will be forfeited.
4. When a player wins, he/she can get the bet on each hand of cards in the current round doubled back as a bonus. For example, player X claims a win in round when he bet $200 on each of his hands, then he could get $800 in total back.
5. A game can be played by 5 five human users at maximum. They can quit the game at their will whenever a round is over.
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTcxMTkyMDEwXX0=
-->