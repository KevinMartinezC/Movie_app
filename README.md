# 🎬 Movies App 

The Movies App is a composable Android application built using Jetpack Compose that allows users to search for movies, view details, and manage their list of favorite movies. It uses the OMDB API to fetch movie data and display search results.

## 🌟 Features

1. Search for movies.
2. View movie details such as poster, type, and year.
3. Manage a list of favorite movies.
4. User authentication with username and API key.
5. Logout drawer displaying user information and providing a logout option.

## 📱 Composables

- `MoviesScreen`: Displays a search bar and a list of movies fetched from the OMDB API. Users can search for movies and mark them as favorites.
- `FavoriteScreen`: Displays a horizontal pager of favorite movies. Users can swipe through their favorite movies in a card format.
- `DetailScreen`: Displays the details of a selected movie in a bottom sheet, including the movie poster, type, and year.
- `LoginScreen`: Provides a simple login form where users can enter their username and API key. Displays the login status and error messages.
- `LogoutDrawer`: Displays a navigation drawer with user information such as username and last login date, as well as a logout button.

## 🚀 How to run the project

1. Clone the repository.
2. Open the project in Android Studio.
3. Obtain an API key from the [OMDB API website](http://www.omdbapi.com/).
4. Replace the placeholder API key in the project with your own.
5. Run the app on an Android device or emulator.

## 📋 Requirements

- Android Studio Arctic Fox or newer.
- Android SDK version 31 or higher.
- Jetpack Compose version 1.0.0 or higher.
- Kotlin version 1.5.10 or higher.

## 📚 Libraries used

- Jetpack Compose
- Kotlin Coroutines
- Room
- Retrofit
- Coil
- Material 3

## 🤝 Contributing

Contributions are welcome! If you have any ideas for improvements or bug fixes, please feel free to submit a pull request. Be sure to include a description of your changes and any relevant testing information.

## 📄 License

 This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
