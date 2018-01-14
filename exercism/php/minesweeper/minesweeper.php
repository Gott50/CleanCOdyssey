<?php
function solve( $board ) {
	$field = toArray( $board );

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
			if ( isValidField( $field, $y + $dy, $x + $dx )
			     && $field[ $y + $dy ][ $x + $dx ] == "*" ) {
				$count ++;
			}
		}
	}

	return $count > 0 ? $count : " ";
}

/**
 * @param $field
 * @param $y
 * @param $x
 *
 * @return bool
 */
function isValidField( $field, $y, $x ): bool {
	return $y > 0 && $y < sizeof( $field ) - 1
	       && $x > 0 && $x < strlen( $field[0] ) - 1;
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
	return isSideBorderValid( $field ) && isTopBorderValid( $field )
	       && areCornersValid( $field ) && hasBoardMoreThan1Square( $field )
	       && doseRowsOfSameLength( $field ) && doseBoardOnlyContainsMines( $field );
}

/**
 * @param $field
 *
 * @return bool
 */
function doseBoardOnlyContainsMines( $field ): bool {
	for ( $y = 1; $y < sizeof( $field ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $field[0] ) - 1; $x ++ ) {
			if ( $field[ $y ][ $x ] != " " && $field[ $y ][ $x ] != "*" ) {
				return false;
			}
		}
	}

	return true;
}

/**
 * @param $field
 *
 * @return bool
 */
function doseRowsOfSameLength( $field ): bool {
	for ( $i = 1; $i < sizeof( $field ); $i ++ ) {
		if ( strlen( $field[ $i ] ) != strlen( $field[0] ) ) {
			return false;
		}
	}

	return true;
}

/**
 * @param $board
 *
 * @return bool
 */
function hasBoardMoreThan1Square( $board ): bool {
	return ( sizeof( $board ) - 2 ) * ( strlen( $board[0] ) - 2 ) > 1;
}

/**
 * @param $field
 *
 * @return bool
 */
function areCornersValid( $field ): bool {
	return $field[0][0] == "+" && $field[ sizeof( $field ) - 1 ][0] == "+" &&
	       $field[0][ strlen( $field[0] ) - 1 ] == "+" &&
	       $field[ sizeof( $field ) - 1 ][ strlen( $field[0] ) - 1 ] == "+";
}

/**
 * @param $field
 *
 * @return bool
 */
function isTopBorderValid( $field ): bool {
	for ( $i = 1; $i < strlen( $field[0] ) - 1; $i ++ ) {
		if ( $field[0][ $i ] != "-" || $field[ sizeof( $field ) - 1 ][ $i ] != "-" ) {
			return false;
		}
	}

	return true;
}

/**
 * @param $field
 *
 * @return bool
 */
function isSideBorderValid( $field ): bool {
	for ( $i = 1; $i < sizeof( $field ) - 1; $i ++ ) {
		if ( $field[ $i ][0] != "|" ) {
			return false;
		}
	}

	return true;
}