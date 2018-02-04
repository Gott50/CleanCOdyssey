<?php

function acronym( $input ) {
	if ( preg_match( "/^\w*$/", $input ) ) {
		return "";
	}

	return implode( "", array_map( "getUpperLetters",
		preg_split( "/\s|-/", $input ) ) );
}

/**
 * @param $input
 *
 * @return string
 */
function getUpperLetters( $input ): string {
	if ( preg_match( "/^[A-Z][a-z]+[A-Z]/", $input ) ) {
		return preg_replace( "/[a-z]/", "", $input );
	}

	return mb_strtoupper( mb_substr( $input, 0, 1 ) );
}