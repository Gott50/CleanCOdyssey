<?php
function encode( $input ) {
	$en  = function ( $in ) {
		if ( preg_match( "/\d/", $in ) ) {
			return $in;
		}

		return chr( ord( 'a' ) + ord( 'z' ) - ord( strtolower( $in ) ) );
	};
	$red = function ( $carry, $item ) {
		$s = sizeof( $carry );
		if ( $s <= 0 || strlen( $carry[ $s - 1 ] ) >= 5 ) {
			array_push( $carry, $item );
		} else {
			$carry[ $s - 1 ] .= $item;
		}

		return $carry;
	};


	$input = preg_replace( "/\W/", "", $input . " " );

	$pieces = array_reduce( array_map( $en, str_split( $input ) ), $red, [] );

	return implode( " ", $pieces );
}