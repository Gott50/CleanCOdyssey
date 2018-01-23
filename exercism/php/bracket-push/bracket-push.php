<?php

function brackets_match( $input ) {
	$input = preg_replace( "/\s/", "", $input . " " );

	$stack = [];
	for ( $i = 0; $i < strlen( $input ); $i ++ ) {
		if ( $input[ $i ] == "[" || $input[ $i ] == "{" || $input[ $i ] == "(" ) {
			array_push( $stack, $input[ $i ] );
		} elseif ( ! match( array_pop( $stack ), $input[ $i ] ) ) {
			return false;
		}
	}

	return sizeof( $stack ) == 0;
}

function match( $a, $b ) {
	return ( $a == "[" && $b == "]" ) || ( $a == "{" && $b == "}" )
	       || ( $a == "(" && $b == ")" );
}