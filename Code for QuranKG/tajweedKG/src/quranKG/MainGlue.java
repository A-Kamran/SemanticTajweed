package quranKG;

import tajweedKG_Generator.QuranKG_Generation;
import java.util.Scanner;

public class MainGlue {
	public static void main(String[] args) {
		// *****************input vocabulary file (Ontology)*****************
//        String SOURCE_FILE = "RuleOntology-vFinal.rdf";
		String SOURCE_FILE = "TajweedOntology-vFinal-Merged.owl";

		// Set up the Scanner to capture user input
		Scanner scanner = new Scanner(System.in);

		// Prompt user for mode of operation
		System.out.println("Select mode of operation:");
		System.out.println("1 - Generate KG for the entire Quran in one file");
		System.out.println("2 - Generate KG for each surah in separate files");
		System.out.println("3 - Generate KG for a specific surah");
		System.out.println("4 - Generate KG for a specific surah and verse");

		int choice = scanner.nextInt();

		QuranKG_Generation ic;

		switch (choice) {
		case 1:
			// Generate KG for the entire Quran in one file
			String outputFileAll = "QuranKG_Jan25.rdf";
			System.out.println("Generating KG for the entire Quran -> " + outputFileAll);

			ic = new QuranKG_Generation(SOURCE_FILE, outputFileAll);
			ic.InitializeQuranEngine();
			ic.createQuranKG("Quran.txt"); // Method for generating KG for the entire Quran
			ic.saveOnt();
			break;

		case 2:
			// Generate KG for the entire Quran, but with separate files for each surah
			for (int surahNumber = 1; surahNumber <= 114; surahNumber++) {
				String outputFileSurah = "Surah" + surahNumber + "_Jan25.rdf";
				System.out.println("Generating KG for Surah: " + surahNumber + " -> " + outputFileSurah);

				ic = new QuranKG_Generation(SOURCE_FILE, outputFileSurah);
				ic.InitializeQuranEngine();
				ic.createKGforChapter("Quran.txt", surahNumber); // Method for generating KG for a specific surah
				ic.saveOnt();
			}
			break;

		case 3:
			// Generate KG for a specific surah
			System.out.println("Enter the Surah number (1 to 114):");
			int surahNumber = scanner.nextInt();
			String outputFileSingleSurah = "Surah" + surahNumber + "_Jan25.rdf";
			System.out.println("Generating KG for Surah: " + surahNumber + " -> " + outputFileSingleSurah);

			ic = new QuranKG_Generation(SOURCE_FILE, outputFileSingleSurah);
			ic.InitializeQuranEngine();
			ic.createKGforChapter("Quran.txt", surahNumber); // Method for generating KG for a specific surah
			ic.saveOnt();
			break;

		case 4:
			// Generate KG for a specific surah and verse
			System.out.println("Enter the Surah number (1 to 114):");
			int surahNo = scanner.nextInt();
			System.out.println("Enter the Verse number:");
			int verseNumber = scanner.nextInt();

			String outputFileVerse = "QuranKG/Surah" + surahNo + "_Verse" + verseNumber + "_Jan25.rdf";
			System.out.println(
					"Generating KG for Surah: " + surahNo + ", Verse: " + verseNumber + " -> " + outputFileVerse);

			ic = new QuranKG_Generation(SOURCE_FILE, outputFileVerse);
			ic.InitializeQuranEngine();
			ic.createKGforVerse("Quran.txt", surahNo, verseNumber); // Method for generating KG for a specific verse
			ic.saveOnt();
			break;

		default:
			System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
			break;
		}

		// Close the scanner
		scanner.close();
	}
}
