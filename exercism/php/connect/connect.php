<?php
function resultFor( $field ) {
	for ( $y = 0; $y < sizeof( $field ); $y ++ ) {
		$winner = fromXPosition( $field, 0, $y );
		if ( $winner != null ) {
			return $winner;
		}
	}
	for ( $x = 0; $x < strlen( $field[0] ); $x ++ ) {
		$winner = fromYPosition( $field, $x, 0 );
		if ( $winner != null ) {
			return $winner;
		}
	}
}

/**
 * @param $field
 * @param $x
 * @param $y
 * @param array $tested
 *
 * @return null|string
 */
function fromXPosition( $field, $x, $y, $tested = [] ) {
	if ( $x + 1 >= strlen( $field[ $y ] ) ) {
		return winner( $field[ $y ][ $x ] );
	}

	return tryNext( $field, $x, $y, $tested );
}

/**
 * @param $field
 * @param $x
 * @param $y
 * @param array $tested
 *
 * @return null|string
 */
function fromYPosition( $field, $x, $y, $tested = [] ) {
	if ( $y + 1 >= sizeof( $field ) ) {
		return winner( $field[ $y ][ $x ] );
	}

	return tryNext( $field, $x, $y, $tested, 0 );
}

/**
 * @param $field
 * @param $x
 * @param $y
 * @param $tested
 * @param int $nextX
 *
 * @return null|string
 */
function tryNext( $field, $x, $y, $tested, $nextX = 1 ) {
	if ( $field[ $y ][ $x ] == "." ) {
		return null;
	}

	$out = tryPosition( $field[ $y ][ $x ], $field, $x + 1, $y, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field[ $y ][ $x ], $field, $x + 1, $y - 1, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field[ $y ][ $x ], $field, $x, $y - 1, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field[ $y ][ $x ], $field, $x, $y + 1, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field[ $y ][ $x ], $field, $x - 1, $y + 1, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field[ $y ][ $x ], $field, $x - 1, $y, $tested, $nextX );
	if ( $out != null ) {
		return $out;
	}

	return null;
}

/**
 * @param $color
 * @param $field
 * @param $x
 * @param $y
 * @param array $tested
 * @param int $nextX
 *
 * @return null|string
 */
function tryPosition( $color, $field, $x, $y, $tested = [], $nextX = 1 ) {
	if ( $color != "." && $y >= 0 && $x >= 0
	     && $y < sizeof( $field ) && $x < strlen( $field[ $y ] ) ) {
		if ( $color == $field[ $y ][ $x ] && ! array_search( [ $y, $x ], $tested ) ) {
			array_push( $tested, [ $y, $x ] );

			return $nextX ? fromXPosition( $field, $x, $y, $tested ) :
				fromyPosition( $field, $x, $y, $tested );
		}
	}

	return null;
}

/**
 * @param $color
 *
 * @return null|string
 */
function winner( $color ) {
	if ( $color == "X" ) {
		return "black";
	} else if ( $color == "O" ) {
		return "white";
	} else {
		return null;
	}
}