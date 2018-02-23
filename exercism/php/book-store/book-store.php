<?php

function total( $basket ) {
	$grouping = [];
	foreach ( $basket as $book ) {
		$grouping = group( $grouping, $book );
	}
	$out = 0;
	foreach ( $grouping as $group ) {
		$out += 8.0 * sizeof( $group ) * get_discount( sizeof( $group ) );
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
		if ( array_search( $book, $group ) == false ) {
			array_push( $group, $book );

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
			return .95;
		default :
			return 1;
	}
}