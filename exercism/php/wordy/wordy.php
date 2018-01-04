<?php
function calculate( $string ) {
	$string = preg_filter( "/What is /", "", $string );
	$string = preg_filter( "/by/", "", $string . "by");
	$string = preg_filter( "/\?/", "", $string );
	$split = preg_split( "/\s+/", $string );
	echo var_dump( $split );

	return calcAt( $split, 1 );

}

/**
 * @param $split
 * @param $index
 *
 * @return float|int
 */
function calcAt( $split, $index ){
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