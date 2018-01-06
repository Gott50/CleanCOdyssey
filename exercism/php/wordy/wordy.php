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

	$ret = calcAt( $split[0] ,$split[1], $split[2] );
	$tmp = array_slice($split,0,sizeof($split)-3);
	array_push( $tmp, $ret );
	echo var_dump( $tmp );

	return calc($tmp);
}

function calcAt( $num1,$op,$num2) {
	if ( $op == "plus" ) {
		return $num1 + $num2;
	}
	if ( $op == "minus" ) {
		return $num1- $num2;
	}
	if ($op == "multiplied" ) {
		return $num1 * $num2;
	}
	if ( $op == "divided" ) {
		return $num1 / $num2;
	}

	return 0;
}