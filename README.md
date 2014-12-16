OptimalControlProblem
=====================

Dynamic solution for an OptimalControl Problem

This program, written in java, finds dynamic solution to the Optimal Control Problem.
Basically you have a directed graph with each arc having a cost function, which represents the cost of 
the jump. You start at node 0 and at each point of time you have to jump somewhere and you have to
decide where is it better to do so by comparing cost functions and finding the one that has minimal value
at this point of time, having in mind what total cost of previous jumps is there already.
By default it shows solution for 6 nodes and 6 different times a node has to make a jump.
You can change the former inside class Nodes, and the latter inside class Application (variable time).
As the program starts and gives you the table of results, you can click each cell of this table, that
contains a rezult to see a "route" of decisions that led to being in that node at that time and the 
corresponding total costs near them (inside brackets).
If you are interested in algorythm, the main engine of it is inside History.iterate(), everything else is
programming logic.
