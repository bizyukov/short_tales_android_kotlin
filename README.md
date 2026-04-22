# Bash.im Reader – RxJava2 Android Sample

[![Kotlin](https://img.shields.io/badge/Kotlin-1.2+-blue.svg)](https://kotlinlang.org)
[![RxJava](https://img.shields.io/badge/RxJava-2.x-brightgreen.svg)](https://github.com/ReactiveX/RxJava)
[![Status: Archived](https://img.shields.io/badge/status-archived-lightgrey.svg)](https://github.com/yourusername/bash-im-reader)

> **⚠️ ARCHIVED – EDUCATIONAL USE ONLY**  
> This project was built as a learning exercise around 2017–2018. The third‑party API (`umorili.herokuapp.com`) may be unavailable, and the code relies on deprecated Android Support Libraries.

## 📖 About

A minimal Android application that fetches and displays quotes from **bash.im** (a popular Russian humour site) using a public API proxy. The app demonstrates:

- **RxJava 2** for network calls and threading (`subscribeOn`/`observeOn`).
- **Retrofit** with RxJava adapter.
- **RecyclerView** with custom adapters and click listeners.
- **ButterKnife** for view binding (pre‑data binding era).
- Custom listener interface (`ChangeSourceListener`) for communication between adapter and activity.

## 🛠️ Tech Stack

- Kotlin
- RxJava 2 + RxAndroid
- Retrofit 2
- Gson
- ButterKnife
- Android Support Libraries (AppCompat, RecyclerView)

## 📁 Code Overview

| File | Description |
|------|-------------|
| `MainActivity.kt` | First screen – loads list of quote sources, handles item clicks via `ChangeSourceListener`. |
| `QuotesActivity.kt` | Second screen – displays quotes for the selected source. |
| `BashImApiService.kt` | Retrofit interface with endpoints: `/api/sources` and `/api/get`. |
| `Quote.kt`, `SourceOfQuotes.kt` | Data classes for API responses. |
| `SearchRepository.kt` | Simple repository abstraction over the API service. |
| `SourceOfQuotesAdapter.kt`, `QuotesAdapter.kt` | RecyclerView adapters with custom click handling. |
| `ChangeSourceListener.kt` | Interface for propagating item clicks from adapter to activity. |

## ⚠️ Limitations & Known Issues

- **API Endpoint**: `umorili.herokuapp.com` was a free Heroku proxy; it may no longer respond.
- **Hardcoded Query Limit**: `searchQuotes` always requests 50 items – no pagination.
- **Deprecated Dependencies**: RxJava 2, ButterKnife, and Support Libraries are outdated.
- **No Architecture Component**: No `ViewModel`, `LiveData`, or Coroutines – typical for the pre‑Jetpack era.

## 🧪 How to Run (if you must)

1. Clone the repository.
2. Open in Android Studio (version 3.x recommended).
3. If the API is down, replace `baseUrl` in `BashImApiService` with a working alternative (or mock the responses).
4. Build and run on an emulator/device with API level 16+.

## 📚 Why Keep This Repository?

This code is preserved as a **historical reference** of my early Android/Kotlin work. It complements other legacy projects in my GitHub profile, demonstrating a breadth of experience across different domains and technologies.

For the **EB‑1A extraordinary ability visa** portfolio, this repository illustrates:
- Adoption of reactive programming patterns early in the Android ecosystem.
- Clean separation of concerns even in small projects.
- Ability to integrate third‑party APIs and handle asynchronous data.

## 📄 License

MIT – use at your own risk.

---

*“The best way to predict the future is to implement it.”* – Alan Kay