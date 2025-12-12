package com.example.tp;

import java.util.*;
import java.util.stream.Collectors;

public class DictionaryManager {
	// Map sans ordre particulier, accès O(1) en moyenne
	private final Map<String, String> hashMap = new HashMap<>();

	// Map préservant l’ordre d’insertion
	private final Map<String, String> linkedMap = new LinkedHashMap<>();

	// Map triée selon l’ordre naturel des clés (case-insensitive)
	private final NavigableMap<String, String> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	/** Ajoute ou met à jour une entrée dans les trois maps. */
	public void addEntry(String eng, String fr) {
		hashMap.put(eng, fr);
		linkedMap.put(eng, fr);
		treeMap.put(eng, fr);
	}

	/** Renvoie la traduction depuis le HashMap (équivalent pour toutes). */
	public String getTranslation(String eng) {
		return hashMap.get(eng);
	}

	/** Supprime l’entrée des trois maps ; retourne true si existait. */
	public boolean removeEntry(String eng) {
		boolean existed = hashMap.remove(eng) != null;
		linkedMap.remove(eng);
		treeMap.remove(eng);
		return existed;
	}

	/** Affiche toutes les paires clef→valeur pour chaque type de Map. */
	public void displayAll() {
		System.out.println("=== HashMap (unordered) ===");
		hashMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
		System.out.println("\n=== LinkedHashMap (insertion order) ===");
		linkedMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
		System.out.println("\n=== TreeMap (alphabetical order) ===");
		treeMap.forEach((k, v) -> System.out.printf("%-10s → %s%n", k, v));
	}

	/**
	 * Recherche dans le TreeMap les entrées dont la clé commence par 'prefix'
	 * (insensible à la casse), et renvoie un LinkedHashMap pour conserver l’ordre
	 * trié.
	 */
	public Map<String, String> searchByPrefix(String prefix) {
		String low = prefix.toLowerCase(Locale.ROOT);
		return treeMap.entrySet().stream().filter(e -> e.getKey().toLowerCase(Locale.ROOT).startsWith(low))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, // pas de fusion
																								// nécessaire
						LinkedHashMap::new // maintenir l’ordre de stream
				));
	}
}