# Car Rental Application

This is a partially-complete application to support car rental operations. It's the starting point for a refactoring/TDD exercise similar to Gilded Rose, but a little more involved.

Here are the "specs" for the car rental application. The documentation may or may not be complete and accurate. 

## Specs for the car rental application 

This is the kind of incomplete and unclear documentation we often inherit with a legacy code base.

All monetary amounts are carried in US dollars. Output for locales where a different currency is used must be converted. _Hint:_ There is a class named ```I18N``` that calls a public API for currency conversion. It may or may not work.

All distances are carried in miles. Output for locales where kilometers are used must be converted.

### Vehicle Classes                                  

city car  
economy                          
standard sedan                   
luxury sedan (must be 21 years old)                    
suv (must be 21 years old)                              
pickup truck  

Vehicles of class "city car" are only available in select large cities.

### Base daily rental rates by location before applying adjustments

US base daily rates except CA, NY, NJ  

    city car                         N/A  
    economy                          45  
    standard sedan                   55  
    luxury sedan                     75  
    suv                              75  
    pickup truck                     75  

US California base daily rates  

    city car                         28 (San Francisco, Los Angeles, Sacramento only)  
    economy                          52  
    standard sedan                   65  
    luxury sedan                    105  
    suv                              95  
    pickup truck                     95  

US New York base daily rates  

    city car                         35 (NYC and Newark greater metro area only)  
    economy                          55  
    standard sedan                   68  
    luxury sedan                    133  
    suv                             140  
    pickup truck                     81  

US New Jersey base daily rates  

    city car                         35 (NYC and Newark greater metro area only)  
    economy                          45  
    standard sedan                   58  
    luxury sedan                    123  
    suv                             130  
    pickup truck                     81  

Canada base daily rates except Vancouver, Toronto  

    city car                         N/A  
    economy                          48  
    standard sedan                   54  
    luxury sedan                    110  
    suv                              78  
    pickup truck                     70  

Canada base daily rates for Vancouver  

    city car                         35  
    economy                          52  
    standard sedan                   58  
    luxury sedan                    114  
    suv                              82  
    pickup truck                     76  

Canada base daily rates for Toronto  

    city car                         35  
    economy                          52  
    standard sedan                   58  
    luxury sedan                    108  
    suv                              80  
    pickup truck                     75  

Germany base daily rates for Frankfurt  

    city car                         38  
    economy                          58  
    standard sedan                   68  
    luxury sedan                     88  
    suv                              88  
    pickup truck                     75  

Germany base daily rates except Frankfurt  

    city car                         33  
    economy                          54  
    standard sedan                   66  
    luxury sedan                     88  
    suv                              85  
    pickup truck                     72  

France base daily rates for Paris metro area  

    city car                         35  
    economy                          44  
    standard sedan                   63  
    luxury sedan                     90  
    suv                              85  
    pickup truck                     72  

France base daily rates except Paris  

    city car                         32  
    economy                          42  
    standard sedan                   55  
    luxury sedan                     63  
    suv                              70  
    pickup truck                     70  

Spain base daily rates for Madrid metro area  

    city car                         35  
    economy                          44  
    standard sedan                   63  
    luxury sedan                     90  
    suv                              85  
    pickup truck                     72  

Spain base daily rates for Barcelona metro area  

    city car                         35  
    economy                          44  
    standard sedan                   63  
    luxury sedan                     90  
    suv                              85  
    pickup truck                     72  

Spain base daily rates except Madrid and Barcelona  

    city car                         32  
    economy                          42  
    standard sedan                   55  
    luxury sedan                     63  
    suv                              70  
    pickup truck                     70  

### Vehicle power

diesel                             
gasoline                           
methane (Germany, France, Spain)                         
hydrogen (Germany, France, Spain)
liquefied petroleum gas (Germany, France, Spain)                         
electric                   
hybrid diesel electric            
hybrid gasoline electric  

A "city car" is a small electric vehicle. The only valid vehicle power type for "city car" is "electric". 

Not all fuels are used in all countries.

### Rental taxes and mileage fees 

Applied to the final bill, not the daily rental rate.

    countries                      rental_tax          mileage_fee (based on miles)  
    US - California                  .12                 .06  
    US - New Jersey                  .13                 .08  
    US - New York                    .15                 .09  
    US                               .09                 .02  
    CA                               .11                 .04  
    DE                               .22                 .05  
    FR                               .24                 .06  
    ES                               .17                 .03  

### Daily rental rate adjustments for vehicle power types

The daily rental rate may be adjusted upward (to cover maintenance costs or to discourage use) or downward (to encourage use) in various locations.

US daily rate adjustment for power  

    diesel                           0  
    gasoline                         0  
    methane                          N/A  
    hydrogen                         N/A  
    liquefied petroleum gas          N/A                
    electric                        10  
    hybrid diesel electric          10  
    hybrid gasoline electric        10  

Canada daily rate adjustment for power  

    diesel                           4  
    gasoline                         0  
    methane                          N/A  
    hydrogen                         2  
    liquefied petroleum gas          2               
    electric                         4  
    hybrid diesel electric           5  
    hybrid gasoline electric         4  

Germany daily rate adjustment for power  

    diesel                           2  
    gasoline                         8  
    methane                          2  
    hydrogen                         2  
    liquefied petroleum gas          0             
    electric                        -5  
    hybrid diesel electric          -2  
    hybrid gasoline electric        -2  

France daily rate adjustment for power  

    diesel                           0  
    gasoline                         5  
    methane                          2  
    hydrogen                         2  
    liquefied petroleum gas          0               
    electric                         0  
    hybrid diesel electric           0  
    hybrid gasoline electric         2  

Spain daily rate adjustment for power  

    diesel                           2  
    gasoline                        12  
    methane                          2  
    hydrogen                         0  
    liquefied petroleum gas          2               
    electric                        -4  
    hybrid diesel electric          -2  
    hybrid gasoline electric        -1  

### Daily rate adjustments for transmission types

Daily rate adjustment for automatic transmission (internal combustion only)  

    Germany                  9  
    France                   7  
    Spain                    6  
    US, Canada               0  

In the US and Canada, vehicles with manual transmissions are not offered. 

In Germany, France, and Spain, vehicles with manual transmissions are standard and automatic transmissions are an upgrade.

### Airport fees per rental contract (not per day)

Airport fees apply if the pickup location is one of the airports listed, except Germany and France.
For Germany and France, airport fees apply if the dropoff location is one of the airports listed.

    IATA (city)              Fee    
    ATL (Atlanta)            30  
    BCN (Barcelona)          47  
    CDG (Paris)              42  
    CLT (Charlotte)          27  
    DEN (Denver)             28  
    DFW (Dallas/Ft. Worth)   34  
    EWR (Newark)             48.50  
    FRA (Frankfurt)          40  
    IAH (Houston)            32  
    JFK (New York)           50  
    LAX (Los Angeles)        45  
    MAD (Madrid)             31  
    MIA (Miami)              29  
    MUC (Munich)             35  
    SFO (San Francisco)      44  
    YYZ (Toronto)            31.75  

### Driver age daily rate adjustment
              
    US   under 25    40      
    CA   under 25    30
    DE   under 25    20
    FR   under 25    20
    ES   under 27    16

### Daily rate adjustment for driving record 

Values are "good", "average", "bad".

US daily rate adjustment for driving record - drivers 25 or older

                               general  LA, DFW, ATL,    other large  Chicago, Miami, 
                                        NYC metro area,    cities     Houston, Seattle, Philadelphia
                                        DC area
    good driving record          -3%       0                -2
    average driving record        0        2                 0
    bad driving record            5       12                 7


Germany daily rate adjustment for driving record (drivers of all ages)

    good driving record          -4
    average driving record        0
    bad driving record            3 

### Daily rate adjustment for prior insurance claims

US daily rate adjustment for prior insurance claims   

                                           general           NYC, San Fran
    1 claim within past 24 months             5                   2
    2 claims within past 24 months           10                   3
    more than 2 claims within past 36 months 20                   6

### Uranium Plus member referrals

US and Canada one-time daily rate adjustment if new customer was referred by a Uranium Plus member
and joins Uranium Plus at the time of rental

-10% off base rate, not considering other adjustments

US and Canada one-time daily rate adjustment if this customer referred a new customer who
joined Uranium Plus since their last rental.

-4.5% off base rate, not considering other adjustments

### Insurance options

US daily insurance rates - large cities  

    collision (self)                13  
    injury (self + passengers)      19 plus 3 per passenger or additional driver  
    collison (both parties)         20  
    injury (both parties)           44  
    full coverage (self + pass)     28 plus 4.50 per passenger or additional driver  
    full coverage (all parties)     52  

Canada daily insurance rates - except Vancouver, Toronto  

    collision (self)                10  
    injury (self + passengers)      16 plus 3 per passenger or additional driver  
    collison (both parties)         18  
    injury (both parties)           40  
    full coverage (self + pass)     24 plus 3 per passenger or additional driver  
    full coverage (all parties)     46  

Germany daily insurance rates  

    collision (self)                16  
    injury (self + passengers)      12  
    collison (both parties)         30  
    injury (both parties)           20  
    full coverage (self + pass)     32    
    full coverage (all parties)     48  

France daily insurance rates  

    collision (self)                23  
    injury (self + passengers)      10  
    collison (both parties)         46  
    injury (both parties)           20  
    full coverage (self + pass)     50    
    full coverage (all parties)     57  

Spain daily insurance rates  

    collision (self)                22  
    injury (self + passengers)       9  
    collison (both parties)         45  
    injury (both parties)           19  
    full coverage (self + pass)     46    
    full coverage (all parties)     51  

### Daily rate adjustment for smokers

US, Canada, Germany rate adjustment if driver is a smoker: 4 

### Customer standing 

A customer who is not in good standing will be refused.

## Refactoring and enhancement tasks

As you address the following tasks, you are to approach the work with a "test driven" mindset. Refactor to simplify planned changes. Refactor to enable fine-grained unit testing. Drive changes from test cases when possible, and refactor incrementally as you work.

Your tasks are:

(1) As you refactor the code, make sure the functionality described in the documentation is supported. Some documented functionality might not be in place.

(2) Add support for daily rate reductions for long-term rentals. When the rental period is "weekly", reduce the daily rate by $5. When the rental period is "monthly", reduce the daily rate by $9.

(3) The Uranium Plus program is our customer loyalty program. Uranium Plus members get a 4% discount off the final rental contract price (not the daily rental rate), except insurance charges.

(4) Add support for our new business location, Phoenix, Arizona, US. Country code is US. State code is AZ. Airport code is PHX. Airport fee is $33.

(5) Add a new Vehicle Class, "panel van", to be available in Germany, France, and Spain.

(6) If dropoff location is different from pickup location, add a $30 service charge to the final bill (not the daily rate). The two locations cannot be separated by an ocean.

## Technical notes 

You will need a 2023 or later version of JetBrains IntelliJ IDEA and Java release 21. The starter code depends on some Java 21 features, and the first version of IntelliJ IDEA to support that release is 2023. You are free to use another IDE or editor, but the starter project has not been configured or tested for IDEs/editors other than IntelliJ IDEA.

Due to incompatibilities between Gradle 8._n_ and Java 21, we must use Maven for this project.

All tests won't run together. There's some sort of conflict under the covers between the "bad" unit tests created for the refactoring exercise and certain other tests. The other tests run fine in isolation. If you use ```mvn test``` Mockito will throw invalid stubbing exceptions, but those errors do not point to the real problem.

To run all the tests, use these two commands separately:

```shell
mvn test -Dtest=\!*ServiceTest   <== omit unit tests of Spring services
mvn test -Dtest=*ServiceTest     <== run unit tests of Spring services alone
```

If you're working on Unix, Linux, Mac OS X, or on Microsoft Windows Subsystem for Linux or a Windows bash shell, you can use the provided script to run the tests:

```shell
mvnw ./run-tests.sh 
```

To start the server and load the in-memory database, use this:

```shell
mvnw spring-boot:run
```

If you're working on Unix, Linux, Mac OS X, or on Microsoft Windows Subsystem for Linux or a bash shell, you can use the provided script to start the server:

```shell
./server-start.sh
```

This is meant for practicing refactoring, unit testing, and test-driving new and modified code. It is not set up to be deployed to a "real" server. It uses an in-memory database that is created by Spring Boot when the server starts.

## OpenAPI and Swagger 

The car-rental project is set up with OpenAPI and Swagger. Access http://localhost:8080/swagger-ui/index.html to use the Swagger UI.

Note that the server will throw a lot of exceptions and spew a lot of text to the console because of (intentional) bad design. Don't be alarmed.


