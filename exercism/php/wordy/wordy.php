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

	$ret = calcAt( $split, 1 );
	$tmp = array_slice($split,0,sizeof($split)-3);
	array_push( $tmp, $ret );
	echo var_dump( $tmp );

	return calc($tmp);
}

/**
 * @param $split
 * @param $index
 *
 * @return float|int
 */
function calcAt( $split, $index ) {
	if ( $split[ $index ] == "plus" ) {
		return $split[ $index - 1 ] + $split[ $index + 1 ];
	}
	if ( $split[1] == "minus" ) {
		return $split[ $index - 1 ] - $split[ $index + 1 ];
	}
	if ( $split[1] == "multiplied" ) {
		return $split[ $index - 1 ] * $split[ $index + 1 ];
	}
	if ( $split[1] == "divided" ) {
		return $split[ $index - 1 ] / $split[ $index + 1 ];
	}

	return 0;
}