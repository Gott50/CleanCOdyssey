<?php
function solve( $board ) {
	$board = toArray( $board );

	if ( ! isValid( $board ) ) {
		throw new InvalidArgumentException();
	}

	for ( $y = 1; $y < sizeof( $board ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $board[ $y ] ) - 1; $x ++ ) {
			if ( $board[ $y ][ $x ] != "*" ) {
				$board[ $y ][ $x ] = countMines( $board, $y, $x );
			}
		}
	}

	return "\n" . implode( "\n", $board ) . "\n";
}

/**
 * @param $board
 * @param $y
 * @param $x
 *
 * @return int|string
 */
function countMines( $board, $y, $x ) {
	$count = 0;
	for ( $dy = - 1; $dy <= 1; $dy ++ ) {
		for ( $dx = - 1; $dx <= 1; $dx ++ ) {
			if ( isValidField( $board, $y + $dy, $x + $dx )
			     && $board[ $y + $dy ][ $x + $dx ] == "*" ) {
				$count ++;
			}
		}
	}

	return $count > 0 ? $count : " ";
}

/**
 * @param $board
 * @param $y
 * @param $x
 *
 * @return bool
 */
function isValidField( $board, $y, $x ): bool {
	return $y > 0 && $y < sizeof( $board ) - 1
	       && $x > 0 && $x < strlen( $board[0] ) - 1;
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
 * @param $board
 *
 * @return bool
 */
function isValid( $board ): bool {
	return isSideBorderValid( $board ) && isTopBorderValid( $board )
	       && areCornersValid( $board ) && hasBoardMoreThan1Square( $board )
	       && doseRowsOfSameLength( $board ) && doseBoardOnlyContainsMines( $board );
}

/**
 * @param $board
 *
 * @return bool
 */
function doseBoardOnlyContainsMines( $board ): bool {
	for ( $y = 1; $y < sizeof( $board ) - 1; $y ++ ) {
		for ( $x = 1; $x < strlen( $board[0] ) - 1; $x ++ ) {
			if ( $board[ $y ][ $x ] != " " && $board[ $y ][ $x ] != "*" ) {
				return false;
			}
		}
	}

	return true;
}

/**
 * @param $board
 *
 * @return bool
 */
function doseRowsOfSameLength( $board ): bool {
	for ( $i = 1; $i < sizeof( $board ); $i ++ ) {
		if ( strlen( $board[ $i ] ) != strlen( $board[0] ) ) {
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
 * @param $board
 *
 * @return bool
 */
function areCornersValid( $board ): bool {
	return $board[0][0] == "+" && $board[ sizeof( $board ) - 1 ][0] == "+" &&
	       $board[0][ strlen( $board[0] ) - 1 ] == "+" &&
	       $board[ sizeof( $board ) - 1 ][ strlen( $board[0] ) - 1 ] == "+";
}

/**
 * @param $board
 *
 * @return bool
 */
function isTopBorderValid( $board ): bool {
	for ( $i = 1; $i < strlen( $board[0] ) - 1; $i ++ ) {
		if ( $board[0][ $i ] != "-" || $board[ sizeof( $board ) - 1 ][ $i ] != "-" ) {
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
function isSideBorderValid( $board ): bool {
	for ( $i = 1; $i < sizeof( $board ) - 1; $i ++ ) {
		if ( $board[ $i ][0] != "|" ) {
			return false;
		}
	}

	return true;
}