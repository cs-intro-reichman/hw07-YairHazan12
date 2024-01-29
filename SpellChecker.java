
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.substring(0, 1).equals(word2.substring(0,1))) {
			return levenshtein(tail(word1), tail(word2));
		}
		return 1 + Math.min(levenshtein(tail(word1), word2), Math.min(levenshtein(word1, tail(word2)),levenshtein(tail(word1), tail(word2)))); 
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			String word = in.readLine();
			dictionary[i] = word;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String similarWord = "";
		int minSimilar = threshold;
		for (int i = 0; i < dictionary.length; i++) {
			int lev = levenshtein(word, dictionary[i]);
			if (lev <= threshold) {
				if (lev < minSimilar) {
					minSimilar = lev;
					similarWord = dictionary[i];
				}
			}
		}
		return similarWord;
	}

}
