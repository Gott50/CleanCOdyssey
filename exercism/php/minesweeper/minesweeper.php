<?php
function solve( $board ) {
	$exp = explode( "\n", $board );
	unset( $exp[ sizeof( $exp ) - 1 ], $exp[0] );
	$exp = array_values( $exp );
	echo var_dump( $exp );

	for ( $i = 1; $i < sizeof( $exp ) - 1; $i ++ ) {
		if ( $exp[ $i ][0] != "|" ) {
			throw new InvalidArgumentException();
		}
	}
	for ( $i = 1; $i < strlen( $exp[0] ) - 1; $i ++ ) {
		if ( $exp[0][ $i ] != "-" || $exp[ sizeof( $exp ) - 1 ][ $i ] != "-" ) {
			throw new InvalidArgumentException();
		}
	}
	if ( $exp[0][0] != "+" || $exp[ sizeof( $exp ) - 1 ][0] != "+" ||
	     $exp[0][ strlen( $exp[0] ) - 1 ] != "+" || $exp[ sizeof( $exp ) - 1 ][ strlen( $exp[0] ) - 1 ] != "+" ) {
		throw new InvalidArgumentException();
	}
	if ( ( sizeof( $exp ) - 2 ) * ( strlen( $exp[0] ) - 2 ) < 2 ) {
		throw new InvalidArgumentException();
	}
	for ( $i = 1; $i < sizeof( $exp ); $i ++ ) {
		if ( strlen( $exp[ $i ] ) != strlen( $exp[0] ) ) {
			throw new InvalidArgumentException();
		}
	}
	for ( $y = 1; $y < sizeof( $exp ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $exp[0] ) - 1; $x ++ ) {
			if ( $exp[ $y ][ $x ] != " " && $exp[ $y ][ $x ] != "*" ) {
				throw new InvalidArgumentException();
			}
		}

	}


	return $board;
}