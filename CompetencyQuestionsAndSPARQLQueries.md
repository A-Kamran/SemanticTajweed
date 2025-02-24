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
```### Competency Question :
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
