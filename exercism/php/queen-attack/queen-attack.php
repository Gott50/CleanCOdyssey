<?php

function placeQueen( $x, $y ) {
	if ( ! isPositionPositive( $x, $y ) ) {
		throw new InvalidArgumentException( "The rank and file numbers must be positive." );
	};
	if ( ! isPositionLessThan8( $x, $y ) ) {
		throw new InvalidArgumentException( "The position must be on a standard size chess board." );
	};

	return true;
}

function canAttack( $w, $b ) {
	for ( $i = 0; $i < 8; $i ++ ) {
		for ( $xi = - 1; $xi <= 1; $xi ++ ) {
			for ( $yi = - 1; $yi <= 1; $yi ++ ) {
				if ( $b[0] == $w[0] + $xi * $i
				     && $b[1] == $w[1] + $yi * $i ) {
					return true;
				}
			}
		}
	}

	return false;
}

/**
 * @param $x
 * @param $y
 *
 * @return bool
 */
function isPositionLessThan8( $x, $y ): bool {
	return ( $x < 8 && $y < 8 );
}

/**
 * @param $x
 * @param $y
 *
 * @return bool
 */
function isPositionPositive( $x, $y ): bool {
	return $x >= 0 && $y >= 0;
}