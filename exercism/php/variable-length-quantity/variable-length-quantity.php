<?php

function vlq_encode( $input ) {
	$out                 = array_map( function ( $b ) {
		return ( 0b10000000 | bindec( $b ));
	}, split( decbin( $input[0] )) );
	$out[sizeof($out)-1] &= 0b01111111;

	return ( $out );
}

function split( $str ) {
	$out = array();
	while ( strlen( $str ) > 0 ) {
		array_push( $out, substr( $str, 0, 7 ) );
		$str = substr( $str, 7 );
	}

	return $out;
}