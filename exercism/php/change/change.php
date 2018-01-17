<?php
function findFewestCoins( $coins, $change ) {
	if ( $change == 0 ) {
		return array();
	}
	if ( $change < 0 ) {
		throw new InvalidArgumentException( 'Cannot make change for negative value' );
	}
	if ( $change < $coins[0] ) {
		throw new InvalidArgumentException( 'No coins small enough to make change' );
	}

	$solutions = calculateSolutions( $coins, $change );
	sort( $solutions );

	return $solutions[0];
}

/**
 * @param $coins
 * @param $change
 *
 * @return array
 */
function calculateSolutions( $coins, $change ): array {
	$solutions = [];
	while ( sizeof( $coins ) >= 1 ) {
		$sol = findCoins( $coins, $change );
		if ( sizeof( $sol ) >= 1 ) {
			array_push( $solutions, $sol );
		}
		unset( $coins[ sizeof( $coins ) - 1 ] );
	}

	return $solutions;
}

/**
 * @param $coins
 * @param $change
 *
 * @return array
 */
function findCoins( $coins, $change ): array {
	$out = array();
	for ( $i = sizeof( $coins ) - 1; $i >= 0; $i -- ) {
		while ( $coins[ $i ] <= $change ) {
			array_push( $out, $coins[ $i ] );
			$change -= $coins[ $i ];
		}
	}
	sort( $out );

	return $out;
}