<?php

function total( $basket ) {
	$grouping = [];
	foreach ( $basket as $book ) {
		$grouping = group( $grouping, $book );
	}
	$out = 0;
	foreach ( $grouping as $group ) {
		$out += 8 * sizeof( $group )
		        * ( 1 - get_discount( sizeof( $group ) ) );
	}

	return $out;
}

/**
 * @param $grouping
 * @param $book
 *
 * @return mixed
 */
function group( $grouping, $book ) {
	foreach ( $grouping as &$group ) {
		if ( ! in_array( $book, $group ) ) {
			array_push( $group, $book );
			sort( $grouping );

			return $grouping;
		}
	}
	array_push( $grouping, [ $book ] );

	return $grouping;
}

/**
 * @param $size
 *
 * @return float
 */
function get_discount( $size ) {
	switch ( $size ) {
		case 2:
			return .05;
		case 3:
			return .10;
		case 4:
			return .20;
		case 5:
			return .25;
		default :
			return 0;
	}
}