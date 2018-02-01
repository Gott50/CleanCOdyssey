<?php

function vlq_decode( $input ) {
	$out = array_map( function ( $b ) {
		echo $b, PHP_EOL;

		return substr( decbin( $b | 0b10000000 ), 1 );
	}, $input );

	return [ bindec( implode( $out ) ) ];
}

function vlq_encode( $input ) {
	$out = array();
	foreach ( $input as $i ) {
		foreach ( vlq_encode_byte( $i ) as $item ) {
			array_push( $out, $item );
		}
	}

	return $out;
}

/**
 * @param $input
 *
 * @return array
 */
function vlq_encode_byte( $input ): array {
	$out                       = array_map( function ( $b ) {
		return ( 0b10000000 | bindec( $b ) );
	}, array_reverse( split( decbin( $input ) ) ) );
	$out[ sizeof( $out ) - 1 ] &= 0b01111111;

	return ( $out );
}

function split( $str ) {
	$out = array();
	while ( strlen( $str ) > 0 ) {
		array_push( $out, substr( $str, strlen( $str ) - 7 ) );
		$str = substr( $str, 0, strlen( $str ) - 7 );
	}

	return $out;
}