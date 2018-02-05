<?php

function nucleotideCount( $dna ) {
	$dna = strtoupper( $dna );
	return array(
		'a' => countLetter( $dna, "A" ),
		'c' => countLetter( $dna, "C" ),
		't' => countLetter( $dna, "T" ),
		'g' => countLetter( $dna, "G" )
	);
}

/**
 * @param $dna
 *
 * @param $subject
 *
 * @return int
 */
function countLetter( $dna, $subject ): int {
	return strlen( $dna ) - strlen( str_replace( $subject, "", $dna ) );
}