<?php

function acronym( $input ) {
	$out = preg_split( "/\s|-/", $input );
	$out = array_map( function ( $i ) {
		if ( preg_match( "/^[A-Z][a-z]+[A-Z]/", $i ) ) {
			return preg_replace( "/[a-z]/", "", $i );
		}

		return strtoupper( $i[0] );
	}, $out );


	return implode( "", $out );
}