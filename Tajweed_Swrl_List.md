# Enhanced SWRL Definitions for Tajweed Rules

This document groups SWRL conditions by their corresponding Tajweed rule types. Each section provides a clear summary of the rule and lists all associated logic conditions.

## 1. AlifWasl/Hamzatul Wasl

**Description**: Silent Alif at the beginning of words, pronounced when continuing but silent when stopping.

**SWRL Logic Variants**:
### Rule: Hamzatul Wasl
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, AlifWasl)
```

## 2. Ghunnah-Complete

**Description**: Full nasal sound with Noon or Meem accompanied by Shadda, pronounced clearly and fully.

**SWRL Logic Variants**:
### Rule: Ghunnah Complete
```swrl
LetterOccurrence(?LOP) ^ followedBy(?LOP, ?LO) ^ GhunnahLetter(?gl) ^ followedBy(?LO1, ?LO) ^ LetterOccurrence(?LO) ^ involvesLetter(?LO, ?gl) ^ involvesDiacritic(?LO, Shadda) ^ involvesDiacritic(?LO1, ?h) ^ BasicHarakaat(?h) ^ isPartOfWord(?LOP, ?w1) ^ isPartOfWord(?LO, ?w1) ^ swrlx:makeOWLThing(?R, ?LO, ?LO1) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ghunnah-Complete)
```

## 3. GreaterQalqalah

**Description**: Strong echoing sound on Qalqalah letters at the end of a word or with a stopping pause.

**SWRL Logic Variants**:
###  Rule: Greater Qalqalah
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleState(?R, Stopping) ^ hasRuleType(?R, GreaterQalqalah)
```

## 4. Idghaam-Mutajanisaan

**Description**: Assimilation of letters with similar articulation points but different characteristics.

**SWRL Logic Variants**:
### Variant 1: Idghaam Mutajanisaan
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?B) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, ?L) ^ isSimilarTo(?B, ?L) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R,  Idghaam-Mutajanisaan)
```

## 5. Idghaam-Mutaqaribaan

**Description**: Assimilation of letters with closely related articulation points.

**SWRL Logic Variants**:
### Variant 1: Idghaam Mutaqaribaan Laam
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Laam) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Raa) ^ isCloseTo(Laam, Raa) ^ isPartOfWord(?LOB, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

### Variant 2: Idghaam Mutaqaribaan Qaaf
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Qaaf) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Kaaf) ^ isCloseTo(Qaaf, Kaaf)  ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

## 6. IdghaamAshShafawi

**Description**: Merging of Meem Sakinah with Ba, producing a nasal sound.

**SWRL Logic Variants**:
### Variant 1: Idgham Shafawi Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamShafawiLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi)
```

### Variant 2: Idgham Shafawi Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 7. IdghaamWithGhunnah

**Description**: Merging of Noon Sakinah or Tanween with (ي ن م و) with nasalization.

**SWRL Logic Variants**:
### Variant 1: Idgham With Ghunnah Words
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ IdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Variant 2: Tanween Idgham With Ghunnah Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Variant 3: Tanween Idgham With Ghunnah Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Variant 4: Tanween Idgham With Ghunnah Rule Silent
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Variant 5: Idgham With Ghunnah Rule Silent State
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 8. IdghaamWithGhunnah-Complete

**Description**: Full nasal assimilation with Shadda, ensuring a complete nasal sound.

**SWRL Logic Variants**:
### Variant 1: Idghaam With Ghunnah Complete
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  CompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)
```

### Variant 2: Tanween Idghaam With Ghunnah Complete
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ CompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)
```

## 9. IdghaamWithGhunnah-InComplete

**Description**: Partial nasal assimilation without Shadda, producing a lighter nasal sound.

**SWRL Logic Variants**:
### Variant 1: Idghaam With Ghunnah Incomplete
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  IncompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)
```

### Variant 2: Tanween Idghaam With Ghunnah Incomplete
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IncompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)
```

## 10. IdghaamWithoutGhunnah

**Description**: Merging without nasalization when Noon Sakinah or Tanween is followed by ر or ل.

**SWRL Logic Variants**:
### Variant 1: Idgham Without Ghunnah Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Variant 2: Tanween Idgham Without Ghunnah Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Variant 3: Tanween Idgham Without Ghunnah Rule Silent
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Variant 4: Idgham Without Ghunnah Rule Silent State
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Variant 5: Tanween Idgham Without Ghunnah Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 11. Ikhfa

**Description**: Concealment of Noon Sakinah or Tanween, producing a nasal sound between Idhaar and Idghaam.

**SWRL Logic Variants**:
### Variant 1: Ikhfa Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Variant 2: Ikhfa Rule High Noon
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighNoon) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Variant 3: Tanween Ikhfa Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Variant 4: Tanween Ikhfa Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Variant 5: Tanween Ikhfa Rule Silent
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Variant 6: Ikhfa Rule Silent State
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 12. IkhfaAshShafawi

**Description**: Concealment of Meem Sakinah when followed by Ba, producing a light nasal sound.

**SWRL Logic Variants**:
### Rule : Ikhfa Shafawi Rule
```swrl
 LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaShafawiLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IkhfaAshShafawi)
```

## 13. Iqlab

**Description**: Conversion of Noon Sakinah or Tanween into Meem before Ba, with complete nasalization.

**SWRL Logic Variants**:
### Variant 1: Iqlab Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Variant 2: Iqlab Rule Meem
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighMeem) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Variant 3: Tanween Iqlab Rule
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Variant 4: Tanween Iqlab Rule Silent
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Variant 5: Iqlab Rule Silent State
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Variant 6: Iqlab Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 14. LaamAlQamari

**Description**: Clear pronunciation of Laam before Qamari letters (e.g., القمر).

**SWRL Logic Variants**:
### Variant 1: Laam Al Qamari
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ QamariLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, LaamAlQamari)
```

## 15. LaamAshShamsi

**Description**: Silent Laam before Shamsi letters, with Shadda on the next letter (e.g., الشمس).

**SWRL Logic Variants**:
### Variant 1: Laam Ash Shamsi
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)
```

### Variant 2: Laam Ash Shamsi Silent Laam
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)
```

## 16.  Leen-Wow

**Description**: (Heuristic description not confidently generated)

**SWRL Logic Variants**:
### Variant 1: Leen Wow
```swrl
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LO, Fatha) ^ Letter(?p) ^ involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Wow)
```

## 17. Leen-Yaa

**Description**: (Heuristic description not confidently generated)

**SWRL Logic Variants**:
### Variant 1: Leen Yaa
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p)  ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^  LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^   involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Yaa)
```

## 18. LesserQalqalah

**Description**: Weaker echoing sound compared to Greater Qalqalah, usually in the middle of a word.

**SWRL Logic Variants**:
### Variant 1: Lesser Qalqalah
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun)^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleType(?R, LesserQalqalah)
```

## 19.  Medd-Alif

**Description**: Elongation of Alif vowel sound by two Harakaat when preceded by Fatha.

**SWRL Logic Variants**:
### Variant 1: Medd Alif1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Alif) ^ hasRuleType(?R, OriginalMedd)
```

## 20. Medd-DaggerAlif

**Description**: Short elongation with a small Alif symbol above certain letters, pronounced for two Harakaat.

**SWRL Logic Variants**:
### Variant 1: Alif Medd Dagger
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-DaggerAlif) ^ hasRuleType(?R, OriginalMedd)
```

## 21. Medd-Wow

**Description**: Elongation of Wow vowel sound by two Harakaat when preceded by Damma.

**SWRL Logic Variants**:
### Variant 1: Medd Wow1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Damma) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow) ^ hasRuleType(?R, OriginalMedd)
```

## 22. Medd-Wow-SmallWow

**Description**: Short elongation with a small Wow symbol.

**SWRL Logic Variants**:
### Variant 1: Medd Wow Small Wow
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow-SmallWow) ^ hasRuleType(?R, OriginalMedd)
```

## 23. Medd-Yaa

**Description**: Elongation of Yaa vowel sound by two Harakaat when preceded by Kasra.

**SWRL Logic Variants**:
### Variant 1: Medd Yaa
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 2: Medd Yaa1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

## 24. Medd-Yaa-SmallHighYaa

**Description**: Short elongation of Yaa with a small high Yaa symbol.

**SWRL Logic Variants**:
### Variant 1: Medd Yaa Small High Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallHighYaa) ^ involvesLetter(?LO, Yaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallHighYaa) ^ hasRuleType(?R, OriginalMedd)
```

## 25. Medd-Yaa-SmallYaa

**Description**: Short elongation of Yaa with a small Yaa symbol.

**SWRL Logic Variants**:
### Variant 1: Medd Yaa Small Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, AlifMaksura) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallYaa) ^ hasRuleType(?R, OriginalMedd)
```

## 26. MeddAlEwad

**Description**: Elongation of Tanween Fatha at the end of a verse when stopping, replacing it with Alif sound.

**SWRL Logic Variants**:
### Variant 1: Medd Al Ewad
```swrl
followedBy(?LO, ?LOF) ^ involvesDiacritic(?LO, Fathatain) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesLetter(?LO, ?p) ^ involvesPauseMarker(?LOF, endOfAyah) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)
```

### Variant 2: Medd Al Ewad Hamza
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Fathatain) ^ involvesLetter(?LO, Hamza) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)
```

## 27. MeddAsSila

**Description**: Short elongation with a small Yaa symbol for the pronoun Ha.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Small Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallYaa)
```

### Variant 2: Medd As Sila Small Wow
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallWow)
```

## 28. MeddAsSila-SmallWow

**Description**: Short elongation with a small Wow symbol for the pronoun Ha.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Small Wow
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallWow)
```

## 29. MeddAsSila-SmallYaa

**Description**: Short elongation with a small Yaa symbol for the pronoun Ha.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Small Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallYaa)
```

## 30. MeddAsSilaKubra

**Description**: Extended elongation with a small Yaa symbol, pronounced up to 6 Harakaat.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Yaa Kubra
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaYaaKubra)
```

### Variant 2: Medd As Sila Wow Kubra
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaWowKubra)
```

## 31. MeddAsSilaWowKubra

**Description**: Extended elongation with a small Wow symbol, pronounced up to 6 Harakaat.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Wow Kubra
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaWowKubra)
```

## 32. MeddAsSilaYaaKubra

**Description**: Extended elongation with a small Yaa symbol, pronounced up to 6 Harakaat.

**SWRL Logic Variants**:
### Variant 1: Medd As Sila Yaa Kubra
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaYaaKubra)
```

### Variant 2: Medd As Sila Yaa Kubra Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAsSilaYaaKubra) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 33. MeddMuttasil-Derived

**Description**: Primary form of joined elongation, pronounced for 4 to 6 Harakaat.

**SWRL Logic Variants**:
### Variant 1: Medd Muttasil Rule State
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Derived) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

## 34. MeddMuttasil-Primary

**Description**: Primary form of joined elongation, pronounced for 4 to 6 Harakaat.

**SWRL Logic Variants**:
### Variant 1: Medd Muttasil Primary
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Primary)
```

## 35. MeddWowLeen

**Description**: Soft elongation of Wow when preceded by Fatha and followed by Sukoon.

**SWRL Logic Variants**:
### Variant 1: Medd Wow Leen
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddWowLeen) ^ hasRuleState(?R, Stopping)
```

## 36. MeddYaaLeen

**Description**: Soft elongation of Yaa when preceded by Fatha and followed by Sukoon.

**SWRL Logic Variants**:
### Variant 1: Medd Yaa Leen
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddYaaLeen) ^ hasRuleState(?R, Stopping)
```

## 37. Nabr-HamzaSukun

**Description**: Special stopping behavior with pause on certain letters or elongations (Nabr).

**SWRL Logic Variants**:
### Variant 1: Nabr Hamza Sukun
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ HamzaLetter(?p) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-HamzaSukun)
```

## 38. Nabr-StopShadd

**Description**: Nasal sound (Ghunnah) identified by presence of Shadda on a specific letter.

**SWRL Logic Variants**:
### Variant 1: Nabr Stop Shadd
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Shadda) ^ involvesLetter(?LO, ?L) ^ involvesPauseMarker(?LO, endOfAyah) ^ NonGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-StopShadd) ^ hasRuleState(?R, Stopping)
```

## 39. Nabr-WowShadd

**Description**: Special stopping behavior with pause on certain letters or elongations (Nabr).

**SWRL Logic Variants**:
### Variant 1: Nabr Wow Shadd
```swrl
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ involvesDiacritic(?LOF, Shadda) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaDamma(?diac) ^ involvesLetter(?LOF, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-WowShadd)
```

## 40. Nabr-YaaShadd

**Description**: Special stopping behavior with pause on certain letters or elongations (Nabr).

**SWRL Logic Variants**:
### Variant 1: Nabr Yaa Shadd
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaKasra(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Shadda) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-YaaShadd)
```

## 41. OriginalMedd

**Description**: Elongation of Alif vowel sound by two Harakaat when preceded by Fatha.

**SWRL Logic Variants**:
### Variant 1: Medd Alif1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Alif) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 2: Alif Medd Dagger
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-DaggerAlif) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 3: Medd Wow1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Damma) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 4: Medd Wow Small Wow
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow-SmallWow) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 5: Medd Yaa
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 6: Medd Yaa1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 7: Medd Yaa Small Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, AlifMaksura) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallYaa) ^ hasRuleType(?R, OriginalMedd)
```

### Variant 8: Medd Yaa Small High Yaa
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallHighYaa) ^ involvesLetter(?LO, Yaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallHighYaa) ^ hasRuleType(?R, OriginalMedd)
```

## 42. Qalqalah

**Description**: Echoing sound produced with Sukoon on Qalqalah letters (ق ط ب ج د), creating a bouncing effect.

**SWRL Logic Variants**:
### Variant 1: Qalqalah
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)
```

### Variant 2: Qalqalah No Diacritic
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, NoDiacritic) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)
```

### Variant 3: Lesser Qalqalah
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun)^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleType(?R, LesserQalqalah)
```

### Variant 4: Greater Qalqalah
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleState(?R, Stopping) ^ hasRuleType(?R, GreaterQalqalah)
```

### Variant 5: Qalqalah Stopping
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)  ^ swrlx:makeOWLThing(?R, ?LO) ->  hasRuleState(?R, Stopping)
```

## 43. Tafkheem-Alif

**Description**: Heaviness in Alif pronunciation when preceded by certain letters or sounds.

**SWRL Logic Variants**:
### Variant 1: Tafkheem Alif
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)
```

### Variant 2: Tafkheem Alif1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)
```

## 44. Tafkheem-Highest

**Description**: Strongest form of heaviness, usually with heavy letters in prominent positions.

**SWRL Logic Variants**:
### Variant 1: Tafkheem Highest
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ FathaFathatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Highest)
```

## 45. Tafkheem-Lowest

**Description**: Weakest form of heaviness in certain phonetic contexts.

**SWRL Logic Variants**:
### Variant 1: Tafkheem Lowest
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ KasraKasratain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Lowest)
```

## 46. Tafkheem-Middle

**Description**: Medium level of heaviness depending on the surrounding letters.

**SWRL Logic Variants**:
### Variant 1: Tafkheem Middle
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ DammaDammatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Middle)
```

## 47. Tarqeeq-Alif

**Description**: Light pronunciation of Alif in certain contexts.

**SWRL Logic Variants**:
### Variant 1: Tarqeeq Alif
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)
```

### Variant 2: Tarqeeq Alif1
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)
```
