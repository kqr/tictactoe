tictactoe
=========

A simplistic tic-tac-toe game in Java with computer opponents and difficulty
setting. Made primarily because I needed to learn how handling different
game stages works with the GameState thing in Slick2D.

I've also always wanted to write a computer opponent for a tic-tac-toe game,
so I took the chance to integrate one of those. It's not the best one, it's
unreasonably slow especially for the easiest difficulty setting, but the game
is small enough that I don't think it matters. What's important is that it
actually plays the game (and rather well at that, given a higher difficulty
setting.)


Dependencies
------------

Depends on Slick2D.


Settings
--------

![Options window](https://raw.githubusercontent.com/kqr/tictactoe/master/settings.gif "Options window")

You can assign either crosses or circles (or both or none!) to a computer
opponent. Check "Cross" and uncheck "Circle" if you want to practise your
openings as circle against a computer opponent!

The "Impossible" difficulty setting causes any computer players to always
play the best move available. This computer opponent will never lose – they
will either tie or win (if their opponent sucks.) Two "Impossible" computers
against each other always tie.

The lower difficulty settings simply increase the range of moves the
computer is allowed to play. The "Easy" computer will almost play on random.
The reason the lower difficulty settings are slow is, of course, that all
difficulty settings *know* which move is best. The lower settings just
makes it so the computer doens't always choose that.


Gameplay
--------

![Gameplay screenshot](https://raw.githubusercontent.com/kqr/tictactoe/master/gameplay.gif "Gamesplay screenshot")

The gameplay is... well... tic-tac-toe. It says in the sidebar whose turn
it is and whether or not that player is controlled by a human or the
computer. It also shows the value of each square the mouse is hovered over.
A higher value is better. This value is of course the same as the one the
computer opponents use. Don't look at this if you don't want to get spoiled.

The "Back" button discards the current game and takes you back to the game
options screen.

