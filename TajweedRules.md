# Definitions for Tajweed Rules and their corresponding SWRL Encodings

This file documents the Tajweed rules and their corresponding SWRL logical encodings. It is intended for researchers, developers, and educators working on computational Tajweed applications.

## 📘 Format
- **Rule Name**: The named Tajweed rule.
- **Description/Definition**: Explanation of the rule's phonetic or grammatical purpose.
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

###  Ghunnah After Medd (Ghunnah-Complete after a Natural Madd)

**Description**: This rule identifies the occurrence of complete Ghunnah that follows a Natural Medd (Primary elongation), when the Ghunnah letter (Meem or Noon) with a Shaddah and basic harakah (Fatha, Damma, or Kasra) appears after the Madd. This captures the recitation pattern where elongation is followed by nasalization.

**Applicable Scenario**: 
- Start with an occurrence of a Natural Madd rule at letter ?LO.
- Traverse two positions forward: ?LO → ?LOH → ?LOF.
- At ?LOF, confirm the letter is a Ghunnah letter (Noon or Meem).
- Ensure ?LOF has both:
  - Shaddah (gemination mark),
  - A Basic Harakah (vowel mark like Fatha, Damma, Kasra).
- Confirm all involved letters are in the same word.

```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ 
hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ 
followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ 
GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ 
involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, ?h) ^ BasicHarakaat(?h) ^ 
isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ 
swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> 
RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)

```

**Examples:**
- <span dir="rtl" lang="ar">إِنَّا أَنزَلْنَاهُ </span>  — Noon with Shaddah (Ghunnah), after a Madd on Alif in the same word.

###  Ghunnah After Medd with Dammatain (Ghunnah-Complete with Double Dammah)

**Description**:This rule identifies the occurrence of complete Ghunnah that follows a Natural Medd, where the Ghunnah letter (Noon or Meem) has both:
   - A Shaddah (gemination),
   - A Dammatain (double Dammah, indicating Tanween).

This pattern signals that nasalization (Ghunnah) should be applied following an elongated vowel sound, often occurring in stopping or intonation-heavy parts of recitation.

**Applicable Scenario**: 
- A Natural Medd occurs at ?LO.
- Two letters after (?LOF), a Ghunnah letter (Noon/Meem) appears.
- This Ghunnah letter carries both:
   - A Shaddah,
   - A Dammatain (a type of Tanween).
- All letters involved are in the same word.

```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ 
hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ 
followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ 
GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ 
involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, Dammatain) ^ 
isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ 
swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> 
RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)

```

**Examples:**
- <span dir="rtl" lang="ar">سَمِيعٌۭ عَلِيمٌۭ </span>  — A Noon with Shaddah and Dammatain after a Madd, indicating a strong nasalization at the end of the word.
  
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

### Tanween Idgham Without Ghunnah Rule


**Description**:This rule detects Idghaam without Ghunnah when Tanween (ً ٍ ٌ) is followed by one of the letters ر (Ra) or ل (Lam) — known as Idghaam Without Ghunnah Letters. In this case, the Tanween is completely merged into the next letter without nasalization (Ghunnah). The transition is smooth, and the nasal sound is omitted.

**Applicable Scenario**: 
- A Tanween diacritic is applied to a letter.
- The following letter is either ر or ل.
- The merge happens without any nasalization.
- This rule captures this specific interaction for rule inference.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ 
Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ 
Tanween(?T) ^ 
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
  
**Interpretation:**  This rule handles cases where Tanween merges into Ra or Lam without producing the nasal sound associated with Ghunnah, and is a crucial distinction from Idghaam with Ghunnah.

###  Tanween Idgham Without Ghunnah Rule (With Silent Letter Between)

**Description**: This rule detects Idghaam without Ghunnah when a letter with Tanween (ً ٍ ٌ) is followed by a Silent Letter, which is then followed by ر (Ra) or ل (Lam) — the designated Idghaam Without Ghunnah Letters. The rule captures the transitional scenario where a non-pronounced (silent) character exists between the Tanween and the merging letter, and the nasalization (Ghunnah) is still not applied.

**Applicable Scenario**: 
- A letter carries a Tanween diacritic.
- The next letter is a silent letter (e.g., Alif Sakinah or similar).
- That silent letter is immediately followed by Ra or Lam.
- The Tanween is fully merged without Ghunnah despite the intermediate silent character.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ 
Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ 
Tanween(?T) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?S) ^ 
SilentLetter(?S) ^ 
followedBy(?LOF, ?LOH) ^ 
LetterOccurrence(?LOH) ^ 
involvesLetter(?LOH, ?L) ^ 
IdghaamWithoutGhunnahLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) -> 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithoutGhunnah)
```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

**Interpretation:** Even when a silent separator exists between the Tanween and the Idghaam target letter, the merging process can still occur without nasalization, which this rule captures.

###  Idghām Without Ghunnah – Continuation State at End of Ayah

**Description**:This rule identifies instances of Idghām Without Ghunnah where the Noon Sākinah or Tanween merges into ر (Raa') or ل (Laam) without nasalization, and this merging continues even at the end of the ayah, i.e., there is no stopping or pause in recitation.
This rule specifically detects whether Idghām Without Ghunnah applies despite the occurrence of an end-of-ayah marker, indicating that the recitation is intended to continue smoothly rather than pause.

Key Notes:
- This rule refines the application of Idghām Without Ghunnah in real recitation contexts, by accounting for pausal vs non-pausal intentions.
- It is important for reciters who wish to apply correct Tajweed even when not pausing at the end of a verse.


```swrl
LetterOccurrence(?LO) ^ 
involvesPauseMarker(?LO, endOfAyah) ^ 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithoutGhunnah) ^  
swrlx:makeOWLThing(?R, ?LO) 
-> hasRuleState(?R, Continuation)
```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

###  Idghaam Without Ghunnah – Rule State (Continuation after Silent Letter & End of Ayah)

**Description**: This rule determines the state of an existing Idghaam Without Ghunnah occurrence when it is followed by a Silent Letter, and a Pause Marker (such as the end of an Ayah). In this context, the rule state is inferred as "Continuation", meaning that although the recitation pauses (e.g., due to the end of a verse), the Idghaam rule logically holds as a structural feature of the text, even if not recited fully

```swrl
LetterOccurrence(?LO) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?S) ^ 
SilentLetter(?S) ^ 
involvesPauseMarker(?LOF, endOfAyah) ^ 
RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamWithoutGhunnah) ^  
swrlx:makeOWLThing(?R, ?LO) -> 
hasRuleState(?R, Continuation)

```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —

**Interpretation:** This rule ensures your system properly classifies Idghaam Without Ghunnah as logically applicable even when not verbally completed due to a pause in recitation. This is crucial for knowledge representation, where rule validity and phonetic realization may diverge.


###  Idghām Al-Mutajānissayn (Idghām of Similar Letters)

**Description**:This rule identifies Idghām Al-Mutajānissayn—a form of assimilation that occurs when two adjacent letters share the same point of articulation (makhraj), but differ slightly in characteristics (ṣifāt). In this case, the first letter is non-voweled (sākin) and the second follows it immediately and is similar in articulation.  Upon recitation, the first letter is assimilated into the second, and only the second is pronounced—with a shaddah (gemination) if applicable.

**Articulatory Basis:** 
The two letters must come from the same articulation point but may differ in strength, airflow, or other ṣifāt.
Common pairs include: 
- <span dir="rtl" lang="ar"> ت with ط </span> 
- <span dir="rtl" lang="ar"> د with ت </span>  
- <span dir="rtl" lang="ar"> ذ with ظ </span>  

**Applicable Scenario**: 
- The first letter must be sākin (with sukoon or implied sukoon).
- The second letter must be mutaharrik (with a vowel).
- The letters must be adjacent, either within or across words.
  
```swrl
LetterOccurrence(?LOB) ^ 
involvesLetter(?LOB, ?B) ^ 
involvesDiacritic(?LOB, NoDiacritic) ^ 
followedBy(?LOB, ?LO) ^ 
involvesLetter(?LO, ?L) ^ 
isSimilarTo(?B, ?L) ^ 
swrlx:makeOWLThing(?R, ?LOB) 
-> RuleOccurrence(?R) ^ 
occursAt(?R, ?LOB) ^ 
hasRuleType(?R, Idghaam-Mutajanisaan)

```

**Examples:**
- <span dir="rtl" lang="ar">قَد تَّبَيَّنَ — d merges into t → pronounced: قَتَّبَيَّنَ </span>  
- <span dir="rtl" lang="ar">إِذ ظَلَمُوا → dh merges into zh → pronounced: إِظَّلَمُوا </span>  

###  Idghām Al-Mutaqāribayn (إِدْغَام الْمُتَقَارِبَيْن)

**Description**: Idghām Al-Mutaqāribayn occurs when two consecutive letters have close articulation points (makhārij) and similar characteristics (ṣifāt). In this rule, the first letter is sākin (has no vowel) and the second is mutaḥarrik (has a vowel). The first letter is merged into the second, often resulting in a geminated (shaddah) pronunciation of the second letter. "Idghām" means merging, and "Mutaqāribayn" refers to two letters that are close (in articulation and characteristics).

**Articulatory Basis:** 
- The first letter must be sākin.
- The second letter must be mutaḥarrik.
- Letters must be adjacent.
- Occurs within or across words.

Letter pairs include: 
- <span dir="rtl" lang="ar"> ق with ك (Qaaf + Kaaf) </span> 
- <span dir="rtl" lang="ar"> ل with ر (Laam + Raa) </span>

- These pairs are close in articulation:
    Qaaf and Kaaf are both back-of-tongue letters.
    Laam and Raa are both front-of-tongue letters.

```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Laam) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Raa) ^ isCloseTo(Laam, Raa) ^ isPartOfWord(?LOB, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Qaaf) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Kaaf) ^ isCloseTo(Qaaf, Kaaf)  ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

**Examples:**
- <span dir="rtl" lang="ar"> اِرْكَبْ مَعَنَا → ب merges into م → pronounced: اِرْكَمَّعَنَا </span>  

### Idghaam Ash-Shafawi (إدغام شفوي)

**Definition:**  Idghām Shafawī is a Tajweed rule that involves the merging of Meem Sākinah (مْ) into another Meem (م) that directly follows it. The result is a nasalized, merged sound (ghunnah), articulated using the lips — hence the term "shafawī" which means "labial."


<!-- | **Rule**           | **Trigger**                                    | **Description**                                            |
| ------------------ | ---------------------------------------------- | ---------------------------------------------------------- |
| **Idghām Shafawī** | Meem Sākinah (مْ) followed by Meem (م)         | Merge with ghunnah (nasal sound) using lips                |
| **Ikhfā’ Shafawī** | Meem Sākinah (مْ) followed by Ba (ب)           | Hide the Meem sound partially, with light ghunnah          |
| **Izhār Shafawī**  | Meem Sākinah (مْ) followed by any other letter | Pronounce the Meem clearly without merging or nasalization |
-->


**Applicable Scenario**: 
- The first letter is Meem Sākinah (مْ) — a Meem with no vowel.
-  It is followed immediately by another Meem (م).
-  The merging occurs with ghunnah, pronounced for two counts.
-  It can happen within one word or between two words.


```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Meem) ^ 
involvesDiacritic(?LO, ?diac) ^ 
NonPronouncedDiacritic(?diac) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, Meem) ^ 
hasLetterPosition(?LO, ?P) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) 
-> RuleOccurrence(?R) ^ 
occursAt(?R, ?LO) ^ 
hasRuleType(?R, IdghaamAshShafawi)

```
**Examples:**
- <span dir="rtl" lang="ar">فَهُمْ مِسْتَبْشِرُونَ</span> → The Meem in فَهُمْ (which is Sākinah) merges into the Meem in مِسْتَبْشِرُونَ:


###  Ikhfāʾ (الإخفاء)

**Definition**: Ikhfāʾ literally means to hide. In Tajweed, it refers to partially hiding the pronunciation of Noon Sakinah (نْ) or Tanween (ــًــٍــٌ) when followed by one of 15 specific letters. The sound is produced from a position between Iẓhār (clear) and Idghām (merged)—accompanied by a light nasalization (ghunnah) for about two counts.
The 15 letters that trigger Ikhfāʾ when they follow Noon Sakinah or Tanween are:  <span dir="rtl" lang="ar"> ت ث ج د ذ ز س ش ص ض ط ظ ف ق ك </span>
These are collectively remembered using the mnemonic:
<span dir="rtl" lang="ar">     "صف ذا ثنا كم جاد شخص قد سما دم طيب زد في تقى ضع ظالما"</span> 

###  Variants and Logic Conditions

### 1. Standard Ikhfāʾ Rule (Noon Sakinah)
**Description**: Detects Ikhfāʾ when Noon Sakinah is followed by any of the 15 Ikhfāʾ letters.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Noon) ^ 
involvesDiacritic(?LO, NoDiacritic) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?L) ^ 
IkhfaLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) 
-> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)

```

**Examples:**
- <span dir="rtl" lang="ar"> مِنْكُمْ </span>  — Noon is lightly hidden with nasalization before the Kaf

###  2. Ikhfāʾ with Small High Noon

**Description**: Identifies Ikhfāʾ in contexts where Noon is indicated with Small High Noon diacritic, typically seen in Uthmani script.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, Noon) ^ 
involvesDiacritic(?LO, SmallHighNoon) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?L) ^ 
IkhfaLetter(?L) ^ 
hasLetterPosition(?LO, ?P) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) 
-> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)

```

###  3. Tanween Ikhfa Rule (Ikhfāʾ of Tanween)

**Description**:Detects Ikhfāʾ after Tanween, when followed by any of the 15 Ikhfāʾ letters.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ 
Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ 
Tanween(?T) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?L) ^ 
IkhfaLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) 
-> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)

```

###  4. Tanween Ikhfāʾ Silent (Skip letter before rule)

**Description**:Covers Ikhfāʾ cases where a silent letter exists between the Tanween and the actual Ikhfāʾ letter.

```swrl
LetterOccurrence(?LO) ^ 
involvesLetter(?LO, ?p) ^ 
Letter(?p) ^ 
involvesDiacritic(?LO, ?T) ^ 
Tanween(?T) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?S) ^ 
SilentLetter(?S) ^ 
followedBy(?LOF, ?LOH) ^ 
LetterOccurrence(?LOH) ^ 
involvesLetter(?LOH, ?L) ^ 
IkhfaLetter(?L) ^ 
swrlx:makeOWLThing(?R, ?LO, ?LOF) 
-> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)

```
###   5. Ikhfāʾ Rule State (End of Ayah)

**Description**: Annotates the state of the rule when it occurs at the end of an ayah, to indicate recitation continuation is expected, not a full stop.

```swrl
LetterOccurrence(?LO) ^ 
involvesPauseMarker(?LO, endOfAyah) ^ 
RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ 
hasRuleType(?R, Ikhfa) ^ 
swrlx:makeOWLThing(?R, ?LO) 
-> hasRuleState(?R, Continuation)

```
###  6. Ikhfāʾ Rule Silent State

**Description**:Similar to above, but also includes silent letter detection before the pause, enabling proper tagging for real recitation contexts.

```swrl
LetterOccurrence(?LO) ^ 
followedBy(?LO, ?LOF) ^ 
LetterOccurrence(?LOF) ^ 
involvesLetter(?LOF, ?S) ^ 
SilentLetter(?S) ^ 
involvesPauseMarker(?LOF, endOfAyah) ^ 
RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ 
hasRuleType(?R, Ikhfa) ^ 
swrlx:makeOWLThing(?R, ?LO) 
-> hasRuleState(?R, Continuation)

```
###  Rule

**Description**:

```swrl
```


###  Rule

**Description**:

```swrl
```

**Examples:**
- <span dir="rtl" lang="ar"> </span>  —
  
