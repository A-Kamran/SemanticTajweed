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
### Competency Question 3:
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
### Competency Question 4:
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

### Competency Question 5:
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

### Competency Question 6:
**Question:What occurrences of Letter X are pronounced heavy (Tafkheem) or light (Tarqeeq)?** 

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


### Competency Question 7:
**Question:**

**SPARQL Query:**
```


```
### Competency Question 8:
**Question:** 

**SPARQL Query:**
```

```
