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
## 2. Data Analytics Competency Questions (Statistical Queries)

### Competency Question 13:
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
### Competency Question 14:
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
### Competency Question 15:
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
### Competency Question 16:
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

### Competency Question 17:
**Question: What are the most frequently occurring tajweed rules?** 

**SPARQL Query:**
```
```
### Competency Question 18:
**Question: What are the least frequently occurring tajweed rules?** 

**SPARQL Query:**
```
```
### Competency Question 19:
**Question: Are there overlapping occurrences of Rule X and Rule Y in the same verse?** 

**SPARQL Query:**
```
```
### Competency Question 20:
**Question: How many instances of Rule X are triggered in StopState or ContinuationState?** 

**SPARQL Query:**
```
```
### Competency Question 21:
**Question: Which letters have a higher occurrence of Ghunnah than Ikhfa?** 

**SPARQL Query:**
```
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
