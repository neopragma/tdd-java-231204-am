# Module: tdd-samples, Package: fizzplay 

One of the exercises you can choose to practice using TDD for greenfield development is the venerable FizzBuzz problem. You've probably done this exercise before, possibly without using TDD, for various job interviews. 

The ```fizzplay``` package contains several implementations of FizzBuzz as well as a single test case. There are three reasons to include this package in the sample codebase. 

**_First_**, many people get carried away trying to write clever implementations. Some want to write a one-line solution. Others want to exercise some particular feature of the programming language they're using, even if it isn't necessary to write FizzBuzz. Still others want to write a solution that's intentionally hard to understand, just for fun. 

So, to help you get that out of your system, I thought it would be helpful to provide several FizzBuzz solutions that elevate cleverness to the level of absurdity. The most absurd solutions are the extremes - ```BruteForceFizzBuzz```, which just returns the expected value, hard-coded; and ```BrainBuzz```, which runs a Brainf\*ck interpreter written in Java to execute FizzBuzz written in Brainf\*ck. If that isn't absurd enough for you, then I give up.

**_Second_**, a common misconception about TDD is that the test suite locks in the design of the production code because any change to the production code will break the tests. 

When I hear/read someone say this, I know the speaker/author does not understand TDD even on a basic level. There are two tell-tales: 

(1) When you drive the production code from test cases, you always write the test cases that express the desired functionality _before_ you change any of the production code. Then you change the production code to make the desired functionality a reality. It is literally impossible to break tests by changing production code, because you never modify production code before you've adjusted the test suite.

In fact, the test suite is your safety net for making changes to the production code. If a test case breaks, it's because you made a mistake changing the production code. In that case, you definitely _want_ the test case to fail. The failure provides early warning of a problem that could be hard to diagnose if it reached production.

(2) It's possible to write test cases that depend on a particular implementation of the production code. When we do that, the test suite can't function as a safety net for making intentional changes in the code's behavior or for refactoring the code. 

The rule of thumb here is to write test cases that make assertions about the behavior of the SUT that is visible through the SUT's public APIs, and to avoid writing test cases that depend on underlying implementation details. That said, there are times when we need to do that in order to have meaningful checks - but it is the exception, not the norm.

So, the sample code in package ```fizzplay``` demonstrates that the same test case can check the behavior of all the different FizzBuzz implementations. Some of the implementations are very different indeed, but they all exhibit the same behavior as seen through their public API, the ```fizzbuzz()``` method.

**_Third_**, in almost all interview/screening situations, the interviewer wants to see how fast the candidate can scribble a FizzBuzz solution on a whiteboard. But there are also situations when the interviewer wants to assess the candidate's thought process and approach to coding. 

The latter are usually the more-deirable jobs. They don't care how clever your solution is. They don't care if you finish a solution. They want to see how you build up the code logically. That usually means they want to see you apply TDD to the problem, step by step. So you might as well practice doing it that way.
