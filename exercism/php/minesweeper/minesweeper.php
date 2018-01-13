<?php
function solve( $board ) {
	$field = toArray( $board );
	echo var_dump( $field );

	if ( ! isValid( $field ) ) {
		throw new InvalidArgumentException();
	}

	for ( $y = 1; $y < sizeof( $field ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $field[ $y ] ) - 1; $x ++ ) {
			if ( $field[ $y ][ $x ] != "*" ) {
				$field[ $y ][ $x ] = countMines( $field, $y, $x );
			}
		}
	}

	return "\n" . implode( "\n", $field ) . "\n";
}

/**
 * @param $field
 * @param $y
 * @param $x
 *
 * @return int|string
 */
function countMines( $field, $y, $x ) {
	$count = 0;
	for ( $dy = - 1; $dy <= 1; $dy ++ ) {
		for ( $dx = - 1; $dx <= 1; $dx ++ ) {
			if ( $y + $dy > 0 && $y + $dy < sizeof( $field ) - 1 && $x + $dx > 0
			     && $x + $dx < strlen( $field[ $y ] ) - 1 ) {
				if ( $field[ $y + $dy ][ $x + $dx ] == "*" ) {
					$count ++;
				}
			}
		}
	}

	return $count > 0 ? $count : " ";
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
 * @param $field
 *
 * @return bool
 */
function isValid( $field ): bool {
	for ( $i = 1; $i < sizeof( $field ) - 1; $i ++ ) {
		if ( $field[ $i ][0] != "|" ) {
			return false;
		}
	}
	for ( $i = 1; $i < strlen( $field[0] ) - 1; $i ++ ) {
		if ( $field[0][ $i ] != "-" || $field[ sizeof( $field ) - 1 ][ $i ] != "-" ) {
			return false;
		}
	}
	if ( $field[0][0] != "+" || $field[ sizeof( $field ) - 1 ][0] != "+" ||
	     $field[0][ strlen( $field[0] ) - 1 ] != "+" ||
	     $field[ sizeof( $field ) - 1 ][ strlen( $field[0] ) - 1 ] != "+" ) {
		return false;
	}
	if ( ( sizeof( $field ) - 2 ) * ( strlen( $field[0] ) - 2 ) < 2 ) {
		return false;
	}
	for ( $i = 1; $i < sizeof( $field ); $i ++ ) {
		if ( strlen( $field[ $i ] ) != strlen( $field[0] ) ) {
			return false;
		}
	}
	for ( $y = 1; $y < sizeof( $field ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $field[0] ) - 1; $x ++ ) {
			if ( $field[ $y ][ $x ] != " " && $field[ $y ][ $x ] != "*" ) {
				return false;
			}
		}
	}

	return true;
}