<?php
function resultFor( $field ) {
	echo var_dump( $field );

	for ( $y = 0; $y < sizeof( $field ); $y ++ ) {
		if ( $field[ $y ][0] != "." ) {
			$winner = fromPosition( $field, 0, $y );
			echo "winner: ", var_dump( $winner );
			if ( $winner != null ) {
				return $winner;
			}
		}
	}
}

/**
 * @param $field
 * @param $x
 * @param $y
 *
 * @return null|string
 */
function fromPosition( $field, $x, $y ) {
	if ( $x >= strlen( $field[ $y ] ) - 1 ) {
		return winner( $field[ $y ][ $x ] );
	}

	$out = array(
		tryPosition( $field[ $y ][ $x ], $field, $x + 1, $y ),
		tryPosition( $field[ $y ][ $x ], $field, $x + 1, $y - 1 ),
		tryPosition( $field[ $y ][ $x ], $field, $x, $y + 1 ),
		tryPosition( $field[ $y ][ $x ], $field, $x - 1, $y + 1 )
	);

	echo var_dump( $out );
	foreach ( $out as $o ) {
		if($o != null)
			return $o;
	}
}

/**
 * @param $color
 * @param $field
 * @param $x
 * @param $y
 *
 * @return null|string
 */
function tryPosition( $color, $field, $x, $y ) {
	if ( $color != "." && $y > 0 && $x > 0
	     && $y < sizeof( $field ) && $x < strlen( $field[ $y ] ) ) {
		if ( $color == $field[ $y ][ $x ] ) {
			return fromPosition( $field, $x, $y );
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