<?php
function resultFor( $field ) {
	echo var_dump( $field );

	for ( $y = 0; $y < sizeof( $field ); $y ++ ) {
			$winner = fromPosition( $field, 0, $y );
			if ( $winner != null ) {
				return $winner;
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
function fromPosition( $field, $x, $y,$tested = []) {
	if ( $field[ $y ][ $x ] == ".") {
		return null;
	}
	if ( $x+1 >= strlen( $field[ $y ] ) ) {
		return winner( $field[ $y ][ $x ] );
	}

	$out = tryPosition( $field, $x + 1, $y ,$x,$y,$tested);
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field, $x + 1, $y - 1 ,$x,$y,$tested);
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field, $x, $y + 1 ,$x,$y,$tested);
	if ( $out != null ) {
		return $out;
	}
	$out = tryPosition( $field, $x - 1, $y + 1 ,$x,$y,$tested);
	if ( $out != null ) {
		return $out;
	}

	return null;
}

/**
 * @param $field
 * @param $x
 * @param $y
 * @param $fx
 * @param $fy
 * @param $tested
 *
 * @return null|string
 */
function tryPosition( $field, $x, $y ,$fx ,$fy,$tested) {
	if ( $field[ $fy ][ $fx ] != "." && $y >= 0 && $x >= 0
	     && $y < sizeof( $field ) && $x < strlen( $field[ $y ] ) ) {
		if ( $field[ $fy ][ $fx ] == $field[ $y ][ $x ] && ! array_search([$y,$x],$tested) ) {
			array_push($tested,[$y,$x]);
			return fromPosition( $field, $x, $y ,$tested);
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