<?php
function findFewestCoins( $coins, $change ) {
	return findCoins( $coins, $change );
}

/**
 * @param $coins
 * @param $change
 * @param $solutions
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