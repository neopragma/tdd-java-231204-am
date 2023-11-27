# Module: tdd-samples, Package: checksetup 

Purpse: See if we have our JUnit dependencies defined correctly.

This package contains a single "production" class named TrivialClass and a single test class named TrivialTest. There is a single test method that asserts method _isThisTrivial()_ on TrivialClass returns the string, "This is not trivial." The method actually returns "This is trivial," so the example fails. We want to see it fail "for the right reason" and not because the build is unable to find JUnit dependencies. 

