<?php

function brackets_match( $input ) {
	$stack = [];
	for ( $i = 0; $i < strlen( $input ); $i ++ ) {
		if ( isOpeninBracket( $input, $i ) ) {
			array_push( $stack, $input[ $i ] );
		} elseif ( isClosingBracket( $input, $i )
		           && ! match( array_pop( $stack ), $input[ $i ] ) ) {
			return false;
		}
	}

	return sizeof( $stack ) == 0;
}

/**
 * @param $input
 * @param $i
 *
 * @return bool
 */
function isClosingBracket( $input, $i ): bool {
	return $input[ $i ] == "]" || $input[ $i ] == "}" || $input[ $i ] == ")";
}

/**
 * @param $input
 * @param $i
 *
 * @return bool
 */
function isOpeninBracket( $input, $i ): bool {
	return $input[ $i ] == "[" || $input[ $i ] == "{" || $input[ $i ] == "(";
}

function match( $a, $b ) {
	return ( $a == "[" && $b == "]" ) || ( $a == "{" && $b == "}" )
	       || ( $a == "(" && $b == ")" );
}