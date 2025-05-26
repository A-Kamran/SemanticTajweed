# SWRL Logical Definitions for Tajweed Rules

This file documents the Tajweed rules and their corresponding SWRL logical encodings. It is intended for researchers, developers, and educators working on computational Tajweed applications.

## 📘 Format
- **Rule Name**: The named Tajweed rule.
- **Description**: Explanation of the rule's phonetic or grammatical purpose.
- **SWRL Logical Condition**: The logic expression used for automatic detection.

---

## 🔁 Rule Descriptions with SWRL Logic

### Ghunnah Complete

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOP) ^ followedBy(?LOP, ?LO) ^ GhunnahLetter(?gl) ^ followedBy(?LO1, ?LO) ^ LetterOccurrence(?LO) ^ involvesLetter(?LO, ?gl) ^ involvesDiacritic(?LO, Shadda) ^ involvesDiacritic(?LO1, ?h) ^ BasicHarakaat(?h) ^ isPartOfWord(?LOP, ?w1) ^ isPartOfWord(?LO, ?w1) ^ swrlx:makeOWLThing(?R, ?LO, ?LO1) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ghunnah-Complete)
```

### Ikhfa Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Ikhfa Rule High Noon

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighNoon) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Tanween Ikhfa Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Tanween Ikhfa Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Tanween Ikhfa Rule Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)
```

### Ikhfa Rule Silent State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Ikhfa Shafawi Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaShafawiLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IkhfaAshShafawi)
```

### Iqlab Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Iqlab Rule Meem

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighMeem) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Tanween Iqlab Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Tanween Iqlab Rule Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)
```

### Iqlab Rule Silent State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Iqlab Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Idgham With Ghunnah Words

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ IdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Idghaam With Ghunnah Complete

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  CompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)
```

### Idghaam With Ghunnah Incomplete

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  IncompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)
```

### Tanween Idgham With Ghunnah Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Tanween Idgham With Ghunnah Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Tanween Idgham With Ghunnah Rule Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)
```

### Tanween Idghaam With Ghunnah Complete

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ CompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)
```

### Tanween Idghaam With Ghunnah Incomplete

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IncompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)
```

### Idgham With Ghunnah Rule Silent State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Idgham Without Ghunnah Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Tanween Idgham Without Ghunnah Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Tanween Idgham Without Ghunnah Rule Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)
```

### Idgham Without Ghunnah Rule Silent State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Idgham Shafawi Rule

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamShafawiLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi)
```

### Qalqalah

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)
```

### Qalqalah No Diacritic

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, NoDiacritic) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)
```

### Lesser Qalqalah

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun)^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleType(?R, LesserQalqalah)
```

### Greater Qalqalah

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleState(?R, Stopping) ^ hasRuleType(?R, GreaterQalqalah)
```

### Qalqalah Stopping

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)  ^ swrlx:makeOWLThing(?R, ?LO) ->  hasRuleState(?R, Stopping)
```

### Hamzatul Wasl

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, AlifWasl)
```

### Medd Alif1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Alif) ^ hasRuleType(?R, OriginalMedd)
```

### Alif Medd Dagger

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-DaggerAlif) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Wow1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Damma) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Wow Small Wow

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow-SmallWow) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Yaa1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Yaa Small Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, AlifMaksura) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallYaa) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Yaa Small High Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallHighYaa) ^ involvesLetter(?LO, Yaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallHighYaa) ^ hasRuleType(?R, OriginalMedd)
```

### Medd Munfasil Primary

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)
```

### Medd Munfasil Primary Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LOS, NoDiacritic) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)
```

### Medd Munfasil Primary Silent1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, Alif) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)
```

### Medd Munfasil Derived

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)
```

### Medd Munfasil Derived Harf Nida Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, Yaa) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)
```

### Medd Munfasil Derived Harf Nida Round Haa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, RoundHaa) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)
```

### Medd Munfasil Derived Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)
```

### Medd Munfasil Derived Silent1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif)  ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)
```

### Medd Muttasil Primary

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Primary)
```

### Medd Muttasil Primary

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)
```

### Medd Muttasil Primary Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^  followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, ?S) ^ SilentLetter(?S) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^  HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)
```

### Medd Muttasil Derived

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, ?L) ^ NonHaaYaaLetter(?L) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ involvesDiacritic(?LOH, ?d) ^ BasicHarakaat(?d) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Derived)
```

### Medd Muttasil Derived Dammatain

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, ?L) ^ NonHaaYaaLetter(?L) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ involvesDiacritic(?LOH, Dammatain) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Derived)
```

### Medd Muttasil Silent Alif Zero

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, Alif) ^ involvesDiacritic(?LOH, SmallHighRoundedZero) ^ followedBy(?LOH, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, Meddah) ^ followedBy(?LOF, ?LOS) ^ involvesLetter(?LOS, ?h) ^  HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)
```

### Medd Laazim Kalmi MK

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Sukun) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiMK)
```

### Medd Laazim Kalmi M

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)
```

### Medd Laazim Kalmi MSilent Laam

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ involvesLetter(?LOS, Laam) ^ involvesDiacritic(?LOS, NoDiacritic) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)
```

### Medd Al Arid Sukoon

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddAlAridSukoon) ^ hasRuleState(?RN, Stopping)
```

### Medd Al Arid Sukoon Derived

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOF) ^ involvesDiacritic(?LOF, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOF, endOfAyah) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddAlAridSukoon) ^ hasRuleState(?RN, Stopping)
```

### Medd Al Ewad

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
followedBy(?LO, ?LOF) ^ involvesDiacritic(?LO, Fathatain) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesLetter(?LO, ?p) ^ involvesPauseMarker(?LOF, endOfAyah) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)
```

### Medd Al Ewad Hamza

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Fathatain) ^ involvesLetter(?LO, Hamza) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)
```

### Medd As Sila Small Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallYaa)
```

### Medd As Sila Small Wow

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallWow)
```

### Medd As Sila Yaa Kubra

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaYaaKubra)
```

### Medd As Sila Wow Kubra

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaWowKubra)
```

### Leen Wow

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LO, Fatha) ^ Letter(?p) ^ involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Wow)
```

### Leen Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p)  ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^  LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^   involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Yaa)
```

### Medd Wow Leen

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddWowLeen) ^ hasRuleState(?R, Stopping)
```

### Medd Yaa Leen

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddYaaLeen) ^ hasRuleState(?R, Stopping)
```

### Medd As Sila Yaa Kubra Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAsSilaYaaKubra) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Laam Ash Shamsi

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)
```

### Laam Ash Shamsi Silent Laam

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)
```

### Laam Al Qamari

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ QamariLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, LaamAlQamari)
```

### Nabr Wow Shadd

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ involvesDiacritic(?LOF, Shadda) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaDamma(?diac) ^ involvesLetter(?LOF, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-WowShadd)
```

### Nabr Yaa Shadd

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaKasra(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Shadda) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-YaaShadd)
```

### Nabr Medd

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda)  ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM) ^ hasRuleType(?RN, Nabr-Medd)
```

### Nabr Stop Shadd

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Shadda) ^ involvesLetter(?LO, ?L) ^ involvesPauseMarker(?LO, endOfAyah) ^ NonGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-StopShadd) ^ hasRuleState(?R, Stopping)
```

### Nabr Hamza Sukun

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ HamzaLetter(?p) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-HamzaSukun)
```

### Tafkheem Highest

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ FathaFathatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Highest)
```

### Tafkheem Middle

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ DammaDammatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Middle)
```

### Tafkheem Lowest

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ KasraKasratain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Lowest)
```

### Tafkheem Alif

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)
```

### Tarqeeq Alif

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)
```

### Tafkheem Alif1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)
```

### Tarqeeq Alif1

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)
```

### Tanween Idgham Without Ghunnah Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Idghaam Mutajanisaan

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?B) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, ?L) ^ isSimilarTo(?B, ?L) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R,  Idghaam-Mutajanisaan)
```

### Idghaam Mutaqaribaan Laam

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Laam) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Raa) ^ isCloseTo(Laam, Raa) ^ isPartOfWord(?LOB, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

### Idghaam Mutaqaribaan Qaaf

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Qaaf) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Kaaf) ^ isCloseTo(Qaaf, Kaaf)  ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)
```

### Ghunnah Complete Madd

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, ?h) ^ BasicHarakaat(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)
```

### Ghunnah Complete Madd Dammatain

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, Dammatain) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)
```

### Medd Laazim Huroof EMuqata’aat

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ SixHarakahLetter(?L) ^ involvesDiacritic(?LO, Meddah) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 1) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLazim)
```

### Medd Laazim Huroof EMuqata’aat Ayah2

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ SixHarakahLetter(?L) ^ involvesDiacritic(?LO, Meddah) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 2) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLazim)
```

### Medd Laazim Huroof EMuqata’aat2

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ TwoHarakahLetter(?L) ^ involvesDiacritic(?LO, NoDiacritic) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 1) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, )
```

### Idgham Shafawi Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Medd Laazim Kalmi MDerived

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)
```

### Medd Muttasil Rule State

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Derived) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)
```

### Tafkheem Raa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)
```

### Tafkheem Raa Sukun

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)
```

### Tafkheem Raa Stopping

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa) ^ hasRuleState(?RN, Stopping)
```

### Tafkheem Raa Alif Wasl

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesLetter(?LOF, AlifWasl) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, Kasra)^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)
```

### Tafkheem Raa Tafkheem Letter

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Kasra) ^ involvesDiacritic(?LO, Sukun) ^ involvesLetter(?LO, Raa) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ PermanentTafkheemLetter(?h) ^ involvesDiacritic(?LOH, ?d) ^ NotKasra(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)
```

### Tarqeeq Raa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?d) ^ KasraKasratain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa)
```

### Tarqeeq Raa Sukun

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, ?d) ^ KasraKasratain(?d)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa)
```

### Tarqeeq Raa Stopping

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, ?d) ^ KasraKasratain(?d)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa) ^ hasRuleState(?RN, Stopping)
```

### Tarqeeq Raa Stopping Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, NoDiacritic)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa) ^ hasRuleState(?RN, Stopping)
```

### Laam Jalalah

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, Shadda) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LON) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w1) ^ isPartOfWord(?LON, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, LaamJalalah)
```

### Laam Jalalah Meem

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ followedBy(?LOB, ?LOP)  ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN, LaamJalalah)
```

### Tafkheem Laam

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^  involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LON) ^  isPartOfWord(?LO, ?w1)  ^  isPartOfWord(?LOH, ?w1) ^ isPartOfWord(?LON, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tafkheem-Laam-Jalalah)
```

### Tafkheem Laam Meem

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)
```

### Tafkheem Laam Wow

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO1) ^ involvesLetter(?LO1, Wow) ^ followedBy(?LO1, ?LO2) ^ involvesLetter(?LO2, Alif) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)
```

### Tafkheem Laam Silent

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO2) ^ involvesLetter(?LO2, ?S) ^ SilentLetter(?S) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)
```

### Tafkheem Laam Wow M

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO1) ^ involvesLetter(?LO1, Wow) ^ followedBy(?LO1, ?LO2)  ^ involvesLetter(?LO2, Alif) ^  followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH)  ^ involvesLetter(?LOH, RoundHaa)^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tafkheem-Laam-Jalalah)
```

### Tarqeeq Laam Silent Yaa

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, Kasra) ^ followedBy(?LOB, ?LO2) ^ involvesLetter(?LO2, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LO2, NoDiacritic) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tarqeeq-Laam)
```

### Tarqeeq Laam Kasra

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF)  ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, Shadda) ^ followedBy(?LOF, ?LOH)  ^ involvesLetter(?LOH, RoundHaa)^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tarqeeq-Laam)
```

### Tarqeeq Laam

**Description**: _(To be added: describe the Tajweed rule in plain language.)_

**SWRL Logical Condition**:
```swrl
LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, Kasra) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tarqeeq-Laam)
```
