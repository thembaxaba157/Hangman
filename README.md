# 🎮 Hangman CLI Game

[![Java](https://img.shields.io/badge/Java-17+-brightgreen.svg)](https://www.oracle.com/java/)  
[![SQLite](https://img.shields.io/badge/SQLite-Database-blue.svg)](https://www.sqlite.org/)  
[![Tests](https://img.shields.io/badge/JUnit%20%2B%20Mockito-Test-yellow.svg)](https://junit.org/)  
[![Versioning](https://img.shields.io/badge/Semantic-Versioning-blueviolet.svg)](https://semver.org/)  
[![CI/CD](https://img.shields.io/badge/GitHub%20Actions-Automation-lightgrey)](https://github.com/yourname/hangman/actions)

A feature-rich command-line Hangman game written in **Java**. It uses **SQLite** for persistent storage, **JSON** for word categories, and follows **Semantic Versioning** with **GitHub Actions** for automated testing and releases.

---

## 📌 Overview

This CLI game challenges users to guess words across various categories and difficulties. It includes:
- Scoring and point mechanics
- Persistent user accounts
- Hints using point currency
- Top 5 leaderboard system
- Word categories from JSON

---

## 🚀 Features

- 🎯 Word selection by category and difficulty
- 💰 Points system for hints
- 🏆 Scores awarded only for wins
- 📊 Persistent data in SQLite
- 🔁 User management (create/load/delete/view)
- 🧠 Hint types: reveal letter, extra attempt, reveal fact
- 🧪 Unit tested using JUnit & Mockito
- 📦 JSON-based word list (with descriptions and hints)

---

## 🗂️ Tech Stack

| Purpose        | Technology           |
|----------------|----------------------|
| Language       | Java 17+             |
| Database       | SQLite (via JDBC)    |
| Word Storage   | JSON (words.json)    |
| Input Handling | Scanner (CLI)        |
| Testing        | JUnit + Mockito      |
| CI/CD          | GitHub Actions       |
| Versioning     | Semantic Versioning  |

---

## 🏗 Architecture

```
Main.java
  |
  v
Hangman.java
  ├── GameState enum
  ├── UserSession (manages users)
  ├── WordManager (loads JSON, selects words)
  └── GameSession (executes game loop)
       ├── Uses HintSystem, InputHandler, DisplayManager
       └── Updates Score & Points via SQLite
```

---

## 🎮 Game Mechanics

### 💰 Points vs. 🏆 Scores

| Action                  | Points         | Scores        |
|-------------------------|----------------|---------------|
| Correct guess           | +1 to +3       | —             |
| Complete word (win)     | —              | +total points |
| Use hint                | −3 to −5       | —             |
| Lose round              | 0              | 0             |

- Points = Currency for hints, carried between games  
- Scores = Only earned when winning; used for leaderboard  

### 🛒 Hint Types

| Hint                   | Cost (Points) |
|------------------------|---------------|
| Reveal a letter        | 5             |
| Reveal fact about word | 3             |
| Add extra guess        | 4             |

---

## 📂 Word Format (JSON)

Sample `words.json`:

```json
{
  "Animals": {
    "description": "Creatures in the animal kingdom",
    "easy": [
      { "word": "cat", "hint": "Purrs and meows" }
    ],
    "medium": [
      { "word": "giraffe", "hint": "Tallest land animal" }
    ]
  }
}
```

---

## 🛠 Installation & Running

### Prerequisites

- Java 17+
- Maven

### Run the game

```bash
mvn compile exec:java -Dexec.mainClass="com.example.Main"
```

### Run tests

```bash
mvn test
```

---

## 🧠 How the Game Works

1. Start from a CLI main menu.
2. Create or load a user.
3. Pick a category and difficulty.
4. Play by guessing letters.
5. Use hints (points are deducted).
6. Win to earn score and leaderboard rank.

---

## 🏆 Leaderboard & Stats

- SQLite stores:
  - `players` (user info)
  - `scores` (per game)
  - `points` (hint currency)
- `Top 5` users by highest score shown at any time

---

## 📈 Future Features

- Multiplayer mode (via sockets or REST)
- Daily challenge words
- Game streaks and badges
- Visual frontend (JavaFX or web)
- Export/import word sets

---

## 🤝 Contributing

```bash
# Fork, then:
git checkout -b feat/my-feature
git commit -m "Add new feature"
git push origin feat/my-feature
# Open a pull request
```

---

## 📄 License

This project is licensed under the MIT License.

