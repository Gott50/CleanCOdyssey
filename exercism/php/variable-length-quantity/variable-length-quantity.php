<?php

function vlq_encode( $input ) {
	$out    = array_map( function ( $b ) {
		return 0b10000000 | bindec( $b );
	}, array_reverse( str_split( decbin( $input[0] ), 7 ) ) );
	$out[0] &= 0b01111111;

	return array_reverse( $out );
}