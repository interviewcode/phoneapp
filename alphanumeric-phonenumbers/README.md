# AlphanumericPhonenumbers
# Frontend Solution to the Given Programming Problem

This is the very simple front end solution to the problem.
Like the backend, it has a lot of assumptions/things it does that are ignored for the scope
of the programming demonstration. 

     * Users & Security    
     * Tests 
     * Automatic Builds/Deployment & Alerting on Failures
     * Deployment & Runtime Environmental concerns (what environment is this running in)
     * Is not built into the application deployment     
     * Etc.
    
Out of necessity & time constraints this "solution" ignores most of the above concerns.
The tests (prebuilt) run, but were not extended.

### Implementation Decisions  

Uses the pre-built Material Design with some animations to make for a better 
user experience, somewhat of a progressive style app. 
It provides some rudimentary navigation.  Usually designers will provide a
more expressive (and significantly better) look & feel for the styling & css-
this roughs it out so that the style can then be adjusted/set by a designer
who knows the fonts, color schemes, etc. desired. 

The app is responsive & will work on smaller phones as well as desktops.
The default page size of 10 was chosen with that in mind.   The numbers returned
from the backend are returned without formatting and are formatted on the 
front end as presentation concerns are usually a front-end concern. 


## Runtime Assumption:

It is assumed that this is running on the same "localhost" as the backendserver
and that that server is running on:
    
    http://localhost:8080/api

The host is defined in app.constants.ts.

In a working environment this project might still be separate (and the host would
be picked up dynamically at runtime from environment variables), or alternately
the Angular code might be compiled and served up directly by the SpringBoot
container. 

As the projects were built separately (to minimize the work done by the framework)
and make my creative process more apparent, the projects remain separate.

## Further things that could be done
   * Improve paging: Make the paging size changeable
     or being able to jump directly to the middle of the result-set (rather than having to page)
   * Change the presentation on more problematic numbers 
     (those where a 0 or an O might get confused)
   * In conjunction with the backend, changing the ability to sort.   
   * etc.     


    
# Initial Project Generation

This project was generated using the steps described here: 
 [Angular Local Setup](https://angular.io/guide/setup-local)
 
_everything below was generated by Angular CLI_ 

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).