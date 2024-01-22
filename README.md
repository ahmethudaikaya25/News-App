# NewsApp Android Application

This Android application has been developed utilizing the open-source news API provided by
NewsAPI.org. Created with modern Android technologies, the app encompasses features such as viewing
news, saving favorite articles, exploring breaking news, and conducting custom news searches.

## Getting Started

Before launching the project, you need to add your API key to the `gradle.properties` file. This key
can be obtained after creating an account on NewsAPI.org.

propertiesCopy code

`# gradle.properties

apiKey=YOUR_NEWSAPI_ORG_API_KEY`

## Project Structure

The project consists of the following key components:

- **ViewModel**: Classes responsible for processing news data and communicating with the UI.
- **Retrofit**: A library used for sending and receiving HTTP requests.
- **Dagger Hilt**: A dependency injection library utilized for dependency injection.
- **Paging3**: A library employed for pagination.
- **Glide**: A library for loading and displaying images.
- **Room**: A library for database access.
- **Shimmer**: A library for providing visual feedback while loading data.

## Features

The app includes the following main pages:

### Home Page

Users can explore breaking news on this dedicated page.

[![Home Page](./screenshots/home_page.png)]

[![Home Page With Category](./screenshots/home_page2.png)]

### Favorite Articles

Users can mark interesting articles as favorites and access them on this page.

[![Favorite Articles](./screenshots/favorites.png)

### News Search

Users can conduct custom news searches using specific keywords.

[![News Search](./screenshots/search.png)

### News Details

Users can access detailed information about each news article on this page.

[![News Details](./screenshots/detail.png)

## Installation

The project can be opened using Android Studio or any other suitable IDE. After adding your API key
to the `gradle.properties` file in the project directory, you can run the project on your Android
device or an emulator.

## Important Notes

- Keep your API key secure and avoid sharing it.

- Do not forget to change the line in the `build.gradle` file that contains your API key:

  kotlinCopy code

  `// build.gradle (Module: app)`
  `buildConfigField("String", "API_KEY", "\"$apiKey\"")`

- Customize other configurations in the project as needed.

## Contribution

If you wish to contribute to this project, please fork it and submit a pull request. We welcome any
feedback and contributions.
