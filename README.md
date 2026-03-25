# 🌿 SacredClarity

![Android](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue?style=for-the-badge)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-purple?style=for-the-badge)
![NLP](https://img.shields.io/badge/NLP-TF--IDF-orange?style=for-the-badge)
![Offline](https://img.shields.io/badge/Mode-Offline-success?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**SacredClarity** is a calm, reflective Android application that helps users understand their emotions and gain clarity through insights derived from the **Bhagavad Gita**.

The app uses an **offline NLP engine (TF-IDF)** to analyze user input and match it with relevant philosophical lessons, providing meaningful, real-life guidance.

---

## ✨ Features

* **Emotion Reflection Interface**
  Users can freely express their thoughts and feelings.

* **Bhagavad Gita Insights**
  Matches user emotions with relevant verses and life lessons.

* **Offline NLP (TF-IDF Engine)**
  No internet required — all processing happens on-device.

* **Real-life Guidance**
  Outputs practical lessons instead of raw verses.

* **Calm & Minimal UI**
  Designed with soft colors, animations, and a meditative experience.

---

## How It Works

```
User Input
     ↓
Text Preprocessing
     ↓
TF-IDF Similarity Engine
     ↓
Best Matching Emotion / Verse
     ↓
Real-life Lesson Display
```

The app compares the user's input with a dataset of **emotion descriptions + psychological interpretations** to find the most relevant match.

---

## Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **Android Studio**
* **TF-IDF (Natural Language Processing)**
* **CSV-based local dataset**
* **Offline architecture**

---

## Project Structure

```
app/
 └─ src/main/
     ├─ java/com/example/sacredclarity/
     │    MainActivity.kt
     │    Verse.kt
     │    VerseRepository.kt
     │    TextProcessor.kt
     │    TfidfEngine.kt
     │
     ├─ res/
     │    drawable/
     │
     ├─ assets/
     │    verses.csv
     │
     └─ AndroidManifest.xml
```

---

## Getting Started

1. Clone the repository:

```
git clone https://github.com/Lyynn777/SacredClarity.git
```

2. Open in **Android Studio**

3. Run the app on an emulator or physical device

---

## 🌱 Future Improvements

* Enhanced NLP (semantic similarity / embeddings)
* Expanded verse dataset (700+ verses)
* Reflection journaling system
* Emotion tracking & history
* Optional cloud-based intelligence

---

## Purpose

SacredClarity is designed to:

* Encourage **self-reflection**
* Provide **clarity in moments of confusion**
* Bridge **ancient wisdom with modern emotional needs**

---

## 💡 Inspiration

Inspired by the idea that **answers often lie within**, and wisdom texts like the **Bhagavad Gita** can guide us when we learn how to interpret our emotions.

---

> *"From confusion to clarity — through reflection."*
