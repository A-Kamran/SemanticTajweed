# MIRO Report for the SemanticTajweed Ontology

This document reports the metadata and development details of the [SemanticTajweed Ontology](https://github.com/A-Kamran/SemanticTajweedKG/blob/main/SemanticTajweedOntology.owl) following the MIRO (Minimum Information for Reporting of an Ontology) guidelines [1].

---

## A. Basics

1. **Ontology name (MUST)**  
   *SemanticTajweed Ontology*, version 1.0.1

2. **Ontology owner (MUST)**  
   [Amna Kamran](https://github.com/A-Kamran)

3. **Ontology license (MUST)**  
   [CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/)

4. **Ontology URL (MUST)**  
   <https://github.com/A-Kamran/SemanticTajweedKG/blob/main/SemanticTajweedOntology.owl>

5. **Ontology repository (MUST)**  
   <https://github.com/A-Kamran/SemanticTajweedKG>

6. **Methodological framework (MUST)**  
   The ontology was developed using the METHONTOLOGY methodology, which includes stages for specification, knowledge acquisition, conceptualisation, formalisation, integration, and implementation.

---

## B. Motivation

1. **Need (MUST)**  
   There was no existing formal ontology capturing the principles, rules, and articulatory structures of Tajweed. This ontology addresses the gap by enabling machine-processable knowledge representation and reasoning over Tajweed recitation rules.

2. **Competition (MUST)**  
   While datasets such as CPFair offer tagged Tajweed annotations, they lack a formal OWL ontology with reasoning capabilities. No direct competitor offers multilingual support, phonological rule inference, and RDF-based Quranic representation.

3. **Target audience (MUST)**  
   Tajweed instructors and learners, NLP and semantic web researchers, Quranic linguists, and developers of educational or recitation-support technologies.

---

## C. Scope, Requirements, Development Community

1. **Scope and coverage (MUST)**  
   Models 48 Tajweed rules, 17 articulation points, characteristics of 28 Arabic letters, and Quranic content (surahs, verses, words, and letter occurrences). It is aligned with the Hafs ʿan ʿĀṣim recitation style.

2. **Development community (MUST)**  
   Developed at [IKnex], National University of Computer & Emerging Sciences (NUCES-FAST), Islamabad, Pakistan.

3. **Communication (MUST)**  
   Project issues and discussions are managed via GitHub:  
   <https://github.com/A-Kamran/SemanticTajweedKG/issues>

---

## D. Knowledge Acquisition

1. **Knowledge acquisition method (MUST)**  
   Multi-source acquisition combining:
   - Structured extraction from Tajweed texts (e.g., “Tajweed Rules” by Kareema Carol).
   - Interviews with certified Tajweed experts.
   - Review of existing ontologies and Quranic linguistic resources.

2. **Source knowledge location (SHOULD)**  
   - [Tajweed Book](https://islamhouse.com/en/books/396784/)
   - Expert interviews
   - CPFair Tajweed dataset
   - Quran Ontology, DBpedia resources

3. **Content selection (SHOULD)**  
   Selected based on Tajweed relevance, frequency in recitation, and inclusion in canonical Quranic teaching. Coverage focuses on rules active in the Hafs style.

---

## E. Ontology Content

1. **Knowledge representation language (MUST)**  
   OWL 2 (DL profile). Uses a subset corresponding to the Description Logic ALCRIF(D).

2. **Development environment (OPTIONAL)**  
   - Protégé 5.5.0  
   - OWL API  
   - Apache Jena and SWRL API (for reasoning and population)

3. **Ontology metrics (SHOULD)**  
   - Classes: 103  
   - Object Properties: 32  
   - Data Properties: 18  
   - Individuals: 226 (excluding auto-generated rule inferences)

4. **Incorporation of other ontologies (MUST)**  
   - [DCTerms](http://purl.org/dc/terms)  
   - [DBpedia](http://dbpedia.org)

5. **Entity naming convention (MUST)**  
   - Classes: `CamelCase`  
   - Object/Data properties: `mixedCaseVerbStyle`  
   - Labels are multilingual: Arabic (`@ar`), English (`@en`), and Urdu (`@ur`)

6. **Identifier generation policy (MUST)**  
   Programmatically generated instance URIs using structured naming conventions (e.g., `CH114_V001_W001_LO001`)

7. **Identity metadata policy (MUST)**  
   Entities annotated using `dcterms:description` in English and Urdu.

8. **Upper ontology (MUST)**  
   Uses `dcterms`, but no formal upper ontology like BFO is employed.

9. **Ontology relationships (MUST)**  
   Defined using object properties (`:hasArticulationPoint`, `:hasCharacteristic`, etc.) and data properties (`:verseIndex`, `:diacriticType`, etc.)

10. **Axiom pattern (MUST)**  
 <!--  - 158 total axioms  
   - 68 logical axioms  
   - 12 `SubClassOf`, 6 `EquivalentClasses`, 5 `InverseObjectProperties`, etc.  -->
   - Pattern examples include class equivalence (`≡`), `PartOf`, and rule inferences via SWRL.

11. **Dereferencable URI (OPTIONAL)**  
   Not currently dereferencable, but URI design allows for future Linked Data compatibility.

---

## F. Managing Change

1. **Sustainability plan (MUST)**  
   The ontology will be extended in future projects targeting Qira’at variants and learner feedback systems.

2. **Entity deprecation strategy (MUST)**  
   Deprecated classes will be marked using a custom annotation property `:isDeprecated` and described via `dcterms:description`.

3. **Versioning policy (MUST)**  
   Follows semantic versioning (`v1.0.1`, `v1.1.0`, etc.). Changes are logged in the GitHub changelog and commits.

---

## G. Quality Assurance

1. **Testing (MUST)**  
   Consistency checked using three OWL reasoners: HermiT, Pellet, and FaCT++. No unsatisfiable classes found. SWRL rule testing was performed across the entire Quran.

2. **Evaluation (MUST)**  
   Structural evaluation via OOPS!, correctness tested with 5 Tajweed experts across 94 queries, rule accuracy compared against CPFair dataset. Cohen’s Kappa = 0.98.

3. **Examples of use (MUST)**  
   - RDF representation of full Quran annotated with Tajweed rules  
   - SWRL-based inference of phonological rules  
   - SPARQL queries to explore rule occurrences and articulation features

4. **Institutional endorsement (OPTIONAL)**  
   Developed and reviewed at NUCES-FAST (Pakistan); endorsed internally by IKnex Lab.

5. **Evidence of use (MUST)**  
   Currently being integrated into automated Tajweed feedback systems and Quran learning tools; publicly available on GitHub.

---

## References

[1] Matentzoglu, N., Malone, J., Mungall, C., & Stevens, R. (2018). MIRO: Guidelines for Minimum Information for the Reporting of an Ontology. *Journal of Biomedical Semantics*, 9(1), 6.
