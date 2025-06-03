package org.swrlapi.example;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
//import org.semanticweb.owlapi.model.SWRLRule;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.exceptions.SWRLBuiltInException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

 public class TajweedRules3
{

	String workingDir = System.getProperty("user.dir");
	 static OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
	    static String[][] rules = {
	    		new String[]{"R001", "GhunnahComplete", "LetterOccurrence(?LOP) ^ followedBy(?LOP, ?LO) ^ GhunnahLetter(?gl) ^ followedBy(?LO1, ?LO) ^ LetterOccurrence(?LO) ^ involvesLetter(?LO, ?gl) ^ involvesDiacritic(?LO, Shadda) ^ involvesDiacritic(?LO1, ?h) ^ BasicHarakaat(?h) ^ isPartOfWord(?LOP, ?w1) ^ isPartOfWord(?LO, ?w1) ^ swrlx:makeOWLThing(?R, ?LO, ?LO1) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ghunnah-Complete)"},

	    		new String[]{"R002", "IkhfaRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)"},
	    		new String[]{"R003", "IkhfaRuleHighNoon", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighNoon) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)"},

	    		new String[]{"R004", "TanweenIkhfaRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)"},
	    		new String[]{"R005", "TanweenIkhfaRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R006", "TanweenIkhfaRuleSilent", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IkhfaLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa)"},
	    		new String[]{"R007", "IkhfaRuleSilentState", "LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Ikhfa) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R008", "IkhfaShafawiRule", " LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, NoDiacritic) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IkhfaShafawiLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IkhfaAshShafawi)"},

	    		new String[]{"R009", "IqlabRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)"},
	    		new String[]{"R010", "IqlabRuleMeem", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, SmallHighMeem) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)"},
	    		new String[]{"R011", "TanweenIqlabRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IqlabLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)"},
	    		new String[]{"R012", "TanweenIqlabRuleSilent", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IqlabLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab)"},
	    		new String[]{"R012a", "IqlabRuleSilentState", "LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R012b", "IqlabRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Iqlab) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},

	    		new String[]{"R013", "IdghamWithGhunnahWords", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ IdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)"},
	    		new String[]{"R014", "IdghaamWithGhunnahComplete", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  CompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)"},
	    		new String[]{"R015", "IdghaamWithGhunnahIncomplete", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^  IncompleteIdghaamWithGhunnahLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LO, ?LOH) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)"},

	    		new String[]{"R016", "TanweenIdghamWithGhunnahRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)"},
	    		new String[]{"R017", "TanweenIdghamWithGhunnahRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R018", "TanweenIdghamWithGhunnahRuleSilent", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah)"},
	    		new String[]{"R019", "TanweenIdghaamWithGhunnahComplete", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ CompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-Complete)"},
	    		new String[]{"R020", "TanweenIdghaamWithGhunnahIncomplete", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IncompleteIdghaamWithGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah-InComplete)"},
	    		new String[]{"R024", "IdghamWithGhunnahRuleSilentState", "LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},


	    		new String[]{"R021", "IdghamWithoutGhunnahRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Noon) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)"},
	    		new String[]{"R022", "TanweenIdghamWithoutGhunnahRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)"},
	    		new String[]{"R023", "TanweenIdghamWithoutGhunnahRuleSilent", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?T) ^ Tanween(?T) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?L) ^ IdghaamWithoutGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah)"},
	    		new String[]{"R025", "IdghamWithoutGhunnahRuleSilentState", "LetterOccurrence(?LO) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesPauseMarker(?LOF, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R026", "IdghamShafawiRule", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Meem) ^ involvesDiacritic(?LO, ?diac) ^ NonPronouncedDiacritic(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?L) ^ IdghaamShafawiLetter(?L) ^ hasLetterPosition(?LO, ?P) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi)"},

	    		new String[]{"R027", "Qalqalah", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)"},
	    		new String[]{"R028", "QalqalahNoDiacritic", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, NoDiacritic) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)"},
	    		new String[]{"R029", "LesserQalqalah", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, Sukun)^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleType(?R, LesserQalqalah)"},
	    		new String[]{"R030", "GreaterQalqalah", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ QalqalahLetter(?L) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah) ^ hasRuleState(?R, Stopping) ^ hasRuleType(?R, GreaterQalqalah)"},
	    		new String[]{"R031", "QalqalahStopping", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Qalqalah)  ^ swrlx:makeOWLThing(?R, ?LO) ->  hasRuleState(?R, Stopping)"},
	    		
	    		new String[]{"R032", "HamzatulWasl", "LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, AlifWasl)"},
	    		new String[]{"R033", "MeddAlif1", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Alif) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R034", "AlifMeddDagger", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-DaggerAlif) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R035", "MeddWow1", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Damma) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R036", "MeddWowSmallWow", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Medd-Wow-SmallWow) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R037", "MeddYaa", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R038", "MeddYaa1", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R039", "MeddYaaSmallYaa", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, AlifMaksura) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallYaa) ^ hasRuleType(?R, OriginalMedd)"},
	    		new String[]{"R040", "MeddYaaSmallHighYaa", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallHighYaa) ^ involvesLetter(?LO, Yaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Medd-Yaa-SmallHighYaa) ^ hasRuleType(?R, OriginalMedd)"},
	    		
	    		new String[]{"R041", "MeddMunfasilPrimary", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)"},
	    		new String[]{"R042", "MeddMunfasilPrimarySilent", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LOS, NoDiacritic) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)"},
	    		new String[]{"R042a", "MeddMunfasilPrimarySilent1", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, Alif) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Primary)"},

	    		new String[]{"R043", "MeddMunfasilDerived", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)"},
	    		new String[]{"R043a", "MeddMunfasilDerived_HarfNidaYaa", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, Yaa) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)"},
	    		new String[]{"R043b", "MeddMunfasilDerived_HarfNidaRoundHaa", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, RoundHaa) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)"},
	    		new String[]{"R044", "MeddMunfasilDerivedSilent", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)"},
	    		new String[]{"R044a", "MeddMunfasilDerivedSilent1", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif)  ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMunfasil-Derived)"},


	    		
	    		//new String[]{"R045", "MeddMuttasilPrimary", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Primary)"},
	    		new String[]{"R045", "MeddMuttasilPrimary", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)"},
	    		new String[]{"R046", "MeddMuttasilPrimarySilent", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^  followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ LetterOccurrence(?LOS) ^ involvesLetter(?LOS, ?S) ^ SilentLetter(?S) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^  HamzaLetter(?h) ^ isPartOfWord(?LOF, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)"},
	    		new String[]{"R047", "MeddMuttasilDerived", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, ?L) ^ NonHaaYaaLetter(?L) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ involvesDiacritic(?LOH, ?d) ^ BasicHarakaat(?d) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Derived)"},
	    		new String[]{"R047a", "MeddMuttasilDerivedDammatain", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R)  ^ involvesLetter(?LO, ?L) ^ NonHaaYaaLetter(?L) ^  followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ HamzaLetter(?h) ^ involvesDiacritic(?LOH, Dammatain) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Derived)"},

	    		new String[]{"R047b", "MeddMuttasilSilentAlifZero", "LetterOccurrence(?LO) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, Alif) ^ involvesDiacritic(?LOH, SmallHighRoundedZero) ^ followedBy(?LOH, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, Meddah) ^ followedBy(?LOF, ?LOS) ^ involvesLetter(?LOS, ?h) ^  HamzaLetter(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOH) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddMuttasil-Primary)"},

	    		
	    		new String[]{"R048", "MeddLaazimKalmiMK", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Sukun) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiMK)"},
	    		new String[]{"R049", "MeddLaazimKalmiM", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)"},
	    		new String[]{"R049a", "MeddLaazimKalmiMSilentLaam", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOS) ^ involvesLetter(?LOS, Laam) ^ involvesDiacritic(?LOS, NoDiacritic) ^ followedBy(?LOS, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)"},

	    		new String[]{"R050", "MeddAlAridSukoon", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddAlAridSukoon) ^ hasRuleState(?RN, Stopping)"},
	    		new String[]{"R050a", "MeddAlAridSukoonDerived", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOF) ^ involvesDiacritic(?LOF, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOF, endOfAyah) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddAlAridSukoon) ^ hasRuleState(?RN, Stopping)"},

	    		new String[]{"R051", "MeddAlEwad", "followedBy(?LO, ?LOF) ^ involvesDiacritic(?LO, Fathatain) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesLetter(?LO, ?p) ^ involvesPauseMarker(?LOF, endOfAyah) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)"},
	    		new String[]{"R052", "MeddAlEwadHamza", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Fathatain) ^ involvesLetter(?LO, Hamza) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAlEwad) ^ hasRuleState(?R, Stopping)"},
	    		new String[]{"R053", "MeddAsSilaSmallYaa", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallYaa)"},
	    		new String[]{"R054", "MeddAsSilaSmallWow", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSila) ^ hasRuleType(?R, MeddAsSila-SmallWow)"},
	    		new String[]{"R055", "MeddAsSilaYaaKubra", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallYaa) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaYaaKubra)"},
	    		new String[]{"R056", "MeddAsSilaWowKubra", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, SmallWow) ^ involvesLetter(?LO, RoundHaa) ^ Letter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, ?h) ^ HamzaLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, MeddAsSilaKubra) ^ hasRuleType(?R, MeddAsSilaWowKubra)"},
	    		new String[]{"R057", "LeenWow", "followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LO, Fatha) ^ Letter(?p) ^ involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Wow)"},
	    		new String[]{"R058", "LeenYaa", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p)  ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^  LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^   involvesDiacritic(?LOF, Sukun) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Leen-Yaa)"},
	    		new String[]{"R059", "MeddWowLeen", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Wow) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddWowLeen) ^ hasRuleState(?R, Stopping)"},
	    		new String[]{"R060", "MeddYaaLeen", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, Fatha) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOF, ?LOH) ^ LetterOccurrence(?LOH) ^ involvesLetter(?LOH, ?LH) ^ involvesDiacritic(?LOH, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LOH, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddYaaLeen) ^ hasRuleState(?R, Stopping)"},
	    		
	    		new String[]{"R055a", "MeddAsSilaYaaKubraRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddAsSilaYaaKubra) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},

	    		
	    		new String[]{"R061", "LaamAshShamsi", "LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)"},
	    		new String[]{"R061a", "LaamAshShamsiSilentLaam", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, NoDiacritic) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ ShamsiLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOF) ^ hasRuleType(?R, LaamAshShamsi)"},
	    		new String[]{"R062", "LaamAlQamari", "LetterOccurrence(?LO) ^ involvesLetter(?LO, AlifWasl) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ QamariLetter(?h) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, LaamAlQamari)"},

	    		new String[]{"R063", "NabrWowShadd", "followedBy(?LO, ?LOF) ^ LetterOccurrence(?LO) ^ involvesDiacritic(?LOF, Shadda) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LO, ?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaDamma(?diac) ^ involvesLetter(?LOF, Wow) ^ Letter(?p) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-WowShadd)"},
	    		new String[]{"R064", "NabrYaaShadd", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ Letter(?p) ^ involvesDiacritic(?LO, ?diac) ^ FathaKasra(?diac) ^ followedBy(?LO, ?LOF) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, Shadda) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-YaaShadd)"},
	    		new String[]{"R065", "NabrMedd", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOF) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda)  ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM) ^ hasRuleType(?RN, Nabr-Medd)"},
	    		new String[]{"R066", "NabrStopShadd", "LetterOccurrence(?LO) ^ involvesDiacritic(?LO, Shadda) ^ involvesLetter(?LO, ?L) ^ involvesPauseMarker(?LO, endOfAyah) ^ NonGhunnahLetter(?L) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-StopShadd) ^ hasRuleState(?R, Stopping)"},
	    		new String[]{"R067", "NabrHamzaSukun", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ HamzaLetter(?p) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Nabr-HamzaSukun)"},
	    		
	    		new String[]{"R068", "TafkheemHighest", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ FathaFathatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Highest)"},
	    		new String[]{"R069", "TafkheemMiddle", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ DammaDammatain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Middle)"},
	    		new String[]{"R070", "TafkheemLowest", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ involvesDiacritic(?LO, ?d) ^ KasraKasratain(?d) ^ swrlx:makeOWLThing(?R, ?LO) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, Tafkheem-Lowest)"},
	    		
	    		new String[]{"R071", "TafkheemAlif", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)"},
	    		new String[]{"R072", "TarqeeqAlif", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Alif) ^ involvesDiacritic(?LOF, ?d) ^ MeddDiacritic(?d) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)"},
	    		new String[]{"R073", "TafkheemAlif1", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTafkheemLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tafkheem-Alif)"},
	    		new String[]{"R074", "TarqeeqAlif1", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?p) ^ PermanentTarqeeqLetter(?p) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, AlifMaksura) ^ involvesDiacritic(?LOF, DaggerAlif) ^ swrlx:makeOWLThing(?R, ?LO, ?LOF) -> RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^  hasRuleType(?R, Tarqeeq-Alif)"},
	    		
	    		new String[]{"R075", "TanweenIdghamWithoutGhunnahRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamWithoutGhunnah) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R076", "IdghaamMutajanisaan", "LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?B) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, ?L) ^ isSimilarTo(?B, ?L) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R,  Idghaam-Mutajanisaan)"},
	    		new String[]{"R077", "IdghaamMutaqaribaanLaam", "LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Laam) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Raa) ^ isCloseTo(Laam, Raa) ^ isPartOfWord(?LOB, ?w1) ^ isPartOfWord(?LO, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)"},
	    		new String[]{"R078", "IdghaamMutaqaribaanQaaf", "LetterOccurrence(?LOB) ^ involvesLetter(?LOB, Qaaf) ^ involvesDiacritic(?LOB, NoDiacritic) ^ followedBy(?LOB, ?LO) ^ involvesLetter(?LO, Kaaf) ^ isCloseTo(Qaaf, Kaaf)  ^ swrlx:makeOWLThing(?R, ?LOB) -> RuleOccurrence(?R) ^ occursAt(?R, ?LOB) ^ hasRuleType(?R, Idghaam-Mutaqaribaan)"},
	    		
	    		new String[]{"R079", "GhunnahCompleteMadd", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, ?h) ^ BasicHarakaat(?h) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)"},
	    		new String[]{"R080", "GhunnahCompleteMaddDammatain", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddPrimary(?R) ^ followedBy(?LO, ?LOH) ^ followedBy(?LOH, ?LOF) ^ GhunnahLetter(?gl) ^ LetterOccurrence(?LOF) ^ involvesLetter(?LOF, ?gl) ^ involvesDiacritic(?LOF, Shadda) ^ involvesDiacritic(?LOF, Dammatain) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOF, ?w1) ^ swrlx:makeOWLThing(?RN, ?LO, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Ghunnah-Complete)"},
	    		
	    		new String[]{"R081", "MeddLaazimHuroofEMuqata’aat", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ SixHarakahLetter(?L) ^ involvesDiacritic(?LO, Meddah) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 1) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLazim)"},
	    		new String[]{"R081a", "MeddLaazimHuroofEMuqata’aatAyah2", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ SixHarakahLetter(?L) ^ involvesDiacritic(?LO, Meddah) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 2) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLazim)"},
//	    		new String[]{"R082", "MeddLaazimHuroofEMuqata’aat2", "LetterOccurrence(?LO) ^ involvesLetter(?LO, ?L) ^ TwoHarakahLetter(?L) ^ involvesDiacritic(?LO, NoDiacritic) ^ isPartOfWord(?LO, ?w) ^ wordIndex(?w, 1) ^ isPartOfVerse(?w, ?ayah) ^ verseIndex(?ayah, 1) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, )"},

	    		new String[]{"R083", "IdghamShafawiRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, IdghaamAshShafawi) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    		new String[]{"R084", "MeddLaazimKalmiMDerived", "RuleOccurrence(?RO) ^ occursAt(?RO, ?LO) ^ LetterOccurrence(?LO) ^ hasRuleType(?RO, ?R) ^ NaturalMeddDerived(?R) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ involvesDiacritic(?LOH, Shadda) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^ hasRuleType(?RN, MeddLaazimKalmiM)"},
	    		new String[]{"R085", "MeddMuttasilRuleState", "LetterOccurrence(?LO) ^ involvesPauseMarker(?LO, endOfAyah) ^ RuleOccurrence(?R) ^ occursAt(?R, ?LO) ^ hasRuleType(?R, MeddMuttasil-Derived) ^  swrlx:makeOWLThing(?R, ?LO) -> hasRuleState(?R, Continuation)"},
	    	
	    		new String[]{"R086", "TafkheemRaa", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)"},
	    		new String[]{"R087", "TafkheemRaaSukun", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)"},
	    		new String[]{"R088", "TafkheemRaaStopping", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, ?d) ^ FathainDammain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa) ^ hasRuleState(?RN, Stopping)"},
	    		new String[]{"R089", "TafkheemRaaAlifWasl", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesLetter(?LOF, AlifWasl) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, Kasra)^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)"},
	    		new String[]{"R090", "TafkheemRaaTafkheemLetter", "LetterOccurrence(?LO) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Kasra) ^ involvesDiacritic(?LO, Sukun) ^ involvesLetter(?LO, Raa) ^ followedBy(?LO, ?LOH) ^ involvesLetter(?LOH, ?h) ^ PermanentTafkheemLetter(?h) ^ involvesDiacritic(?LOH, ?d) ^ NotKasra(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN, Tafkheem-Raa)"},
	    		new String[]{"R091", "TarqeeqRaa", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?d) ^ KasraKasratain(?d) ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa)"},
	    		new String[]{"R092", "TarqeeqRaaSukun", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, Sukun) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, ?d) ^ KasraKasratain(?d)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa)"},
	    		new String[]{"R093", "TarqeeqRaaStopping", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesDiacritic(?LOF, Sukun) ^ followedBy(?LOH, ?LOF) ^ involvesDiacritic(?LOH, ?d) ^ KasraKasratain(?d)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa) ^ hasRuleState(?RN, Stopping)"},
	    		new String[]{"R094", "TarqeeqRaaStoppingYaa", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Raa) ^ involvesDiacritic(?LO, ?diac) ^ SukoonOnStopDiacritic(?diac) ^ involvesPauseMarker(?LO, endOfAyah) ^ followedBy(?LOF, ?LO) ^ involvesLetter(?LOF, Yaa) ^ involvesDiacritic(?LOF, NoDiacritic)  ^ swrlx:makeOWLThing(?RN, ?LO) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LO) ^  hasRuleType(?RN,Tarqeeq-Raa) ^ hasRuleState(?RN, Stopping)"},

	    		new String[]{"R095","LaamJalalah", "LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, Shadda) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LON) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOH, ?w1) ^ isPartOfWord(?LON, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, LaamJalalah)"},
	    		new String[]{"R096","LaamJalalah_Meem", "LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ followedBy(?LOB, ?LOP)  ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN, LaamJalalah)"},
	    		new String[]{"R097","TafkheemLaam","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^  involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LON) ^  isPartOfWord(?LO, ?w1)  ^  isPartOfWord(?LOH, ?w1) ^ isPartOfWord(?LON, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:greaterThan(?index2, ?index1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tafkheem-Laam-Jalalah)"},
	    		new String[]{"R098","TafkheemLaam_Meem","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w2) ^ wordIndex(?w1, ?index1) ^ wordIndex(?w2, ?index2) ^ swrlb:equal(?index1, ?index2) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)"},
	    		new String[]{"R099","TafkheemLaam_Wow","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO1) ^ involvesLetter(?LO1, Wow) ^ followedBy(?LO1, ?LO2) ^ involvesLetter(?LO2, Alif) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)"},
	    		new String[]{"R100","TafkheemLaam_Silent","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO2) ^ involvesLetter(?LO2, ?S) ^ SilentLetter(?S) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tafkheem-Laam-Jalalah)"},
	    		new String[]{"R101","TafkheemLaam_WowM","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, ?dia) ^ FathaDamma(?dia) ^ followedBy(?LOB, ?LO1) ^ involvesLetter(?LO1, Wow) ^ followedBy(?LO1, ?LO2)  ^ involvesLetter(?LO2, Alif) ^  followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH)  ^ involvesLetter(?LOH, RoundHaa)^ followedBy(?LOH, ?LOM) ^ involvesLetter(?LOM, Meem) ^ isPartOfWord(?LO, ?w1) ^ isPartOfWord(?LOM, ?w1) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tafkheem-Laam-Jalalah)"},
	    		new String[]{"R102","TarqeeqLaamSilentYaa","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, Kasra) ^ followedBy(?LOB, ?LO2) ^ involvesLetter(?LO2, ?S) ^ SilentLetter(?S) ^ involvesDiacritic(?LO2, NoDiacritic) ^ followedBy(?LO2, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tarqeeq-Laam)"},
	    		new String[]{"R103","TarqeeqLaamKasra","LetterOccurrence(?LO) ^ involvesLetter(?LO, Laam) ^ involvesDiacritic(?LO, Kasra) ^ followedBy(?LO, ?LOF)  ^ involvesLetter(?LOF, Laam) ^ involvesDiacritic(?LOF, Shadda) ^ followedBy(?LOF, ?LOH)  ^ involvesLetter(?LOH, RoundHaa)^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^  hasRuleType(?RN,  Tarqeeq-Laam)"},
	    		new String[]{"R104","TarqeeqLaam","LetterOccurrence(?LOB) ^ involvesLetter(?LOB, ?L) ^ involvesDiacritic(?LOB, Kasra) ^ followedBy(?LOB, ?LOP) ^ involvesLetter(?LOP, AlifWasl) ^ followedBy(?LOP, ?LO) ^ involvesLetter(?LO, Laam) ^ followedBy(?LO, ?LOF) ^ involvesLetter(?LOF, Laam) ^ followedBy(?LOF, ?LOH) ^ involvesLetter(?LOH, RoundHaa) ^ swrlx:makeOWLThing(?RN, ?LOF) -> RuleOccurrence(?RN) ^ occursAt(?RN, ?LOF) ^ hasRuleType(?RN, Tarqeeq-Laam)"},

	    		
	    };

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Get user input for choice of action
	        System.out.println("Choose an option:");
	        System.out.println("1. Run rules on a single Surah");
	        System.out.println("2. Run rules on all Surah files");
	        System.out.print("Enter your choice (1-2): ");
	        int choice = scanner.nextInt();

	        switch (choice) 
	        {
	            case 1:
	                processSingleSurah(scanner);
	                break;
	            case 2:
	                processAllSurahFiles();
	                break;
	      
	            default:
	                System.out.println("Invalid choice. Exiting.");
	        }

	        scanner.close();
	    }

	    private static void processSingleSurah(Scanner scanner) {
	        System.out.print("Enter Surah number (1-114): ");
	        int surahNumber = scanner.nextInt();
	       

	        String owlFilename = String.format("QuranKG/Surah%d.rdf", surahNumber);
	        try {
	            OWLOntology ontology = ontologyManager.loadOntologyFromOntologyDocument(new File(owlFilename));
	            SWRLRuleEngine swrlRuleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);

	            
	                for (String[] rule : rules) {
	                    try {
							swrlRuleEngine.createSWRLRule(rule[1], rule[2]);
						} catch (SWRLParseException | SWRLBuiltInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            
	             

	            swrlRuleEngine.infer();
	            saveOnt(ontology, new File(String.format("Surah%d_rules.rdf", surahNumber)));
	        }
	        catch (OWLOntologyCreationException e) {
	            System.err.println("Error creating OWL ontology: " + e.getMessage());
	        } catch (RuntimeException e) {
	            System.err.println("Error starting application: " + e.getMessage());
	        }
	    }




	    private static void processAllSurahFiles() {
	        for (int surahNumber = 1; surahNumber <= 114; surahNumber++) {
	            String owlFilename = String.format("QuranKG/Surah%d_Jan25.rdf", surahNumber);
	            try {
	                OWLOntology ontology = ontologyManager.loadOntologyFromOntologyDocument(new File(owlFilename));
	                SWRLRuleEngine swrlRuleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);

	                for (String[] rule : rules) {
	                    try {
							swrlRuleEngine.createSWRLRule(rule[1], rule[2]);
						} catch (SWRLParseException | SWRLBuiltInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }

	                swrlRuleEngine.infer();
	                saveOnt(ontology, new File(String.format("Surah%d_rules.rdf", surahNumber)));
	                ontologyManager.removeOntology(ontology);

	            } catch (OWLOntologyCreationException e) {
	                System.err.println("Error creating OWL ontology for Surah " + surahNumber + ": " + e.getMessage());
	            } catch (RuntimeException e) {
	                System.err.println("Error starting application for Surah " + surahNumber + ": " + e.getMessage());
	            }
	        }
	    }


	    public static void saveOnt(OWLOntology ontology, File fileToSave) {
	        // Save in RDF format
	        try {
	            ontology.saveOntology(new RDFXMLDocumentFormat(), new FileOutputStream(fileToSave));
	            System.out.println("------- Saved successfully: " + fileToSave.getName());
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (OWLOntologyStorageException e) {
	            e.printStackTrace();
	        }
	    }
	}