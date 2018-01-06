<?php
function calculate( $string ) {
	$string = preg_filter( "/What is /", "", $string );
	$string = preg_filter( "/by/", "", $string . "by" );
	$string = preg_filter( "/\?/", "", $string );
	$split  = preg_split( "/\s+/", $string );

	return calc( $split );

}

/**
 * @param $split
 *
 * @return float|int
 */
function calc( $split ) {
	if(sizeof($split) == 1)
		return $split[0];

	$split[0] = calcAt( $split);
	unset( $split[1] , $split[2] );

	return calc( array_values( $split ) );
}

/**
 * @param $split
 *
 * @return float|int
 */
function calcAt( $split) {
	switch ( $split[1] ) {
		case "plus":
			return $split[0] + $split[2];
		case "minus":
			return $split[0] - $split[2];
		case "multiplied":
			return $split[0] * $split[2];
		case "divided":
			return $split[0] / $split[2];
	}

	throw new InvalidArgumentException();
}