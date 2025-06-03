
package tajweedKG_Generator;

/**
 * @author amna kamran
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;

import TajweedOntology.TajweedFactory;

import TajweedOntology.*;

public class QuranKG_Generation {

	// ***************** @param args*****************
	
	
	public static String SOURCE_FILE;
	public static String OutputFile;
	public static OWLOntology factoryOnt;
	private static OWLOntology owlOntology;
	private static TajweedFactory tajweedFactory;
	private static OWLOntologyManager manager;
	//private static OWLDataFactory df = manager.getOWLDataFactory();
	private static String baseUrl= "http://www.semantictajweed.com/ontology/";


	public QuranKG_Generation(String SOURCE_FILE, String OutputFile)
	{
		this.SOURCE_FILE = SOURCE_FILE;
		this.OutputFile = OutputFile;
	}
	
	public void InitializeQuranEngine() 
	{
		try {
		// mapping of imported Ontologies 
			manager = OWLManager.createOWLOntologyManager();
	
			// Load Ontology From File
			owlOntology = manager.loadOntologyFromOntologyDocument(new 
					FileInputStream(SOURCE_FILE));
			tajweedFactory = new TajweedFactory(owlOntology);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void InstanciateOntology()
	{
		letterInstance();
		letterCategoryInstance();
		ruleInstance();
	}
	
	
	//***************Create KG for Surah *******************
	public void createKGforChapter(String fileName, int chapter)
	{
		letterInstance();
	//  letterCategoryInstance();
		chapterInstance(chapter);
		//ruleInstance();
		verseInstancesInChapter(fileName, chapter);
		wordInstancesInChapter(fileName, chapter);
		letterOccurrenceInstancesInChapter(fileName, chapter);
		//AdjustHarakats();
		NoDiacritic();
	}
	public void createKGforVerse(String fileName, int chapter, int verse)
	{
		letterInstance();
	//	letterCategoryInstance();
		chapterInstance(chapter);
		verseInstance(fileName, chapter, verse);
		wordInstancesInVerse(fileName, chapter, verse);
		letterOccurrenceInstancesInVerse(fileName, chapter, verse);
		ruleInstance();
		NoDiacritic();
		//AdjustHarakats();

	}
	
	
	//***************Create KG for Quran *******************
	public void createQuranKG(String fileName)
	{
		//******** Creating all instances for all Chapters(Surahs) in Quran *****************
		allChapterInstances();
		letterInstance();
		//letterCategoryInstance();
		//***************** Creating all instances for all Verses in Quran ******************
		allVerseInstances(fileName);
		//***************** Creating all instances for all Words in Quran *******************
		allWordInstances(fileName);
		allLetterOccurrenceInstances(fileName);
		NoDiacritic();
//		ruleInstance();
//		AdjustHarakats();

	}
		
	// ******************* Save the Ontology *****************
	public void saveOnt()
	{
		factoryOnt = tajweedFactory.getOwlOntology();
		File fileformated = new File(OutputFile);
		
		//Get the Ontology format
		OWLOntologyFormat format = manager.getOntologyFormat(factoryOnt);
		
		RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();

		if (format.isPrefixOWLOntologyFormat()) { 
			rdfxmlFormat.copyPrefixesFrom(format.asPrefixOWLOntologyFormat()); 
		}
		try {
			manager.saveOntology(factoryOnt, rdfxmlFormat, IRI.create(fileformated.toURI()));
			System.out.println("saved");
		} catch (OWLOntologyStorageException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	// ******************* Helper Functions *****************

	public static String padding(Integer number, Integer requiredDigits) {
		String paddedString = "";
		int length = (int) (Math.log10(number) + 1);
		if(length<requiredDigits) {
			 paddedString = String.format("%0"+requiredDigits+"d" , number);
			 return paddedString;
		}
		else {
		return number+"";
		}
	}
	public String chapterName(int chapterNo)
	{
		String chapPadded = padding(chapterNo, 3);
		String chapterName = "CH"+ chapPadded;	
		return chapterName;
	}
	public String verseName(int chapterNo, int verseNo)
	{
		String chapterName = chapterName(chapterNo);
		String versePadded = padding(verseNo, 3);
		String verseName = chapterName + "_V"+ versePadded;
		return verseName;
	}
	public String wordName(int chapterNo, int verseNo, int wordNo )
	{
		String chapPadded = padding(chapterNo, 3);
		String chapterName = "CH"+ chapPadded;
		String versePadded = padding(verseNo, 3);
		String verseName = chapterName + "_V"+ versePadded;
		String wordPadded = padding(wordNo, 3);
		String wordName = verseName + "_W"+ wordPadded;
		return wordName;
	}
	public String LetterOccurrenceName(int chapterNo, int verseNo, int wordNo, int letterNo)
	{
		String chapPadded = padding(chapterNo, 3);
		String chapterName = "CH"+ chapPadded;
		String versePadded = padding(verseNo, 3);
		String verseName = chapterName + "_V"+ versePadded;
		String wordPadded = padding(wordNo, 3);
		String wordName = verseName + "_W"+ wordPadded;
		String letterPadded = padding(letterNo, 3);
		String letterOccurrenceName = wordName + "_LO" + letterPadded ;
		return letterOccurrenceName;
	}
	public void ReadFile(String fileName)
	{
	
		File file = new File(fileName);
		String encoding = null;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						String[] line = currentLineVar.split("\\|");
						String surahNo = line[0];
						System.out.println(surahNo);
						
						String verseNo = line[1];
						System.out.println(verseNo);

						String verse = line[2];
						System.out.println(verse);

						String[] words = verse.split(" ");
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	
	}
	
	// ******************* Create Chapter one (Surah) Instance *****************
	private static Chapter chapterInstance;

	public void chapterInstance(int chapterNo)
	{	
		String[] ChapterNamesEng = {"The Opening", "Al-Baqara", "Aal-i-Imraan", "An-Nisaa", "The Table", "The Cattle", "Al-A'raaf", "The Spoils of War", "At-Tawba", "Jonas", "Hud", "Joseph", "The Thunder", "Abraham", "Al-Hijr", "An-Nahl", "Al-Israa", "Al-Kahf", "Mary", "Taa-Haa", "The Prophets", "The Pilgrimage", "Al-Muminoon", "An-Noor", "Al-Furqaan", "Ash-Shu'araa", "An-Naml", "The Stories", "The Spider", "Ar-Room", "Luqman", "As-Sajda", "Al-Ahzaab", "Saba", "The Originator", "Yaseen", "As-Saaffaat", "Saad", "Az-Zumar", "Al-Ghaafir", "Explained in detail", "Ash-Shura", "Ornaments of gold", "Ad-Dukhaan", "Crouching", "Al-Ahqaf", "Muhammad", "Al-Fath", "Al-Hujuraat", "Qaaf", "The Winnowing Winds", "The Mount", "An-Najm", "Al-Qamar", "Ar-Rahmaan", "The Inevitable", "Al-Hadid", "Al-Mujaadila", "The Exile", "Al-Mumtahana", "The Ranks", "Al-Jumu'a", "Al-Munaafiqoon", "Mutual Disillusion", "At-Talaaq", "At-Tahrim", "Al-Mulk", "Al-Qalam", "Al-Haaqqa", "The Ascending Stairways", "Nooh", "Al-Jinn", "Al-Muzzammil", "Al-Muddaththir", "Al-Qiyaama", "Man", "The Emissaries", "An-Naba", "Those who drag forth", "He frowned", "At-Takwir", "Al-Infitaar", "Al-Mutaffifin", "Al-Inshiqaaq", "Al-Burooj", "At-Taariq", "Al-A'laa", "Al-Ghaashiya", "Al-Fajr", "Al-Balad", "Ash-Shams", "Al-Lail", "The Morning Hours", "Ash-Sharh", "The Fig", "The Clot", "The Power, Fate", "The Evidence", "Az-Zalzala", "Al-Aadiyaat", "The Calamity", "Competition", "The Declining Day, Epoch", "The Traducer", "Al-Fil", "Quraish", "Almsgiving", "Al-Kawthar", "The Disbelievers", "Divine Support", "Al-Masad", "Sincerity", "Al-Falaq", "Mankind"};
		String[] ChapterNames = {"الفاتحة", "البقرة", "آل عمران", "النساء", "المائدة", "الأنعام", "الأعراف", "الأنفال", "التوبة", "يونس", "هود", "يوسف", "الرعد", "ابراهيم", "الحجر", "النحل", "الإسراء", "الكهف", "مريم", "طه", "الأنبياء", "الحج", "المؤمنون", "النور", "الفرقان", "الشعراء", "النمل", "القصص", "العنكبوت", "الروم", "لقمان", "السجدة", "الأحزاب", "سبإ", "فاطر", "يس", "الصافات", "ص", "الزمر", "غافر", "فصلت", "الشورى", "الزخرف", "الدخان", "الجاثية", "الأحقاف", "محمد", "الفتح", "الحجرات", "ق", "الذاريات", "الطور", "النجم", "القمر", "الرحمن", "الواقعة", "الحديد", "المجادلة", "الحشر", "الممتحنة", "الصف", "الجمعة", "المنافقون", "التغابن", "الطلاق", "التحريم", "الملك", "القلم", "الحاقة", "المعارج", "نوح", "الجن", "المزمل", "المدثر", "القيامة", "الانسان", "المرسلات", "النبإ", "النازعات", "عبس", "التكوير", "الإنفطار", "المطففين", "الإنشقاق", "البروج", "الطارق", "الأعلى", "الغاشية", "الفجر", "البلد", "الشمس", "الليل", "الضحى", "الشرح", "التين", "العلق", "القدر", "البينة", "الزلزلة", "العاديات", "القارعة", "التكاثر", "العصر", "الهمزة", "الفيل", "قريش", "الماعون", "الكوثر", "الكافرون", "النصر", "المسد", "الإخلاص", "الفلق", "الناس"};
		String [] DBPedia = {"Al-Fatiha", "Al-Baqara", "Ali-Imran", "An-Nisa", "Al-Ma'idah", "Al-An'am", "Al-A'raf", "Al-Anfal", "At-Taubah", "Yunus", "Hud", "Yusuf", "Ar-Ra'd", "Ibrahim", "Al-Hijr", "An-Nahl", "Al-Isra", "Al-Kahf", "Maryam", "Taha", "Al-Anbiya", "Al-Hajj", "Al-Mu’minun", "An-Noor", "Al-Furqan", "Ash-Shuara", "An-Naml", "Al-Qasas", "Al-Ankabut", "Ar-Rum", "Luqman", "As-Sajda", "Al-Ahzab", "Saba", "Fatir", "Ya-Sin", "As-Saaffat", "Sad", "Az-Zumar", "Ghafir", "Fussilat", "Ash-Shura", "Az-Zukhruf", "Ad-Dukhan", "Al-Jathiya", "Al-Ahqaf", "Muhammad", "Al-Fath", "Al-Hujraat", "Qaf", "Adh-Dhariyat", "At-Tur", "An-Najm", "Al-Qamar", "Al-Rahman", "Al-Waqia", "Al-Hadid", "Al-Mujadila", "Al-Hashr", "Al-Mumtahina", "As-Saff", "Al-Jumua", "Al-Munafiqoon", "At-Taghabun", "At-Talaq", "At-Tahrim", "Al-Mulk", "Al-Qalam", "Al-Haaqqa", "Al-Maarij", "Nooh", "Al-Jinn", "Al-Muzzammil", "Al-Muddathir", "Al-Qiyama", "Al-Insan", "Al-Mursalat", "An-Naba", "An-Naziat", "Abasa", "At-Takwir", "Al-Infitar", "Al-Mutaffifin", "Al-Inshiqaq", "Al-Burooj", "At-Tariq", "Al-Ala", "Al-Ghashiya", "Al-Fajr", "Al-Balad", "Ash-Shams", "Al-Lail", "Ad-Dhuha", "Al-Inshirah", "At-Tin", "Al-Alaq", "Al-Qadr", "Al-Bayyina", "Al-Zalzala", "Al-Adiyat", "Al-Qaria", "At-Takathur", "Al-Asr", "Al-Humaza", "Al-Fil", "Quraish", "An-Nasr", "Al-Kawthar", "Al-Kafirun", "An-Nasr", "Al-Masadd", "Al-Ikhlas", "Al-Falaq", "An-Nas"};
		int [] RevelationOrder = {5, 87, 89, 92, 112, 55, 39, 88, 113, 51, 52, 53, 96, 72, 54, 70, 50, 69, 44, 45, 73, 103, 74, 102, 42, 47, 48, 49, 85, 84, 57, 75, 90, 58, 43, 41, 56, 38, 59, 60, 61, 62, 63, 64, 65, 66, 95, 111, 106, 34, 67, 76, 23, 37, 97, 46, 94, 105, 101, 91, 109, 110, 104, 108, 99, 107, 77, 2, 78, 79, 71, 40, 3, 4, 31, 98, 33, 80, 81, 24, 7, 82, 86, 83, 27, 36, 8, 68, 10, 35, 26, 9, 11, 12, 28, 1, 25, 100, 93, 14, 30, 16, 13, 32, 19, 29, 17, 15, 18, 114, 6, 22, 20, 21};
		int[] verseCount = {7, 286, 200, 176, 120, 165, 206, 75, 129, 109, 123, 111, 43, 52, 99, 128, 111, 110, 98, 135, 112, 78, 118, 64, 77, 227, 93, 88, 69, 60, 34, 30, 73, 54, 45, 83, 182, 88, 75, 85, 54, 53, 89, 59, 37, 35, 38, 29, 18, 45, 60, 49, 62, 55, 78, 96, 29, 22, 24, 13, 14, 11, 11, 18, 12, 12, 30, 52, 52, 44, 28, 28, 20, 56, 40, 31, 50, 40, 46, 42, 29, 19, 36, 25, 22, 17, 19, 26, 30, 20, 15, 21, 11, 8, 8, 19, 5, 8, 8, 11, 11, 8, 3, 9, 5, 4, 7, 3, 6, 3, 5, 4, 5, 6};
		String[] RevelationPlace = {"Mecca", "Medina", "Medina", "Medina", "Medina", "Mecca", "Mecca", "Medina", "Medina", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Medina", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Medina", "Medina", "Medina", "Medina", "Medina", "Medina", "Medina", "Medina", "Medina", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Medina", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Mecca", "Medina", "Mecca", "Mecca", "Mecca", "Mecca"};
		String quranOntologyLink = "http://quranontology.com/Resource/quran";
		String SemQuranLink = "http://mlode.nlp2rdf.org/semanticquran/quran";
		String DBPediaLink = "http://dbpedia.org/resource/";		
	
		String instanceName = chapterName(chapterNo);		
		// Create Chapter Instance and add its data properties
		chapterInstance = tajweedFactory.createChapter(instanceName);
		
		String chapterIndex = String.valueOf(chapterNo);	
		quranOntologyLink = "http://quranontology.com/Resource/quran" + chapterIndex;
		SemQuranLink = "http://mlode.nlp2rdf.org/semanticquran/quran" + chapterIndex;
		DBPediaLink = "http://dbpedia.org/resource/" + DBPedia[chapterNo-1];
		chapterInstance.addowl_sameAs(quranOntologyLink);
		chapterInstance.addowl_sameAs(SemQuranLink);
		chapterInstance.addowl_sameAs(DBPediaLink);
		chapterInstance.addChapterIndex(chapterNo);
		chapterInstance.addrdfs_label(ChapterNames[chapterNo-1]+"@ar");
		chapterInstance.addrdfs_label(DBPedia[chapterNo-1]);
		chapterInstance.addrdfs_label(ChapterNamesEng[chapterNo-1]+"@en");
		chapterInstance.addVerseCount(verseCount[chapterNo-1]);
		chapterInstance.addRevelationOrder(RevelationOrder[chapterNo-1]);
		chapterInstance.addRevelationPlace("http://dbpedia.org/resource/" + RevelationPlace[chapterNo-1]);

	
	}
	
	// ******* Create Chapter (Surah) Instances *****************

	public void allChapterInstances()
	{	
		
		for (int chapterNo=1; chapterNo<115; chapterNo++)
		{			
			// Create Chapter Instance and add its data properties
			chapterInstance(chapterNo);
		}
	}
	
	// ******************* Create Verse Instances *****************
	private static Verse verseInstance;
	public void allVerseInstances(String fileName)
	{	
		File file = new File(fileName);
		String encoding = null;
		int verseNo, chapterNo;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						String[] line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);
						verseNo = Integer.valueOf(line[1]);
						String Verse = line[2];
						createVerseInstance(chapterNo,verseNo, Verse);

					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	public void createVerseInstance(int chapterNo, int verseNo, String Verse)
	{
		String instanceName = verseName(chapterNo, verseNo);
		// Create Verse Instance and add its data properties
		verseInstance = tajweedFactory.createVerse(instanceName);
		verseInstance.addChapterIndex(chapterNo);
		verseInstance.addVerseIndex(verseNo);		
		verseInstance.addVerseText(Verse);
		String Chapter = chapterName(chapterNo);
		chapterInstance(chapterNo);
		chapterInstance = tajweedFactory.getChapter(Chapter);
		verseInstance.addIsPartOfChapter(chapterInstance);
	}
	public void verseInstancesInChapter(String fileName, int chapter)
	{	
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo;
		String[] line ;
		String Verse;
		try
		{
			for (String currentLineVar : FileUtils.readLines(file, encoding))
			{
				line = currentLineVar.split("\\|");
				chapterNo = Integer.valueOf(line[0]);
				if (chapterNo == chapter )
				{
					verseNo = Integer.valueOf(line[1]);
					Verse = line[2];
					createVerseInstance(chapterNo,verseNo, Verse);

				}

			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void verseInstance(String fileName, int chapter, int verse)
	{	
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo;
		String Verse;

				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						String[] line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);
						if (chapterNo == chapter )
						{
							verseNo = Integer.valueOf(line[1]);
							if(verseNo == verse )
							{
								Verse = line[2];
								createVerseInstance(chapterNo,verseNo, Verse);

							}
							
						}
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}

	// ******************* Create Word Instances *****************
	private static Word wordInstance;
	public void createWordInstance(int chapterNo, int verseNo, int wordNo, String Word)
	{
		// Create Word Instance and add its data properties
		String wordName = wordName(chapterNo, verseNo, wordNo);
		String verseName = verseName(chapterNo, verseNo);
		wordInstance = tajweedFactory.createWord(wordName);
		wordInstance.addChapterIndex(chapterNo);
		wordInstance.addVerseIndex(verseNo);
		wordInstance.addWordText(Word+" @ar");
		wordInstance.addWordIndex(wordNo);
		verseInstance = tajweedFactory.getVerse(verseName);
		wordInstance.addIsPartOfVerse(verseInstance);
	}
	public void allWordInstances(String fileName)
	{	
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo;
		String[] line, words;
		String verse;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);				
						verseNo = Integer.valueOf(line[1]);					
						verse = line[2];
						words = verse.split(" ");
												
						for (int wordNo=1; wordNo<= words.length; wordNo++) 
						{
							createWordInstance(chapterNo, verseNo, wordNo, words[wordNo-1]);
						}
							
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	public void wordInstancesInChapter(String fileName, int chapter)
	{
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo;
		String[] line, words;
		String verse;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);	
						if (chapterNo == chapter )
						{
							verseNo = Integer.valueOf(line[1]);					
							verse = line[2];
							words = verse.split(" ");
													
							for (int wordNo=1; wordNo<= words.length; wordNo++) 
							{
								createWordInstance(chapterNo, verseNo, wordNo, words[wordNo-1]);
							}
						}		
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	public void wordInstancesInVerse(String fileName, int chapter, int verse)
	{
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo;
		String[] line, words;
		String Verse;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);	
						if (chapterNo == chapter )
						{
							verseNo = Integer.valueOf(line[1]);	
							if(verseNo == verse )
							{
								Verse = line[2];
								words = Verse.split(" ");
													
								for (int wordNo=1; wordNo<= words.length; wordNo++) 
								{
									createWordInstance(chapterNo, verseNo, wordNo, words[wordNo-1]);
								}
							}
						}		
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	
	// ******************* Create Letter Instances *****************
	private static Letter letterInstance;
	private static Diacritic diacriticInstance;
	private static Tanween tanweenInstance;
	private static PauseMarker pauseMarkerInstance;
	private static Dictionary<String, String> LetterDictionary = new Hashtable<String, String>();
	private static Dictionary<String, String> DiacriticDictionary = new Hashtable<String, String>();

	public void letterInstance()
	{	

		String[] letters = {"ء", "ٔ", "ٱ", "آ", "أ", "إ", "ا", "ب", "ة", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ؤ", "ى", "ي", "ئ"};	
		String[] letterNames = {"Hamza", "HighHamza", "AlifWasl", "AlifMeddah", "HighHamzaAlif", "AlifWithHamzaBelow", "Alif", "Baa", "TaaMarbuta", "Taa", "Thaa", "Jeem", "Haa", "Khaa", "Daal", "Zaal", "Raa", "Zaa", "Seen", "Sheen", "Saad", "Daad", "Toa", "Zoa", "Aain", "Ghain", "Faa", "Qaaf", "Kaaf", "Laam", "Meem", "Noon", "RoundHaa", "Wow", "HighHamzaWow", "AlifMaksura", "Yaa", "HighHamzaYaa"};

		
	
		for (int letterNo=1; letterNo <=letters.length ; letterNo++)
		{
			String instanceName = letterNames[letterNo-1];
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createLetter(instanceName);
			letterInstance.addrdfs_label(letters[letterNo-1]);
			LetterDictionary.put(letters[letterNo-1], letterNames[letterNo-1]);
		}
		String[] diacritics = {"ً", "ٌ", "ٍ", "َ", "ُ", "ِ", "ّ", "ْ", "ٓ", "ٰ", "ۭ", "ۢ", "ۥ", "ـ", "۟","ۜ", "۠","ۧ", "ۣ","ۨ","۫","۪","۬", "ۦ"} ;
		String[] diacriticNames = {"Fathatain", "Dammatain", "Kasratain", "Fatha", "Damma", "Kasra", "Shadda", "Sukun", "Meddah", "DaggerAlif", "SmallLowMeem", "SmallHighMeem", "SmallWow", "Tatweel", "SmallHighRoundedZero", "SmallHighSeen", "SmallHighUprightRectangularZero", "SmallHighYaa", "SmallLowSeen", "SmallHighNoon", "EmptyCentreHighStop", "EmptyCentreLowStop", "RoundedHighStop", "SmallYaa"};

		for(int diacriticNo=1; diacriticNo <=3 ; diacriticNo++)
		{
			String instanceName = diacriticNames[diacriticNo-1];
			//System.out.println(instanceName);
			tanweenInstance = tajweedFactory.createTanween(instanceName);
			tanweenInstance.addrdfs_label(diacritics[diacriticNo-1]);
			DiacriticDictionary.put(diacritics[diacriticNo-1], diacriticNames[diacriticNo-1]);

		}
		for (int diacriticNo=4; diacriticNo <=diacritics.length ; diacriticNo++)
		{
			String instanceName = diacriticNames[diacriticNo-1];
		//	System.out.println(instanceName);
			diacriticInstance = tajweedFactory.createDiacritic(instanceName);
			diacriticInstance.addrdfs_label(diacritics[diacriticNo-1]);
			DiacriticDictionary.put(diacritics[diacriticNo-1], diacriticNames[diacriticNo-1]);

		}
		
		String AEM = "endOfAyah";
		pauseMarkerInstance = tajweedFactory.createPauseMarker(AEM);
		pauseMarkerInstance.addrdfs_label("۝");

	}
	public void letterCategoryInstance()
	{	

		
		//String[] IdghaamShafawiLetters = {"م"};
		letterInstance = tajweedFactory.createIdghaamShafawiLetter("Meem");
		
		//String[] IqlabLetters = {"ب"};
		letterInstance = tajweedFactory.createIqlabLetter("Baa");

		//String[] IkhfaShafawiLetters = {"ب"};
		letterInstance = tajweedFactory.createIkhfaShafawiLetter("Baa");

		//String[] IdghaamWithoutGhunnahLetters = {"ر", "ل" };
		letterInstance = tajweedFactory.createIdghaamWithoutGhunnahLetter("Raa");
		letterInstance = tajweedFactory.createIdghaamWithoutGhunnahLetter("Laam");

		
		//String[] MadLetters = { "ا", "و", "ي"};  // , "ؤ", "ى","ئ"};
		letterInstance = tajweedFactory.createMadLetter("Wow");
		letterInstance = tajweedFactory.createMadLetter("Alif");
		letterInstance = tajweedFactory.createMadLetter("Yaa");
		
	
		String[] IdghaamWithGhunnahLetters = {"Meem", "Noon", "Wow", "Yaa"}; // "م", "ن", "ؤ", "ى","ئ"};
		for (int letterNo=1; letterNo <=IdghaamWithGhunnahLetters.length ; letterNo++)
		{
			String instanceName = IdghaamWithGhunnahLetters[letterNo-1];
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createIdghaamWithGhunnahLetter(instanceName);
		}
		
		String[] IkhfaLetters = {"ت", "ث", "ج", "د", "ذ", "ز", "س", "ش", "ص", "ض", "ط", "ظ", "ف", "ق", "ك"};
		for (int letterNo=1; letterNo <=IkhfaLetters.length ; letterNo++)
		{
			String instanceName = LetterDictionary.get(IkhfaLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createIkhfaLetter(instanceName);
		}
		
		String[] IzharLetters = {"ء", "ح", "خ", "ع", "غ", "ه"};
		for (int letterNo=1; letterNo <=IzharLetters.length ; letterNo++)
		{
			String instanceName = LetterDictionary.get(IzharLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createIzharLetter(instanceName);
		}
		
		String[] IzharShafawiLetters = {"ء", "ا", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "ن", "ه", "و",  "ي"}; // , "ؤ", "ى","ئ"};
		for (int letterNo=1; letterNo <=IzharShafawiLetters.length ; letterNo++)
		{
			String instanceName = LetterDictionary.get(IzharShafawiLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createIzharShafawiLetter(instanceName);
		}
		
		String[] QalqalahLetters = {"ب", "ج", "د", "ط", "ق"};
		for (int letterNo=1; letterNo <=QalqalahLetters.length ; letterNo++)
		{
			String instanceName = LetterDictionary.get(QalqalahLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createQalqalahLetter(instanceName);
		}
		
		String[] SilentLetters = {"ا", "ى" };
		for (int letterNo=1; letterNo <=SilentLetters.length ; letterNo++)
		{
			String instanceName = LetterDictionary.get(SilentLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createSilentLetter(instanceName);
		}
	//	String[] NonGhunnahLetters = {"ء", "ٱ", "آ", "أ", "إ", "ا", "ب", "ة", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "ه", "و", "ؤ", "ى", "ي", "ئ"};
		String[] NonGhunnahLetters = {"Hamza", "HighHamza", "AlifWasl", "AlifMeddah", "HighHamzaAlif", "AlifWithHamzaBelow", "Alif", "Baa", "TaaMarbuta", "Taa", "Thaa", "Jeem", "Haa", "Kha", "Daal", "Zaal", "Raa", "Zaa", "Seen", "Sheen", "Saad", "Daad", "Toa", "Zoa", "Aeen", "Ghaeen", "Faa", "Qaaf", "Kaaf", "Laam",  "RoundHaa", "Wow", "HighHamzaWow", "AlifMaksura", "Yaa", "HighHamzaYaa"};

		for (int letterNo=1; letterNo <=NonGhunnahLetters.length ; letterNo++)
		{
			//String instanceName = LetterDictionary.get(NonGhunnahLetters[letterNo-1]);
			//System.out.println(instanceName);
			letterInstance = tajweedFactory.createNonGhunnahLetter(NonGhunnahLetters[letterNo-1]);
		}


		//String[] QamariLetters = {"ء", "ا", "ب", "ج", "ح", "خ",  "ع", "غ", "ف", "ق", "ك", "م", "ه", "ي"};	
		String[] QamariLetters = {"Hamza", "Alif", "Baa", "Jeem", "Haa", "Kha", "Aeen", "Ghaeen", "Faa", "Qaaf", "Kaaf", "Meem",  "RoundHaa", "Yaa"};
		for (int letterNo=1; letterNo <=QamariLetters.length ; letterNo++)
		{
//			String instanceName = LetterDictionary.get(QamariLetters[letterNo-1]);
			letterInstance = tajweedFactory.createQamariLetter(QamariLetters[letterNo-1]);
		}

		// String[] ShamsiLetters = {"ت", "ث", "د", "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط", "ظ",  "ل", "ن"};	
		String[] ShamsiLetters = { "Taa", "Thaa", "Daal", "Zaal", "Raa", "Zaa", "Seen", "Sheen", "Saad", "Daad", "Toa", "Zoa", "Laam", "Noon"};
		for (int letterNo=1; letterNo <=ShamsiLetters.length ; letterNo++)
		{
//			String instanceName = LetterDictionary.get(ShamsiLetters[letterNo-1]);
			letterInstance = tajweedFactory.createShamsiLetter(ShamsiLetters[letterNo-1]);
		}

		//String[] HamzaLetters = {"ء", "ٔ", "آ", "أ", "إ"};
		String[] HamzaLetters = {"Hamza", "AlifMeddah", "HighHamzaAlif", "AlifWithHamzaBelow"};
		for (int letterNo=1; letterNo <=HamzaLetters.length ; letterNo++)
		{
//			String instanceName = LetterDictionary.get(HamzaLetters[letterNo-1]);
			letterInstance = tajweedFactory.createHamzaLetter(HamzaLetters[letterNo-1]);
		}


		//String[] GhunnahLetters = {"م", "ن"};
		letterInstance = tajweedFactory.createGhunnahLetter("Noon");
		letterInstance = tajweedFactory.createGhunnahLetter("Meem");
		
	}



	// ******************* Create Letter Occurrence Instances *****************

	public void allLetterOccurrenceInstances(String fileName)
	{	
		
		LetterOccurrence LOI = null,LOI2 = null, previousLOI = null;
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo, position, letterNo;
		//int globalposition = 1;
		String[] line, words;
		String verse, instanceName = null, wordName, word;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);
						verseNo = Integer.valueOf(line[1]);
						position = 1;					
						verse = line[2];
						words = verse.split(" ");
						
						for (int wordNo=1; wordNo<= words.length; wordNo++) 
						{	// Create Word Instance and add its data properties	
							wordName = wordName(chapterNo, verseNo, wordNo);
							wordInstance = tajweedFactory.getWord(wordName);
							word = words[wordNo-1];
							char c [] = word.toCharArray();
							letterNo = 1;
							String letter, diacritic;

							for (int j = 1; j <= c.length; j++) 
							{

								//System.out.println("Character = " + c[j-1]);
								letter = LetterDictionary.get(String.valueOf(c[j-1]));									
								diacritic = DiacriticDictionary.get(String.valueOf(c[j-1]));

								if (letter != null)
								{
									letterInstance = tajweedFactory.getLetter(letter);
									if (letterInstance != null)
									{	
										instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
										//System.out.println(wordNo);
										LOI = tajweedFactory.createLetterOccurrence(instanceName);
										LOI.addInvolvesLetter(letterInstance);
										LOI.addIsPartOfWord(wordInstance);
										LOI.addHasLetterPosition(position);
										//LOI.addHasGlobalPosition(globalposition);
										position++;
										//globalposition++;
										letterNo++;
									}
								}	
								else if (diacritic != null)
								{
										//System.out.println(diacritic);
										diacriticInstance = tajweedFactory.getDiacritic(diacritic);
										if(diacriticInstance != null)
										{
											LOI = tajweedFactory.getLetterOccurrence(instanceName);
											//System.out.println(instanceName);
											LOI.addInvolvesDiacritic(diacriticInstance);
											LOI.addHasDiacriticPosition(position);
											position++;
											//globalposition++;
										
										}
								}
								
								else if (letterInstance == null && diacriticInstance == null)
								{
									System.out.println(word);
									System.out.println("Character not in ontology - " + c[j-1]);
		
								}
								if(wordNo == words.length && j==c.length)
								{
									PauseMarker pm = tajweedFactory.getPauseMarker("endOfAyah");
									LOI.addInvolvesPauseMarker(pm);
								}
							}
							
							position++;
							//globalposition++;

						}
						for (int wordNo=1; wordNo<= words.length; wordNo++) 
						{
							String instanceName2;
							word = words[wordNo-1];
							char c [] = word.toCharArray();
							letterNo = 1;
							for (int j = 1; j <= c.length; j++) 
							{
								String letter = LetterDictionary.get(String.valueOf(c[j-1]));
								if(letter != null)
								{
									letterInstance = tajweedFactory.getLetter(letter);
								
									if (letterInstance != null)
									{	
										instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
//										System.out.println("LOI1-----" + instanceName);
										instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo+1);
//										System.out.println("LOI2-----" + instanceName2);

										LOI = tajweedFactory.getLetterOccurrence(instanceName);
										LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
										if(LOI != null)  
										{
											if(wordNo==1 && j==1 && previousLOI != null)
											{
												previousLOI.addFollowedBy(LOI);
											}
											if(LOI2!= null)
											{
												LOI.addFollowedBy(LOI2);
												letterNo++;
											}
											else
											{
												if(wordNo+1 <= words.length)
												{
													instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo+1, 1);
//													System.out.println("LOI3" + instanceName2);
													LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
													LOI.addFollowedBy(LOI2);
													letterNo++;
												}
											
											}			
										}
									}
								}
							}
						}
					
					}
				}
					

				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	public void letterOccurrenceInstancesInChapter(String fileName, int chapter)
	{	
		LetterOccurrence LOI = null, LOI2 = null, previousLOI = null;
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo, position, letterNo;
		String[] line, words;
		String verse, instanceName = null, wordName, word;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);
						if (chapterNo == chapter )
						{		
							verseNo = Integer.valueOf(line[1]);
							position = 1;					
							verse = line[2];
							words = verse.split(" ");
							
							for (int wordNo=1; wordNo<= words.length; wordNo++) 
							{
								
								// Create Word Instance and add its data properties	
								wordName = wordName(chapterNo, verseNo, wordNo);
								//System.out.println(wordNo);
								wordInstance = tajweedFactory.getWord(wordName);
								word = words[wordNo-1];
								//System.out.println(word);

								char c [] = word.toCharArray();
								letterNo = 1;
								String letter, diacritic;
								for (int j = 1; j <= c.length; j++) 
								{
									
//									System.out.println("Character = " + c[j-1]);
									letter = LetterDictionary.get(String.valueOf(c[j-1]));									
									diacritic = DiacriticDictionary.get(String.valueOf(c[j-1]));

									if (letter != null)
									{
										letterInstance = tajweedFactory.getLetter(letter);
										if (letterInstance != null)
										{	
											instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
//											System.out.println(instanceName);
											LOI = tajweedFactory.createLetterOccurrence(instanceName);
											LOI.addInvolvesLetter(letterInstance);
											LOI.addIsPartOfWord(wordInstance);
											LOI.addHasLetterPosition(position);
											position++;
											letterNo++;
										}
									}	
									else if (diacritic != null)
									{
											//System.out.println(diacritic);
											diacriticInstance = tajweedFactory.getDiacritic(diacritic);
											if(diacriticInstance != null)
											{
												LOI = tajweedFactory.getLetterOccurrence(instanceName);
//												System.out.println(diacriticInstance);
												LOI.addInvolvesDiacritic(diacriticInstance);
												position++;
											
											}
									}
									
									else if (letterInstance == null && diacriticInstance == null)
									{
										System.out.println(word);
										System.out.println("Character not in ontology - " + c[j-1]);
			
									}
									
									if(wordNo == words.length && j==c.length)
									{
										PauseMarker pm = tajweedFactory.getPauseMarker("endOfAyah");
										//System.out.println(pm);
										LOI.addInvolvesPauseMarker(pm);
									}
								}
								
							}
							for (int wordNo=1; wordNo<= words.length; wordNo++) 
							{
								String instanceName2;
								word = words[wordNo-1];
								char c [] = word.toCharArray();
								letterNo = 1;
								for (int j = 1; j <= c.length; j++) 
								{
									String letter = LetterDictionary.get(String.valueOf(c[j-1]));
									if(letter != null)
									{
										letterInstance = tajweedFactory.getLetter(letter);	
										if (letterInstance != null)
										{	
											instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
//											System.out.println("LOI1-----" + instanceName);
											instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo+1);
//											System.out.println("LOI2-----" + instanceName2);

											LOI = tajweedFactory.getLetterOccurrence(instanceName);
											LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
											if(LOI != null)  
											{
												if(wordNo==1 && j==1 && previousLOI != null)
												{
													previousLOI.addFollowedBy(LOI);
												}
												if(LOI2!= null)
												{
													LOI.addFollowedBy(LOI2);
													letterNo++;
												}
												else
												{
													if(wordNo+1 <= words.length)
													{
														instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo+1, 1);
//														System.out.println("LOI3" + instanceName2);
														LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
														LOI.addFollowedBy(LOI2);
														letterNo++;														
													}
												}			
											}
										}
									}
								}
							}
							
						}
						previousLOI = LOI;
					}
				}	
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}
	public void letterOccurrenceInstancesInVerse(String fileName, int chapter, int verse)
	{	
		LetterOccurrence LOI,LOI2 = null;
		File file = new File(fileName);
		String encoding = null;
		int chapterNo, verseNo, position, letterNo;
		String[] line, words;
		String Verse, instanceName = null, wordName, word;
				try
				{
					for (String currentLineVar : FileUtils.readLines(file, encoding))
					{
						line = currentLineVar.split("\\|");
						chapterNo = Integer.valueOf(line[0]);
						if (chapterNo == chapter )
						{		
							verseNo = Integer.valueOf(line[1]);
							if(verseNo == verse )
							{
								position = 1;					
								Verse = line[2];
								words = Verse.split(" ");
								
								for (int wordNo=1; wordNo<= words.length; wordNo++) 
								{
									// Create Word Instance and add its data properties	
									wordName = wordName(chapterNo, verseNo, wordNo);
									wordInstance = tajweedFactory.getWord(wordName);
									word = words[wordNo-1];
									char c [] = word.toCharArray();
									letterNo = 1;
									String letter, diacritic;

									for (int j = 1; j <= c.length; j++) 
									{

										//System.out.println("Character = " + c[j-1]);
										letter = LetterDictionary.get(String.valueOf(c[j-1]));									
										diacritic = DiacriticDictionary.get(String.valueOf(c[j-1]));

										if (letter != null)
										{
											letterInstance = tajweedFactory.getLetter(letter);
											if (letterInstance != null)
											{	
												instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
												//System.out.println(wordNo);
												LOI = tajweedFactory.createLetterOccurrence(instanceName);
												LOI.addInvolvesLetter(letterInstance);
												LOI.addIsPartOfWord(wordInstance);
												LOI.addHasLetterPosition(position);
												position++;
												letterNo++;
												if(wordNo == words.length && j==c.length)
												{
													PauseMarker pm = tajweedFactory.getPauseMarker("endOfAyah");
													LOI.addInvolvesPauseMarker(pm);
												}
											}
											
										}	
										else if (diacritic != null)
										{
												//System.out.println(diacritic);
												diacriticInstance = tajweedFactory.getDiacritic(diacritic);
												if(diacriticInstance != null)
												{
													LOI = tajweedFactory.getLetterOccurrence(instanceName);
													//System.out.println(instanceName);
													LOI.addInvolvesDiacritic(diacriticInstance);
													position++;
												
												}
										}
										
										else if (letterInstance == null && diacriticInstance == null)
										{
											System.out.println(word);
											System.out.println("Character not in ontology - " + c[j-1]);
				
										}	
										
									
									}
								}
								for (int wordNo=1; wordNo<= words.length; wordNo++) 
								{
									String instanceName2;
									word = words[wordNo-1];
									char c [] = word.toCharArray();
									letterNo = 1;
									for (int j = 1; j <= c.length; j++) 
									{
										String letter = LetterDictionary.get(String.valueOf(c[j-1]));
										if(letter != null)
										{
										letterInstance = tajweedFactory.getLetter(String.valueOf(c[j-1]));
										if (letterInstance != null)
										{	
											instanceName = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo);
//											System.out.println("LOI1-----" + instanceName);
											instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo, letterNo+1);
//											System.out.println("LOI2-----" + instanceName2);

											LOI = tajweedFactory.getLetterOccurrence(instanceName);
											LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
											if(LOI != null)  
											{
												if(LOI2!= null)
												{
													 LOI.addFollowedBy(LOI2);
													 letterNo++;
												}
												else
												{
													if(wordNo+1 <= words.length)
													{
														instanceName2 = LetterOccurrenceName(chapterNo, verseNo, wordNo+1, 1);
														LOI2 = tajweedFactory.getLetterOccurrence(instanceName2);
														LOI.addFollowedBy(LOI2);
														letterNo++;
													}
													
												}
		
											}
										}
										}
									}
								}
							}
						}
					
					}
				}	
				catch (IOException e)
				{
					e.printStackTrace();
				}
	}


//	// ******************* Create Rule Instances *****************
	private static Rule ruleInstance;

	public static void ruleInstance()
		{	
			String[] RuleNamesEng = {"laamAlQamari", "laamAshShamsi", "meddLaazimKalmiM", "specialGhunnah", "smallYaaMedd", "meddMunfasil", "meddMuttasil", "silaYaaKubra", "silaWowKubra", "wowLeen", "wowMedd", "yaaLeen", "yaaMedd",  "smallWowSila" ,"smallWowMedd", "sila", "alifMedd", "idghaamShafawi", "idghaamWithoutGhunnah", "idghaamWithGhunnah", "ikhfa", "ikhfaShafawi", "incompleteGhunnah", "iqlab", "izhar", "mostCompleteGhunnah", "qalqalah", "meemSakinah", "noonSakinah", "hamzatulWasal"  };
			for (int ruleNo=1; ruleNo <=RuleNamesEng.length ; ruleNo++)
			{
				String instanceName = RuleNamesEng[ruleNo-1];
				ruleInstance = tajweedFactory.createRule(instanceName);
			}
		}
	// ******************* Adjust Harakat /Diacritics *****************
//	public static void AdjustHarakats() {
//			//getting all harkats from the ontology
//			Diacritic Sakina = tajweedFactory.getDiacritic("Sukun");
//			Tanween TanweenZair = tajweedFactory.getTanween("Kasratain");
//			Tanween TanweenZaber = tajweedFactory.getTanween("Fathatain");
//			Tanween TanweenPaish = tajweedFactory.getTanween("Dammatain");
//			Diacritic Meem = tajweedFactory.getDiacritic("SmallHighMeem");
//			Diacritic NoDiacritic = tajweedFactory.createDiacritic("NoDiacritic");
//			
//			Letter Noon = tajweedFactory.getLetter("Noon");
//			Letter BigMeem = tajweedFactory.getLetter("Meem");
//			Letter Ya = tajweedFactory.getLetter("AlifMaksura");
//			Letter Yadots = tajweedFactory.getLetter("Yaa");
//			Letter YaHamza = tajweedFactory.getLetter("HighHamzaYaa");
//			Letter waaw = tajweedFactory.getLetter("Wow");
//			Letter Alif = tajweedFactory.getLetter("Alif");
//			
//			//Its an Array taking all the LO made from parsestr() getting LO from the ontology
//			Collection<? extends LetterOccurrence> letterOccurrences = tajweedFactory.getAllLetterOccurrenceInstances(); 
//			for (LetterOccurrence LO : letterOccurrences) {
//				Collection involvedLetters = LO.getInvolvesLetter();//getting all involve letter for LO
//				Collection<? extends WrappedIndividual> harakats = LO.getInvolvesDiacritic(); //Array for all harakat of LO
//				if (involvedLetters.contains(Noon)) { //if the involve letter is noon
//					if (harakats.isEmpty()) { // if No harakat
//						//System.out.println("No harakats found");
//						LO.addInvolvesDiacritic(Sakina); //add sakinah
//					} 
//					else if (harakats.contains(Meem)) { 
//						LO.removeInvolvesDiacritic(Meem);
//						LO.addInvolvesDiacritic(Sakina);
//					}
//				}
//				if (involvedLetters.contains(BigMeem)) 
//				{
//					if (harakats.isEmpty()) {
//						LO.addInvolvesDiacritic(Sakina);
//					}
//
//				}
//				else 
//				{	if(harakats.isEmpty())
//					{
//						LO.addInvolvesDiacritic(NoDiacritic);
//					}
//				}
//				
//				// letter has tanween
//				// if followedby is alif or ya
//				// make followed by of letter of tanween the followed by of alif/ya
//				// make preceeded by of the next letter after alif/ya the preceededby of letter of tanween
//				//System.out.println(harakats);
//
//				for (WrappedIndividual harakat : harakats) {
//					if (tajweedFactory.canAs(harakat, Tanween.class)) {
//						Collection<? extends WrappedIndividual> followedBy = LO.getFollowedBy();
//						for (WrappedIndividual FO : followedBy) {
//							LetterOccurrence FB = tajweedFactory.as(FO, LetterOccurrence.class);
//							Collection<? extends WrappedIndividual> letters = FB.getInvolvesLetter();
//							if (letters.contains(Alif) || letters.contains(Ya)) {
//								if (FB.getFollowedBy().iterator().hasNext()) {
//									LO.addFollowedBy(FB.getFollowedBy().iterator().next());
//								}
//							} 
//						}
//					}
//				}
//			}
//		}
	public static void NoDiacritic() {
		//getting all harkats from the ontology
		Diacritic NoDiacritic = tajweedFactory.createDiacritic("NoDiacritic");
		//Its an Array taking all the LO made from parsestr() getting LO from the ontology
		Collection<? extends LetterOccurrence> letterOccurrences = tajweedFactory.getAllLetterOccurrenceInstances(); 
		for (LetterOccurrence LO : letterOccurrences) {
			Collection<? extends WrappedIndividual> harakats = LO.getInvolvesDiacritic(); //Array for all harakat of LO
			if(harakats.isEmpty())
			{
				LO.addInvolvesDiacritic(NoDiacritic);
			}		
		}
	}


}
