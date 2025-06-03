package tajweed;

import tajweedKG_Generator.QuranKG_Generation;

/**
 * @author amna kamran
 *
 */

public class Tajweed_MainGlue {
	
	//private static String baseUrl= "http://www.semantictajweed.com/ontology/";

	public static void main(String[] args)
	{
		// *****************input vocabulary file (Ontology)*****************
		String SOURCE_FILE = "TajweedOntologyTest3.owl";
		//String workingDir = System.getProperty("user.dir");
		
		// *****************Path and file to save resulting Graph.*****************
		String OutputFile = "Ikhlas.rdf";
		System.out.println(OutputFile);
		
		QuranKG_Generation ic = new QuranKG_Generation(SOURCE_FILE, OutputFile);
		
		ic.InitializeQuranEngine();
			
	
		//************************ Generate LetterOccurrence level KG for the complete Quran.
		//	String SOURCE_FILE = "TajweedOntologyTest2.owl";

		//	ic.createQuranKG("Quran.txt");
		
		//************************ Generate LetterOccurrence level KG for the given chapter No
		ic.createKGforChapter("Quran.txt", 112);
		
		ic.saveOnt();	
	}	

}