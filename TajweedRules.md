# Definitions for Tajweed Rules and their corresponding SWRL Encodings

This file documents the Tajweed rules and their corresponding SWRL logical encodings. It is intended for researchers, developers, and educators working on computational Tajweed applications.

## 📘 Format
- **Rule Name**: The named Tajweed rule.
- **Description**: Explanation of the rule's phonetic or grammatical purpose.
- **SWRL Logical Condition**: The logic expression used for automatic detection.

---


### Ghunnah (Nasalization)

**Definition:**  
*Ghunnah* (غُنَّة) refers to a nasal sound produced during the pronunciation of certain Arabic letters, primarily **Meem (م)** and **Noon (ن)**, under specific phonetic conditions. It is a continuous, humming sound that resonates through the nasal cavity and is typically held for **two counts (harakahs)**.

**Linguistic Characteristics:**  
Ghunnah is an inherent feature of Meem and Noon when they appear with a **Shaddah (ّ)** — the diacritical mark indicating consonantal doubling. It is also an essential component of several Tajweed rules involving **Noon Sakinah** and **Tanween**, such as:

- **Idghaam with Ghunnah**
- **Ikhfaa**
- **Iqlab**

**Conditions Triggering Ghunnah:**

1. When **Meem (مّ)** or **Noon (نّ)** has a Shaddah.
2. When **Noon Sakinah (نْ)** or **Tanween (ــً, ــٍ, ــٌ)** is followed by specific letters:
   - **Idghaam with Ghunnah**: Followed by *ي، ن، م، و*
   - **Ikhfaa**: Followed by one of 15 Ikhfaa letters
   - **Iqlab**: Followed by *ب* (Ba)

**Examples:**

- <span dir="rtl" lang="ar">إِنَّ</span> (`inna`) → Noon with Shaddah is pronounced with Ghunnah.
- <span dir="rtl" lang="ar">مِمَّا</span> (`mimma`) → Meem with Shaddah is pronounced with Ghunnah.
- <span dir="rtl" lang="ar">مَن يُؤْمِنُ</span> (`man yu’minu`) → Noon Sakinah followed by Ya’ triggers Idghaam with Ghunnah.

**Function in Tajweed:**  
Ghunnah contributes to the fluency, clarity, and aesthetic quality of Quranic recitation. Correct application is critical for preserving the phonetic authenticity and melodic flow of the Quran.

### Ghunnah Complete

**Description**: This rule detects a complete Ghunnah when a Ghunnah letter (either Meem or Noon) carries a Shaddah and is preceded by a letter with a basic harakah (Fatha, Damma, or Kasra) within the same word. This phonetic condition causes a full nasal sound extension during recitation.

**Tajweed Significance**:
Complete Ghunnah is an essential phonetic phenomenon where the nasal sound is pronounced clearly and completely for two counts (morae) when a Ghunnah letter is mushaddad (has Shaddah). It emphasizes clarity and rhythm in Quranic recitation.

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOP) ^ 
followedBy(?LOP, ?LO) ^ 
GhunnahLetter(?gl) ^ 
followedBy(?LO1, ?LO) ^ 
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?gl) ^ 
involvesDiacritic(?LO, Shadda) ^ 
involvesDiacritic(?LO1, ?h) ^ 
BasicHarakaat(?h) ^ 
isPartOfWord(?LOP, ?w1) ^ 
isPartOfWord(?LO, ?w1) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LO1) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, Ghunnah-Complete)
```


### Idghām (Assimilation)

**Definition:**  
*Idghām* (إِدْغَام) in Tajweed refers to the **merging or assimilation** of one letter into another such that the first letter is not fully pronounced. It occurs when a **Noon Sakinah (نْ)** or **Tanween (ــً, ــٍ, ــٌ)** is followed by one of the **Idghām letters**.

---

### Types of Idghām

#### 1. Idghām with Ghunnah (nasalization)
- Triggered by the letters: **ي، ن، م، و**
- Includes a nasal sound (*ghunnah*) of approximately two counts
- Example:  
  - <span dir="rtl" lang="ar">مَن يَقُولُ</span> (`man yaqūlu`) – Noon merges into **Yā’** with nasalization

#### 2. Idghām without Ghunnah (no nasalization)
- Triggered by the letters: **ل، ر**
- No nasalization present
- Example:  
  - <span dir="rtl" lang="ar">مِن رَبِّهِمْ</span> (`min rabbihim`) – Noon merges into **Rā’** without nasalization

---

#### Idghām With Ghunnah Words
**Description:** This rule detects Idghām with Ghunnah when a Noon Sakinah (نْ) at the end of a word is immediately followed by one of the Idghām letters with Ghunnah (ي, ن, م, or و) at the beginning of the next word. It ensures that:
   - The Noon has a non-pronounced diacritic (i.e., it is silent),
   - It is directly followed by one of the valid Idghām-with-Ghunnah letters,
   - The two letters belong to different words (i.e., it's a cross-word Idghām),
   - The second word follows the first one in textual sequence (enforced by the wordIndex comparison).

**Tajweed Significance**: This variation is crucial in Tajweed because assimilation across words affects recitation but is often missed by basic letter-level implementations. By handling this structurally, your ontology captures an advanced contextual inference that’s often difficult to automate in procedural or pattern-matching systems.

```swrl
LetterOccurrence(?LO) ^
involvesLetter(?LO, Noon) ^
involvesDiacritic(?LO, ?diac) ^
NonPronouncedDiacritic(?diac) ^
followedBy(?LO, ?LOH) ^
involvesLetter(?LOH, ?h) ^
IdghaamWithGhunnahLetter(?h) ^
isPartOfWord(?LO, ?w1) ^
isPartOfWord(?LOH, ?w2) ^
wordIndex(?w1, ?index1) ^
wordIndex(?w2, ?index2) ^
swrlb:greaterThan(?index2, ?index1) ^
swrlx:makeOWLThing(?R, ?LO, ?LOH) ->
RuleOccurrence(?R) ^
occursAt(?R, ?LO) ^
hasRuleType(?R, IdghaamWithGhunnah)
```
**Examples:**

- <span dir="rtl" lang="ar">مَنَّ </span> (manna) — The Noon (ن) has a Shaddah and is preceded by a letter with Fatha. It is recited with a complete nasal sound.

#### Idghaam With Ghunnah – Complete
**Description:** This rule identifies complete Idghaam with Ghunnah, where a Noon Sakinah (نْ) with a non-pronounced diacritic (such as Sukun or no diacritic) is immediately followed—across word boundaries—by one of the Complete Idghaam with Ghunnah letters:(ي, ن, م, or و) . This combination causes the Noon Sakinah to fully merge into the following letter with a complete nasal sound (Ghunnah), typically extended for two counts (morae), and the Noon is not pronounced independently.

**Tajweed Significance**:  This is one of the core Idghaam types. The merging is complete, meaning the identity of the Noon Sakinah is fully assimilated into the next letter while retaining nasalization.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Noon) ^ 
involvesDiacritic(?LO, ?diac) ^ 
NonPronouncedDiacritic(?diac) ^ 
followedBy(?LO, ?LOH) ^ 
involvesLetter(?LOH, ?h) ^  
CompleteIdghaamWithGhunnahLetter(?h) ^ 
isPartOfWord(?LO, ?w1) ^ 
isPartOfWord(?LOH, ?w2) ^ 
wordIndex(?w1, ?index1) ^ 
wordIndex(?w2, ?index2) ^ 
swrlb:greaterThan(?index2, ?index1) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOH) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithGhunnah-Complete)

```
**Examples:**

- <span dir="rtl" lang="ar">مَن يَقُولُ </span> (man yaqulu) — Noon Sakinah (نْ) is followed by Ya (ي), triggering complete Idghaam with Ghunnah.


### Idghaam With Ghunnah Incomplete

**Description**:This rule detects cases of incomplete Idghaam with Ghunnah, where Noon Sakinah (نْ) or Tanween (ً ٍ ٌ) with a non-pronounced diacritic merges into a subsequent letter that is classified as an Incomplete Idghaam with Ghunnah letter. These letters usually include Meem (م) and Waw (و) under certain conditions.

In incomplete Idghaam, the merging is partial — the Noon or Tanween is not fully assimilated, and a faint nasalization (Ghunnah) is retained, but the resulting pronunciation preserves more of the original sound than in complete Idghaam.

**Tajweed Significance**: This form represents a transitional blend where nasalization occurs but the articulation of the Noon or Tanween isn't entirely lost. It is slightly more distinct than complete Idghaam and occurs between words.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Noon) ^ 
involvesDiacritic(?LO, ?diac) ^ 
NonPronouncedDiacritic(?diac) ^ 
followedBy(?LO, ?LOH) ^ 
involvesLetter(?LOH, ?h) ^  
IncompleteIdghaamWithGhunnahLetter(?h) ^ 
isPartOfWord(?LO, ?w1) ^ 
isPartOfWord(?LOH, ?w2) ^ 
wordIndex(?w1, ?index1) ^ 
wordIndex(?w2, ?index2) ^ 
swrlb:greaterThan(?index2, ?index1) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOH) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithGhunnah-InComplete)
```

**Examples:**

- <span dir="rtl" lang="ar">مِن مَالٍ </span>  — Noon Sakinah (نْ) is followed by Meem (م). This triggers incomplete Idghaam with Ghunnah, where a partial merging occurs with nasalization.

###  Tanween Idgham With Ghunnah Rule

**Description**: This rule detects cases of Idghaam with Ghunnah triggered by Tanween (ً ٍ ٌ) at the end of a word, when followed by one of the Idghaam with Ghunnah letters:(ي, ن, م, or و) 
In this case, the Tanween is assimilated into the following letter, and a nasal sound (Ghunnah) is produced. This occurs across word boundaries and is a foundational Tajweed rule applied frequently in Quranic recitation.

**Tajweed Significance**: 
- A Tanween merges into the following letter.
- Ghunnah (nasalization) is preserved.
- Occurs when the merging letter is from the set: ي ن م و.
- Common in fluent recitation and mandatory in Hafs 'an 'Asim

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ 
Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ 
Tanween(?T) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?L) ^ 
IdghaamWithGhunnahLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithGhunnah)
```

**Examples:**

- <span dir="rtl" lang="ar">عَلِيمٌ حَكِيمٌ  </span>  — The ٌ on عَلِيمٌ is Tanween Damma, and it's followed by a Ha’ (not an Idghaam letter, so no rule).
- <span dir="rtl" lang="ar"> غَفُورٌ رَحِيمٌ </span>  — But here the Tanween ٌ is followed by ر, triggering Idghaam without Ghunnah instead — this highlights how letter context matters.


###  Tanween Idgham With Ghunnah Rule State

**Description**: This rule represents a state-based extension of the Idghaam with Ghunnah rule, applied at the end of an Ayah. If an Idghaam with Ghunnah rule has already been detected on a Tanween or Noon Sakinah, and the letter occurs at a position marked with a pause (end of Ayah), this rule marks its recitation mode as Continuation (i.e., when the recitation continues smoothly without stopping).

**Tajweed Significance**: 
- In Quranic recitation, certain rules change depending on whether a reciter stops or continues.
- This rule ensures that Idghaam with Ghunnah is maintained if the reciter chooses to continue without stopping at the end of the Ayah.
- If the reciter stops, a different rule might apply (e.g., Izhaar or silence).
  
```swrl
LetterOccurrence(?LO) ^ 
involvesPauseMarker(?LO, endOfAyah) ^ 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithGhunnah) ^  
swrlx:makeOWLThing(?R, ?LO) -> 
hasRuleState(?R, Continuation)
```
**Examples:**

- <span dir="rtl" lang="ar">مِنْ وَرَائِهِمْ </span>  — If recited with continuation, Idghaam with Ghunnah is preserved.
- If recited with a stop, recitation rules change and Ghunnah may not be applied depending on the situation.

###  Tanween Idgham With Ghunnah Rule Silent

**Description**: This rule captures a variation of Idghaam with Ghunnah where the Tanween is followed indirectly by a letter that would normally trigger Idghaam with Ghunnah, but is separated by a silent letter. Specifically:
- A Tanween occurs at position ?LO
- It is followed by a Silent Letter at ?LOF
- The actual Idghaam with Ghunnah Letter appears after the silent letter at ?LOH

This accounts for silent bridges between the Tanween and the triggering letter (ي ن م و), ensuring non-obvious assimilation patterns are still recognized.

**Tajweed Significance**: In continuous recitation, some letters may not be pronounced (e.g., due to orthographic elongations or recitation modes).
This rule maintains Idghaam with Ghunnah despite an intervening silent letter, enabling precise and authentic Tajweed automation.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ 
followedBy(?LOF, ?LOH) ^ 
LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) -> 
RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —
  
###  Tanween Idghaam With Ghunnah Complete

**Description**: This rule captures cases of Complete Idghaam with Ghunnah when Tanween is followed by one of the Idghaam With Ghunnah letters — ي ن م و — and the merging is complete (i.e., no trace of the original Tanween sound is pronounced). This usually happens when the letters belong to different words or result in full assimilation with Shaddah on the merging letter. 

**Applicable Scenario**: 
- A letter with Tanween (e.g., ً ٍ ٌ) is followed by a letter from the CompleteIdghaamWithGhunnahLetter set.
- The next letter is fully assimilated, and the Tanween is no longer independently pronounced.
- Shaddah is typically present on the assimilated letter.
  
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^
involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^
involvesLetter(?LOF, ?L) ^ CompleteIdghaamWithGhunnahLetter(?L) ^
swrlx:makeOWLThing(?R, ?LO, ?LOF) ->
RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^
hasRuleType(?R, IdghaamWithGhunnah-Complete)

```

**Examples:**
- <span dir="rtl" lang="ar">سَمِيعٌۭ نَّصِيرًا  </span>  — (samīʿun-naṣīraa) - Tanween on ع (ʿayn) is Followed by نَّ (noon with shaddah), Resulting in complete merging with ghunnah.
  

###  Tanween Idghaam With Ghunnah Incomplete

**Description**: This rule detects Incomplete Idghaam with Ghunnah, where a letter with Tanween (ً ٍ ٌ) is followed by one of the incomplete Idghaam with Ghunnah letters, typically resulting in partial assimilation. Unlike complete Idghaam, some trace of the original Tanween sound may still be present or more perceptible.

**Applicable Scenario**: 
- A Tanween-marked letter is followed by a Ya or Waw (from the IncompleteIdghaamWithGhunnahLetter set).
- The merging occurs but without full assimilation (often less emphasized or lighter).
- Typically occurs within the same word or when contextual pronunciation doesn’t demand full assimilation.

```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^
involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^
involvesLetter(?LOF, ?L) ^ IncompleteIdghaamWithGhunnahLetter(?L) ^
swrlx:makeOWLThing(?R, ?LO, ?LOF) ->
RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^
hasRuleType(?R, IdghaamWithGhunnah-InComplete)

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —
  
###  Idgham With Ghunnah Rule Silent State

**Description**: This rule identifies the continuation state of Idghaam with Ghunnah when the letter that follows the Idghaam trigger is a Silent Letter, and that silent letter is marked with an end-of-Ayah pause marker. In this scenario, while there may be a visual pause (end marker), there is no actual pause in recitation. The Idghaam with Ghunnah rule is still considered active and ongoing, and the merging pronunciation should still be applied.

**Applicable Scenario**: 
- A Noon Sakinah or Tanween occurs and merges into a Ghunnah letter (ن، م، ي، و).
- The following letter is silent (e.g., an Alif Sakinah).
- The pause marker (end of Ayah) is attached to the silent letter.
- Despite the visual presence of a pause, recitation flows without a stop, so Ghunnah continues naturally.

```swrl
LetterOccurrence(?LO) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?S) ^ 
SilentLetter(?S) ^ 
involvesPauseMarker(?LOF, endOfAyah) ^ 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithGhunnah) ^  
swrlx:makeOWLThing(?R, ?LO) -> 
hasRuleState(?R, Continuation)

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

**Interpretation:**
This rule ensures the ontology correctly captures linguistic continuity of Idghaam with Ghunnah even when a pause marker is syntactically present but not phonologically enacted.


###  Idgham Without Ghunnah Rule

**Description**: This rule detects Idghaam without Ghunnah, which occurs when a Noon Sakinah (نْ) or Tanween is followed by one of the letters ر (Ra) or ل (Lam) — collectively called Idghaam Without Ghunnah Letters. In such cases, the Noon or Tanween is merged into the next letter without nasalization (Ghunnah). The merging is smooth and complete but void of the nasal sound.

**Applicable Scenario**: 
- A Noon Sakinah or Tanween is present.
- The following letter is ر or ل.
- The Noon is unpronounced (sukoon or non-pronounced diacritic).
- The merging happens without Ghunnah.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Noon) ^ 
involvesDiacritic(?LO, ?diac) ^ 
NonPronouncedDiacritic(?diac) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?L) ^ 
IdghaamWithoutGhunnahLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithoutGhunnah)

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —
  
**Interpretation:** This rule ensures the system distinguishes non-nasal Idghaam from other forms, allowing accurate Tajweed rule classification for cases involving Ra or Lam after Noon Sakinah or Tanween.

###  Tanween Idgham Without Ghunnah Rule


**Description**:

**Applicable Scenario**: 
- 

```swrl

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —


###  Rule

**Description**:

**Applicable Scenario**: 
- 

```swrl

```

**Examples:**

- <span dir="rtl" lang="ar"> </span>  —

###  Rule

**Description**:

**Applicable Scenario**: 
- 

```swrl

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

###  Rule

**Description**:

**Applicable Scenario**: 
- 

```swrl

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

###  Rule

**Description**:

**Tajweed Significance**: 

```swrl

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

###  Rule

**Description**:

**Tajweed Significance**: 

```swrl
```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —
  
