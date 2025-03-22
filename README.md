things used/will be used so far
- sqlite
- junit and mockito for testing
- sementic versioning and workflows
- json for words




# Hangman Game - Rules & Mechanics

## 📌 Overview
The Hangman game is a word-guessing game where players try to complete a hidden word by guessing letters. Players can earn **points** for correct guesses and **scores** for winning rounds. Points can be used to **buy hints**, while scores determine leaderboard rankings.

---

## 🎯 Objectives
- Correctly guess all the letters of a word before running out of attempts.
- Earn **points** for each correct guess.
- Earn **scores** by completing words.
- Spend **points** to buy hints if stuck.
- Maintain a **streak** for bonus rewards.

---

## 🏆 Score & 💰 Points System (Independent)
| **Action**                         | **Effect on Points**            | **Effect on Score**             |
|-------------------------------------|--------------------------------|---------------------------------|
| Guess a correct letter             | +1 (Easy), +2 (Medium), +3 (Hard) | No effect                      |
| Guess a wrong letter               | No effect                      | No effect                      |
| Complete the word (Win)            | No effect                      | +Total Points Earned for the Round |
| Lose the round                     | No effect                      | No effect                      |
| Win multiple games in a row        | No effect                      | +Win Streak Bonus              |

📌 **Points** = Earned per letter guessed correctly (used to buy hints).  
📌 **Scores** = Only awarded when the player **wins a round** (used for leaderboard ranking).

---

## 🎮 Game Flow
1️⃣ **Main Menu**: Play Game | Pick User | View Stats | Exit.  
2️⃣ **Pick User** (Load | Delete | Create).  
3️⃣ **Play** → Pick **Category** → Pick **Difficulty** → Start Game.  
4️⃣ **Guess Letters**: Earn points per correct guess.  
5️⃣ **Win or Lose**: Win to earn a score. Losing gives no points.  
6️⃣ **Continue?**: Stay in the same difficulty to keep your current score.  
7️⃣ **Use Points for Hints** (Optional).  

---

## 🛒 Hint System (Uses Points)
| **Hint Type**                   | **Cost (Points)** |
|---------------------------------|------------------|
| **Reveal a random letter**      | 5 Points        |
| **Show the category of the word** | 3 Points        |
| **Remove a wrong letter choice**  | 4 Points        |

📌 **Players can buy hints anytime using available points.**  
📌 **Points do NOT reset between games (carry over).**

---

## 🔥 Streak & Challenge Bonuses
- **Win Streak Bonus** → Consecutive wins increase the score multiplier.  
- **Difficulty Progression Bonus** → Switching to a harder difficulty grants a +10% initial score boost.  
- **"Almost Win" Compensation** → If a player is **90% close to winning**, they get 50% of their potential score.  

---

## 📜 Game Rules
1️⃣ **If a player wins and continues without changing category/difficulty, the score carries over.**  
2️⃣ **Players only earn a score if they win the round.**  
3️⃣ **Points are earned per letter and used for hints.**  
4️⃣ **Switching difficulty resets the score but keeps points.**  
5️⃣ **Players lose after exceeding maximum incorrect guesses.**  
6️⃣ **Leaderboard is based on score, not points.**  

---

## 🎯 Future Expansions
- **Multiplayer Mode** with shared word pools.  
- **Timed Challenges** (Earn more points for faster completion).  
- **Daily Words & Streak Tracking**.  

---

## 🛠️ Developer Notes
- **`GameSession`** → Runs gameplay loop, handles score & points.  
- **`WordPicker`** → Selects a word based on category & difficulty.  
- **`DisplayManager`** → Handles UI output (static methods).  
- **`InputHandler`** → Manages user input.  
- **`DatabaseManager` (Future)** → Stores leaderboard & user data (SQLite).  

---

This document serves as a **reference for coding**. You can update it as features evolve. 🚀

