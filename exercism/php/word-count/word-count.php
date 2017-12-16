<?php
function wordCount( $sentence ) {
	return countOccurrences( extractWords( cleanup( $sentence ) ) );
}

/**
 * @param $words
 *
 * @return array
 */
function countOccurrences( $words ): array {
	$unique = array();
	for ( $i = 0; $i < sizeof( $words ); $i ++ ) {
		if ( array_key_exists( $words[ $i ], $unique ) ) {
			$unique[ $words[ $i ] ] ++;
		} else {
			$unique[ $words[ $i ] ] = 1;
		}
	}

	return $unique;
}

/**
 * @param $sentence
 *
 * @return array|array[]|false|string[]
 */
function extractWords( $sentence ) {
	$words = preg_split( "/ +/", $sentence );
	$words = array_filter( $words, function ( $e ) {
		return $e != "";
	} );
	$words = array_values( $words );

	return $words;
}

/**
 * @param $sentence
 *
 * @return mixed|null|string|string[]
 */
function cleanup( $sentence ) {
	$sentence = strtolower( $sentence );
	$sentence = str_replace( array( "\n", "\r", "\t", "\u000b", "\u00a0", "\u2002" ), " ", $sentence );
	$sentence = preg_filter( "/[^a-z\d ]/", "", "!" . $sentence );

	return $sentence;
}