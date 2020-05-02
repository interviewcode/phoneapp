# Alphanumeric Phonenumbers App

This is the solution to a very simple problem statement
for an interviewing coding exercise.

The problem statemnt (less specifically) is simply to 
generate alphanumeric phonenumbers given an input.

## To run this project:

Clone the project and open 2 terminal windows in the cloned directory.

Then start the backend by running:
```
cd backend
./gradlew bootrun
```
And you should see something to the effect of:
```
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.6.RELEASE)

2020-05-01 20:44:30.925  INFO 72821 --- [           main] demo.code.phone.PhoneApplication         : Starting PhoneApplication on Dianas-MacBook-Pro.local with PID 72821 (/Users/mccune/projects2/interviewing/compugain/phoneapp/backend/build/classes/java/main started by mccune in /Users/mccune/projects2/interviewing/compugain/phoneapp/backend)
2020-05-01 20:44:30.927  INFO 72821 --- [           main] demo.code.phone.PhoneApplication         : No active profile set, falling back to default profiles: default

...

2020-05-01 20:44:31.924  INFO 72821 --- [           main] demo.code.phone.PhoneApplication         : Started PhoneApplication in 1.254 seconds (JVM running for 1.513)
<=========----> 75% EXECUTING [1m 26s]
> :bootRun
```

Then IN THE SECOND TERMINAL, start the UI:
```
cd alphanumeric-phonenumbers/
ng serve
```
After which you will see something to the effect of:
```
chunk {main} main.js, main.js.map (main) 251 kB [initial] [rendered]
...
Date: 2020-05-02T00:46:42.958Z - Hash: d36ee0c3d2d346f34576 - Time: 9120ms
** Angular Live Development Server is listening on localhost:4200, open your browser on http://localhost:4200/ **
: Compiled successfully.
```

You then should be able to:

```
open your browser on http://localhost:4200/ 
```

And interact with the app!


### CAVEAT: This is a  coding exercise (done with limited time & effort) 
As such not all decisions / work represent what I would necessarily do.  

For instance, testing being omitted on the front end is not advisable.
Having build badges is also something you might normally do on github... 
Not having users or security concerns in the app...
Nor would necessarily having the UI and the API run separately be something done.
However, to emphasize the creative effort on my part rather than some tooling/framework's part,
each project was built minimally.

### Further information...
 is in the
  * UI [alphanalphanumeric-phonenumbers/**README.md**](https://github.com/interviewcode/phoneapp/blob/master/alphanumeric-phonenumbers/README.md) 
  * [backend/**README.md**](https://github.com/interviewcode/phoneapp/blob/master/backend/README.md) 

There is given some explanation about:
  * about how the project was created
  * scope included & excluded
  * implementation choices considered 
  * some things you might follow on doing if this were a real app
  * etc.
 