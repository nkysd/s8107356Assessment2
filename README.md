# s8107356Assessment2

## Android Application - Music Album Viewer

This Android app was developed as part of the **NIT3213 Final Assessment**.  
It demonstrates **Login authentication**, **Dashboard display**, and **Detail viewing** using **API integration**, **MVVM architecture**, **Hilt dependency injection**, and **unit testing**.

---

## Student Info

- **Name:** Mio Mizutani  
- **Student ID:** s8107356

---

## Features

### 1. Login Screen
- Users log in using their first name and student ID.
- Sends a POST request to the correct `/auth` endpoint based on location (`/sydney/auth` used).
- Displays error messages for invalid credentials.
- On success, navigates to Dashboard and stores the received `keypass`.

### 2. Dashboard Screen
- Fetches entity list from the endpoint `/dashboard/{keypass}`.
- Displays a list of music albums using `RecyclerView`.
- Tapping on an item navigates to the Details screen with SafeArgs.

### 3. Details Screen
- Shows full details of a selected album:
  - Title
  - Artist
  - Year
  - Genre
  - Track Count
  - Most Popular Track
  - Description

---

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM
- **Dependency Injection:** Hilt
- **HTTP Client:** Retrofit
- **UI:** ViewBinding, Fragment, RecyclerView
- **Navigation:** SafeArgs + Navigation Component
- **Testing:** JUnit, Mockito, LiveData Test Utils

---

## Unit Tests

| Tested Class             | Method                             | Result    |
|--------------------------|------------------------------------|-----------|
| `LoginViewModelTest`     | loginSuccess / loginFail scenarios | ☑ Passed |
| `DashboardViewModelTest` | fetch success / failure            | ☑ Passed |

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/s8107356Assessment2.git
   ```

---

## Project Structure (Overview)
```
app/
├── data/         # Model classes and API service (Retrofit)
├── di/           # Hilt modules for dependency injection
├── repository/   # Logic to handle API calls and data
├── ui/           # Fragments for Login, Dashboard, and Details
├── viewmodel/    # ViewModel classes for UI logic

Each layer follows **MVVM architecture**.  
Navigation is managed using **SafeArgs**.  
Project is structured for clarity and scalability.
```

---
