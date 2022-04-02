# GPA Calculator

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A calculator that can calculate a user's GPA based on the amount of credits, past classes' grades, and total credits required

### App Evaluation
- **Category:** Education and Productivity
- **Mobile**: Mobile first experience
- **Story:** Can calculate the user's GPA based on the classes that they've taken so far at a college or 
- **Market:** This app is mainly tagreted for high school and college students since these are the customers whose is the most self conscious about their grades.
- **Habit:** Helps students calculate the GPA and know where thay are to improve by the end of each semester.
- **Scope:** The app will be used mainly for calculate a studet's GPA. However, in the near future, the app will expand on this idea and be able to do multiple different calculations such as predicting if a student will be able to do a major based on the classes already taken, GPA, etc.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can calculate their overall GPA for college & high school.
* User can calculate/predict thier grade for a specific grade.
* Bottom View Navigation that can switch between the two options above
* When user exits out of app and reopens it, previously submitted info will still be there

**Optional Nice-to-have Stories**

* Inform user of what grades and credit they need to get to obtain a desired GPA
* For the GPA Activty have it be more broken down into Semesters
* Have a GPA scale based on the college/high school they go to

### 2. Screen Archetypes

* GPA Calculator Screen
  * Includes the ability to input Couse Name (User's Input is Optional), Credits for the Course (User's Input is Required), Grade Obtained/Desired for the Class (User's Input is Requried)
  * Ability for User to add more courses with an add button
* Course/Class Grade Calculator Screen
  * Includes the ability to input Course Name (User's Input is Optional), Assignment Name (ex HomeWork, Midterm, Quiz 1, Final, etc.)(User's Input is Optional), Assignment Worth (User's Input is Required), Grade for Assignment (User can put Letter Grade or Percentage Grade ie B or 88)(User's Input is Required)
  * Ability for User to add more assignments with an add button

### 3. Navigation

**Bottom Navigation** (Tab to Screen)

* GPA
* Course/Class Grade

Optional Navigation

* Desired GPA  Calculator



* GPA Screen
   
* Grade Calculator Screen
  

## Wireframes
![wireframe](https://user-images.githubusercontent.com/70610982/161167159-093cb2ac-1a7c-4146-8756-47d3e2006ffc.png)

## Schema 
### Models
| Property | Type | Description |
|---|---|---|
|className|string| The name of the course that the user is currently taking|
|gradingProcedure|string|The grade breakdown in a single class|
|letterGrade| string |The predicted grade that the user is going to get by the end of the semester (A - F)|
|grade| string |The predicted grade that the user is going to get by the end of the semester (0 - 100)|
|credit|int|The number of credits in a course|
|weight| string |The importance of a certain class|
|currentGPA |float| The current GPA of the student|
|percentage| int |The percentage of each grading procedure|
|desiredGPA| float | The desired GPA of the student want to achieve|

### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
