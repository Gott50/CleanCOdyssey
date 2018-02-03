<?php

function vlq_decode( $input ) {
	$split = array( array() );
	foreach ( $input as $item ) {
		array_push( $split[ sizeof( $split ) - 1 ], $item );
		if ( strlen( decbin( $item ) ) < 8 ) {
			array_push( $split, [] );
		}
	}
	if ( sizeof( $split ) <= 1 ) {
		throw new InvalidArgumentException();
	}
	unset( $split[ sizeof( $split ) - 1 ] );

	return array_map( "vlq_decodePart", $split );
}

/**
 * @param $input
 *
 * @return int
 */
function vlq_decodePart( $input ): int {
	$out = array_map( function ( $b ) {
		return substr( decbin( $b | 0b10000000 ), 1 );
	}, $input );

	if ( gettype( bindec( implode( $out ) ) ) !== "integer" ) {
		throw new OverflowException();
	}

	return bindec( implode( $out ) );
}

/**
 * @param $input
 *
 * @return array
 */
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

/**
 * @param $str
 *
 * @return array
 */
function split( $str ) {
	$out = array();
	while ( strlen( $str ) > 0 ) {
		array_push( $out, substr( $str, strlen( $str ) - 7 ) );
		$str = substr( $str, 0, strlen( $str ) - 7 );
	}

	return $out;
}