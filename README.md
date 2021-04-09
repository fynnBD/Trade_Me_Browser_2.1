# Trade_Me_Browser_2.1
### Small API/layout test

Kia ora! This is a very small project I created to test my skills using APIs and layouts in android studio/ gradle.
I was given access to an old version of the trademe recruitment test, which required using their sandbox api to display
the search categories and allow users to see the first 20 results for any particular category.

I initially set up the categories and listings by having two activities and swapping through them with both the categories
and listings being displayed in simple listviews. From this I iterated quite a lot, changing the layout, adding a card based
listing view, a string search function, and consolidating all of the functions onto a single activity. There is still 
a significant amount of work to do on this, like properly moving the http functions into their own enclosed set of classes
(aiming for model-view-controller). Properly hiding the API keys (they are currently extremely insecure, although keeping them secret
is not very important), adding proper error control and solutions for when the user does not have an internet connection.

## Libraries Used
### RetroFit 2
used to make the HTTP calls to JSON and parse the objects correctly

### Picasso
used to grab the image resources from the URLS given by the api
