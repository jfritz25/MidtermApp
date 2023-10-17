# Midterm - Guessing Game
<span style="font-size: smaller;"><strong>Jacob Fritz</strong></span>

---
<span style="font-size: smaller;"><strong> Description </strong> </span>
In my implementation the user can play a number guessing game and be able to view the all time highscores from the game

This app allows for a user to:
- Play the game
- View highscores
- Delete highscores

To begin I integrated SQL into our data handling to store our scores. We used a recycler view to store the scores and display them on the highscores page (screen)
and it allows for users to scroll and view all previous scores without fear of losing the content when it goes off the page.
<br>
<br>
To begin a user is prompted to either play the game or view highscores. If they play the game they navigate to the game screen where they can enter a player name and a guess.
When the user presses ok their guess is checked and a toast displays telling them if they were too high, too low, or just right along with a correct/incorrect sound. Every guess is incremented on the bottom half of the screen.
When a user gets it correct. They navigate back to the main screen where they see their name, their score, and the option to play again or view highscores.
<br>
<br>
When a user clicks view highscores it takes them to a recylcer view displaying all names and highscores sorted ascending by score.
Users then hav the option to delete highscores in which case a dialog will pop up confirming they want to delete that score.


## Functionality
'*' indicates tested in GIF  
The following **required** functionality is completed:
<br>
Safeargs and View Groups were implemented to transfer data from MainActivity.kt to Fragment1.kt. This allows us to gather the user data and translate it in real time!

**Demonstrated**
<br>
**START**
<br>
* [Play Game] -> [Answer: 63] [Guesses: 50 (too low!), 75(too high!), 63(correct!)] -> navigates back to main screen, displaying 3 guesses and no name
* [Play Game] -> [Answer: 63] [Name: Jacob, Guesses: 50 (too low!), 75(too high!), 63(correct!)] -> navigates back to main screen, displaying 3 guesses and Jacob as the associated name
* [ViewHighScore] -> Shows 2 records in recycler view "": 3 and Jacob: 3 [Back to Main]
* [ViewHighScore][DeleteJacob] -> Dialog pops up confirming user action [YES] -> Deletes from DB
* [ViewHighScore][Delete""] -> Dialog pops up confirming user action [NO] -> Does not delete from DB

<br>

**END**


---
## Video Walkthrough
Watch a demonstration of the different options when working with the notes app in the gif available on Github
Here's a walkthrough of a few translations:
**there was a slight adjustment to the buttons size that was implemented after the video was created and the changes do not alter the performance of the app -> however this changes clossly
reflects the UI requested in the PDF**
<br>
<img src= title='Project6 Video Walkthrough' width='50%' height = '50%' alt='Video Walkthrough' />

GIF created with [CloudConvert](https://cloudconvert.com/).

## Notes
UI Challenges:
- Getting layouts to behave as expected, especially with the 2 fragments nested in another

Backend Challenges:
- Determining what to setup conditonal around for when we need to change the text for the recycler view

## License

    Copyright [2023] [Jacob Fritz]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.