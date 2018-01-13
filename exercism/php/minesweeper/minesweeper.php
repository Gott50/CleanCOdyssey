<?php
function solve( $board ) {
	$field = toArray( $board );
	echo var_dump( $field );

	if ( ! isValid( $field ) ) {
		throw new InvalidArgumentException();
	}

	return "\n" . implode( "\n", $field ) . "\n";
}

/**
 * @param $board
 *
 * @return array
 */
function toArray( $board ): array {
	$exp = explode( "\n", $board );
	unset( $exp[ sizeof( $exp ) - 1 ], $exp[0] );
	$exp = array_values( $exp );

	return $exp;
}

/**
 * @param $exp
 *
 * @return bool
 */
function isValid( $exp ): bool {
	for ( $i = 1; $i < sizeof( $exp ) - 1; $i ++ ) {
		if ( $exp[ $i ][0] != "|" ) {
			return false;
		}
	}
	for ( $i = 1; $i < strlen( $exp[0] ) - 1; $i ++ ) {
		if ( $exp[0][ $i ] != "-" || $exp[ sizeof( $exp ) - 1 ][ $i ] != "-" ) {
			return false;
		}
	}
	if ( $exp[0][0] != "+" || $exp[ sizeof( $exp ) - 1 ][0] != "+" ||
	     $exp[0][ strlen( $exp[0] ) - 1 ] != "+" ||
	     $exp[ sizeof( $exp ) - 1 ][ strlen( $exp[0] ) - 1 ] != "+" ) {
		return false;
	}
	if ( ( sizeof( $exp ) - 2 ) * ( strlen( $exp[0] ) - 2 ) < 2 ) {
		return false;
	}
	for ( $i = 1; $i < sizeof( $exp ); $i ++ ) {
		if ( strlen( $exp[ $i ] ) != strlen( $exp[0] ) ) {
			return false;
		}
	}
	for ( $y = 1; $y < sizeof( $exp ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $exp[0] ) - 1; $x ++ ) {
			if ( $exp[ $y ][ $x ] != " " && $exp[ $y ][ $x ] != "*" ) {
				return false;
			}
		}
	}

	return true;
}