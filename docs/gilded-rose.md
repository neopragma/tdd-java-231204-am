# Gilded Rose Refactoring Kata 

This is a very popular kata to practice refactoring and other skills related to debugging, testing, and programming. For this course, I borrowed the starter code from here: [Emily Bache](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/main). 

The requirements for the Gilded Rose kata are here: [Requirements](https://github.com/emilybache/GildedRose-Refactoring-Kata/blob/main/GildedRoseRequirements.txt).

The kata can be approached in various ways, depending on what one wants to achieve. For this course, we're going to pretend we inherited this code when we started working at Our Fine Company, and we want to build a unit test suite to cover the current functionality before we start making changes. 

This is a common situation in Real Life. Two viable approaches are (1) to use Approval Testing to create a Golden Master and compare the output from our modified logic to that master, and (2) refactor incrementally, adding microtests as we go, until we have a reasonable safety net to proceed with modifications.

In the context of learning TDD, we're going to take the second approach in class. 
 