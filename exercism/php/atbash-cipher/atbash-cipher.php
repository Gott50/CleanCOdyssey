<?php
function encode( $input ) {
	$cleaned = preg_replace( "/\W/", "", $input . " " );
	$encoded = array_map( "encodeLetter", str_split( $cleaned ) );
	$split   = array_reduce( $encoded, "splitAtLength5", [] );

	return implode( " ", $split );
}

function encodeLetter( $in ) {
	if ( preg_match( "/\d/", $in ) ) {
		return $in;
	}

	return chr( ord( 'a' ) + ord( 'z' ) - ord( strtolower( $in ) ) );
}

function splitAtLength5( $carry, $item ) {
	$s = sizeof( $carry );
	if ( $s <= 0 || strlen( $carry[ $s - 1 ] ) >= 5 ) {
		array_push( $carry, $item );
	} else {
		$carry[ $s - 1 ] .= $item;
	}

	return $carry;
}