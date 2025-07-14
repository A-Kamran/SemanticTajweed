# Competency questions for the Semantic Tajweed Ontology

The given SPARQL are _examples_ that may be reinterpreted and reused for applications.

## 1. Modeling Competency Questions (Ontology Structure & Rules)

### Competency Question 1:
**Question: What are all the rules applicable to Letter X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
SELECT distinct ?rule 
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :occursAt ?letterOccurrrence .
  ?ruleOccurrence :hasRuleType ?rule. 
  ?letterOccurrrence :involvesLetter ?letter.
  FILTER (?letter = :Baa)  # Replace with any letter (e.g., :Noon)
}
```
     
### Competency Question 2:
**Question: Does every Tajweed rule apply to at least one letter?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

ASK {
  FILTER NOT EXISTS {
    ?rule rdf:type :Rule .
    FILTER NOT EXISTS {
      ?ruleOccurrence rdf:type :RuleOccurrence .
      ?ruleOccurrence :hasRuleType ?rule .
      ?ruleOccurrence :occursAt ?letter .
    }
  }
}
```
### Competency Question 3:
**Question: What is the articulation point of Letter X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
SELECT ?articulationPoint
WHERE {
  ?letter rdf:type :Letter .
  ?letter :hasArticulationPoint ?articulationPoint .
    FILTER (?letter = :Baa)
}
```
### Competency Question 4:
**Question: How many articulation points are defined for AnatomicalUnit X?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT (COUNT(DISTINCT ?point) AS ?pointCount)
WHERE {
  ?point :involvesAnatomicalUnit ?unit.
   ?unit :isPartOf ?area.
    FILTER (?area = :Tongue)   
}
```
### Competency Question 5:
**Question: What are the characteristics of Letter X?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?characteristic
WHERE {
  ?letter rdf:type :Letter .
  ?letter :hasCharacteristic ?characteristic .
  FILTER (?letter = :Noon)
}
```

### Competency Question 6:
**Question: Which letters have strong characteristics?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

Select distinct * 
where{
    ?letter a :Letter;
  	:hasCharacteristic ?characteristic.
    ?characteristic :hasCharacteristicType :TheStrongCharacteristics.

}
```
### Competency Question 7:
**Question: Which letters belong to Group Y?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?letter
WHERE {
  ?letter rdf:type :Letter .
  ?letter :belongsToGroup :HaroofAshshafawiya .
}
```

### Competency Question 8:
**Question: What occurrences of Letter X are pronounced heavy (Tafkheem) or light (Tarqeeq)?** 

**SPARQL Query:**
```
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?occurrence 
WHERE {
  ?occurrence rdf:type :LetterOccurrence .
  ?occurrence :hasRuleInstance ?x.
  ?x :hasRuleType :Tarqeeq-Raa.  
  ?occurrence :isPartOfWord/:wordText ?word.
}
```


### Competency Question 9:
**Question: Which letters trigger elongation rules (Medd)?**

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
SELECT DISTINCT ?letter
WHERE {
  ?letter rdf:type :NaturalMeddPrimary .
}
```
### Competency Question 10:
**Question: What rules are triggered when stopping on Letter X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT distinct ?rule
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :occursAt ?LO .
  ?ruleOccurrence :hasRuleState :Stopping .
  ?ruleOccurrence :hasRuleType ?rule.
  ?LO :involvesLetter ?letter.
  FILTER (?letter = :Baa)
}
```
### Competency Question 11:
**Question: What rules are triggered when continuing after Letter X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT distinct ?rule
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :occursAt ?LO .
  ?ruleOccurrence :hasRuleState :Stopping .
  ?ruleOccurrence :hasRuleType ?rule.
  ?LO :involvesLetter ?letter.
  FILTER (?letter = :Baa)
}

```
### Competency Question 12:
**Question: What rules depend on the state of recitation (stopping or continuation)?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT distinct ?rule ?state 
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :occursAt ?LO .
  ?ruleOccurrence :hasRuleState ?state.
  ?ruleOccurrence :hasRuleType ?rule.
}
```
### Competency Question 13:
**Question:What are the possible articulation points for letters in Group X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?letter ?articulation
WHERE {
  ?letter :belongsToGroup :HaroofAshshafawiya .
  ?letter :hasArticulationPoint ?articulation.
}
ORDER BY ?letter

```

### Competency Question 14:
**Question:Retrieve all ayahs that contain Tafkheem letters with articulation from the throat.** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText ?artic
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType ?ruleType.
  FILTER(CONTAINS(LCASE(STR(?ruleType)), "tafkheem"))

  ?occ :involvesLetter ?letter.
  ?letter :hasArticulationPoint/:involvesAnatomicalUnit ?artic.
  ?artic rdfs:label ?articLabel.
  FILTER(CONTAINS(LCASE(STR(?articLabel)), "throat"))
  ?ayah :verseText ?ayahText.	
}
ORDER BY ?ayah

```
### Competency Question 15:
**Question:List ayahs containing Laam Jalalah pronounced with Tafkheem.** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText 
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :involvesLetter :Laam. 
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType :Tafkheem-Laam-Jalalah.
  ?ayah :verseText ?ayahText. 

}
ORDER BY ?ayah

```

## 2. Data Analytics Competency Questions (Statistical Queries)

### Competency Question 16:
**Question:Which ayah contains the most occurrences of the Hams (TheWhisper) letters?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?ayah ?ayahText (COUNT(?occ) AS ?whisperCount)
WHERE {
  :TheWhisper :isCharacteristicOf ?letter .
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :involvesLetter ?letter.
  ?ayah :verseText ?ayahText. 
}
GROUP BY ?ayah ?ayahText
ORDER BY DESC(?whisperCount)
LIMIT 1
```
### Competency Question 17:
**Question:Which verses exhibit both Ikhfa and Ghunnah?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ1, ?occ2.

  # First rule: Ikhfa
  ?occ1 :hasRuleInstance ?ikhfaInstance.
  ?ikhfaInstance :hasRuleType ?ikhfaRule.
  FILTER(CONTAINS(LCASE(STR(?ikhfaRule)), "ikhfa"))

  # Second rule: Ghunnah
  ?occ2 :hasRuleInstance ?ghunnahInstance.
  ?ghunnahInstance :hasRuleType ?ghunnahRule.
  FILTER(CONTAINS(LCASE(STR(?ghunnahRule)), "ghunnah"))

  OPTIONAL { ?ayah :verseText ?ayahText. }
}
ORDER BY ?ayah

```
### Competency Question 18:
**Question:Retrieve ayahs with rare or optional Medd rules (e.g., Madd Al-Ewad).** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText ?word
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType :MeddAlEwad.
 ?ayah :verseText ?ayahText. 
}
ORDER BY ?ayah

```
### Competency Question 19:
**Question:Which surahs have the greatest variety of articulation points within a single ayah?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?surah ?ayah (MAX(?articVariety) AS ?maxArticPointsInAyah)
WHERE {
  SELECT ?surah ?ayah (COUNT(DISTINCT ?artic) AS ?articVariety)
  WHERE {
    ?surah :hasVerse ?ayah.
    ?ayah :hasWord ?word.
    ?word :hasLetterOccurrence ?occ.
    ?occ :involvesLetter ?letter.
    ?letter :hasArticulationPoint ?artic.
  }
  GROUP BY ?surah ?ayah
}
GROUP BY ?surah ?ayah
ORDER BY DESC(?maxArticPointsInAyah)

```
### Competency Question 20:
**Question:Identify all verses that contain letters articulated from the throat and have the characteristic TheWhisper (Hams).** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText ?word
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :involvesLetter ?letter.

  ?letter :hasArticulationPoint/:involvesAnatomicalUnit ?artic.
  ?artic rdfs:label ?articLabel.
  FILTER(CONTAINS(LCASE(STR(?articLabel)), "throat"))
  :TheWhisper :isCharacteristicOf ?letter.

  OPTIONAL { ?ayah :verseText ?ayahText. }
}
ORDER BY ?ayah

```
### Competency Question 21:
**Question:Which ayah has the highest number of Medd rules?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?ayah ?ayahText (COUNT(?ruleInstance) AS ?meddCount)
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType ?ruleType.
  FILTER(CONTAINS(LCASE(STR(?ruleType)), "medd"))  
 ?ayah :verseText ?ayahText.
}
GROUP BY ?ayah ?ayahText
ORDER BY DESC(?meddCount)
LIMIT 1

```

### Competency Question 22:
**Question: How many instances of Rule X are found in Chapter/Surah Y?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT (COUNT(?ruleOccurrence) AS ?count)
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :hasRuleType :Qalqalah .
  ?ruleOccurrence :occursAt ?LO.
    ?LO :isPartOfWord/:isPartOfVerse/:isPartOfChapter :CH112.
 }

```
### Competency Question 23:
**Question: What are all the rules applied in Chapter/Surah Y?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT distinct ?rule
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :hasRuleType ?rule .
  ?ruleOccurrence :occursAt ?LO.
    ?LO :isPartOfWord/:isPartOfVerse/:isPartOfChapter :CH112.
 }

```
### Competency Question 24:
**Question: How many occurrences of Tajweed Rule X are there across the Quran?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT (COUNT(?ruleOccurrence) AS ?count)
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :hasRuleType :Ikhfa .
  ?ruleOccurrence :occursAt ?LO.
 }

```
### Competency Question 25:
**Question:What are the different types of Medd rules?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ruleType 
WHERE {
  ?ruleInstance :hasRuleType ?ruleType.
  FILTER(CONTAINS(LCASE(STR(?ruleType)), "medd")) 
}


```

### Competency Question 26:
**Question: Which verses contain the most occurrences of Rule X?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT ?verse (COUNT(?ruleOccurrence) AS ?count)
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence .
  ?ruleOccurrence :hasRuleType :Ikhfa .
  ?ruleOccurrence :occursAt/:isPartOfWord/:isPartOfVerse ?verse.
    
 }
GROUP BY ?verse
ORDER BY DESC(?count)
LIMIT 1
```
![image](https://github.com/user-attachments/assets/361bffb7-c2c1-4b3e-9bde-3f3822fe54eb)

### Competency Question 27:
**Question: What are the most frequently occurring tajweed rules?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?ruleType (COUNT(?ruleInstance) AS ?ruleCount)
WHERE {
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType ?ruleType.
 
}
GROUP BY ?ruleType 
ORDER BY DESC(?ruleCount)
Limit 10
```
### Competency Question 28:
**Question: What are the least frequently occurring tajweed rules?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?ruleType (COUNT(?ruleInstance) AS ?ruleCount)
WHERE {
  ?occ :hasRuleInstance ?ruleInstance.
  ?ruleInstance :hasRuleType ?ruleType.
 
}
GROUP BY ?ruleType 
ORDER BY Asc(?ruleCount)
Limit 10
```
### Competency Question 29:
**Question: Are there overlapping occurrences of Rule X and Rule Y in the same verse?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?ayah ?ayahText ?occ
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.

  # First rule instance (Ikhfa)
  ?occ :hasRuleInstance ?ruleInstance1.
  ?ruleInstance1 :hasRuleType ?ruleType1.
  FILTER(CONTAINS(LCASE(STR(?ruleType1)), "ikhfa"))

  # Second rule instance (Ghunnah)
  ?occ :hasRuleInstance ?ruleInstance2.
  ?ruleInstance2 :hasRuleType ?ruleType2.
  FILTER(CONTAINS(LCASE(STR(?ruleType2)), "ghunnah"))

  OPTIONAL { ?ayah :verseText ?ayahText. }
}
ORDER BY ?ayah

```
### Competency Question 30:
**Question: How many instances of Rule X are triggered in StopState or ContinuationState?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT (COUNT(?ruleOccurrence) AS ?ruleXCount)
WHERE {
  ?ruleOccurrence rdf:type :RuleOccurrence ;
                  :hasRuleType ?ruleType ;
                  :hasRuleState ?state .

  FILTER(CONTAINS(LCASE(STR(?ruleType)), "qalqalah"))
    FILTER(?state IN (:Stopping))
}

```
### Competency Question 31:
**Question: Which letters have a higher occurrence of Ghunnah than Ikhfa?** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?letter (COUNT(DISTINCT ?ghOcc) AS ?ghunnahCount) (COUNT(DISTINCT ?ikOcc) AS ?ikhfaCount)
WHERE {
  {
    SELECT ?letter ?ghOcc
    WHERE {
      ?ghOcc :involvesLetter ?letter ;
             :hasRuleInstance ?ghRuleInstance .
      ?ghRuleInstance :hasRuleType ?ghType .
      FILTER(CONTAINS(LCASE(STR(?ghType)), "ghunnah"))
    }
  }
  OPTIONAL {
    SELECT ?letter ?ikOcc
    WHERE {
      ?ikOcc :involvesLetter ?letter ;
             :hasRuleInstance ?ikRuleInstance .
      ?ikRuleInstance :hasRuleType ?ikType .
      FILTER(CONTAINS(LCASE(STR(?ikType)), "ikhfa"))
    }
  }
}
GROUP BY ?letter
HAVING (COUNT(DISTINCT ?ghOcc) > COUNT(DISTINCT ?ikOcc))
ORDER BY DESC(?ghunnahCount)

```

<!--
### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```

### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```
### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```

### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```
### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```
### Competency Question :
**Question: ** 

**SPARQL Query:**
```
```
-->

**All instances of Rule Occurrence with subcategory showing the reasoning of why it is occurring**
**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX tajweed: <http://www.semantictajweed.com/ontology/>

SELECT Distinct  ?verseText ?wtext ?LO  ?rule
WHERE {
      ?ruleOccurrence rdf:type tajweed:RuleOccurrence .
      ?ruleOccurrence tajweed:hasRuleType ?rule .
      ?rule rdf:type :NabrRule.
      ?ruleOccurrence tajweed:occursAt ?LO.
      ?LO tajweed:isPartOfWord/:wordIndex ?wno ;
       tajweed:isPartOfWord/tajweed:wordText ?wtext ;
        tajweed:isPartOfWord/tajweed:isPartOfVerse ?v1 .
    	?v1 tajweed:verseText ?verseText.
     }
```


**All verses starting with a particular letter** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?ayah ?ayahText
WHERE {
  ?surah :hasVerse ?ayah.
  ?ayah :hasWord/:hasLetterOccurrence ?occurrence.
  ?occurrence :hasLetterPosition 1;
              :involvesLetter ?letter.
  ?letter rdfs:label ?letterLabel.
  FILTER (STR(?letterLabel) = "ق")
  OPTIONAL { ?ayah :verseText ?ayahText. }
}
```

**First Letter of Every Ayah** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>

Select * 
{	
    :CH112 :hasVerse ?ayah.
    ?ayah :hasWord/:hasLetterOccurrence ?LO.
    ?ayah :verseText ?ayahT.
    ?LO :hasLetterPosition 1;
    	:involvesLetter ?letter;
    	:involvesDiacritic ?Diacritic.
    
    
}
```


**Visualise Word-wise Rules Occurrence with contruct** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>

CONSTRUCT {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?word :wordText ?wordText.
  ?occ :involvesLetter ?letter.
  ?occ :hasRuleType ?rule.
  ?ayah :verseText ?ayahText.
}
WHERE {
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?word :wordText ?wordText.
  ?occ :hasRuleInstance ?RO.
  ?RO :hasRuleType ?rule.
  OPTIONAL { ?ayah :verseText ?ayahText. }
  OPTIONAL { ?occ :involvesLetter ?letter. }
  
  FILTER (?word = :CH018_V019_W034)

} 

```

**Rule frequency by surah** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?surah (COUNT(?rule) AS ?ruleCount)
WHERE {
  ?surah :hasVerse ?ayah.
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occ.
  ?occ :hasRuleInstance ?RO.
  ?RO :hasRuleType ?rule.
  FILTER (CONTAINS(STR(?rule), "Tafkheem"))
}
GROUP BY ?surah 
ORDER BY ASC(?surah)
```

**Verse with most Rule X** 

**SPARQL Query:**
```
PREFIX : <http://www.semantictajweed.com/ontology/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?ayah ?ayahText  (COUNT(?rule) AS ?ruleCount)
WHERE {
  ?surah :hasVerse ?ayah.  # or remove :CH112 if you want all surahs
  ?ayah :hasWord ?word.
  ?word :hasLetterOccurrence ?occurrence.
  ?occurrence :hasRuleInstance ?RO.
  ?RO :hasRuleType ?rule.
  FILTER (CONTAINS(STR(?rule), "Ikhfa"))
  OPTIONAL { ?ayah :verseText ?ayahText. }
}
GROUP BY ?ayah ?ayahText
ORDER BY DESC(?ruleCount)
LIMIT 1
```

**No. of Words in each Surah** 

**SPARQL Query:**
```

PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?surah (COUNT(?word) AS ?wordCount)
WHERE {
  ?surah :hasVerse ?ayah.
  ?ayah :hasWord ?word.
}
GROUP BY ?surah
ORDER BY ?surah
```

**count how many ayahs start with each letter** 

**SPARQL Query:**
```
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX : <http://www.semantictajweed.com/ontology/>

SELECT ?letter (COUNT(DISTINCT ?ayah) AS ?ayahCount)
WHERE {
  ?surah :hasVerse ?ayah.
  ?ayah :hasWord/:hasLetterOccurrence ?LO.
  ?ayah :verseText ?ayahT.
  ?LO :hasLetterPosition 1;
       :involvesLetter ?letterT.
   ?letterT rdfs:label ?letter
}
GROUP BY ?letter
ORDER BY DESC(?ayahCount)

```
<!--

**h** 

**SPARQL Query:**
```

```

**h** 

**SPARQL Query:**
```

```


**h** 

**SPARQL Query:**
```

```

**h** 

**SPARQL Query:**
```

```


**h** 

**SPARQL Query:**
```

```
-->
