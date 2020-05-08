# Amiibo App

## Team Takumi
### Alexandre Casanova-Frascarella, Catherine Biella, Jordan Waldron, Ramon Christopher Isleta

## Main activity 
The main activity contains a recycle view with a list of all the amiibo figures.
- If you long click any item in the recycle view a context menu will appear and you will be able to add the amiibo to favorites and remove from favorites.A snackbar confirms the event being clicked.
- There is a search bar that allows the user to search any amiibo in the list.
- The options menu button in the task bar allows the user to read about us, the developers of the app.
- The settings button in the task bar allows the user to apply dark mode to the app, which save the setting in shared preferences. 
-You can also click a drawer menu to display the favorites fragment

## Collection Activity
The collection activity also contains a recycle view with a list of the amiibos however this activity allows the user to enter the date of when the user obtained that specific figure and price.  A user must click on Enter date or Enter price to pop up a Dialog to input the values. It allows the user to keep track which figures they are able to obtain.

## Stats Activity
This page was supposed to keep track of the user's progression. However, because we could not get the database to fully work, we were able to just display the progression bars for each series of figures that the user was trying to collect for.
- On the top of the activity there is a button that will display a second context menu that will allow the user to pick between to different types of information. Information on the company Nintendo and information on the amiibos themselves.
-When a user clicks the stats page, an Android notification is created by the application

## Database
We tried to create a database, we think we succeed however we do not know how to manipulate it with the Json calls. We attempted to create three databases one for the main activity, a second to store the users favorite figures and the third to store the information that the user enters on the collection activity.

##Navigation Bar
the bottom navigation bar allows the user to traverse between all the activities in the application. 
every time the button for the stats activity is clicked the user will be prompted  a notification.
