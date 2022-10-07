<h1>Overmovie  🎥</h1>

> 👨‍💻Status: Developing

### It's a mobile application planned by me, where I use the TMDB API.

## 🚀 Features 

+ Explore the Upcoming, Trending, Popular and Top Rated Movies.
+ Get detailed decription of all the movies.
+ Search for movies.

## 🧰 Built With 

* [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Colletion of libraries that hel you design o bust, testable, and maintainabel apps.

  * [Paging 3 library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging library helps you load and display pages of data from
  a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) -  Data objects that notify views when the underlying database changes.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  * [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  * [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
 
 * [Koin](https://insert-koin.io/) - Dependency Injection Framework
  
 * [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
  
* [Navigation Graph](https://developer.android.com/guide/navigation/navigation-design-graph) - The Navigation component uses a navigation graph to manage your app's navigation.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.

## 📷 Screenshot

![ScreenShotReadme](https://user-images.githubusercontent.com/58862763/190878296-fe2d7955-0453-40f1-a56c-00494f731d45.jpg)

## ⚠️ Setup Requirements
Obtain your key from [TMDB](https://www.themoviedb.org/documentation/api) API and replace it in the const val KEY = "PUT YOUR API KEY HERE":

## Permissions 💻
+ Internet

## Structural design pattern
The app is built with the Model-View-ViewModel (MVVM) is its structural design pattern that separates objects into three distinct groups:

Models hold application data. They’re usually structs or simple classes.
Views display visual elements and controls on the screen. They’re typically subclasses of UIView.
View models transform model information into values that can be displayed on a view. They’re usually classes, so they can be passed around as references.
